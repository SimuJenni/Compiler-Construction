package ch.unibe.iam.scg.minijava.prettyprint;

import ch.unibe.iam.scg.javacc.syntaxtree.Assignment;
import ch.unibe.iam.scg.javacc.syntaxtree.BinaryOperator;
import ch.unibe.iam.scg.javacc.syntaxtree.BooleanType;
import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.ExpressionPrime;
import ch.unibe.iam.scg.javacc.syntaxtree.Goal;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.syntaxtree.If;
import ch.unibe.iam.scg.javacc.syntaxtree.MainClass;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodCall;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.New;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeListOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.Statement;
import ch.unibe.iam.scg.javacc.syntaxtree.StatementList;
import ch.unibe.iam.scg.javacc.syntaxtree.Type;
import ch.unibe.iam.scg.javacc.syntaxtree.VarDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.WhileLoop;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

/**
 * Change at will!
 * 
 * @author kursjan
 *
 */
public class PrettyPrinter extends DepthFirstVoidVisitor {
	private StringBuffer buffer;
	private int indentCount;

	public PrettyPrinter() {
		super();
		this.buffer = new StringBuffer();
		indentCount = 0;
	}

	public String prettyPrint(Object node) {
		INode n = (INode) node;
		n.accept(this);
		return buffer.toString();
	}

	/**
	 * Visits a {@link NodeToken} node.
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final NodeToken n) {
		buffer.append(n.tokenImage);
	}

	/**
	 * Visits a {@link VarDeclaration} node, whose children are the following :
	 * <p>
	 * f0 -> Type()<br>
	 * f1 -> Identifier()<br>
	 * f2 -> <SEMICOLON><br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final VarDeclaration n) {
		// f0 -> Type()
		final Type n0 = n.f0;
		n0.accept(this);
		makeSpace();
		// f1 -> Identifier()
		final Identifier n1 = n.f1;
		n1.accept(this);
		// f2 -> <SEMICOLON>
		final NodeToken n2 = n.f2;
		n2.accept(this);
	}

	/**
	 * Visits a {@link BinaryOperator} node, whose child is the following :
	 * <p>
	 * f0 -> <BINARY_OPERATOR><br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final BinaryOperator n) {
		// f0 -> <BINARY_OPERATOR>
		makeSpace();
		final NodeToken n0 = n.f0;
		n0.accept(this);
		makeSpace();
	}

	/**
	 * Visits a {@link Assignment} node, whose child is the following :
	 * <p>
	 * f0 -> <EQUALS_SIGN><br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final Assignment n) {
		makeSpace();
		// f0 -> <EQUALS_SIGN>
		final NodeToken n0 = n.f0;
		n0.accept(this);
		makeSpace();
	}

	/**
	 * Visits a {@link New} node, whose child is the following :
	 * <p>
	 * f0 -> <NEW><br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final New n) {
		// f0 -> <NEW>
		final NodeToken n0 = n.f0;
		n0.accept(this);
		makeSpace();
	}

	/**
	 * Visits a {@link StatementList} node, whose children are the following :
	 * <p>
	 * f0 -> <BRACE_LEFT><br>
	 * f1 -> ( Statement() )*<br>
	 * f2 -> <BRACE_RIGHT><br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final StatementList n) {
		// f0 -> <BRACE_LEFT>
		final NodeToken n0 = n.f0;
		n0.accept(this);
		makeNewLine(indentCount);
		// f1 -> ( Statement() )*
		final NodeListOptional n1 = n.f1;
		if (n1.present()) {
			for (int i = 0; i < n1.size(); i++) {
				makeTab();
				final INode nloeai = n1.elementAt(i);
				nloeai.accept(this);
				makeNewLine(indentCount);
			}
		}
		// f2 -> <BRACE_RIGHT>
		final NodeToken n2 = n.f2;
		n2.accept(this);
	}

	/**
	 * Visits a {@link BooleanType} node, whose child is the following :
	 * <p>
	 * f0 -> <BOOLEAN_TYPE><br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final BooleanType n) {
		buffer.append("bool");
	}

	/**
	 * Visits a {@link ClassDeclaration} node, whose children are the following
	 * :
	 * <p>
	 * f0 -> <CLASS><br>
	 * f1 -> Identifier()<br>
	 * f2 -> ( #0 <EXTENDS> #1 Identifier() )?<br>
	 * f3 -> <BRACE_LEFT><br>
	 * f4 -> ( VarDeclaration() )*<br>
	 * f5 -> ( MethodDeclaration() )*<br>
	 * f6 -> <BRACE_RIGHT><br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final ClassDeclaration n) {
		// f0 -> <CLASS>
		final NodeToken n0 = n.f0;
		n0.accept(this);
		makeSpace();
		// f1 -> Identifier()
		final Identifier n1 = n.f1;
		n1.accept(this);
		makeSpace();
		// f2 -> ( #0 <EXTENDS> #1 Identifier() )?
		final NodeOptional n2 = n.f2;
		if (n2.present()) {
			final NodeSequence seq = (NodeSequence) n2.node;
			// #0 <EXTENDS>
			final INode seq1 = seq.elementAt(0);
			seq1.accept(this);
			makeSpace();
			// #1 Identifier()
			final INode seq2 = seq.elementAt(1);
			seq2.accept(this);
			makeSpace();
		}
		// f3 -> <BRACE_LEFT>
		final NodeToken n3 = n.f3;
		n3.accept(this);
		indentCount++;
		// f4 -> ( VarDeclaration() )*
		final NodeListOptional n4 = n.f4;
		if (n4.present()) {
			for (int i = 0; i < n4.size(); i++) {
				makeNewLine(indentCount);
				final INode nloeai = n4.elementAt(i);
				nloeai.accept(this);
			}
		}
		// f5 -> ( MethodDeclaration() )*
		final NodeListOptional n5 = n.f5;
		if (n5.present()) {
			makeNewLine(0);
			for (int i = 0; i < n5.size(); i++) {
				makeNewLine(indentCount);
				final INode nloeai = n5.elementAt(i);
				nloeai.accept(this);
				makeNewLine(0);
			}
		}
		indentCount--;
		makeNewLine(indentCount);
		// f6 -> <BRACE_RIGHT>
		final NodeToken n6 = n.f6;
		n6.accept(this);
	}

	/**
	 * Visits a {@link MethodDeclaration} node, whose children are the following
	 * :
	 * <p>
	 * f0 -> <PUBLIC_MODIFIER><br>
	 * f1 -> Type()<br>
	 * f2 -> Identifier()<br>
	 * f3 -> <PARENTHESIS_LEFT><br>
	 * f4 -> ( #0 Type() #1 Identifier()<br>
	 * .. .. . #2 ( $0 <COMMA> $1 Type() $2 Identifier() )* )?<br>
	 * f5 -> <PARENTHESIS_RIGHT><br>
	 * f6 -> <BRACE_LEFT><br>
	 * f7 -> ( VarDeclaration() )*<br>
	 * f8 -> ( Statement() )*<br>
	 * f9 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?<br>
	 * f10 -> <BRACE_RIGHT><br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final MethodDeclaration n) {
		// f0 -> <PUBLIC_MODIFIER>
		final NodeToken n0 = n.f0;
		n0.accept(this);
		makeSpace();
		// f1 -> Type()
		final Type n1 = n.f1;
		n1.accept(this);
		makeSpace();
		// f2 -> Identifier()
		final Identifier n2 = n.f2;
		n2.accept(this);
		// f3 -> <PARENTHESIS_LEFT>
		final NodeToken n3 = n.f3;
		n3.accept(this);
		// f4 -> ( #0 Type() #1 Identifier()
		// .. .. . #2 ( $0 <COMMA> $1 Type() $2 Identifier() )* )?
		final NodeOptional n4 = n.f4;
		if (n4.present()) {
			final NodeSequence seq = (NodeSequence) n4.node;
			// #0 Type()
			final INode seq1 = seq.elementAt(0);
			seq1.accept(this);
			makeSpace();
			// #1 Identifier()
			final INode seq2 = seq.elementAt(1);
			seq2.accept(this);
			// #2 ( $0 <COMMA> $1 Type() $2 Identifier() )*
			final INode seq3 = seq.elementAt(2);
			final NodeListOptional nlo = (NodeListOptional) seq3;
			if (nlo.present()) {
				for (int i = 0; i < nlo.size(); i++) {
					final INode nloeai = nlo.elementAt(i);
					final NodeSequence seq4 = (NodeSequence) nloeai;
					// $0 <COMMA>
					final INode seq5 = seq4.elementAt(0);
					seq5.accept(this);
					makeSpace();
					// $1 Type()
					final INode seq6 = seq4.elementAt(1);
					seq6.accept(this);
					makeSpace();
					// $2 Identifier()
					final INode seq7 = seq4.elementAt(2);
					seq7.accept(this);
				}
			}
		}
		// f5 -> <PARENTHESIS_RIGHT>
		final NodeToken n5 = n.f5;
		n5.accept(this);
		makeSpace();
		// f6 -> <BRACE_LEFT>
		final NodeToken n6 = n.f6;
		n6.accept(this);
		indentCount++;
		// f7 -> ( VarDeclaration() )*
		final NodeListOptional n7 = n.f7;
		if (n7.present()) {
			for (int i = 0; i < n7.size(); i++) {
				makeNewLine(indentCount);
				final INode nloeai = n7.elementAt(i);
				nloeai.accept(this);
			}
			makeNewLine(0);
		}
		// f8 -> ( Statement() )*
		final NodeListOptional n8 = n.f8;
		if (n8.present()) {
			for (int i = 0; i < n8.size(); i++) {
				makeNewLine(indentCount);
				final INode nloeai = n8.elementAt(i);
				nloeai.accept(this);
			}
		}
		// f9 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?
		final NodeOptional n9 = n.f9;
		if (n9.present()) {
			makeNewLine(indentCount);
			final NodeSequence seq8 = (NodeSequence) n9.node;
			// #0 <RETURN>
			final INode seq9 = seq8.elementAt(0);
			seq9.accept(this);
			makeSpace();
			// #1 Expression()
			final INode seq10 = seq8.elementAt(1);
			seq10.accept(this);
			// #2 <SEMICOLON>
			final INode seq11 = seq8.elementAt(2);
			seq11.accept(this);
		}
		indentCount--;
		makeNewLine(indentCount);
		// f10 -> <BRACE_RIGHT>
		final NodeToken n10 = n.f10;
		n10.accept(this);
	}

	/**
	 * Visits a {@link WhileLoop} node, whose children are the following :
	 * <p>
	 * f0 -> <WHILE><br>
	 * f1 -> <PARENTHESIS_LEFT><br>
	 * f2 -> Expression()<br>
	 * f3 -> <PARENTHESIS_RIGHT><br>
	 * f4 -> Statement()<br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final WhileLoop n) {
		// f0 -> <WHILE>
		final NodeToken n0 = n.f0;
		n0.accept(this);
		makeSpace();
		// f1 -> <PARENTHESIS_LEFT>
		final NodeToken n1 = n.f1;
		n1.accept(this);
		// f2 -> Expression()
		final Expression n2 = n.f2;
		n2.accept(this);
		// f3 -> <PARENTHESIS_RIGHT>
		final NodeToken n3 = n.f3;
		n3.accept(this);
		makeSpace();
		// f4 -> Statement()
		final Statement n4 = n.f4;
		n4.accept(this);
	}

	/**
	 * Visits a {@link MethodCall} node, whose children are the following :
	 * <p>
	 * f0 -> <DOT><br>
	 * f1 -> Identifier()<br>
	 * f2 -> <PARENTHESIS_LEFT><br>
	 * f3 -> ( #0 Expression()<br>
	 * .. .. . #1 ( $0 <COMMA> $1 Expression() )* )?<br>
	 * f4 -> <PARENTHESIS_RIGHT><br>
	 * f5 -> ExpressionPrime()<br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final MethodCall n) {
		// f0 -> <DOT>
		final NodeToken n0 = n.f0;
		n0.accept(this);
		// f1 -> Identifier()
		final Identifier n1 = n.f1;
		n1.accept(this);
		// f2 -> <PARENTHESIS_LEFT>
		final NodeToken n2 = n.f2;
		n2.accept(this);
		// f3 -> ( #0 Expression()
		// .. .. . #1 ( $0 <COMMA> $1 Expression() )* )?
		final NodeOptional n3 = n.f3;
		if (n3.present()) {
			final NodeSequence seq = (NodeSequence) n3.node;
			// #0 Expression()
			final INode seq1 = seq.elementAt(0);
			seq1.accept(this);
			// #1 ( $0 <COMMA> $1 Expression() )*
			final INode seq2 = seq.elementAt(1);
			final NodeListOptional nlo = (NodeListOptional) seq2;
			if (nlo.present()) {
				for (int i = 0; i < nlo.size(); i++) {
					final INode nloeai = nlo.elementAt(i);
					final NodeSequence seq3 = (NodeSequence) nloeai;
					// $0 <COMMA>
					final INode seq4 = seq3.elementAt(0);
					seq4.accept(this);
					makeSpace();
					// $1 Expression()
					final INode seq5 = seq3.elementAt(1);
					seq5.accept(this);
				}
			}
		}
		// f4 -> <PARENTHESIS_RIGHT>
		final NodeToken n4 = n.f4;
		n4.accept(this);
		// f5 -> ExpressionPrime()
		final ExpressionPrime n5 = n.f5;
		n5.accept(this);
	}

	/**
	 * Visits a {@link If} node, whose children are the following :
	 * <p>
	 * f0 -> <IF><br>
	 * f1 -> <PARENTHESIS_LEFT><br>
	 * f2 -> Expression()<br>
	 * f3 -> <PARENTHESIS_RIGHT><br>
	 * f4 -> Statement()<br>
	 * f5 -> <ELSE><br>
	 * f6 -> Statement()<br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final If n) {
		// f0 -> <IF>
		final NodeToken n0 = n.f0;
		n0.accept(this);
		makeSpace();
		// f1 -> <PARENTHESIS_LEFT>
		final NodeToken n1 = n.f1;
		n1.accept(this);
		// f2 -> Expression()
		final Expression n2 = n.f2;
		n2.accept(this);
		// f3 -> <PARENTHESIS_RIGHT>
		final NodeToken n3 = n.f3;
		n3.accept(this);
		makeSpace();
		// f4 -> Statement()
		final Statement n4 = n.f4;
		n4.accept(this);
		makeSpace();
		// f5 -> <ELSE>
		final NodeToken n5 = n.f5;
		n5.accept(this);
		makeSpace();
		// f6 -> Statement()
		final Statement n6 = n.f6;
		n6.accept(this);
	}

	/**
	 * Visits a {@link MainClass} node, whose children are the following :
	 * <p>
	 * f0 -> <CLASS><br>
	 * f1 -> Identifier()<br>
	 * f2 -> <BRACE_LEFT><br>
	 * f3 -> <PUBLIC_MODIFIER><br>
	 * f4 -> <STATIC_MODIFIER><br>
	 * f5 -> <VOID_TYPE><br>
	 * f6 -> <MAIN_METHOD_NAME><br>
	 * f7 -> <PARENTHESIS_LEFT><br>
	 * f8 -> <STRING_TYPE><br>
	 * f9 -> <BRACKET_LEFT><br>
	 * f10 -> <BRACKET_RIGHT><br>
	 * f11 -> Identifier()<br>
	 * f12 -> <PARENTHESIS_RIGHT><br>
	 * f13 -> <BRACE_LEFT><br>
	 * f14 -> ( Statement() )?<br>
	 * f15 -> <BRACE_RIGHT><br>
	 * f16 -> <BRACE_RIGHT><br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final MainClass n) {
		// f0 -> <CLASS>
		final NodeToken n0 = n.f0;
		n0.accept(this);
		makeSpace();
		// f1 -> Identifier()
		final Identifier n1 = n.f1;
		n1.accept(this);
		makeSpace();
		// f2 -> <BRACE_LEFT>
		final NodeToken n2 = n.f2;
		n2.accept(this);
		makeNewLine(0);
		indentCount++;
		makeNewLine(indentCount);
		// f3 -> <PUBLIC_MODIFIER>
		final NodeToken n3 = n.f3;
		n3.accept(this);
		makeSpace();
		// f4 -> <STATIC_MODIFIER>
		final NodeToken n4 = n.f4;
		n4.accept(this);
		makeSpace();
		// f5 -> <VOID_TYPE>
		final NodeToken n5 = n.f5;
		n5.accept(this);
		makeSpace();
		// f6 -> <MAIN_METHOD_NAME>
		final NodeToken n6 = n.f6;
		n6.accept(this);
		// f7 -> <PARENTHESIS_LEFT>
		final NodeToken n7 = n.f7;
		n7.accept(this);
		// f8 -> <STRING_TYPE>
		final NodeToken n8 = n.f8;
		n8.accept(this);
		// f9 -> <BRACKET_LEFT>
		final NodeToken n9 = n.f9;
		n9.accept(this);
		// f10 -> <BRACKET_RIGHT>
		final NodeToken n10 = n.f10;
		n10.accept(this);
		makeSpace();
		// f11 -> Identifier()
		final Identifier n11 = n.f11;
		n11.accept(this);
		// f12 -> <PARENTHESIS_RIGHT>
		final NodeToken n12 = n.f12;
		n12.accept(this);
		makeSpace();
		// f13 -> <BRACE_LEFT>
		final NodeToken n13 = n.f13;
		n13.accept(this);
		indentCount++;
		makeNewLine(indentCount);
		// f14 -> ( Statement() )?
		final NodeOptional n14 = n.f14;
		if (n14.present()) {
			n14.accept(this);
		}
		indentCount--;
		makeNewLine(indentCount);
		// f15 -> <BRACE_RIGHT>
		final NodeToken n15 = n.f15;
		n15.accept(this);
		makeNewLine(0);
		indentCount--;
		makeNewLine(indentCount);
		// f16 -> <BRACE_RIGHT>
		final NodeToken n16 = n.f16;
		n16.accept(this);
	}

	/**
	 * Visits a {@link Goal} node, whose children are the following :
	 * <p>
	 * f0 -> MainClass()<br>
	 * f1 -> ( ClassDeclaration() )*<br>
	 * f2 -> <EOF><br>
	 *
	 * @param n
	 *            the node to visit
	 */
	@Override
	public void visit(final Goal n) {
		buffer.append("//Pretty Printer says Hi There!\n");
		// f0 -> MainClass()
		final MainClass n0 = n.f0;
		n0.accept(this);
		makeNewLine(0);
		makeNewLine(0);
		// f1 -> ( ClassDeclaration() )*
		final NodeListOptional n1 = n.f1;
		if (n1.present()) {
			for (int i = 0; i < n1.size(); i++) {
				final INode nloeai = n1.elementAt(i);
				nloeai.accept(this);
			}
			makeNewLine(0);
			makeNewLine(0);
		}
		// f2 -> <EOF>
		final NodeToken n2 = n.f2;
		n2.accept(this);
	}

	private void makeNewLine(int numTabs) {
		buffer.append("\n");
		for (int i = numTabs; i > 0; i--)
			makeTab();
	}

	private void makeTab() {
		buffer.append("    ");
	}

	private void makeSpace() {
		buffer.append(" ");
	}

}
