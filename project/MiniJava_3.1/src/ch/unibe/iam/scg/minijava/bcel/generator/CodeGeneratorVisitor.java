package ch.unibe.iam.scg.minijava.bcel.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ARRAYLENGTH;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.FieldGen;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.IASTORE;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.IFGT;
import org.apache.bcel.generic.IFNE;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.ISTORE;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.NEWARRAY;
import org.apache.bcel.generic.NOP;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.TargetLostException;
import org.apache.bcel.generic.Type;

import ch.unibe.iam.scg.javacc.syntaxtree.AssignmentStatement;
import ch.unibe.iam.scg.javacc.syntaxtree.ClassConstructorCall;
import ch.unibe.iam.scg.javacc.syntaxtree.ClassDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.Expression;
import ch.unibe.iam.scg.javacc.syntaxtree.Goal;
import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.javacc.syntaxtree.IfStatement;
import ch.unibe.iam.scg.javacc.syntaxtree.MainClass;
import ch.unibe.iam.scg.javacc.syntaxtree.MainMethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.MethodDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeChoice;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeListOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeOptional;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeSequence;
import ch.unibe.iam.scg.javacc.syntaxtree.NodeToken;
import ch.unibe.iam.scg.javacc.syntaxtree.PrintStatement;
import ch.unibe.iam.scg.javacc.syntaxtree.Statement;
import ch.unibe.iam.scg.javacc.syntaxtree.TypedDeclaration;
import ch.unibe.iam.scg.javacc.syntaxtree.WhileStatement;
import ch.unibe.iam.scg.javacc.visitor.DepthFirstVoidVisitor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.IdentifierNameExtractor;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.AbstractOperatorToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ArrayAccessToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ArrayLengthAccessToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.BinaryOperatorToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.IToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.LiteralToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.MethodCallToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ObjectInstantiationToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.ShuntingYard;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.UnaryOperatorToken;
import ch.unibe.iam.scg.minijava.typechecker.extractor.shuntingyard.VariableToken;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;
import ch.unibe.iam.scg.minijava.typechecker.type.BooleanType;
import ch.unibe.iam.scg.minijava.typechecker.type.IType;
import ch.unibe.iam.scg.minijava.typechecker.type.IntType;
import ch.unibe.iam.scg.minijava.typechecker.type.Method;
import ch.unibe.iam.scg.minijava.typechecker.type.Variable;

public class CodeGeneratorVisitor extends DepthFirstVoidVisitor {

	private InstructionList il;
	private ConstantPoolGen cp;
	private ClassGen cg;
	private MethodGen mg;
	private InstructionFactory iFact;
	private Map<INode, IScope> scopeMap;
	private Map<String, Method> methodMap = new HashMap<String, Method>();
	private IScope currentScope;
	private Map<String, Integer> registerMap = new HashMap<String, Integer>();
	private JavaBytecodeGenerator bytecodeGenerator;
	private boolean isField, inIf, inWhile, inAssignment;
	private Stack<IToken> tokenStack;
	private Variable assignee;
	private List<Variable> localVariables = new ArrayList<Variable>();
	private Object result;

	public CodeGeneratorVisitor(JavaBytecodeGenerator bytecodeGenerator,
			ClassGen cg, MethodGen mg, InstructionList il,
			InstructionFactory iFact) {
		super();
		this.bytecodeGenerator = bytecodeGenerator;
		this.il = il;
		this.cg = cg;
		this.cp = cg.getConstantPool();
		this.mg = mg;
		this.iFact = iFact;
	}

	public void generate(INode node, Map<INode, IScope> scopeMap) {
		this.scopeMap = scopeMap;
		node.accept(this);
	}

	@Override
	public void visit(Goal n) {
		// f1 -> ( ClassDeclaration() )*
		final NodeListOptional n1 = n.f1;
		if (n1.present()) {
			for (int i = 0; i < n1.size(); i++) {
				final INode nloeai = n1.elementAt(i);
				nloeai.accept(this);
			}
		}
		// f0 -> MainClass()
		final MainClass n0 = n.f0;
		n0.accept(this);
	}

	public void visit(final Expression n) {
		tokenStack = new Stack<IToken>();
		ShuntingYard sy = new ShuntingYard(currentScope);
		List<IToken> tokenList = sy.extract(n);
		Iterator<IToken> it = tokenList.iterator();
		InstructionHandle startExpression = il.append(iFact.NOP);
		while (it.hasNext()) {
			IToken next = it.next();
			next.accept(this);
		}
		evaluateStack(startExpression);
		
	}

	private void evaluateStack(InstructionHandle startExpression) {
		boolean stop = false;
		Stack<Object> stack = new Stack<Object>();
		Stack<IToken> tmp = new Stack<IToken>();
		
		// Stack in reversed order...
		while(!tokenStack.isEmpty()){
			tmp.push(tokenStack.pop());	
		}
		tokenStack = (Stack<IToken>) tmp.clone();
		
		while(!tokenStack.isEmpty()){
			IToken t = tokenStack.pop();
			if(t.isVariable()){
				Variable v = currentScope.lookupVariable(((VariableToken) t).getName());
				if(v.isConstant()){
					if(v.getType().getName().equals("int"))
						stack.push(Integer.parseInt(v.getValue()));
					if(v.getType().getName().equals("boolean"))
						stack.push(Boolean.parseBoolean(v.getValue()));
				} else 
					stop = true;
				continue;
			}
			if(t.isLiteral()){
				if(((LiteralToken) t).getType().getName().equals("int"))
					stack.push(Integer.parseInt(((LiteralToken) t).getValue()));
				if(((LiteralToken) t).getType().getName().equals("boolean"))
					stack.push(Boolean.parseBoolean(((LiteralToken) t).getValue()));
				if(((LiteralToken) t).getValue().equals("this"))
					stack.push("this");
				continue;
			}
			if(t.isOperator()&&!stop){
				if(((AbstractOperatorToken) t).getSymbol().equals("+")){
					stack.push((int) stack.pop() + (int) stack.pop());
					continue;
				}
				if(((AbstractOperatorToken) t).getSymbol().equals("-")){
					stack.push(- (int) stack.pop() + (int) stack.pop());
					continue;
				}
				if(((AbstractOperatorToken) t).getSymbol().equals("*")){
					stack.push((int) stack.pop() * (int) stack.pop());
					continue;
				}
				if(((AbstractOperatorToken) t).getSymbol().equals("<")){
					stack.push((int) stack.pop() > (int) stack.pop());
					continue;
				}
				if(((AbstractOperatorToken) t).getSymbol().equals(">")){
					stack.push((int) stack.pop() < (int) stack.pop());
					continue;
				}
				if(((AbstractOperatorToken) t).getSymbol().equals("&&")){
					stack.push((boolean) stack.pop() && (boolean) stack.pop());
					continue;
				}
				if(((AbstractOperatorToken) t).getSymbol().equals("==")){
					stack.push((int) stack.pop() == (int) stack.pop());
					continue;
				}
				if(((AbstractOperatorToken) t).getSymbol().equals("!")){
					stack.push(!(boolean) stack.pop());
					continue;
				}	
				stop = true;
			}
			if(t.isFunction()&&inAssignment){
				if(inAssignment){
					assignee.setUnknown();
					assignee.increaseUseCount();
				}
				stop = true;
			}
			stop = true;
		}
		
		if(stop){
			result=null;
			while(!tmp.isEmpty()){
				IToken t = tmp.pop();			
				if(t.isVariable()){
					Variable v = currentScope.lookupVariable(((VariableToken) t).getName());
					v.increaseUseCount();
				}
			}
		}
		else {
			if(!stack.isEmpty()){
				result = stack.pop();
				if(!stack.isEmpty())
					return;
				try {
					il.delete(startExpression, il.getEnd());
				} catch (TargetLostException e) {
					e.printStackTrace();
				};
				if(result instanceof Integer)
					il.append(new PUSH(cp,(int) result));
				if(result instanceof Boolean)
					il.append(new PUSH(cp,(boolean) result));
				// This is not working because of function calls...
				if(inAssignment&&!inIf&&!inWhile){
					assignee.setValue(result.toString());
				}
			}
		}
	}

	@Override
	public void visit(MainClass n) {
		currentScope = scopeMap.get(n);

		// f1 -> Identifier()
		String className = n.f1.f0.tokenImage;
		IType classType = currentScope.lookupType(className);
		cg = new ClassGen(className, classType.getParent().toBcelType()
				.toString(), "<generated>", Constants.ACC_PUBLIC
				| Constants.ACC_SUPER, new String[] {});
		cg.addEmptyConstructor(Constants.ACC_PUBLIC);
		cp = cg.getConstantPool(); // cg creates constant pool
		iFact = new InstructionFactory(cg);

		// f3 -> MainMethodDeclaration()
		final MainMethodDeclaration n3 = n.f3;
		n3.accept(this);

		JavaClass jclass = cg.getJavaClass();

		try {
			jclass.dump("bin/" + cg.getClassName().replaceAll("\\.", "/")
					+ ".class");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(MainMethodDeclaration n) {
		il = new InstructionList();
		String methodName = "main";
		currentScope = scopeMap.get(n);
		Method m = currentScope.lookupMethod(methodName);
		extractMG(Constants.ACC_PUBLIC | Constants.ACC_STATIC, methodName, m);

		// f11 -> ( Statement() )?
		final NodeOptional n11 = n.f11;
		if (n11.present()) {
			n11.accept(this);
		}
		this.il.append(InstructionFactory.RETURN);

		mg.setMaxStack();
		mg.setMaxLocals();
		methodMap.put(methodName, m);

		bytecodeGenerator.addMethod(cg, mg);
		il.dispose();
	}

	public void visit(final ClassDeclaration n) {
		currentScope = scopeMap.get(n);

		// f1 -> Identifier()
		String className = n.f1.f0.tokenImage;
		IType classType = currentScope.lookupType(className);
		cg = new ClassGen(className, classType.getParent().toBcelType()
				.toString(), "<generated>", Constants.ACC_PUBLIC
				| Constants.ACC_SUPER, new String[] {});
		cg.addEmptyConstructor(Constants.ACC_PUBLIC);
		cp = cg.getConstantPool(); // cg creates constant pool
		iFact = new InstructionFactory(cg);

		// f2 -> ( #0 <EXTENDS> #1 Identifier() )?
		final NodeOptional n2 = n.f2;
		if (n2.present()) {
			final NodeSequence seq = (NodeSequence) n2.node;
			// #0 <EXTENDS>
			final INode seq1 = seq.elementAt(0);
			// #1 Identifier()
			final INode seq2 = seq.elementAt(1);
		}
		// f4 -> ( VarDeclaration() )*
		isField = true;
		n.f4.accept(this);
		isField = false;

		// f5 -> ( MethodDeclaration() )*
		n.f5.accept(this);

		JavaClass jclass = cg.getJavaClass();

		try {
			jclass.dump("bin/" + cg.getClassName().replaceAll("\\.", "/")
					+ ".class");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void visit(final MethodDeclaration n) {
		localVariables = new ArrayList<Variable>();
		// f2 -> Identifier()
		il = new InstructionList();
		String methodName = n.f2.f0.tokenImage;
		currentScope = scopeMap.get(n);
		Method m = currentScope.lookupMethod(methodName);
		m.setClassName(cg.getClassName());
		registerMap.put("this", 0);
		extractMG(Constants.ACC_PUBLIC, methodName, m);
		// f7 -> ( VarDeclaration() )*
		n.f7.accept(this);
		// f8 -> ( Statement() )*
		n.f8.accept(this);
		// f9 -> ( #0 <RETURN> #1 Expression() #2 <SEMICOLON> )?
		final NodeOptional n9 = n.f9;
		if (n9.present()) {
			n9.accept(this);
			IType returnType = m.getReturnType();
			if (returnType.isVoid()) {
				this.il.append(InstructionFactory.RETURN);
			} else if (returnType.isPrimitive()) {
				this.il.append(InstructionFactory.IRETURN);
			} else {
				this.il.append(InstructionFactory.ARETURN);
			}
		}
		removeDeadCode();
		mg.removeNOPs();
		mg.setMaxStack();
		mg.setMaxLocals();
		methodMap.put(methodName, m);

		bytecodeGenerator.addMethod(cg, mg);
		il.dispose();
	}

	private void removeDeadCode() {
		Iterator<Variable> it = localVariables.iterator();
		while(it.hasNext()){
			Variable var = it.next();
			if(var.getUseCount()==0){
				removeVar(var);
			}
		}
	}

	private void removeVar(Variable var) {
		Iterator<InstructionHandle> it = var.getAssignments().iterator();
		this.mg.removeLocalVariable(var.getLg());
		registerMap.remove(var.getName());
		while(it.hasNext()){
			try {
				InstructionHandle l1 = it.next();
				InstructionHandle l2 = it.next();
				il.delete(l1, l2);
			} catch (TargetLostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void extractMG(int accessFlags, String methodName, Method m) {
		List<Variable> params = m.getParameters();
		int numParam = params.size();
		Type[] pTypes = new Type[numParam];
		String[] pNames = new String[numParam];
		for (int i = 0; i < numParam; i++) {
			Variable p = params.get(i);
			pTypes[i] = p.getType().toBcelType();
			pNames[i] = p.getName();
		}
		Type returnType = m.getReturnType().toBcelType();
		mg = new MethodGen(accessFlags, // access flags
				returnType, // return type
				pTypes, pNames, // arg names
				methodName, cg.getClassName(), // method, class
				il, cp);

		LocalVariableGen[] methodParams = mg.getLocalVariables();
		for (int i = 0; i < methodParams.length; i++) {
			int in = methodParams[i].getIndex();
			registerMap.put(methodParams[i].getName(), in);
		}
	}

	@Override
	public void visit(final TypedDeclaration n) {
		// f0 -> Type()
		// f1 -> Identifier()
		String varName = n.f1.f0.tokenImage;
		Variable v = currentScope.getVariable(varName);

		if (isField) {
			FieldGen fg = new FieldGen(Constants.ACC_PUBLIC, v.getType()
					.toBcelType(), varName, cp);
			cg.addField(fg.getField());
		} else {
			LocalVariableGen lg = mg.addLocalVariable(varName+"_0", v.getType()
					.toBcelType(), null, null);
			int in = lg.getIndex();
			v.setLG(lg);
			localVariables.add(v);
			registerMap.put(varName, in);
		}
	}

	public void visit(final AssignmentStatement n) {
		il.append(iFact.NOP);
		String name = (new IdentifierNameExtractor()).extract(n.f0);
		assignee = currentScope.lookupVariable(name);
		InstructionHandle assignStart = il.getEnd();
		if (registerMap.containsKey(name)) {
			if (n.f0.f0.which == 1) {
				// f2 -> Expression()
				inAssignment = true;
				n.f2.accept(this);
				inAssignment = false;
				// f0 -> Assignee()
				createNewVariable(name); // For SSA
				int reg = registerMap.get(name);
				if (currentScope.lookupVariable(name).getType().isPrimitive()) {
					il.append(new ISTORE(reg));
				} else {
					il.append(new ASTORE(reg));
				}
			} else {
				int reg = registerMap.get(name);
				il.append(new ALOAD(reg));
				n.f0.accept(this);
				n.f2.accept(this);
				il.append(new IASTORE());
			}
		} else {
			String fieldType = currentScope.lookupVariable(name).getType()
					.toBcelType().getSignature();
			if (n.f0.f0.which == 1) {
				il.append(new ALOAD(0));
				inAssignment = true;
				n.f2.accept(this);
				inAssignment = false;
				int i = cp.addFieldref(cg.getClassName(), name, fieldType);
				il.append(new PUTFIELD(i));
			} else {
				il.append(new ALOAD(0));
				int i = cp.addFieldref(cg.getClassName(), name, fieldType);
				il.append(new GETFIELD(i));
				n.f0.accept(this);
				inAssignment = true;
				n.f2.accept(this);
				inAssignment = false;
				il.append(new IASTORE());
			}
		}
		il.append(iFact.NOP);
		assignee.setAssignInstructions(assignStart, il.getEnd());
	}

	private void createNewVariable(String name) {
		Variable v = currentScope.getVariable(name);
		int count = v.getAssignmentCount();
		if(count>0&&!inIf&&!inWhile&&currentScope.lookupVariable(name).getType().isPrimitive()){
			LocalVariableGen lg = mg.addLocalVariable(name+"_"+count, v.getType()
					.toBcelType(), null, null);
			int in = lg.getIndex();
			registerMap.put(name, in);
		}
		v.increaseCount();
	}

	public void visit(final ClassConstructorCall n) {
		String className = n.f0.f0.tokenImage;
		il.append(iFact.createNew(className));
		il.append(InstructionConstants.DUP); // Use predefined constant
		il.append(iFact.createInvoke(className, "<init>", Type.VOID,
				new Type[] {}, Constants.INVOKESPECIAL));
	}

	@Override
	public void visit(final IfStatement n) {
		boolean expressionValue = false, constant = false;
		List<Variable> assignees = new AssigneeExtractor(currentScope).extract(n.f4);
		Iterator<Variable> it = assignees.iterator();
		while(it.hasNext())
			it.next().setUnknown();
		assignees = new AssigneeExtractor(currentScope).extract(n.f6);
		it = assignees.iterator();
		while(it.hasNext())
			it.next().setUnknown();
		
		// f0 -> <IF>
		final NodeToken n0 = n.f0;
		n0.accept(this);
		// f2 -> Expression()
		final Expression n2 = n.f2;
		n2.accept(this);
		if(result!=null){
			expressionValue = (boolean) result;
			constant = true;
		}
		InstructionHandle ifStart = il.getEnd();
		// f4 -> Statement()
		inIf = true;
		final Statement n4 = n.f4;
		n4.accept(this);
		InstructionHandle ifEnd = il.getEnd();
		// f5 -> <ELSE>
		final NodeToken n5 = n.f5;
		n5.accept(this);
		// f6 -> Statement()
		il.append(iFact.NOP);
		final Statement n6 = n.f6;
		n6.accept(this);
		InstructionHandle elseEnd = il.append(iFact.NOP);
		inIf = false;

		if(!constant){
			il.append(ifStart, new IFEQ(ifEnd.getNext()));
			il.append(ifEnd, new GOTO(elseEnd));
		}
		else{
			try {
				if(expressionValue){
					il.delete(ifEnd, elseEnd);
				} else
					il.delete(ifStart, ifEnd);
			} catch (TargetLostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void visit(final WhileStatement n) {
		List<Variable> assignees = new AssigneeExtractor(currentScope).extract(n.f4);
		Iterator<Variable> it = assignees.iterator();
		while(it.hasNext())
			it.next().setUnknown();
		
		il.append(iFact.NOP);
		InstructionHandle startExpression = il.getEnd();
		// f2 -> Expression()
		n.f2.accept(this);
		il.append(iFact.NOP);

		startExpression = startExpression.getNext();
		InstructionHandle endExpression = il.getEnd();
		
		if(result==null||!(boolean) result){
			inWhile = true;
			// f4 -> Statement()
			n.f4.accept(this);
	
			il.append(new GOTO(startExpression));
			InstructionHandle endWhile = il.append(InstructionFactory.NOP);
	
			il.append(endExpression, new IFEQ(endWhile));
			
			inWhile = false;
		} else {
			try {
				il.delete(startExpression, endExpression);
			} catch (TargetLostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void visit(PrintStatement n) {
	    il.append(iFact.createFieldAccess("java.lang.System", "out", new ObjectType("java.io.PrintStream"), Constants.GETSTATIC));
		super.visit(n);
		il.append(iFact.createInvoke("java.io.PrintStream", "println",
				Type.VOID, new Type[] { Type.INT }, Constants.INVOKEVIRTUAL));
	};

	public void visit(BinaryOperatorToken o) {
		tokenStack.push(o);
		if (o.getSymbol().equals("+"))
			il.append(InstructionFactory.IADD);
		if (o.getSymbol().equals("-"))
			il.append(InstructionFactory.ISUB);
		if (o.getSymbol().equals("*"))
			il.append(InstructionFactory.IMUL);
		if (o.getSymbol().equals("&&"))
			il.append(InstructionFactory.IAND);
		if (o.getSymbol().equals(">")) {
			il.append(InstructionFactory.ISUB);
			InstructionHandle isFalse = il.append(new PUSH(cp, false));
			InstructionHandle isTrue = il.append(new PUSH(cp, true));
			InstructionHandle jump = il.append(new NOP());
			il.insert(isFalse, new IFGT(isTrue));
			il.insert(isTrue, new GOTO(jump));
		}
		if (o.getSymbol().equals("<")) {
			il.append(InstructionFactory.SWAP);
			il.append(InstructionFactory.ISUB);
			InstructionHandle isFalse = il.append(new PUSH(cp, false));
			InstructionHandle isTrue = il.append(new PUSH(cp, true));
			InstructionHandle jump = il.append(new NOP());
			il.insert(isFalse, new IFGT(isTrue));
			il.insert(isTrue, new GOTO(jump));
		}
		if (o.getSymbol().equals("==")) {
			il.append(InstructionFactory.ISUB);
			InstructionHandle isFalse = il.append(new PUSH(cp, false));
			InstructionHandle isTrue = il.append(new PUSH(cp, true));
			InstructionHandle jump = il.append(new NOP());
			il.insert(isFalse, new IFEQ(isTrue));
			il.insert(isTrue, new GOTO(jump));
		}
	}

	public void visit(LiteralToken t) {
		tokenStack.push(t);
		String value = t.getValue();
		IType type = t.getType();
		if (type == IntType.INSTANCE) {
			int i = Integer.valueOf(value);
			il.append(new PUSH(cp, i));
		} else if (type == BooleanType.INSTANCE) {
			boolean b = Boolean.valueOf(value);
			il.append(new PUSH(cp, b));
		} else if (value.equals("this")) {
			il.append(new ALOAD(0));
		}
	}

	public void visit(UnaryOperatorToken u) {
		tokenStack.push(u);
		if (u.getSymbol().equals("!")) {
			InstructionHandle ifNe = il.append(new PUSH(cp, true));
			InstructionHandle ifNotNe = il.append(new PUSH(cp, false));
			InstructionHandle nop = il.append(new NOP());
			il.insert(ifNotNe, new GOTO(nop));
			il.insert(ifNe, new IFNE(ifNotNe));
		}
	}

	public void visit(ObjectInstantiationToken t) {
		tokenStack.push(t);
		NodeChoice nch = t.getNode().f1.f0;
		switch (nch.which) {
		case 0:
			// %0 ClassConstructorCall()
			nch.choice.accept(this);
			break;
		case 1:
			// %1 IntArrayConstructorCall()
			il.append(new NEWARRAY(Type.INT));
			break;
		default:
			// should not occur !!!
			break;
		}
	}

	public void visit(ArrayLengthAccessToken t) {
		tokenStack.push(t);
		il.append(new ARRAYLENGTH());
	}

	public void visit(ArrayAccessToken arrayAccessToken) {
		tokenStack.push(arrayAccessToken);
		il.append(InstructionFactory.IALOAD);
	}

	public void visit(VariableToken variableToken) {
		tokenStack.push(variableToken);
		String name = variableToken.getName();
		IType type = variableToken.getType();
		Variable v = this.currentScope.lookupVariable(name);
//		v.increaseUseCount();

		if (registerMap.containsKey(name)) {
			int reg = registerMap.get(name);
			if (type.isPrimitive()) {
				il.append(new ILOAD(reg));
			} else {
				il.append(new ALOAD(reg));
			}
		} else {
			String fieldType = type.toBcelType().getSignature();
			int i = cp.addFieldref(cg.getClassName(), name, fieldType);
			il.append(new ALOAD(0));
			il.append(new GETFIELD(i));
		}
	}

	public void visit(MethodCallToken methodCallToken) {
		tokenStack.push(methodCallToken);
		String methodName = methodCallToken.getNode().f1.f0.tokenImage;
		Method m = this.methodMap.get(methodName);
		if(m==null)
			m = this.currentScope.lookupMethod(methodName);
		List<Variable> methodParam = new ArrayList<Variable>();
		String className = m.getClassName();
		if(className==null)
			className = cg.getClassName();
		il.append(iFact.createInvoke(className, m.getName(), m
				.getReturnType().toBcelType(), m.getParametersBCEL(),
				Constants.INVOKEVIRTUAL));
	}

	public ClassGen getCG() {
		// TODO Auto-generated method stub
		return cg;
	}

	public MethodGen getMG() {
		// TODO Auto-generated method stub
		return mg;
	}
}
