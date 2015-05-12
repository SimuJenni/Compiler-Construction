package ch.unibe.iam.scg.minijava;

import ch.unibe.iam.scg.javacc.ParseException;

public interface MiniJava
{

    public Object Goal() throws ParseException;
    
    public Object MainClass() throws ParseException;

    public Object ClassDeclaration() throws ParseException;

    public Object VarDeclaration() throws ParseException;

    public Object MethodDeclaration() throws ParseException;

    public Object Type() throws ParseException;

    public Object Statement() throws ParseException;

    public Object Expression() throws ParseException;

    public Object Identifier() throws ParseException;
}
