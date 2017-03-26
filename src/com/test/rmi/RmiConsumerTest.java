package com.test.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author zhouj
 * @since 2017/3/19
 */
public class RmiConsumerTest {
    public static void main(String[] args) {
        try {
            String uri = "rmi://127.0.0.1/hello";
//            String uri = "hello";
            RmiHelloTest rmiHelloTest = (RmiHelloTest)Naming.lookup(uri);
            rmiHelloTest.say();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
