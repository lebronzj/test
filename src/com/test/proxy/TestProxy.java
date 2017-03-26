package com.test.proxy;

/**
 * Created by zhouj on 16/4/6.
 */
public class TestProxy {

    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
        bookProxy.addBook();

        int i;
        if((i=3)>5){
            System.out.println("测试");
        }
        System.out.println((i));

    }

}