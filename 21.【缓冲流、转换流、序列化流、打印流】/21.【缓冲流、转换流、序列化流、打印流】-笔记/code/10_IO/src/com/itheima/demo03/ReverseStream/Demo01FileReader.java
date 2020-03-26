package com.itheima.demo03.ReverseStream;

import java.io.FileReader;
import java.io.IOException;

/*
    FileReader可以读取IDE默认编码格式(UTF-8)的文件
    FileReader读取系统默认编码(中文GBK)会产生乱码���
 */
public class Demo01FileReader {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("10_IO\\我是GBK格式的文本.txt");
        int len = 0;
        while((len = fr.read())!=-1){
            System.out.print((char)len);
        }
        fr.close();
    }
}
