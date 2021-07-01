package com.test.instrumentation;

/**
 * @author zhouj
 * @since 2020/1/16
 */
public class SizeOfObject extends SizeOf {

    @Override
    protected Object newInstance() {
        return new Object();
    }

    public static void main(String[] args) throws Exception {
        SizeOf sizeOf = new SizeOfObject();
        System.out.println("所占内存：" + sizeOf.size() + "字节");
    }
}
