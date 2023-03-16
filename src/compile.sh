#!/usr/bin/env bash

if [ -d classes ]; then
    rm -rf classes;
fi
mkdir classes

javac -cp /Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/lib/tools.jar com/test/annotation/Getter* -d classes/

javac -cp classes -d classes -processor com.test.annotationProcesser.GetterProcessor com/test/annotation/App.java

javap -p classes/com/test/annotation/App.class

java com.test.annotationProcesser.App