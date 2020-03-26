package cn.itcast.day11.demo04;

/*
局部内部类，如果希望访问所在方法的局部变量，那么这个局部变量必须是【有效final的】。

备注：从Java 8+开始，只要局部变量事实不变，那么final关键字可以省略。

原因：
1. new出来的对象在堆内存当中。
2. 局部变量是跟着方法走的，在栈内存当中。
3. 方法运行结束之后，立刻出栈，局部变量就会立刻消失。
4. 但是new出来的对象会在堆当中持续存在，直到垃圾回收消失。
 */
public class MyOuter {

    public void methodOuter() {
        int num = 10; // 所在方法的局部变量

        class MyInner {
            public void methodInner() {
                System.out.println(num);
            }
        }
    }

}
