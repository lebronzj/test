package com.test.spi;

import java.util.ServiceLoader;

/**
 * @author zhouj
 * @since 2020-11-09
 */
public class Test {

    public static void main(String[] args) {

        ServiceLoader<IService> serviceLoader  = ServiceLoader.load(IService.class);
        for(IService service : serviceLoader) {
            System.out.println(service.getScheme()+"="+service.sayHello());
        }
    }
}
