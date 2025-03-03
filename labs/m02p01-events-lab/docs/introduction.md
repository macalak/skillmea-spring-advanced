# Introduction

![events](assets/sa_events.png)

## Application events
The event publishing is provided by _ApplicationContext_. Spring’s eventing mechanism is designed for simple communication 
between Spring beans within the same application context. It is implementation of the Observer pattern.
Spring handles either custom event classes which extends the _ApplicationEvent_, or any arbitrary classes. 

## ApplicationEventPublisher
Spring provides the *ApplicationEventPublisher* bean, you can use to publish events. 

```Java
@Component
public class SpringDomainEventPublisher implements DomainEventPublisher {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void fireEvent(GenericEvent payload) {
        applicationEventPublisher.publishEvent(payload);
    }
}
```

## Consuming events
Events are consumed by event consumer, which is Spring bean with methos annotated with the *@EventListener* annotation. The annotation enables to filter events based on types, and even on Event payload. Event listener can be implemented as Beans which implements Spring interface as *ApplicationListener*, or as annotation driven listeners using *EventListener* annotation.

```Java
    @EventListener(BookDeletedEvent.class)
    public void handleBookDeletedEvent(GenericEvent event){
        LOG.info("AUDIT LOG: Book Deleted {}", event.getPayload());
    }
```	

```Java
    @EventListener(condition = "#event.payload.catId == 'LM-000099'")
    public void handleFilteredEvent(GenericEvent event){
        LOG.info("AUDIT LOG FILTERED: {}", event.getPayload());

    }
```


## Build-in events  
Spring framework is able to publish build-in events as:

* ContextRefreshedEvent
* ContextStartedEvent
* ContextStoppedEvent
* ContextClosedEvent 
* RequestHandledEvent
* ServletRequestHandledEvent

```Java
    public class MyContextRefreshedListener 
      implements ApplicationListener<ContextRefreshedEvent> {
      @Override
      public void onApplicationEvent(ContextRefreshedEvent cse) {
        System.out.println("Handling context re-freshed event. ");
      }
    }
```


## Tx bound events
The *@TransactionalEventListener* annotation, which is an extension of _@EventListener_, 
that allows binding the listener of an event to a phase of the transaction.

* AFTER_COMMIT (default) is used to fire the event if the transaction has completed successfully.
* AFTER_ROLLBACK – if the transaction has rolled back
* AFTER_COMPLETION – if the transaction has completed (an alias for AFTER_COMMIT and AFTER_ROLLBACK)
* BEFORE_COMMIT is used to fire the event right before transaction commit.


```Java
    @EventListener
    // Asynchronous Listener bound to Tx
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void log(GenericEvent event) {
        LOG.info("AUDIT LOG: {}", event.getPayload());
    }
```

## Event Multicaster
You can configure how the event listener will handle events by configuring ApplicationEventMulticaster Bean.

```Java
@Bean
public ApplicationEventMulticaster applicationEventMulticaster() {
		SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
		multicaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
		return multicaster;
}
```