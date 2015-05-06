package ch.unibe.iam.scg.minijava.typechecker.scope;

import java.util.HashMap;
import java.util.Map;

import ch.unibe.iam.scg.minijava.typechecker.type.ArrayType;
import ch.unibe.iam.scg.minijava.typechecker.type.BooleanType;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.IntType;
import ch.unibe.iam.scg.minijava.typechecker.type.LookupException;
import ch.unibe.iam.scg.minijava.typechecker.type.ObjectType;
import ch.unibe.iam.scg.minijava.typechecker.type.StringType;
import ch.unibe.iam.scg.minijava.typechecker.type.VoidType;

public class GlobalScope extends AbstractScope {

	public static final GlobalScope INSTANCE = new GlobalScope();

	protected IType implicitSuperType;
	protected Map<String, IType> types;

	public GlobalScope() {
		super(NullScope.INSTANCE);
		this.types = new HashMap<String, IType>();
		this.implicitSuperType = new ObjectType();
		this.types
				.put(this.implicitSuperType.getName(), this.implicitSuperType);
		IType intType = new IntType();
		IType booleanType = new BooleanType();
		IType voidType = new VoidType();
		IType stringType = new StringType();
		IType intArrayType = new ArrayType(intType);
		IType stringArrayType = new ArrayType(stringType);
		this.types.put(intType.getName(), intType);
		this.types.put(booleanType.getName(), booleanType);
		this.types.put(voidType.getName(), voidType);
		this.types.put(stringType.getName(), stringType);
		this.types.put(intArrayType.getName(), intArrayType);
		this.types.put(stringArrayType.getName(), stringArrayType);
	}

	public IType getImplicitSuperType() {
		return this.implicitSuperType;
	}

	public boolean hasType(String name) {
		return this.types.containsKey(name);
	}

	public IType getType(String name) {
		assert this.hasType(name);
		return this.types.get(name);
	}

	public void putType(String name, IType type) {
		assert !this.hasType(name);
		this.types.put(name, type);
	}

	@Override
	public IType lookupType(String name) throws LookupException {
		if (this.types.containsKey(name)) {
			return this.types.get(name);
		}
		return super.lookupType(name);
	}

}
