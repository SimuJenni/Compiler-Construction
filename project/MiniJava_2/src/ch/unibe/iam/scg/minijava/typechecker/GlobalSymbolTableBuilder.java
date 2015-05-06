package ch.unibe.iam.scg.minijava.typechecker;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.MainClass;
import ch.unibe.iam.scg.minijava.typechecker.extractor.IdentifierNameExtractor;

/**
 * Root symbol-table in the symbol-table hierarchy. Stores references to the
 * symbol-tables of all the classes.
 */
public class GlobalSymbolTableBuilder extends SymbolTableBuilder<SymbolTable> {

	public GlobalSymbolTableBuilder(SymbolTable table) {
		super(table);
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
	 *            - the node to visit
	 */
	@Override
	public void visit(MainClass n) {
		String mainClassName = n.f1.accept(new IdentifierNameExtractor());
		ClassEntry superClass = new NullClassEntry();
		ClassEntry mainClassEntry = new ClassEntry(mainClassName, superClass,
				this.table);
		this.table.put(mainClassName, mainClassEntry);
		String mainMethodName = "main";
		// TODO get rid of nasty casts
		ClassEntry mainMethodReturnType = (ClassEntry) this.table
				.lookup(Types.VOID.getName());
		String mainMethodParameterName = n.f11
				.accept(new IdentifierNameExtractor());
		ClassEntry mainMethodParameterType = (ClassEntry) this.table
				.lookup(Types.STRING_ARRAY.getName());
		List<ClassEntry> mainMethodParameterTypes = new ArrayList<ClassEntry>();
		mainMethodParameterTypes.add(mainMethodParameterType);
		MethodEntry mainMethodEntry = new MethodEntry(mainMethodName,
				mainMethodParameterTypes, mainMethodReturnType, mainClassEntry);
		mainMethodEntry.put(mainMethodParameterName, new VariableEntry(
				mainMethodParameterName, mainMethodParameterType));
		mainClassEntry.put(mainMethodName, mainMethodEntry);
		MethodEntryBuilder methodEntryBuilder = new MethodEntryBuilder(
				mainMethodEntry);
		// delegate
		n.f14.accept(methodEntryBuilder);
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
	 *            - the node to visit
	 */
	@Override
	public void visit(ClassDeclaration n) {
		String name = n.f1.accept(new IdentifierNameExtractor());
		ClassEntry superClass = new NullClassEntry();
		if (n.f2.present()) {
			String superClassName = n.f2.node
					.accept(new IdentifierNameExtractor());
			// TODO get rid of nasty cast
			superClass = (ClassEntry) this.table.lookup(superClassName);
		}
		ClassEntry classEntry = new ClassEntry(name, superClass, this.table);
		this.table.put(name, classEntry);
		ClassEntryBuilder classEntryBuilder = new ClassEntryBuilder(classEntry);
		// delegate
		n.accept(classEntryBuilder);
	}

}
