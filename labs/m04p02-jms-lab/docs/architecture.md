# Application Architecture

The messaging or event driven architectures supports:

* Loose coupling 
* Flexibility
* Reliable messaging
* System integration

When to use:

* The provider wants the components not to depend on information about other components' interfaces, so components can be easily replaced
* The provider wants the application to run whether or not all components are up and running simultaneously
* The application business model allows a component to send information to another and to continue to operate without receiving an immediate response

## Communication styles

* The point-to-point (P2P) style
    * Based on message Queues
    * One message consumer for each message
* The publish-subscribe (pub-sub) style
    * Based on Topics
    * Broadcast type of applications
    * Multiple recipients consume each message