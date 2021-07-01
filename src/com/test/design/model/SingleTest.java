package com.test.design.model;

/**
 * @author zhouj
 * @since 2017/4/6
 */
public class SingleTest {

//    private static SingleTest singleTest;
    private static SingleTest singleTest ;

    static {
        singleTest = new SingleTest();
    }
    private SingleTest(){

    }

    public static SingleTest getSingleTest(){
//        if(singleTest==null){
//            singleTest=new SingleTest();
//        }
        return singleTest;
//        return SingleTestInner.singleTest;
    }

    public static void main(String[] args) {
        SingleTest singleTest = SingleTest.getSingleTest();
        System.out.println(singleTest);
    }

    private static class SingleTestInner{
        private static final SingleTest singleTest = new SingleTest();
    }

}
