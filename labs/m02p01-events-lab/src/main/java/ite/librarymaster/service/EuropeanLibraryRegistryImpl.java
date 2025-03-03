package ite.librarymaster.service;

import ite.librarymaster.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EuropeanLibraryRegistryImpl implements EuropeanLibraryRegistry {
    private final Logger LOG = LoggerFactory.getLogger(EuropeanLibraryRegistryImpl.class);

    @Override
    public void register(Book book) {
        LOG.info("Book {} registered into european registry.", book);
    }

}
