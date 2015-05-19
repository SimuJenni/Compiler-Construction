package ch.unibe.iam.scg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.unibe.iam.scg.minijava.SyntaxTest;
import ch.unibe.iam.scg.minijava.bcel.generator.JavaBytecodeGeneratorTest;
import ch.unibe.iam.scg.minijava.bcel.generator.SanityTest;
import ch.unibe.iam.scg.minijava.bcel.generator.optimization.BytecodesSizeTest;
import ch.unibe.iam.scg.minijava.prettyprint.PrettyPrintTest;
import ch.unibe.iam.scg.minijava.typechecker.TypeCheckerTest;

@RunWith(Suite.class)
@SuiteClasses(
{ 	SyntaxTest.class, 
//	TokenizingTest.class,
//	MiniJavaJTBTest.class, 
//  JTBPrettyPrinterTest.class, 
//  RPNVisitorTest.class, 
    PrettyPrintTest.class,
    TypeCheckerTest.class, 
    JavaBytecodeGeneratorTest.class,
    SanityTest.class,
    BytecodesSizeTest.class})
public class AllTests
{

}
