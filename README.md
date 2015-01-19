Synopsis
========

*WOB = Wall of (Builds|Blame|Bullshit|Beauty|Bread)* is a Java based backend for https://github.com/40bits/teamwall

Getting started
===============

After

```
#!bash
mvn clean package
```

You can start the WOB with

```
#!bash
    java -jar target/wob-X.X-SNAPSHOT-jar-with-dependencies.jar
```

On first start, a `.teamwall` folder will be created in the user home with some defaults, see the [teamwall](https://github.com/40bits/teamwall) documentation on how to add some instruments to `.teamwall/teamwall.json`

LICENSE
=======

MIT, see [LICENSE](LICENSE)