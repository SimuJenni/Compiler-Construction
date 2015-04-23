package ch.unibe.iam.scg.minijava.typechecker.scope;

import java.util.Map;

import ch.unibe.iam.scg.minijava.typechecker.type.Type;

public class GlobalScope {

	protected Map<String, Type> types;

	public GlobalScope(Map<String, Type> types) {
		this.types = types;
	}

	public boolean has(String name) {
		return this.types.containsKey(name);
	}

	public Type get(String name) {
		assert this.has(name);
		return this.types.get(name);
	}

}
