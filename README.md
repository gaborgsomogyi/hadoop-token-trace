hadoop-token-trace
==================

### Introduction
Example application that stores a delegation token in a Hadoop FS which can be traced with trace-agent.

### Build the app
To build, you need Java8, git and maven on the box.
Do a git clone of this repo and then run:
```
cd hadoop-token-trace
mvn clean package
```

### Run the app
```
java -jar target/hadoop-token-trace-1.0-SNAPSHOT-jar-with-dependencies.jar
```
