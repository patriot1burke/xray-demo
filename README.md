# XRay Demo
Build latest quarkus snapshot
```
$ git clone https://github.com/quarkusio/quarkus.git
$ cd quarkus
$ mvn -DskipTests test-compile jar:test-jar install
```

Build this project
```
$ mvn clean install -Dnative
```

Deploy this project (requires sam cli) 
```
sam deploy -t target/sam.native.yaml -g
```