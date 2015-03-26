package ch.unibe.iam.scg.minijava.prettyprint;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.Identifier;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.Type;
import ch.unibe.iam.scg.javacc.syntaxtree.VarDeclaration;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.ast.AstNode;

/**
 * Change at will!
 * 
 * @author kursjan
 *
 */
public class PrettyPrinter extends DepthFirstVoidVisitor
{
	private StringBuffer buffer;
	
    public PrettyPrinter() {
		super();
		this.buffer = new StringBuffer();
	}

    public String prettyPrint(Object node)
    {
        INode n=  (INode) node;
        n.accept(this);
        return buffer.toString();
    }
    
    /**
     * Visits a {@link NodeToken} node.
     *
     * @param n - the node to visit
     */
    @Override
    public void visit(final NodeToken n) {
      buffer.append(n.tokenImage);
    }

}
