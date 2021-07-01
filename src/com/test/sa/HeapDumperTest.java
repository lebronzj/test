package com.test.sa;

import lombok.SneakyThrows;
import sun.jvm.hotspot.HotSpotAgent;
import sun.jvm.hotspot.debugger.Debugger;
import sun.jvm.hotspot.tools.HeapDumper;
import sun.jvm.hotspot.tools.HeapSummary;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhouj
 * @since 2020-12-04
 */
public class HeapDumperTest {

    @SneakyThrows
    public static void main(String[] args) {
        String[] strings = new String[args.length + 1];
        System.arraycopy(args, 0, strings, 0, args.length);
        strings[args.length] = "-h";
        for(String path:System.getProperty("sun.boot.class.path").split(":")){
            System.out.println("boot:"+path);
        }
        for(String path:System.getProperty("java.ext.dirs").split(":")){
            System.out.println("ext:"+path);
        }
        for(String path:System.getProperty("java.class.path").split(":")){
            System.out.println("class:"+path);
        }
        HeapSummary.main(strings);
        System.out.println(HeapDumperTest.class.getClassLoader());
        HotSpotAgent hotSpotAgent = new HotSpotAgent();
        hotSpotAgent.attach(58764);
        Debugger debugger = hotSpotAgent.getDebugger();
        String file = "heap.bin";
        if (args.length > 2 && args[0].equals("-f")) {
            file = args[1];
            String[] newargs = new String[args.length - 2];
            System.arraycopy(args, 2, newargs, 0, args.length - 2);
            args = newargs;
        }
        HeapDumper dumper = new HeapDumper(file);
        System.out.println(ClassLoader.getSystemClassLoader());
        Class c = ClassLoader.getSystemClassLoader().loadClass("sun.jvm.hotspot.tools.HeapDumper");
        Field field = c.getDeclaredField("dumpFile");
        field.setAccessible(true);
        field.set(dumper, file);
        Method method = c.getDeclaredMethod("execute");
        method.setAccessible(true);
        method.invoke(dumper, args);

//        dumper.execute(args);
    }
}
