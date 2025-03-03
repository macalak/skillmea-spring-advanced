package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.event.*;
import ite.librarymaster.model.Book;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibraryAdminServiceImpl implements LibraryAdminService{
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;

	private final DomainEventPublisher domainEventPublisher;
	private final ModelMapper modelMapper;

	public LibraryAdminServiceImpl(DomainEventPublisher domainEventPublisher, ModelMapper modelMapper) {
		this.domainEventPublisher = domainEventPublisher;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional(rollbackFor = {RuntimeException.class})
	public void addBook(Book book) {
		bookRepository.save(book);
		domainEventPublisher.fireEvent(new GenericEvent<BookCreatedEvent>(modelMapper.map(book, BookCreatedEvent.class)));
	}

	@Override
	@Transactional
	public void addBooks(List<Book> books) {
		for(Book book : books){
			bookRepository.save(book);
			domainEventPublisher.fireEvent(new GenericEvent<BookCreatedEvent>(modelMapper.map(book, BookCreatedEvent.class)));
		}
	}

	@Override
	@Transactional(rollbackFor = LibraryException.class)
	public void deleteBook(Book book) throws LibraryException {
		bookRepository.delete(book);
		domainEventPublisher.fireEvent(new GenericEvent<BookDeletedEvent>(modelMapper.map(book, BookDeletedEvent.class)));
		throw new LibraryException("Something wrong");
	}

}
