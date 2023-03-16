package com.test.compile;


import com.sun.tools.javac.api.JavacTool;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * @author zhouj
 * @since 2022-04-03
 */
public class JavaCompileTests {

    public static void main(String[] args) {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        javaCompiler.run(null,null,null);

        JavacTool javacTool = JavacTool.create();
    }
}
