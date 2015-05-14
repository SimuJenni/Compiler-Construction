package ch.unibe.iam.scg.minijava.typechecker;

import java.util.Stack;

import ch.unibe.iam.scg.javacc.syntaxtree.Assignee;
import ch.unibe.iam.scg.javacc.syntaxtree.Assignment;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.Statement;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class StatementVisitor extends DepthFirstVoidVisitor {
	private SymbolTable table;
	private boolean valid;
	private ExpressionVisitor expressionVisistor;
	private String assigneeType;
	
	public StatementVisitor(SymbolTable table) {
		this.table=table;
		valid=true;
		expressionVisistor=new ExpressionVisitor(this.table);
	}
	
	public boolean check(Object node) {
		INode n = (INode) node;
		n.accept(this);
		return valid;
	}

	/**
	   * Visits a {@link Statement} node, whose child is the following :
	   * <p>
	   * f0 -> . %0 StatementList()<br>
	   * .. .. | %1 If()<br>
	   * .. .. | %2 WhileLoop()<br>
	   * .. .. | %3 #0 <PRINT_METHOD> #1 <PARENTHESIS_LEFT> #2 Expression() #3 <PARENTHESIS_RIGHT> #4 <SEMICOLON><br>
	   * .. .. | %4 Assignment()<br>
	   *
	   * @param n - the node to visit
	   */
	  @Override
	  public void visit(final Statement n) {
	    // f0 -> . %0 StatementList()
	    // .. .. | %1 If()
	    // .. .. | %2 WhileLoop()
	    // .. .. | %3 #0 <PRINT_METHOD> #1 <PARENTHESIS_LEFT> #2 Expression() #3 <PARENTHESIS_RIGHT> #4 <SEMICOLON>
	    // .. .. | %4 Assignment()
	    final NodeChoice nch = n.f0;
	    final INode ich = nch.choice;
	    switch (nch.which) {
	      case 0:
	        // %0 StatementList()
	        ich.accept(this);
	        break;
	      case 1:
	        // %1 If()
	        ich.accept(this);
	        break;
	      case 2:
	        // %2 WhileLoop()
	        ich.accept(this);
	        break;
	      case 3:
	        // %3 #0 <PRINT_METHOD> #1 <PARENTHESIS_LEFT> #2 Expression() #3 <PARENTHESIS_RIGHT> #4 <SEMICOLON>
	        final NodeSequence seq = (NodeSequence) ich;
	        // #0 <PRINT_METHOD>
	        final INode seq1 = seq.elementAt(0);
	        seq1.accept(this);
	        // #1 <PARENTHESIS_LEFT>
	        final INode seq2 = seq.elementAt(1);
	        seq2.accept(this);
	        // #2 Expression()
	        final INode seq3 = seq.elementAt(2);
	        valid=this.expressionVisistor.check(seq3);
	   
	        seq3.accept(this);
	        // #3 <PARENTHESIS_RIGHT>
	        final INode seq4 = seq.elementAt(3);
	        seq4.accept(this);
	        // #4 <SEMICOLON>
	        final INode seq5 = seq.elementAt(4);
	        seq5.accept(this);
	        break;
	      case 4:
	        // %4 Assignment()
	        ich.accept(this);
	        break;
	      default:
	        // should not occur !!!
	        break;
	    }
	  }
	  
	  /**
	   * Visits a {@link Assignment} node, whose children are the following :
	   * <p>
	   * f0 -> Assignee()<br>
	   * f1 -> <EQUALS_SIGN><br>
	   * f2 -> Expression()<br>
	   * f3 -> <SEMICOLON><br>
	   *
	   * @param n - the node to visit
	   */
	  @Override
	  public void visit(final Assignment n) {
	    // f0 -> Assignee()
	    final Assignee n0 = n.f0;
	    n0.accept(this);
	    // f2 -> Expression()
	    final Expression n2 = n.f2;
        valid=this.expressionVisistor.check(n2);
        valid=this.expressionVisistor.expressionType.equals(assigneeType);
	    // f3 -> <SEMICOLON>
	    final NodeToken n3 = n.f3;
	    n3.accept(this);
	  }
	  
	  
	  /**
	   * Visits a {@link Assignee} node, whose child is the following :
	   * <p>
	   * f0 -> . %0 #0 Identifier() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT><br>
	   * .. .. | %1 Identifier()<br>
	   *
	   * @param n - the node to visit
	   */
	  @Override
	  public void visit(final Assignee n) {
	    // f0 -> . %0 #0 Identifier() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT>
	    // .. .. | %1 Identifier()
	    final NodeChoice nch = n.f0;
	    final INode ich = nch.choice;
	    switch (nch.which) {
	      case 0:
	        // %0 #0 Identifier() #1 <BRACKET_LEFT> #2 Expression() #3 <BRACKET_RIGHT>
	        final NodeSequence seq = (NodeSequence) ich;
	        String id=ich.accept(new IdentifierNameExtractor());
	        VariableEntry varType =  (VariableEntry)this.table.lookup(id);
	        assigneeType=varType.getEntryTypeName();
	        assigneeType="int";
	        // #2 Expression()
	        final INode seq3 = seq.elementAt(2);
			ExpressionVisitor expressionVisitor = new ExpressionVisitor(this.table);
			expressionVisitor.check(seq3);
			ClassEntry returnedType =  (ClassEntry) this.table.lookup(expressionVisitor.expressionType);
			valid = returnedType.getName().equals(Types.INT.getName());
	        // #3 <BRACKET_RIGHT>
	        final INode seq4 = seq.elementAt(3);
	        seq4.accept(this);
	        break;
	      case 1:
	        // %1 Identifier()
	    	id=ich.accept(new IdentifierNameExtractor());
		    varType =  (VariableEntry)this.table.lookup(id);
		    assigneeType=varType.getEntryTypeName();
	        break;
	      default:
	        // should not occur !!!
	        break;
	    }
	  }
}
