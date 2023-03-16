package com.test.attach;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import lombok.SneakyThrows;
import sun.jvm.hotspot.runtime.VM;
import sun.tools.attach.HotSpotVirtualMachine;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhouj
 * @since 2020-06-15
 */
public class AttachTest {

    private volatile int x = 0;

    @SneakyThrows
    public static void main(String[] args) {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            System.out.println("pid:" + vmd.id() + ":" + vmd.displayName());
            if (vmd.displayName().contains("IDEApplication")) {
                HotSpotVirtualMachine virtualMachine = (HotSpotVirtualMachine) VirtualMachine.attach(vmd.id());
                try {
                    virtualMachine.loadAgent("/Users/zhouj/zuzuche/MyAttach/target/MyAttach.jar", "cxs");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    virtualMachine.detach();
                }
            }
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        AttachTest attachTest = new AttachTest();
        Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    attachTest.x = 1;
                }
                countDownLatch.countDown();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {
                    attachTest.x = 3;
                }
                countDownLatch.countDown();
            }
        });
        thread1.start();
        thread2.start();
        countDownLatch.await();

        System.out.println(attachTest.x);

    }
}
