package ite.librarymaster.event;

public interface DomainEventPublisher {
    void fireEvent(GenericEvent payload);
}
