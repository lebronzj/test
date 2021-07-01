package com.test.agent;

import javassist.ClassPool;
import javassist.CtClass;
import lombok.SneakyThrows;
import org.apache.xmlbeans.impl.jam.mutable.MClass;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author zhouj
 * @since 2020-10-29
 */
public class ClassFileTest implements ClassFileTransformer {
    @SneakyThrows
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassPool mPool = ClassPool.getDefault();
        CtClass ctClass = mPool.get(className);
        return ctClass.toBytecode();
    }
}
