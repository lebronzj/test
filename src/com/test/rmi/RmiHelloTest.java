package com.test.rmi;

import java.rmi.Remote;

/**
 * @author zhouj
 * @since 2017/3/19
 */
public interface RmiHelloTest extends Remote{
    void say();

}
