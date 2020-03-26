package cn.itcast.day11.demo04;

/*
如果一个类是定义在一个方法内部的，那么这就是一个局部内部类。
“局部”：只有当前所属的方法才能使用它，出了这个方法外面就不能用了。

定义格式：
修饰符 class 外部类名称 {
    修饰符 返回值类型 外部类方法名称(参数列表) {
        class 局部内部类名称 {
            // ...
        }
    }
}

小节一下类的权限修饰符：
public > protected > (default) > private
定义一个类的时候，权限修饰符规则：
1. 外部类：public / (default)
2. 成员内部类：public / protected / (default) / private
3. 局部内部类：什么都不能写
 */
class Outer {

    public void methodOuter() {
        class Inner { // 局部内部类
            int num = 10;
            public void methodInner() {
                System.out.println(num); // 10
            }
        }

        Inner inner = new Inner();
        inner.methodInner();
    }

}
