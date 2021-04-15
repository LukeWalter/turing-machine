#!/bin/bash -e

mkdir bin

javac -d bin src/project/tm/Tape.java
javac -d bin -cp bin src/test/TestDriver.java

java -cp bin test.TestDriver

rm -rf bin