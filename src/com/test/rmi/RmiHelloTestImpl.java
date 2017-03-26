package com.test.rmi;

import java.io.Serializable;

/**
 * @author zhouj
 * @since 2017/3/19
 */
public class RmiHelloTestImpl implements RmiHelloTest,Serializable{
    @Override
    public void say() {
        System.out.println("hello");
    }
}
