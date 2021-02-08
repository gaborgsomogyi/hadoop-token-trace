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

### Trace the app
```
$ cat src/main/resources/actions.txt 
trace_args_with_method_call org.apache.hadoop.fs.FileSystem addDelegationTokens param_index:1,method_to_call:getAllTokens
...
$ java -javaagent:/Users/gaborsomogyi/trace-agent/target/trace-agent-1.0-SNAPSHOT.jar -jar target/hadoop-token-trace-1.0-SNAPSHOT-jar-with-dependencies.jar 
TraceAgent is initializing
TraceAgent tries to install actions: [{actionId='trace_param_call_retval', classMatcher='org.apache.hadoop.fs.FileSystem', methodMatcher='addDelegationTokens', actionArgs='param_index:1,method_to_call:getAllTokens'}]
TraceAgent installed actions successfully
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
log4j:WARN No appenders could be found for logger (org.apache.htrace.core.Tracer).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
Hadoop FS obtained: file:///
TraceAgent (trace_args_with_method_call): public default org.apache.hadoop.security.token.Token[] org.apache.hadoop.security.token.DelegationTokenIssuer.addDelegationTokens(java.lang.String,org.apache.hadoop.security.Credentials) throws java.io.IOException parameter instance with index 1 method call "getAllTokens" returns with 
[Kind: testKind, Service: testService, Ident: 74 65 73 74 49 64 65 6e 74 69 66 69 65 72]
Tokens added: [Kind: testKind, Service: testService, Ident: 74 65 73 74 49 64 65 6e 74 69 66 69 65 72]
```
