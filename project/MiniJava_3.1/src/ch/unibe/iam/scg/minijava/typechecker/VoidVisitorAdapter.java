package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.Assignee;
import ch.unibe.iam.scg.javacc.syntaxtree.Assignment;
import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.BooleanType;
import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.ConstructorCall;
import ch.unibe.iam.scg.javacc.syntaxtree.Empty;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.ExpressionPrime;
import ch.unibe.iam.scg.javacc.syntaxtree.Goal;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.syntaxtree.If;
import ch.unibe.iam.scg.javacc.syntaxtree.IntArrayType;
import ch.unibe.iam.scg.javacc.syntaxtree.IntType;
import ch.unibe.iam.scg.javacc.syntaxtree.MainClass;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodCall;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeList;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeListOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeTCF;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.ObjectCreationExpression;
import ch.unibe.iam.scg.javacc.syntaxtree.Parameter;
import ch.unibe.iam.scg.javacc.syntaxtree.ParameterDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.ParameterDeclarationList;
import ch.unibe.iam.scg.javacc.syntaxtree.ParameterList;
import ch.unibe.iam.scg.javacc.syntaxtree.Statement;
import ch.unibe.iam.scg.javacc.syntaxtree.StatementList;
import ch.unibe.iam.scg.javacc.syntaxtree.Type;
import ch.unibe.iam.scg.javacc.syntaxtree.TypedDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.UnaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.VarDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.VoidType;
import ch.unibe.iam.scg.javacc.syntaxtree.WhileLoop;
import ch.unibe.iam.scg.javacc.visitor.IVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.Operator;

public class VoidVisitorAdapter implements IVoidVisitor {

	@Override
	public void visit(NodeChoice n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NodeList n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NodeListOptional n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NodeOptional n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NodeSequence n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NodeTCF n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NodeToken n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Goal n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(MainClass n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ClassDeclaration n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VarDeclaration n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(MethodDeclaration n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ParameterDeclarationList n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ParameterDeclaration n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(TypedDeclaration n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Type n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Statement n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Assignment n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Assignee n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(WhileLoop n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(If n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StatementList n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Expression n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ObjectCreationExpression n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ConstructorCall n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ExpressionPrime n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(MethodCall n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ParameterList n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Parameter n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(UnaryOperator n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BinaryOperator n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BooleanType n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VoidType n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IntType n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IntArrayType n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Identifier n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Empty n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Operator operator) {
		// TODO Auto-generated method stub
		
	}

}
