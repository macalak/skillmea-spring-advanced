# Application events
The event publishing is provided by ApplicationContext. Spring’s eventing mechanism is designed for simple communication 
between Spring beans within the same application context. It is implementation of the Observer pattern.
Spring handles either custom event classes which extends the ApplicationEvent, or any arbitrary classes. 

# Build-in events  
Spring framework is able to publish build-in events as:

* ContextRefreshedEvent
* ContextStartedEvent
* ContextStoppedEvent
* ContextClosedEvent
* ApplicationStartedEvent (SpringBoot) 
* ApplicationReadyEvent (SpringBoot)
* ApplicationFailedEvent (SpringBoot) 
* RequestHandledEvent (web app)
* ServletRequestHandledEvent (servlet based web app)

## Use Cases

* ContextRefreshedEvent:
  * Initializing Data
  * Starting Services
* ContextClosedEvent:
  * Cleanup Resources
  * Stopping Services
* ContextStoppedEvent:
  * Pause Processes
  * Save State
* ContextStartedEvent:
  * Resume Processes
  * Trigger Actions
* ApplicationStartedEvent:
  * Early Initialization
  * Feature Flag Setup
* ApplicationReadyEvent:
  * Post-Startup Actions
  * Health Checks
* ApplicationFailedEvent:
  * Error Reporting
  * Automatic Recovery
* RequestHandledEvent
  * Logging request details
  * Auditing
  * Monitoring
  * Post request handling
  
# Tx bound events
The *@TransactionalEventListener* annotation, which is an extension of @EventListener, 
that allows binding the listener of an event to a phase of the transaction.





