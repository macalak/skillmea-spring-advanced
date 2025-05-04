# Introduction

The JMS (Java Messaging Service) is the standard API that allows applications to send and receive messages asynchronously. 

JMS architecture consists of the following:

* JMS provider: A messaging system that handles the routing and delivery of messages
* JMS client: Any Java application or Java EE component that uses the JMS API either to consume or produce a JMS message
* JMS consumer: A JMS client application that consumes JMS messages
* JMS producer: A JMS client that generates the message
*  JMS message: A message consisting of a header, properties, and a body
    * Header: identifies the message
    * Properties: attributes specific to the application and provider
    * Body: content of the message

## Message Types

|Message| Type Description|
|-----|----|
|_ByteMessage_| Consists of a series of bytes|
|_MapMessage_ |Consists of a set of name/value pairs|
|_ObjectMessage_| Consists of a serialized Java object|
|_StreamMessage_| Consists of a sequence of primitive data types|
|_TextMessage_| Consists of strings|
