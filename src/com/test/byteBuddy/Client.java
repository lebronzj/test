package com.test.byteBuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.SuperMethod;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhouj
 * @since 2020-11-25
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Singable proxy = createByteBuddyDynamicProxy();
        proxy.sing();
        System.out.println(proxy.toString());
    }

    private static Singable createByteBuddyDynamicProxy() throws Exception {
        return (Singable) new ByteBuddy()
                //继承父类实现代理
//                .subclass(Singer.class)
                .subclass(Object.class)
                .implement(Singable.class)
                .method(ElementMatchers.named("sing"))
                //实现InvocationHandler接口实现代理
                .intercept(InvocationHandlerAdapter.of(new SingerInvocationHandler(new Singer())))
                //MethodDelegation 实现代理
//                .intercept(MethodDelegation.to(new SingerAgentInterceptor(new Singer())))
                //继承父类实现代理
//                .intercept(MethodDelegation.to(new SingerAgentInterceptorSuper()))
                .make()
                .load(Client.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

    /**
     * MethodDelegation 实现代理
     */
    public static class SingerAgentInterceptor {

        private Object delegate;

        public SingerAgentInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        /**
         * @param proxy 代理对象
         * @param method 代理方法
         * @param args 方法参数
         */
        public Object interceptor(@This Object proxy, @Origin Method method,
                                  @AllArguments Object[] args) throws Exception {
            System.out.println("bytebuddy delegate proxy before sing ");
            Object ret = method.invoke(delegate, args);
            System.out.println("bytebuddy delegate proxy after sing ");
            return ret;
        }
    }


    /**
     * 实现InvocationHandler 实现代理
     */
    public static class SingerInvocationHandler implements InvocationHandler {

        private Object delegate;

        public SingerInvocationHandler(Object delegate) {
            this.delegate = delegate;
        }

        /**
         * 动态代理调用方法
         *
         * @param proxy 生成的代理对象
         * @param method 代理的方法
         * @param args 方法参数
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("bytebuddy proxy before sing ");
            Object ret = method.invoke(delegate, args);
            System.out.println("bytebuddy proxy after sing ");
            return ret;
        }

    }

    /**
     * 继承父类实现代理
     */
    public static class SingerAgentInterceptorSuper {

        public Object interceptor(@This Object proxy, @Origin Method method,
                                  @SuperMethod Method superMethod,
                                  @AllArguments Object[] args) throws Exception {
            System.out.println("bytebuddy delegate proxy2 before sing ");
            Object ret = superMethod.invoke(proxy, args);
            System.out.println("bytebuddy delegate proxy2 after sing ");
            return ret;
        }
    }

}
