#!/bin/bash
javac -d . MandelbrotSet.java
echo "Main-Class: mandelbrot.MandelbrotSet" > manifest.txt
echo "" >> manifest.txt
jar cfm MandelbrotSet.jar manifest.txt mandelbrot/*.class

java -agentlib:native-image-agent=config-output-dir=META-INF/native-image -jar MandelbrotSet.jar "genReachabilityMetadata"
mkdir bin
native-image -Djava.awt.headless=false -H:+UnlockExperimentalVMOptions -H:+ForeignAPISupport -H:ConfigurationFileDirectories=META-INF/native-image -jar MandelbrotSet.jar -H:Name=bin/MandelbrotSet
