

public class test {
              int bar;
              public int bar() { bar = 321; return bar; }
              public int baz() { int tmp; tmp = this.bar(); return bar; }
}
