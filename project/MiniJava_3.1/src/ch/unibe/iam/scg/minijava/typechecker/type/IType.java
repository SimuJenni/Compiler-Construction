package ch.unibe.iam.scg.minijava.typechecker.type;

import org.apache.bcel.generic.Type;

public interface IType {

	public String getName();

	public IType getParent();

	public boolean canBeAssignedTo(IType type);

	public Type toBcelType();
	
	public boolean isPrimitive();
	
	public boolean isArray();
	
	public boolean isObject();

}