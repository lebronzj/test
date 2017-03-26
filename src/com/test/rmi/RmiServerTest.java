package com.test.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author zhouj
 * @since 2017/3/19
 */
public class RmiServerTest extends UnicastRemoteObject {
    protected RmiServerTest() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Remote rmiHelloTest = new RmiHelloTestImpl();
            Naming.rebind("hello",rmiHelloTest);
            Thread.sleep(30000);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
