package ite.librarymaster.integration;

import ite.librarymaster.event.BookCreatedEvent;

public interface EuropeanLibraryRegistryClient {
    void registerBookToRemoteRegistry(BookCreatedEvent bookCreatedEvent);
}
