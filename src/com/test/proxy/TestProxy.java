package com.test.proxy;

/**
 * Created by zhouj on 16/4/6.
 */
public class TestProxy {

    /**
     * Proxy动态代理理解  动态代理是利用Proxy类生成一个实现BookFacade接口的Class对象及其实例,
     * 实例对应的方法实现就是调用InvocationHandler的invoke方法
     * @param args
     */
    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
        bookProxy.addBook();
        bookProxy.deleteBook();
        System.out.println(bookProxy.getClass());
        System.out.println(bookProxy.getClass().getClassLoader());
        Class[] list = bookProxy.getClass().getInterfaces();
        for(Class c:list){
            System.out.println(c.getName());
        }

        bookProxy.deleteBook();

//        int i;
//        if((i=3)>5){
//            System.out.println("测试");
//        }
//        System.out.println((i));

    }

}