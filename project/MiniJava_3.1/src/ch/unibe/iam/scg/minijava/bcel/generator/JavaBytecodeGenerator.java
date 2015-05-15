package ch.unibe.iam.scg.minijava.bcel.generator;


import java.io.IOException;
import java.util.Map;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;

import ch.unibe.iam.scg.javacc.syntaxtree.INode;
import ch.unibe.iam.scg.minijava.typechecker.TypeChecker;
import ch.unibe.iam.scg.minijava.typechecker.scope.IScope;

/**
 * Change at will! 
 * (http://funnyasduck.net/wp-content/uploads/2013/01/funny-star-trek-picard-tv-fire-will-pics.jpg)
 * 
 * @author kursjan
 *
 */public class JavaBytecodeGenerator
{
    private InstructionList il = new InstructionList();
    private ClassGen cg = createTemporaryClass();
    private InstructionFactory iFact = new InstructionFactory(cg);
    private MethodGen mg;

    private int instructionCount;
    
    public ClassGen getClassGen()
    {
        return cg;
    }
    
    public int getInstructionCount() {
		return instructionCount;
	}
    
    public InstructionList getInstructionList() {
		return il;
	}
    
    public Method getMethod()
    {
        return mg.getMethod();
    }
    
    /**
     * Please, use this method while adding method into the class,
     * 	to keep track of instruction count.
     * 
     * If you need to change this, let me know...
     */
    public void addMethod(ClassGen cg, MethodGen mg) {
    	instructionCount += mg.getInstructionList().getLength();
    	cg.addMethod(mg.getMethod());
    }
    
    public static ClassGen createTemporaryClass()
    {
        String className = "BcelGenerated";
        ClassGen cg = new ClassGen(className, "java.lang.Object", className, Constants.ACC_PUBLIC, new String[0]);
        cg.addEmptyConstructor(Constants.ACC_PUBLIC);
        return cg;
    }
    
    public void generate(Object node)
    {
    	INode n=(INode) node;
    	CodeGeneratorVisitor cgv = new CodeGeneratorVisitor(this,cg,mg,il,iFact);
    	TypeChecker tc = new TypeChecker();
    	tc.check(n);
    	Map<INode, IScope> scopeMap = tc.getScopeMap();
    	cgv.generate(n, scopeMap);
    	this.cg=cgv.getCG();
    	this.mg=cgv.getMG();
    }
    

}
