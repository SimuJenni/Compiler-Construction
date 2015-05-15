package ch.unibe.iam.scg.minijava.bcel.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ARRAYLENGTH;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.BasicType;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.IFGT;
import org.apache.bcel.generic.IFNE;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.ISTORE;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.NEWARRAY;
import org.apache.bcel.generic.NOP;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Type;

import ch.unibe.iam.scg.javacc.syntaxtree.Assignee;
import ch.unibe.iam.scg.javacc.syntaxtree.AssignmentStatement;
import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.syntaxtree.IntType;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeListOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.TypedDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.VarDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.MethodExtractor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ArrayAccessToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ArrayLengthAccessToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.BinaryOperatorToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.IToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.LiteralToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ObjectInstantiationToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ShuntingYard;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.UnaryOperatorToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.VariableToken;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class CodeGeneratorVisitor extends DepthFirstVoidVisitor{
	
    private InstructionList il;
	private ConstantPoolGen cp;
	private ClassGen cg;
	private MethodGen mg;
	private InstructionFactory iFact;
	private Map<INode, IScope> scopeMap;
	private IScope currentScope;
	private Map<String,Integer> registerMap = new HashMap<String,Integer>();
	private JavaBytecodeGenerator bytecodeGenerator;
	private boolean inAssignment=false;
	
	public CodeGeneratorVisitor(JavaBytecodeGenerator bytecodeGenerator, ClassGen cg, MethodGen mg, InstructionList il, InstructionFactory iFact) {
		super();
		this.bytecodeGenerator=bytecodeGenerator;
		this.il = il;
		this.cg=cg;
		this.cp=cg.getConstantPool();
		this.mg=mg;
		this.iFact=iFact;
	}

	public void generate(INode node, Map<INode, IScope> scopeMap) {
		this.scopeMap = scopeMap;
		node.accept(this);
	}
	
	public void visit(final Expression n) {
		ShuntingYard sy = new ShuntingYard(currentScope);
		List<IToken> tokenList = sy.extract(n);
		Iterator<IToken> it=tokenList.iterator();
		while(it.hasNext()){
			IToken next=it.next();
			next.accept(this);
		}
	}
	
	public void visit(final MethodDeclaration n) {
	    // f2 -> Identifier()
	    String methodName = n.f2.f0.tokenImage;
	    MethodExtractor me = new MethodExtractor();
	    currentScope = scopeMap.get(n);
	    Method m = me.extract(n, currentScope);
	    Type returnType = convertTypes(m.getReturnType().getName());
	    mg = new MethodGen(Constants.ACC_PUBLIC, // access flags
	    		returnType,               // return type
                new Type[] { },
                new String[] {}, // arg names
                methodName, cg.getClassName(),    // method, class
                il, cp);
	    // f7 -> ( VarDeclaration() )*
	    n.f7.accept(this);
	    // f8 -> ( Statement() )*
	    n.f8.accept(this);
	    // f9 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?
	    final NodeOptional n9 = n.f9;
	    if (n9.present()) {
	      n9.accept(this);
	      this.il.append(InstructionFactory.IRETURN);
	    }
	    mg.setMaxStack();
		mg.setMaxLocals();

	    bytecodeGenerator.addMethod(cg, mg);
	}
	
	@Override
	public void visit(final TypedDeclaration n) {
		// f0 -> Type()
	    // f1 -> Identifier()
	    String varName = n.f1.f0.tokenImage;
	    Variable v = currentScope.getVariable(varName);
	    LocalVariableGen lg = mg.addLocalVariable(varName, convertTypes(v.getType().getName()), null, null);
	    int in = lg.getIndex();
	    registerMap.put(varName, in);
	    il.append(InstructionConstants.ACONST_NULL);
	    lg.setStart(il.append(new ASTORE(in))); // "i" valid from here
	}
	
	public void visit(final AssignmentStatement n) {
	    // f2 -> Expression()
	    n.f2.accept(this);
	    inAssignment=true;
	    // f0 -> Assignee()
	    n.f0.accept(this);
	}
	
	public void visit(final Identifier n) {
		if(inAssignment){
		    int reg = registerMap.get(n.f0.tokenImage);
			il.append(new ISTORE(reg));
		}
		inAssignment=false;
	}

	private Type convertTypes(String name) {
		if(name.equals("int"))
			return Type.INT;
		if(name.equals("boolean"))
			return Type.BOOLEAN;
		else
			return Type.UNKNOWN;
	}

	public void visit(BinaryOperatorToken o) {
		if(o.getSymbol().equals("+"))
			il.append(InstructionFactory.IADD);
		if(o.getSymbol().equals("-"))
			il.append(InstructionFactory.ISUB);	
		if(o.getSymbol().equals("*"))
			il.append(InstructionFactory.IMUL);	
		if(o.getSymbol().equals("&&"))
			il.append(InstructionFactory.IAND);	
		if(o.getSymbol().equals(">")){
			il.append(InstructionFactory.ISUB);
			InstructionHandle isFalse = il.append(new PUSH(cp, false));
			InstructionHandle isTrue = il.append(new PUSH(cp, true));
			InstructionHandle jump = il.append(new NOP());
			il.insert(isFalse, new IFGT(isTrue));
			il.insert(isTrue, new GOTO(jump));
		}		
		if(o.getSymbol().equals("<")){
			il.append(InstructionFactory.SWAP);
			il.append(InstructionFactory.ISUB);
			InstructionHandle isFalse = il.append(new PUSH(cp, false));
			InstructionHandle isTrue = il.append(new PUSH(cp, true));
			InstructionHandle jump = il.append(new NOP());
			il.insert(isFalse, new IFGT(isTrue));
			il.insert(isTrue, new GOTO(jump));
		}	
		if(o.getSymbol().equals("==")){
			il.append(InstructionFactory.ISUB);
			InstructionHandle isFalse = il.append(new PUSH(cp, false));
			InstructionHandle isTrue = il.append(new PUSH(cp, true));
			InstructionHandle jump = il.append(new NOP());
			il.insert(isFalse, new IFEQ(isTrue));
			il.insert(isTrue, new GOTO(jump));
		}	
	}

	public void visit(LiteralToken t) {
		if(t.type.getName().equals("int")){
			int integer = Integer.valueOf(t.value);
			il.append(new PUSH(cp, integer));
		}
		if(t.type.getName().equals("boolean")){
			if(t.value.equals("true"))
				il.append(new PUSH(cp, true));
			else
				il.append(new PUSH(cp, false));
		}
		
	}

	public void visit(UnaryOperatorToken u) {
		if(u.getSymbol().equals("!")){
			InstructionHandle ifNe = il.append(new PUSH(cp, true));
			InstructionHandle ifNotNe = il.append(new PUSH(cp, false));
			InstructionHandle nop = il.append(new NOP());
			il.insert(ifNotNe, new GOTO(nop));
			il.insert(ifNe, new IFNE(ifNotNe));
		}
			
	}

	public void visit(ObjectInstantiationToken t) {
		il.append(new NEWARRAY(BasicType.INT));
	}

	public void visit(ArrayLengthAccessToken t) {
		il.append(new ARRAYLENGTH());
	}

	public void visit(ArrayAccessToken arrayAccessToken) {
		il.append(InstructionFactory.IALOAD);		
	}

	public void visit(VariableToken variableToken) {
		int reg = registerMap.get(variableToken.name);

		if(variableToken.type.getName().equals("int")){
			il.append(new ILOAD(reg));
			return;
		} 
		if(variableToken.type.getName().equals("boolean")){
			il.append(new ILOAD(reg));	
			return;
		} 
		else {
			il.append(new ALOAD(reg));	
		}
	}

	public ClassGen getCG() {
		// TODO Auto-generated method stub
		return cg;
	}

	public MethodGen getMG() {
		// TODO Auto-generated method stub
		return mg;
	}
}
