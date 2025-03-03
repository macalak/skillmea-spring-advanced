package ite.librarymaster.service;

import ite.librarymaster.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NationalLibraryRegistryImpl implements NationalLibraryRegistry {
    private final Logger LOG = LoggerFactory.getLogger(NationalLibraryRegistryImpl.class);

    @Override
    public void register(Book book) {
        LOG.info("Book {} registered into national registry.", book);
    }
}
