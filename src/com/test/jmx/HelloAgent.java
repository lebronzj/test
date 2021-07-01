package com.test.jmx;


import sun.management.ManagementFactoryHelper;
import sun.management.VMManagement;

import javax.management.*;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhouj
 * @since 2020-12-03
 */
public class HelloAgent {
    public static void main(String[] args) throws JMException, Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        System.out.println("ps name:"+ManagementFactory.getGarbageCollectorMXBeans().get(0).getName());
        for(String name:ManagementFactory.getGarbageCollectorMXBeans().get(0).getMemoryPoolNames()){
            System.out.println(name);
        }
        System.out.println("ps young count:"+ManagementFactory.getGarbageCollectorMXBeans().get(0).getCollectionCount());
        System.out.println("ps young time:"+ManagementFactory.getGarbageCollectorMXBeans().get(0).getCollectionTime());
        System.out.println("ps name:"+ManagementFactory.getGarbageCollectorMXBeans().get(1).getName());
        for(String name:ManagementFactory.getGarbageCollectorMXBeans().get(1).getMemoryPoolNames()){
            System.out.println(name);
        }
        System.out.println("ps old count:"+ManagementFactory.getGarbageCollectorMXBeans().get(1).getCollectionTime());
        System.out.println("ps old time:"+ManagementFactory.getGarbageCollectorMXBeans().get(1).getCollectionTime());
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        //create mbean and register mbean
        server.registerMBean(new HelloWorld(), helloName);
        System.out.println(jvmPid());
        for (GarbageCollectorMXBean collectorMXBean : ManagementFactory.getGarbageCollectorMXBeans()) {

            System.out.println(collectorMXBean.getName());
        }
        ObjectName objectName = new ObjectName("java.util.logging:type=Logging");
        MBeanInfo me = ManagementFactory.getPlatformMBeanServer().getMBeanInfo(objectName);
        me.getDescriptor().getFields();
        for(MBeanAttributeInfo attributeInfo:me.getAttributes()){
        }
        System.out.println(me);
//        Thread.sleep(60 * 60 * 1000);
    }


    public static final int jvmPid() {
        try {
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            Field jvm = runtime.getClass().getDeclaredField("jvm");
            jvm.setAccessible(true);
            VMManagement mgmt = (VMManagement) jvm.get(runtime);
            Method pidMethod = mgmt.getClass().getDeclaredMethod("getProcessId");
            pidMethod.setAccessible(true);
            int pid = (Integer) pidMethod.invoke(mgmt);
            System.out.println(mgmt.getVmId());
            return pid;
        } catch (Exception e) {
            return -1;
        }
    }

}