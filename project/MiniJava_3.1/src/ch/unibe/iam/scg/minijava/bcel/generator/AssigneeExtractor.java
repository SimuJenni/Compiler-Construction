package ch.unibe.iam.scg.minijava.bcel.generator;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.iam.scg.javacc.syntaxtree.Assignee;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.Statement;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.IdentifierNameExtractor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class AssigneeExtractor extends DepthFirstVoidVisitor {
	private List<Variable> assignees = new ArrayList<Variable>();
	private IScope scope;

	public AssigneeExtractor(IScope currentScope) {
		this.scope=currentScope;
	}

	public List<Variable> extract(Statement n) {
		n.accept(this);
		return assignees;
	}
	
	public void visit(final Assignee n) {
	    // f0 -> . %0 AssignableArrayAccess()
	    // .. .. | %1 Identifier()
	    final NodeChoice nch = n.f0;
	    final INode ich = nch.choice;
	    switch (nch.which) {
	      case 0:
	        // %0 AssignableArrayAccess()
	        break;
	      case 1:
	        // %1 Identifier()
	    	String name = new IdentifierNameExtractor().extract(ich);
	    	assignees.add(scope.lookupVariable(name));
	        break;
	      default:
	        // should not occur !!!
	        break;
	    }
	}

}
