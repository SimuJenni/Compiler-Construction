package ch.unibe.iam.scg.minijava.typechecker.type;

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.LocalVariableGen;

public class Variable {

	protected String name, value;
	protected IType type;
	protected boolean isConstant;
	protected int assignementCount = 0, useCount = 0;
	private InstructionHandle initStart, initEnd;
	private List<InstructionHandle> assignments = new ArrayList<InstructionHandle>();
	private LocalVariableGen lg;

	public Variable(String name, IType type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public IType getType() {
		return type;
	}

	public int getAssignmentCount() {
		// TODO Auto-generated method stub
		return assignementCount;
	}

	public void increaseCount() {
		assignementCount++;		
	}

	public void setValue(String value) {
		this.value=value;		
		this.isConstant=true;
	}

	public boolean isConstant() {
		return this.isConstant;
	}

	public String getValue() {
		return this.value;
	}

	public void setUnknown() {
		isConstant=false;
	}

	public void increaseUseCount() {
		useCount++;
	}
	
	public int getUseCount() {
		return useCount;
	}

	public void setInitInstructions(InstructionHandle start,
			InstructionHandle end) {
		initStart = start;
		initEnd = end;
	}

	public void setAssignInstructions(InstructionHandle l1,
			InstructionHandle l2) {
		this.assignments.add(l1);		
		this.assignments.add(l2);		
	}

	public List<InstructionHandle> getAssignments() {
		return assignments;
	}

	public InstructionHandle getInitStart() {
		return initStart;
	}

	public InstructionHandle getInitEnd() {
		return initEnd;
	}

	public void setLG(LocalVariableGen lg) {
		// TODO Auto-generated method stub
		this.lg=lg;
	}

	public LocalVariableGen getLg() {
		return lg;
	}

	public void reinit(LocalVariableGen lg2) {
		this.useCount=0;
		this.lg=lg2;
		this.assignments=new ArrayList<InstructionHandle>();
		initStart=lg.getStart();
		initEnd=lg.getEnd();
	}

}
