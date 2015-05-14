package ch.unibe.iam.scg.minijava.typechecker;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeListOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.ParameterDeclaration;

public class ClassEntryBuilder extends SymbolTableBuilder<ClassEntry> {

	public ClassEntryBuilder(ClassEntry classEntry) {
		super(classEntry);
	}

	/**
	 * Visits a {@link MethodDeclaration} node, whose children are the following
	 * :
	 * <p>
	 * f0 -> <PUBLIC_MODIFIER><br>
	 * f1 -> TypedDeclaration()<br>
	 * f2 -> <PARENTHESIS_LEFT><br>
	 * f3 -> ParameterDeclarationList()<br>
	 * f4 -> <PARENTHESIS_RIGHT><br>
	 * f5 -> <BRACE_LEFT><br>
	 * f6 -> ( VarDeclaration() )*<br>
	 * f7 -> ( Statement() )*<br>
	 * f8 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?<br>
	 * f9 -> <BRACE_RIGHT><br>
	 *
	 * @param n
	 *            - the node to visit
	 */
	@Override
	public void visit(MethodDeclaration n) {
		String name = n.f1.f1.accept(new IdentifierNameExtractor());
		String returnTypeName = n.f1.f0.accept(new TypeNameExtractor());
		List<ParameterDeclaration> parameterDeclarations = new ArrayList<ParameterDeclaration>();
		n.f3.accept(new ParameterDeclarationsExtractor(), parameterDeclarations);
		// TODO get rid of nasty cast
		ClassEntry returnType = (ClassEntry) this.table.lookup(returnTypeName);
		List<ClassEntry> parameterTypes = new ArrayList<ClassEntry>();
		for (ParameterDeclaration parameterDeclaration : parameterDeclarations) {
			String parameterTypeName = parameterDeclaration.f0.f0
					.accept(new TypeNameExtractor());
			// TODO get rid of nasty cast
			ClassEntry parameterType = (ClassEntry) this.table
					.lookup(parameterTypeName);
			parameterTypes.add(parameterType);
		}
		MethodEntry methodEntry = new MethodEntry(name, parameterTypes,
				returnType, this.table);
		methodEntry.put("this", this.table);
		for (ParameterDeclaration parameterDeclaration : parameterDeclarations) {
			String parameterTypeName = parameterDeclaration.f0.f0
					.accept(new TypeNameExtractor());
			// TODO get rid of nasty cast
			ClassEntry parameterType = (ClassEntry) this.table
					.lookup(parameterTypeName);
			String parameterName = parameterDeclaration.f0.f1
					.accept(new IdentifierNameExtractor());
			methodEntry.put(parameterName, new VariableEntry(parameterName,
					parameterType));
		}
		this.table.put(name, methodEntry);
		MethodEntryBuilder methodEntryBuilder = new MethodEntryBuilder(
				methodEntry);
		// delegate
		n.accept(methodEntryBuilder);
		
		
//	    // f6 -> ( VarDeclaration() )*
//	    final NodeListOptional n6 = n.f6;
//	    if (n6.present()) {
//	      VarDeclarationVisitor varVisitor=new VarDeclarationVisitor(methodEntry);
//		  for (int i = 0; i < n6.size(); i++) {
//		    final INode nloeai = n6.elementAt(i);
//		    nloeai.accept(varVisitor);
//		  }
//	    }
//		
//	    // f7 -> ( Statement() )*
//		StatementVisitor statementVisitor=new StatementVisitor(methodEntry);
//	    final NodeListOptional n7 = n.f7;
//	    if (n7.present()) {
//	      for (int i = 0; i < n7.size(); i++) {
//	        final INode nloeai = n7.elementAt(i);
//	        statementVisitor.check(nloeai);
//	      }
//	    }
//		
//		// f8 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?
//	    final NodeOptional n8 = n.f8;
//	    if (n8.present()) {
//	      final NodeSequence seq = (NodeSequence) n8.node;
//	      // #1 Expression()
//	      final INode seq2 = seq.elementAt(1);
//	      ExpressionVisitor expressionVisitor = new ExpressionVisitor(methodEntry);
//		  boolean b=expressionVisitor.check(seq2);
//		  ClassEntry returnedType =  (ClassEntry) this.table.lookup(expressionVisitor.expressionType);
////	      if(!returnedType.getName().equals(returnType))
////	    	  throw new ReturnedTypeCanNotBeAssignedToReturnTypeException(returnedType, returnType);
//	    }
	}

}
