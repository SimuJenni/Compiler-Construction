package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.UnaryOperator;
import ch.unibe.iam.scg.javacc.visitor.IRetArguVisitor;
import ch.unibe.iam.scg.javacc.visitor.IRetVisitor;
import ch.unibe.iam.scg.javacc.visitor.IVoidArguVisitor;
import ch.unibe.iam.scg.javacc.visitor.IVoidVisitor;

public class Operator implements INode {
	private OperatorType operatorType; 
	private boolean isBracket, isLeftParanthesis, isRightParanthesis;
	private boolean isUnary=false;
	
	public Operator(BinaryOperator node) {	
		operatorType=extractOperatorType(node);
		isBracket=false;
		isLeftParanthesis=false;
		isRightParanthesis=false;
	}
	
	public Operator(UnaryOperator node) {
		operatorType=extractOperatorType(node);
		isBracket=false;
		isLeftParanthesis=false;
		isRightParanthesis=false;
		isUnary=true;
	}
	
	public Operator(boolean b, boolean c) {
		isBracket=true;
		isLeftParanthesis=b;
		isRightParanthesis=c;
	}

	private OperatorType extractOperatorType(UnaryOperator node) {
		if(node.f0.tokenImage.toLowerCase().equals("+"))
			return OperatorType.ADD;
		if(node.f0.tokenImage.toLowerCase().equals("-"))
			return OperatorType.MINUS;
		if(node.f0.tokenImage.toLowerCase().equals("*"))
			return OperatorType.MULT;
		if(node.f0.tokenImage.toLowerCase().equals(">"))
			return OperatorType.GREATER_THAN;
		if(node.f0.tokenImage.toLowerCase().equals("!"))
			return OperatorType.NOT;
		if(node.f0.tokenImage.toLowerCase().equals("&&"))
			return OperatorType.AND;
		return null;
	}

	private OperatorType extractOperatorType(BinaryOperator node) {
		if(node.f0.tokenImage.toLowerCase().equals("+"))
			return OperatorType.ADD;
		if(node.f0.tokenImage.toLowerCase().equals("-"))
			return OperatorType.MINUS;
		if(node.f0.tokenImage.toLowerCase().equals("*"))
			return OperatorType.MULT;
		if(node.f0.tokenImage.toLowerCase().equals(">"))
			return OperatorType.GREATER_THAN;
		if(node.f0.tokenImage.toLowerCase().equals("!"))
			return OperatorType.NOT;
		if(node.f0.tokenImage.toLowerCase().equals("&&"))
			return OperatorType.AND;
		return null;
	}

	public boolean hasHigherPrecedence(Operator operator) {
		// TODO Auto-generated method stub
		return this.operatorType.hasHigherPrecedence(operator.operatorType);
	}
	
	
	
	@Override
	public <R, A> R accept(IRetArguVisitor<R, A> vis, A argu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R accept(IRetVisitor<R> vis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A> void accept(IVoidArguVisitor<A> vis, A argu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(IVoidVisitor vis) {
		// TODO Auto-generated method stub
		vis.visit(this);
	}

	public static Operator makeLeftParanthesis() {
		// TODO Auto-generated method stub
		return new Operator(true,false);
	}

	public static Operator makeRightParanthesis() {
		// TODO Auto-generated method stub
		return new Operator(false,true);
	}

	public boolean isLeftParanthesis() {
		return this.isLeftParanthesis;
	}

	public boolean isRightParanthesis() {
		// TODO Auto-generated method stub
		return this.isRightParanthesis;
	}

	public boolean isUnary() {
		// TODO Auto-generated method stub
		return isUnary;
	}

	public boolean isBinary() {
		// TODO Auto-generated method stub
		return !isUnary;
	}

	public String getArgType() {
		switch(this.operatorType){
		case GREATER_THAN :
			return Types.INT.getName();
		case ADD :
			return Types.INT.getName();
		case MULT :
			return Types.INT.getName();
		case AND :
			return Types.BOOLEAN.getName();
		case MINUS :
			return Types.INT.getName();
		case NOT :
			return Types.BOOLEAN.getName();
		case BRACKET_RIGHT :
			return Types.INT.getName();
		default:
			return null;
		}
	}

	public String getReturnType() {
		switch(this.operatorType){
		case GREATER_THAN :
			return Types.BOOLEAN.getName();
		case ADD :
			return Types.INT.getName();
		case MULT :
			return Types.INT.getName();
		case AND :
			return Types.BOOLEAN.getName();
		case MINUS :
			return Types.INT.getName();
		case NOT :
			return Types.BOOLEAN.getName();
		default:
			return null;
		}
	}

	public static Operator makeLeftBracket() {
		Operator op=new Operator(true,false);
		op.isBracket=true;
		return op;
	}

	public static Operator makeRightBracket() {
		Operator op=new Operator(false,true);
		op.isBracket=true;
		op.isUnary=true;
		return op;
	}

	public boolean isBracket() {
		// TODO Auto-generated method stub
		return isBracket;
	}

}
