package ch.unibe.iam.scg.minijava.bcel.generator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.bcel.generic.ARRAYLENGTH;
import org.apache.bcel.generic.BasicType;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.IFGT;
import org.apache.bcel.generic.IFNE;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.NEWARRAY;
import org.apache.bcel.generic.NOP;
import org.apache.bcel.generic.PUSH;

import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.IntType;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ArrayLengthAccessToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.BinaryOperatorToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.IToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.LiteralToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ObjectInstantiationToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ShuntingYard;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.UnaryOperatorToken;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;

public class CodeGeneratorVisitor extends DepthFirstVoidVisitor{
	
    private InstructionList il;
	private ConstantPoolGen cp=new ConstantPoolGen();
	private ClassGen cg;
	private MethodGen mg;
	private InstructionFactory iFact;
	private Map<INode, IScope> scopeMap;
	
	public CodeGeneratorVisitor(ClassGen cg, MethodGen mg, InstructionList il, InstructionFactory iFact) {
		super();
		this.il = il;
		this.cg=cg;
		this.mg=mg;
		this.iFact=iFact;
	}

	public void generate(INode node, Map<INode, IScope> scopeMap) {
		this.scopeMap = scopeMap;
		node.accept(this);
	}
	
	public void visit(final Expression n) {
		ShuntingYard sy = new ShuntingYard(this.scopeMap.get(n));
		List<IToken> tokenList = sy.extract(n);
		Iterator<IToken> it=tokenList.iterator();
		while(it.hasNext()){
			IToken next=it.next();
			next.accept(this);
		}
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
	

}
