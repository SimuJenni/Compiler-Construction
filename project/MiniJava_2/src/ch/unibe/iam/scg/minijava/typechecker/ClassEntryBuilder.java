package ch.unibe.iam.scg.minijava.typechecker;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
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
		MethodEntryBuilder methodEntryBuilder = new MethodEntryBuilder(
				methodEntry);
		// delegate
		n.accept(methodEntryBuilder);
	}

}
