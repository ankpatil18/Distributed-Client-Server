Readme.txt
---------------


File included in this project:

	ThriftServer:
		1)ErrorCode.java
		2)Result.java
		3)KVStore.java
		4)KVServiceHandler.java
		5)KVServer.java

	ThriftClient:
		1)ErrorCode.java
		2)Result.java
		3)KVStore.java
		4)KVClient.java

Environment:
	OS : Windows 10
	Programming Language: Java

How to run:
	The project contain two folder on ThriftServer(contain Server code) and ThriftClient(contains Client code).
	Snapshot for running both the Server and Client project have been provided under name ClientCommand.txt and ServerCommand.txt
	C:\Program Files\Java\* folder consist of JDK 1.8 and external libararies like libthrift-0.9.3 , slf4j-api-1.7.21, slf4j-simple-1.6.1

	Compiling of both project should be done in similar manner
		1)Get it src folder of each project by using command cd and compile and run project from src folder
		2)The project is in package kvstore
		3)set CLASSPATH=C:\Program Files\Java\*;.
		4)command for compling ThriftServer project :   javac  kvstore\ErrorCode.java kvstore\Result.java kvstore\KVServer.java kvstore\KVServiceHandler.java kvstore\KVStore.java
		5)Running Server project:  java kvstore.KVStore
		6)command for compling ThriftClient project  : javac kvstore\ErrorCode.java kvstore\Result.java kvstore\KVStore.java kvstore\KVClient.java
		7)Running Client project: >java kvstore.KVClient -server localhost:9090 -get number11

Client Operation(KVClient):
	The client have three sub operation

	get:	if key is present it return the value , else return Key Not Found
				java kvstore.KVClient -server localhost:9090 -get number102
	
	set:	if key is not present create the ewn key value pair, if present overrides the value for given key
				java kvstore.KVClient -server localhost:9090 -set number102 102

	del:	if key exist, deletes existing pair, else return Key Not Found
				java kvstore.KVClient -server localhost:9090 -del number102				



	