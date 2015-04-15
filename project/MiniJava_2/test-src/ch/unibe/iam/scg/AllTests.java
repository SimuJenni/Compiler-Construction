package ch.unibe.iam.scg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.unibe.iam.scg.minijava.SyntaxTest;
import ch.unibe.iam.scg.minijava.prettyprint.PrettyPrintTest;
import ch.unibe.iam.scg.minijava.typechecker.TypeCheckerTest;

@RunWith(Suite.class)
@SuiteClasses(
{ PrettyPrintTest.class, SyntaxTest.class, TypeCheckerTest.class})
public class AllTests
{

}
