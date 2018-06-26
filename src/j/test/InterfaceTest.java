package j.test;

/**
 * Created by j on 2017/8/3.
 */
public interface InterfaceTest {
    // 默认方法修饰符为 public，abstract；
    public void method1();
    //private void method2(); // not allow
    //protected void method3(); // not allow
    static void method4() {};
    abstract void method5();

    // 默认成员修饰符为 final， static；
    final int a = 0;
    static int b = 0;
    int c = 0;
}
