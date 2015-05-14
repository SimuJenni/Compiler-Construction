package ch.unibe.iam.scg.minijava.bcel.generator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.PUSH;

import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.IntType;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.BinaryOperatorToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.IToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.LiteralToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ShuntingYard;
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
			if(next.isLiteral()){
				LiteralToken t= (LiteralToken) next;
				int integer = Integer.valueOf(t.value);
				il.append(new PUSH(cp, integer));
			}
			if(next.isOperator()){
				BinaryOperatorToken o =  (BinaryOperatorToken) next;
				if(o.getSymbol().equals("+"))
					il.append(InstructionFactory.IADD);
				if(o.getSymbol().equals("-"))
					il.append(InstructionFactory.ISUB);	
				if(o.getSymbol().equals("*"))
					il.append(InstructionFactory.IMUL);	
			}
		}
	}


}
