package com.test.ex;

/**
 * @author zhouj
 * @since 2021-04-26
 */
public class ExceptionTest extends RuntimeException {


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.say();
    }

    public static class Test {
        private void say() {
            throw new ExceptionTest2();
        }
    }

    public static class ExceptionTest2 extends RuntimeException{

    }
}
