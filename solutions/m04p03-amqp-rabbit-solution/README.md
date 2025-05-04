# AMQP concept in RabbitMQ

- Exchanges - An exchange is responsible for routing messages to one or more queues based on routing rules. Producers send messages to exchanges, not directly to queues. 
  - Direct Exchange: Routes messages to queues with a matching routing key.
  - Fanout Exchange: Broadcasts messages to all queues bound to it, ignoring routing keys.
  - Topic Exchange: Routes messages based on pattern matching between the routing key and a binding key.
  - Headers Exchange: Routes messages based on message header attributes instead of routing keys.

- Queues - Queues are where messages are stored until they are consumed by a consumer.
- Bindings - Bindings link exchanges to queues. A binding defines the routing rules that determine how messages flow from an exchange to a queue.
 
## Workflow
- A producer sends a message to an exchange.
- The exchange routes the message to one or more queues based on the binding rules.
- Consumers retrieve messages from the queues for processing.


# Spring Integration AMQP integration
Spring Integration provides Channel Adapters for receiving and sending messages using the 
Advanced Message Queuing Protocol (AMQP). The following adapters are available:

* Inbound Channel Adapter
* Inbound Gateway
* Outbound Channel Adapter
* Outbound Gateway
* Async Outbound Gateway