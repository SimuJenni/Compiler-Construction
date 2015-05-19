package ch.unibe.iam.scg.minijava.bcel.generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ARRAYLENGTH;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.ArrayType;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.FieldGen;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.IASTORE;
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
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.Type;

import ch.unibe.iam.scg.javacc.syntaxtree.AssignmentStatement;
import ch.unibe.iam.scg.javacc.syntaxtree.ClassConstructorCall;
import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.IfStatement;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.Statement;
import ch.unibe.iam.scg.javacc.syntaxtree.TypedDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.WhileStatement;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.IdentifierNameExtractor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.MethodExtractor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ArrayAccessToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ArrayLengthAccessToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.BinaryOperatorToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.IToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.LiteralToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.MethodCallToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ObjectInstantiationToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ShuntingYard;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.UnaryOperatorToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.VariableToken;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class CodeGeneratorVisitor extends DepthFirstVoidVisitor {

	private InstructionList il;
	private ConstantPoolGen cp;
	private ClassGen cg;
	private MethodGen mg;
	private InstructionFactory iFact;
	private Map<INode, IScope> scopeMap;
	private Map<String, MethodGen> methodMap = new HashMap<String, MethodGen>();
	private IScope currentScope;
	private Map<String, Integer> registerMap = new HashMap<String, Integer>();
	private JavaBytecodeGenerator bytecodeGenerator;
	private boolean isField = false;

	public CodeGeneratorVisitor(JavaBytecodeGenerator bytecodeGenerator,
			ClassGen cg, MethodGen mg, InstructionList il,
			InstructionFactory iFact) {
		super();
		this.bytecodeGenerator = bytecodeGenerator;
		this.il = il;
		this.cg = cg;
		this.cp = cg.getConstantPool();
		this.mg = mg;
		this.iFact = iFact;
	}

	public void generate(INode node, Map<INode, IScope> scopeMap) {
		this.scopeMap = scopeMap;
		node.accept(this);
	}

	public void visit(final Expression n) {
		ShuntingYard sy = new ShuntingYard(currentScope);
		List<IToken> tokenList = sy.extract(n);
		Iterator<IToken> it = tokenList.iterator();
		while (it.hasNext()) {
			IToken next = it.next();
			next.accept(this);
		}
	}
	
	  public void visit(final ClassDeclaration n) {
		currentScope = scopeMap.get(n);

		// f1 -> Identifier()
		String className = n.f1.f0.tokenImage;
		cg = new ClassGen(className, "java.lang.Object",
                "<generated>", Constants.ACC_PUBLIC | Constants.ACC_SUPER,
                new String[] {  });
		cg.addEmptyConstructor(Constants.ACC_PUBLIC);
		cp = cg.getConstantPool(); // cg creates constant pool
		iFact = new InstructionFactory(cg);

	    // f2 -> ( #0 <EXTENDS> #1 Identifier() )?
	    final NodeOptional n2 = n.f2;
	    if (n2.present()) {
	      final NodeSequence seq = (NodeSequence) n2.node;
	      // #0 <EXTENDS>
	      final INode seq1 = seq.elementAt(0);
	      // #1 Identifier()
	      final INode seq2 = seq.elementAt(1);
	    }
	    // f4 -> ( VarDeclaration() )*
	    isField = true;
	    n.f4.accept(this);
	    isField = false;

	    // f5 -> ( MethodDeclaration() )*
	    n.f5.accept(this);
	    
	    JavaClass jclass = cg.getJavaClass();	

		try {
			jclass.dump("bin/" + cg.getClassName().replaceAll("\\.", "/")
					+ ".class");
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }


	public void visit(final MethodDeclaration n) {
		// f2 -> Identifier()
	    il = new InstructionList();
		String methodName = n.f2.f0.tokenImage;
		MethodExtractor me = new MethodExtractor();
		currentScope = scopeMap.get(n);
		Method m = me.extract(n, currentScope);
		registerMap.put("this", 0);
		extractMG(methodName, m);
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
		methodMap.put(methodName, (MethodGen) mg.clone());

		bytecodeGenerator.addMethod(cg, mg);
		il.dispose();
	}

	private void extractMG(String methodName, Method m) {
		List<Variable> params = m.getParameters();
		int numParam = params.size();
		Type[] pTypes = new Type[numParam];
		String[] pNames = new String[numParam];
		for (int i = 0; i < numParam; i++) {
			Variable p = params.get(i);
			pTypes[i] = convertTypes(p.getType().getName());
			pNames[i] = p.getName();
		}
		Type returnType = convertTypes(m.getReturnType().getName());
		mg = new MethodGen(Constants.ACC_PUBLIC, // access flags
				returnType, // return type
				pTypes, pNames, // arg names
				methodName, cg.getClassName(), // method, class
				il, cp);

		LocalVariableGen[] methodParams = mg.getLocalVariables();
		for (int i = 0; i < methodParams.length; i++) {
			int in = methodParams[i].getIndex();
			registerMap.put(methodParams[i].getName(), in);
		}
	}

	@Override
	public void visit(final TypedDeclaration n) {
		// f0 -> Type()
		// f1 -> Identifier()
		String varName = n.f1.f0.tokenImage;

		if(isField){
			Variable v = currentScope.getVariable(varName);
			FieldGen fg = new FieldGen(Constants.ACC_PUBLIC, convertTypes(v
					.getType().getName()), varName, cp);
			cg.addField(fg.getField());
		} else {
			Variable v = currentScope.getVariable(varName);
			LocalVariableGen lg = mg.addLocalVariable(varName, convertTypes(v
					.getType().getName()), null, null);
			int in = lg.getIndex();
			registerMap.put(varName, in);
			il.append(InstructionConstants.ACONST_NULL);
			lg.setStart(il.append(new ASTORE(in))); // "i" valid from here
		}
	}

	public void visit(final AssignmentStatement n) {
		String name = (new IdentifierNameExtractor()).extract(n.f0);
		if(registerMap.containsKey(name)){
			int reg = registerMap.get(name);
			if (n.f0.f0.which == 1) {
				// f2 -> Expression()
				n.f2.accept(this);
				// f0 -> Assignee()
				if (currentScope.lookupVariable(name).getType() == currentScope
						.lookupType("int[]")) {
					il.append(new ASTORE(reg));
				} else {
					il.append(new ISTORE(reg));
				}
			} else {
				il.append(new ALOAD(reg));
				n.f0.accept(this);
				n.f2.accept(this);
				il.append(new IASTORE());
			}
		} else {
			String fieldType = "I";
			if (currentScope.lookupVariable(name).getType() == currentScope
					.lookupType("int[]")) {
				fieldType="[I";
			}
			if (n.f0.f0.which == 1) {
				il.append(new ALOAD(0));
				n.f2.accept(this);
				int i = cp.addFieldref(cg.getClassName(), name, fieldType);
				il.append(new PUTFIELD(i));
			} else {
				il.append(new ALOAD(0));
				int i = cp.addFieldref(cg.getClassName(), name, fieldType);
				il.append(new GETFIELD(i));
				n.f0.accept(this);
				n.f2.accept(this);
				il.append(new IASTORE());
			}
		}
	}

	private Type convertTypes(String name) {
		if (name.equals("int")) {
			return Type.INT;
		} else if (name.equals("int[]")) {
			return new ArrayType(Constants.T_INT, 1);
		} else if (name.equals("boolean")) {
			return Type.BOOLEAN;
		} else {
			return Type.UNKNOWN;
		}
	}

	public void visit(BinaryOperatorToken o) {
		if (o.getSymbol().equals("+"))
			il.append(InstructionFactory.IADD);
		if (o.getSymbol().equals("-"))
			il.append(InstructionFactory.ISUB);
		if (o.getSymbol().equals("*"))
			il.append(InstructionFactory.IMUL);
		if (o.getSymbol().equals("&&"))
			il.append(InstructionFactory.IAND);
		if (o.getSymbol().equals(">")) {
			il.append(InstructionFactory.ISUB);
			InstructionHandle isFalse = il.append(new PUSH(cp, false));
			InstructionHandle isTrue = il.append(new PUSH(cp, true));
			InstructionHandle jump = il.append(new NOP());
			il.insert(isFalse, new IFGT(isTrue));
			il.insert(isTrue, new GOTO(jump));
		}
		if (o.getSymbol().equals("<")) {
			il.append(InstructionFactory.SWAP);
			il.append(InstructionFactory.ISUB);
			InstructionHandle isFalse = il.append(new PUSH(cp, false));
			InstructionHandle isTrue = il.append(new PUSH(cp, true));
			InstructionHandle jump = il.append(new NOP());
			il.insert(isFalse, new IFGT(isTrue));
			il.insert(isTrue, new GOTO(jump));
		}
		if (o.getSymbol().equals("==")) {
			il.append(InstructionFactory.ISUB);
			InstructionHandle isFalse = il.append(new PUSH(cp, false));
			InstructionHandle isTrue = il.append(new PUSH(cp, true));
			InstructionHandle jump = il.append(new NOP());
			il.insert(isFalse, new IFEQ(isTrue));
			il.insert(isTrue, new GOTO(jump));
		}
	}

	public void visit(LiteralToken t) {
		if (t.type.getName().equals("int")) {
			int integer = Integer.valueOf(t.value);
			il.append(new PUSH(cp, integer));
		}
		if (t.type.getName().equals("boolean")) {
			if (t.value.equals("true"))
				il.append(new PUSH(cp, true));
			else
				il.append(new PUSH(cp, false));
		}
		if (t.value.equals("this"))
			il.append(new ALOAD(0));
	}

	public void visit(UnaryOperatorToken u) {
		if (u.getSymbol().equals("!")) {
			InstructionHandle ifNe = il.append(new PUSH(cp, true));
			InstructionHandle ifNotNe = il.append(new PUSH(cp, false));
			InstructionHandle nop = il.append(new NOP());
			il.insert(ifNotNe, new GOTO(nop));
			il.insert(ifNe, new IFNE(ifNotNe));
		}
	}

	public void visit(ObjectInstantiationToken t){
		NodeChoice nch = t.getNode().f1.f0;
	    switch (nch.which) {
	      case 0:
	        // %0 ClassConstructorCall()
	    	nch.choice.accept(this);
	        break;
	      case 1:
	        // %1 IntArrayConstructorCall()
	  		il.append(new NEWARRAY(Type.INT));
	        break;
	      default:
	        // should not occur !!!
	        break;
	    }
	}
	
	public void visit(final ClassConstructorCall n) {
		String className = n.f0.f0.tokenImage;
		il.append(iFact.createNew(className));
		il.append(InstructionConstants.DUP); // Use predefined constant
		il.append(iFact.createInvoke(className, "<init>",
                Type.VOID, new Type[] { },
                Constants.INVOKESPECIAL));
	}

	public void visit(ArrayLengthAccessToken t) {
		il.append(new ARRAYLENGTH());
	}

	public void visit(ArrayAccessToken arrayAccessToken) {
		il.append(InstructionFactory.IALOAD);
	}

	public void visit(VariableToken variableToken) {
		
		if(registerMap.containsKey(variableToken.name)){
			int reg = registerMap.get(variableToken.name);
	
			if (variableToken.type.getName().equals("int")) {
				il.append(new ILOAD(reg));
				return;
			}
			if (variableToken.type.getName().equals("boolean")) {
				il.append(new ILOAD(reg));
				return;
			} else {
				il.append(new ALOAD(reg));
			}
		} else {
			String fieldType = "I";
			if (variableToken.type.getName().equals("int[]")){
				fieldType="[I";
			}
			int i = cp.addFieldref(cg.getClassName(), variableToken.name, fieldType);
			il.append(new ALOAD(0));
			il.append(new GETFIELD(i));
		}
	}
	
	public void visit(final IfStatement n) {
	    // f0 -> <IF>
	    final NodeToken n0 = n.f0;
	    n0.accept(this);
	    // f2 -> Expression()
	    final Expression n2 = n.f2;
	    n2.accept(this);
		InstructionHandle ifStart = il.getEnd();
	    // f4 -> Statement()
	    final Statement n4 = n.f4;
	    n4.accept(this);
		InstructionHandle ifEnd = il.getEnd();
	    // f5 -> <ELSE>
	    final NodeToken n5 = n.f5;
	    n5.accept(this);
	    // f6 -> Statement()
	    final Statement n6 = n.f6;
	    n6.accept(this);
		InstructionHandle elseEnd = il.append(iFact.NOP);
		il.append(ifStart, new IFEQ(ifEnd.getNext()));
		il.append(ifEnd, new GOTO(elseEnd));
	}
	
	  /**
	   * Visits a {@link WhileStatement} node, whose children are the following :
	   * <p>
	   * f0 -> <WHILE><br>
	   * f1 -> <PARENTHESIS_LEFT><br>
	   * f2 -> Expression()<br>
	   * f3 -> <PARENTHESIS_RIGHT><br>
	   * f4 -> Statement()<br>
	   *
	   * @param n - the node to visit
	   */
	  @Override
	  public void visit(final WhileStatement n) {
	    // f0 -> <WHILE>
	    final NodeToken n0 = n.f0;
	    n0.accept(this);
	    // f1 -> <PARENTHESIS_LEFT>
	    final NodeToken n1 = n.f1;
	    n1.accept(this);
	    // f2 -> Expression()
	    final Expression n2 = n.f2;
	    n2.accept(this);
	    // f3 -> <PARENTHESIS_RIGHT>
	    final NodeToken n3 = n.f3;
	    n3.accept(this);
	    // f4 -> Statement()
	    final Statement n4 = n.f4;
	    n4.accept(this);
	  }
	
	public void visit(MethodCallToken methodCallToken) {
		String methodName = methodCallToken.getNode().f1.f0.tokenImage;
		MethodGen method = this.methodMap.get(methodName);
		String className = method.getClassName();
		il.append(iFact.createInvoke(className, methodName,
                method.getReturnType(), method.getArgumentTypes(),
                Constants.INVOKEVIRTUAL));
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
