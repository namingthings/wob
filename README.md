Synopsis
========

*WOB = Wall of (Builds|Blame|Bullshit|Beauty|Bread)* is a Java 8 based backend for [teamwall](https://github.com/40bits/teamwall)

Getting started
===============

Build an executable jar with:

```
mvn clean package
```

And then run it, like:

```
java -jar target/wob-X.X-SNAPSHOT-jar-with-dependencies.jar
```

On first start, a `.teamwall` folder will be created in the user home with some defaults, see the [teamwall](https://github.com/40bits/teamwall) documentation on how to add some instruments to `.teamwall/teamwall.json`

Troubleshooting
===============

When accessing a WOB resource which queries an HTTPS service, (e.g. one that queries JIRA for a ticket filter; "url":"/jira-statistics?filters=filter-666"), and you get:

```
java.security.InvalidAlgorithmParameterException: the trustAnchors parameter must be non-empty
```

it may help to install ca certificates. Under Debian this would be the command of choice:

```
apt-get install ca-cert
```

LICENSE
=======

MIT, see [LICENSE](LICENSE)
