#!/bin/bash
JAVA_HOME=$(dirname "$(dirname "$(readlink -f "$(which java)")")")
./bin/MandelbrotSet -Djava.home="$JAVA_HOME"