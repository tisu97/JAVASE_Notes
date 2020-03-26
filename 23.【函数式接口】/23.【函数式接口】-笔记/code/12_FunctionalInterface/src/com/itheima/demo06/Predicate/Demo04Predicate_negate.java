package com.itheima.demo06.Predicate;

import java.util.function.Predicate;

/*
    需求:判断一个字符串长度是否大于5
        如果字符串的长度大于5,那返回false
        如果字符串的长度不大于5,那么返回true
    所以我们可以使用取反符号!对判断的结果进行取反

    Predicate接口中有一个方法negate,也表示取反的意思
    default Predicate<T> negate() {
        return (t) -> !test(t);
    }
 */
public class Demo04Predicate_negate {
    /*
           定义一个方法,方法的参数,传递一个字符串
           使用Predicate接口判断字符串的长度是否大于5
    */
    public static boolean checkString(String s, Predicate<String> pre){
        //return !pre.test(s);
        return  pre.negate().test(s);//等效于return !pre.test(s);
    }

    public static void main(String[] args) {
        //定义一个字符串
        String s = "abc";
        //调用checkString方法,参数传递字符串和Lambda表达式
        boolean b = checkString(s,(String str)->{
            //判断字符串的长度是否大于5,并返回结果
            return str.length()>5;
        });
        System.out.println(b);
    }
}
