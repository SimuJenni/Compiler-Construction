package ch.unibe.iam.scg.minijava.typechecker;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.Type;
import ch.unibe.iam.scg.javacc.syntaxtree.VarDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class SymbolTableBuilder extends DepthFirstVoidVisitor {
	private SymbolTable table;
	private String currentIdentifier; 
	private Types currentType;

	public SymbolTableBuilder() {
		this.table = new SymbolTable();
	}

	public SymbolTable build(Object node) {
		INode n = (INode) node;
		n.accept(this);
		return table;
	}
	
	  /**
	   * Visits a {@link VarDeclaration} node, whose children are the following :
	   * <p>
	   * f0 -> Type()<br>
	   * f1 -> Identifier()<br>
	   * f2 -> <SEMICOLON><br>
	   *
	   * @param n - the node to visit
	   */
	  @Override
	  public void visit(final VarDeclaration n) {
	    // f0 -> Type()
	    n.f0.accept(this);
	    
	    // f1 -> Identifier()
	    n.f1.accept(this);
	    
	    table.putVariable(currentIdentifier, new Variable(currentIdentifier, currentType));
	  }
	  
	  
	  /**
	   * Visits a {@link Type} node, whose child is the following :
	   * <p>
	   * f0 -> . %0 #0 <INT_TYPE> #1 <BRACKET_LEFT> #2 <BRACKET_RIGHT><br>
	   * .. .. | %1 <INT_TYPE><br>
	   * .. .. | %2 BooleanType()<br>
	   * .. .. | %3 <VOID_TYPE><br>
	   * .. .. | %4 Identifier()<br>
	   *
	   * @param n - the node to visit
	   */
	  @Override
	  public void visit(final Type n) {
	    final NodeChoice nch = n.f0;
	    final INode ich = n.f0.choice;
	    switch (nch.which) {
	      case 0:
	        // %0 #0 <INT_TYPE> #1 <BRACKET_LEFT> #2 <BRACKET_RIGHT>
	        currentType= Types.INT_ARRAY;
	        break;
	      case 1:
	        // %1 <INT_TYPE>
		    currentType=Types.INT;
	        break;
	      case 2:
	        // %2 BooleanType()
		    currentType=Types.BOOLEAN;
	        break;
	      case 3:
	        // %3 <VOID_TYPE>
	        ich.accept(this);
	        break;
	      case 4:
	        // %4 Identifier()
	        ich.accept(this);
	        break;
	      default:
	        // should not occur !!!
	        break;
	    }
	  }

}
