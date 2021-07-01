package com.test.virtualMachine;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @author zhouj
 * @since 2020-06-29
 */
public class TestVirtualMachine {
    public static void main(String[] args) throws IOException, AttachNotSupportedException {
        VirtualMachine.list().forEach(virtualMachineDescriptor -> {
            System.out.println(virtualMachineDescriptor.displayName()+":" + virtualMachineDescriptor.id());
            System.out.println(virtualMachineDescriptor.provider().type());
        });
        VirtualMachine virtualMachine = VirtualMachine.attach("");

    }
}
