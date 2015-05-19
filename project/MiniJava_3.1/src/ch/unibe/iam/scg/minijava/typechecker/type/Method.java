package ch.unibe.iam.scg.minijava.typechecker.type;

import java.util.List;

import org.apache.bcel.generic.Type;

public class Method {

	protected String name;
	protected IType returnType;
	protected List<Variable> parameters;

	public Method(String name, IType returnType, List<Variable> parameters) {
		this.name = name;
		this.returnType = returnType;
		this.parameters = parameters;
	}

	public String getName() {
		return this.name;
	}

	public IType getReturnType() {
		return this.returnType;
	}

	public List<Variable> getParameters() {
		return this.parameters;
	}

	public Type[] getParametersBCEL() {
		Type[] args=new Type[parameters.size()];
		for(int i=0;i<parameters.size(); i++){
			args[i]=parameters.get(i).getType().toBcelType();
		}
		return args;
	}

}
