package ch.unibe.iam.scg.minijava.typechecker.scope;

import ch.unibe.iam.scg.minijava.typechecker.type.ArrayType;
import ch.unibe.iam.scg.minijava.typechecker.type.BooleanType;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.IntType;
import ch.unibe.iam.scg.minijava.typechecker.type.ObjectType;
import ch.unibe.iam.scg.minijava.typechecker.type.StringType;
import ch.unibe.iam.scg.minijava.typechecker.type.VoidType;

public class GlobalScope extends Scope {

	public static final GlobalScope INSTANCE = new GlobalScope();

	protected IType implicitSuperType;

	public GlobalScope() {
		super(NullScope.INSTANCE);
		this.implicitSuperType = new ObjectType();
		this.putType(this.implicitSuperType.getName(), this.implicitSuperType);
		IType intType = new IntType();
		IType booleanType = new BooleanType();
		IType voidType = new VoidType();
		IType stringType = new StringType();
		IType intArrayType = new ArrayType(intType);
		IType stringArrayType = new ArrayType(stringType);
		this.putType(intType.getName(), intType);
		this.putType(booleanType.getName(), booleanType);
		this.putType(voidType.getName(), voidType);
		this.putType(stringType.getName(), stringType);
		this.putType(intArrayType.getName(), intArrayType);
		this.putType(stringArrayType.getName(), stringArrayType);
	}

	public IType getImplicitSuperType() {
		return this.implicitSuperType;
	}

}
