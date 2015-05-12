package ch.unibe.iam.scg.minijava.typechecker.type;

import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.scope.NullScope;

public class NullType implements IType {

	public static final NullType INSTANCE = new NullType();

	@Override
	public String getName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public IType getParent() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean canBeAssignedTo(IType type) {
		return false;
	}

	@Override
	public IScope getScope(){
		return NullScope.INSTANCE;
	}

}
