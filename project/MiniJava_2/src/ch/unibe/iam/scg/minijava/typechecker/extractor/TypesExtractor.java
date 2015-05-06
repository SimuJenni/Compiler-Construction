package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.HashMap;
import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.LookupException;

public class TypesExtractor {

	protected static class ClassDeclarationVisitor extends
			DepthFirstVoidVisitor {

		protected Map<String, ClassDeclaration> classDeclarations;

		public ClassDeclarationVisitor() {
			super();
			this.classDeclarations = new HashMap<String, ClassDeclaration>();
		}

		public Map<String, ClassDeclaration> getClassDeclarations() {
			return this.classDeclarations;
		}

		@Override
		public void visit(ClassDeclaration n) {
			String className = n.f1.f0.tokenImage;
			this.classDeclarations.put(className, n);
		}

	}

	public Map<String, IType> extract(INode node, IScope scope)
			throws NameCollisionException, LookupException {
		ClassDeclarationVisitor visitor = new ClassDeclarationVisitor();
		node.accept(visitor);
		Map<String, ClassDeclaration> classDeclarations = visitor
				.getClassDeclarations();
		Map<String, IType> types = new HashMap<String, IType>();
		for (Map.Entry<String, ClassDeclaration> entry : classDeclarations
				.entrySet()) {
			IType type = scope.lookupType(entry.getKey());
			if (types.containsKey(type.getName())) {
				throw new NameCollisionException(type.getName());
			}
			type = (new TypeExtractor()).extract(entry.getValue(), type, scope);
			types.put(type.getName(), type);
		}
		return types;
	}

}
