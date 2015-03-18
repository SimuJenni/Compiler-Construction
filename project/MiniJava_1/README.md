# Project 1

## Pretty Printer

### Setup

Install [JavaCC Eclipse Plugin](http://eclipse-javacc.sourceforge.net/).

### MiniJava.jj

Genereated by processing `grammar.txt` with regular expression search & replace:

1. `s/\s*::=\s*/ : {} {/g`
2. `s/^(\w)/void $1/g`
3. `s/\n(void|$)/}$0/g`
4. `s/(\{| |\t)[A-Z]\w+/$0()/g`

### Resources

[Grammar](http://www.cambridge.org/resources/052182060X/MCIIJ2e/grammar.htm)
[Requirements](http://scg.unibe.ch/download/lectures/cc-exercises-2015/CCProject1.pdf)
[JavaCC Documentation](https://javacc.java.net/)
[Java Tree Builder Documentation](http://compilers.cs.ucla.edu/jtb/)
