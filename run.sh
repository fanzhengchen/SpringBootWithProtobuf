#!/usr/bin/env bash

mvn clean package

bootPath=~/.m2/repository/org/mortbay/jetty/alpn/alpn-boot/8.1.9.v20160720/alpn-boot-8.1.9.v20160720.jar
java -Xbootclasspath/p:${bootPath} -jar ./target/protocol-buff-demo-0.0.1-SNAPSHOT.jar
