package com.test.io;

import java.io.*;
import java.util.Iterator;

/**
 * @author zhouj
 * @since 16/5/9
 */
public class TestFile {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zhouj/Downloads/从前有座灵剑山.txt");
        System.out.println(file.getName());
        System.out.println(file.length());
        System.out.println(file.canRead());
        System.out.println(file.getPath());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getParentFile());
        System.out.println(file.getTotalSpace());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Iterator iterator = bufferedReader.lines().iterator();
        System.out.println(bufferedReader.lines().count());
        while (iterator.hasNext()){
            String str = bufferedReader.readLine();
            System.out.println(str);
        }
        bufferedReader.close();

        File file1 = new File("/Users/zhouj/Downloads/zhoujie.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1));
        bufferedWriter.write("saafasdzsafafa");
        bufferedWriter.close();

        char[] chars = new char[Integer.parseInt(String.valueOf(file.length()))];
        FileReader fileReader = new FileReader(file);
        fileReader.read(chars);
        for(char ch:chars){
            System.out.println(ch);
        }
        fileReader.close();

        String[] strings = file.list();
        System.out.println(strings);
        for (String string:strings){
            System.out.println(string);
        }
        PrintStream printStream = new PrintStream(file1);
        printStream.print("sggsgsgsgsgsg");
        printStream.close();
    }
}
