#!/bin/bash -e

rm -rf bin
mkdir bin

javac -d bin src/project/tm/TMException.java   
javac -d bin -cp bin src/project/tm/MalformedTransitionException.java
javac -d bin src/project/tm/Tape.java
javac -d bin -cp bin src/project/tm/Transition.java
javac -d bin -cp bin src/project/tm/TuringMachine.java
javac -d bin -cp bin src/test/TestDriver.java

java -cp bin test.TestDriver

rm -rf bin