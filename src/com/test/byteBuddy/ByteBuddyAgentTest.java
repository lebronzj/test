package com.test.byteBuddy;

import lombok.extern.log4j.Log4j;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * @author zhouj
 * @since 2020-12-08
 */
public class ByteBuddyAgentTest {


    public static void main(String[] args) {
        Instrumentation instance = ByteBuddyAgent.install();
        new AgentBuilder.Default().type(ElementMatchers.any()).transform((builder, typeDescription, classLoader, module) -> {
            System.out.println("module:" + module.getActualName());
            return builder.method(ElementMatchers.any()).intercept(MethodDelegation.to(Log4j.class));
        }).with(new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {

            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }
        }).installOn(instance);
//        try {
//            instance.addTransformer(new ClassFileTransformer() {
//                @Override
//                public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//                    ClassPool classPool = ClassPool.getDefault();
//                    CtClass ctClass = null;
//                    try {
//                        if(className.contains("Service")){
//                            classPool.insertClassPath(new LoaderClassPath(loader));
//                            ctClass = classPool.get(className.replace("/", "."));
//                            if (ctClass.getAnnotation(lombok.extern.slf4j.Slf4j.class) != null) {
//                                CtConstructor ctConstructor = ctClass.getConstructors()[0];
//                                ctConstructor.insertBefore("log.info(\"" + className + "创建\");");
//                            }
//                        }
//                    } catch (NotFoundException e) {
//                        e.printStackTrace();
//                    } catch (CannotCompileException e) {
//                        e.printStackTrace();
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    if (ctClass != null) {
//                        try {
//                            return ctClass.toBytecode();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (CannotCompileException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    return classfileBuffer;
//                }
//            });
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}
