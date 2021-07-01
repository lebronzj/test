package com.test.proxy;

/**
 * Created by zhouj on 16/4/6.
 */
public class BookFacadeImpl implements BookFacade {

    public void addBook() {
        System.out.println("增加图书方法。。。");
    }

    @Override
    public void deleteBook() {
        System.out.println("删除图书方法...");
    }

}
