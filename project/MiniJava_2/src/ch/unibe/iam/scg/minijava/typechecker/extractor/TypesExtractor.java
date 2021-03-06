package ch.unibe.iam.scg.minijava.typechecker.extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.scope.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.scope.NameCollisionException;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.Type;

public class TypesExtractor {

	protected static class ClassDeclarationVisitor extends
			DepthFirstVoidVisitor {

		protected IType implicitSuperType;
		protected List<String> classNames;
		protected Map<String, String> inheritances;

		public ClassDeclarationVisitor(IType implicitSuperType) {
			super();
			this.implicitSuperType = implicitSuperType;
			this.classNames = new ArrayList<String>();
			this.inheritances = new HashMap<String, String>();
		}

		public List<String> getClassNames() {
			return this.classNames;
		}

		public Map<String, String> getInheritances() {
			return this.inheritances;
		}

		@Override
		public void visit(ClassDeclaration n) {
			String className = n.f1.f0.tokenImage;
			String superClassName = implicitSuperType.getName();
			if (n.f2.present()) {
				superClassName = (new IdentifierNameExtractor()).extract(n.f2);
			}
			if (this.classNames.contains(className)) {
				throw new NameCollisionException(className);
			}
			this.classNames.add(className);
			if (!this.classNames.contains(superClassName)) {
				this.classNames.add(superClassName);
			}
			this.inheritances.put(className, superClassName);
		}

	}

	public List<IType> extract(INode node, IType implicitSuperType)
			throws NameCollisionException, LookupException {
		ClassDeclarationVisitor visitor = new ClassDeclarationVisitor(
				implicitSuperType);
		node.accept(visitor);
		List<String> classNames = visitor.getClassNames();
		Map<String, String> inheritances = visitor.getInheritances();
		classNames = this.getTopologicallySortedClassNames(classNames,
				inheritances);
		Map<String, IType> map = new HashMap<String, IType>();
		List<IType> types = new ArrayList<IType>();
		map.put(implicitSuperType.getName(), implicitSuperType);
		types.add(implicitSuperType);
		for (String className : classNames) {
			if (className.equals(implicitSuperType.getName())) {
				continue;
			}
			String superClassName = inheritances.get(className);
			if (!map.containsKey(superClassName)) {
				throw new LookupException(superClassName);
			}
			IType superType = map.get(superClassName);
			IType thisType = new Type(className, superType);
			map.put(thisType.getName(), thisType);
			types.add(thisType);
		}
		types.remove(implicitSuperType);
		return types;
	}

	/**
	 * @param classNames
	 * @param inheritances
	 *            Maps sub-types with super-types, must not define cycles
	 * @return
	 */
	protected List<String> getTopologicallySortedClassNames(
			List<String> classNames, Map<String, String> inheritances) {
		List<String> sorted = new ArrayList<String>();
		List<String> candidates = new ArrayList<String>(inheritances.values());
		candidates.removeAll(inheritances.keySet());
		while (!candidates.isEmpty()) {
			String current = candidates.remove(0);
			if (sorted.contains(current)) {
				continue;
			}
			sorted.add(current);
			for (Map.Entry<String, String> inheritance : inheritances
					.entrySet()) {
				if (inheritance.getValue().equals(current)) {
					candidates.add(inheritance.getKey());
				}
			}
		}
		return sorted;
	}

}
