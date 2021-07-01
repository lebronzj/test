package com.test.instrumentation;


import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * 其中sizeof方法仅仅获取的是当前对象的大小，而该对象的如果存在对其他对象的引用，则不在计算范围以内，而fullsizeof则会计算整体的大小。
 *
 * 将该java文件进行编译，并打成jar包
 * com.java.basic.SizeOfAgent .java
 * jar cvf sizeOfAgent.jar com/java.basic/SizeOfAgent .class
 *
 * 修改META-INF/MANIFEST.MF文件内容
 * Premain-Class: com.java.basic.SizeOfAgent
 * Boot-Class-Path:
 * Can-Redefine-Classes: false
 * 注意：每个冒号后面都有一个空格，且最后一行会有一个换行
 *
 * 将该jar包导入项目
 * 添加启动参数：-javaagent:E:sizeOfAgent.jar
 * 我这边是将该jar包放在e盘，这里填写绝对路径。
 * 这样我们就可以通过调用该类中的sizeOf方法或者fullSizeOf方法即可。
 * @author zhouj
 * @since 2020/1/16
 */
public class SizeOfAgent
{
    private static Instrumentation inst;

    /** initializes agent */
    public static void premain(String agentArgs, Instrumentation instP)
    {
        inst = instP;
    }

    /**
     * Returns object size without member sub-objects.
     * @param o object to get size of
     * @return object size
     */
    public static long sizeOf(Object o)
    {
        if(inst == null)
        {
            throw new IllegalStateException("Can not access instrumentation environment.\n" +
                    "Please check if jar file containing SizeOfAgent class is \n" +
                    "specified in the java's \"-javaagent\" command line argument.");
        }
        return inst.getObjectSize(o);
    }

    /**
     * Calculates full size of object iterating over
     * its hierarchy graph.
     * @param obj object to calculate size of
     * @return object size
     */
    public static long fullSizeOf(Object obj)
    {
        Map<Object, Object> visited = new IdentityHashMap<Object, Object>();
        Stack<Object> stack = new Stack<Object>();

        long result = internalSizeOf(obj, stack, visited);
        while (!stack.isEmpty())
        {
            result += internalSizeOf(stack.pop(), stack, visited);
        }
        visited.clear();
        return result;
    }

    private static boolean skipObject(Object obj, Map<Object, Object> visited)
    {
        if (obj instanceof String) {//这个if是bug，应当去掉--teasp
            // skip interned string
            if (obj == ((String) obj).intern()) {
                return true;
            }
        }
        return (obj == null) || visited.containsKey(obj);
    }

    @SuppressWarnings("rawtypes")
    private static long internalSizeOf(Object obj, Stack<Object> stack, Map<Object, Object> visited)
    {
        if (skipObject(obj, visited))
        {
            return 0;
        }
        visited.put(obj, null);

        long result = 0;
        // get size of object + primitive variables + member pointers
        result += SizeOfAgent.sizeOf(obj);

        // process all array elements
        Class clazz = obj.getClass();
        if (clazz.isArray())
        {
            if(clazz.getName().length() != 2)
            {// skip primitive type array
                int length =  Array.getLength(obj);
                for (int i = 0; i < length; i++)
                {
                    stack.add(Array.get(obj, i));
                }
            }
            return result;
        }

        // process all fields of the object
        while (clazz != null)
        {
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++)
            {
                if (!Modifier.isStatic(fields[i].getModifiers()))
                {
                    if (fields[i].getType().isPrimitive())
                    {
                        continue; // skip primitive fields
                    }
                    else
                    {
                        fields[i].setAccessible(true);
                        try
                        {
                            // objects to be estimated are put to stack
                            Object objectToAdd = fields[i].get(obj);
                            if (objectToAdd != null)
                            {
                                stack.add(objectToAdd);
                            }
                        }
                        catch (IllegalAccessException ex)
                        {
                            assert false;
                        }
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return result;
    }
}
