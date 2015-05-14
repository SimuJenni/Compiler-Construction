package ch.unibe.iam.scg.minijava.bcel.generator;

import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.PUSH;

import ch.unibe.iam.scg.javacc.syntaxtree.IntType;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;

public class CodeGeneratorVisitor extends DepthFirstVoidVisitor{
	
    private InstructionList il;
	private ConstantPoolGen constPool=new ConstantPoolGen();

	
	  public CodeGeneratorVisitor(InstructionList il) {
		super();
		this.il = il;
	}

	/**
	   * Visits a {@link IntType} node, whose child is the following :
	   * <p>
	   * f0 -> <INT_TYPE><br>
	   *
	   * @param n - the node to visit
	   */
	  @Override
	  public void visit(final IntType n) {
	    // f0 -> <INT_TYPE>
	    final NodeToken n0 = n.f0;
	    int val=Integer.valueOf(n0.tokenImage);
	    il.append(new PUSH(constPool, val));
	  }

}
