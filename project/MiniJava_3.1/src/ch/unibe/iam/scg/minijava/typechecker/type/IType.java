package ch.unibe.iam.scg.minijava.typechecker.type;


public interface IType {

	public String getName();

	public IType getParent();

	public boolean canBeAssignedTo(IType type);

}