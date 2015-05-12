package ch.unibe.iam.scg.minijava.typechecker.type;

import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;

public interface IType {

	public String getName();

	public IType getParent();

	public boolean canBeAssignedTo(IType type);

	public IScope getScope();

}