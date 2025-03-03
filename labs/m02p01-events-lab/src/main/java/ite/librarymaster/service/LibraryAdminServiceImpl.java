package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.model.Book;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.modelmapper.ModelMapper;

@Service
public class LibraryAdminServiceImpl implements LibraryAdminService{


	private final BookRepository bookRepository;
	private final ModelMapper modelMapper;
	private final EuropeanLibraryRegistry europeanLibraryRegistry;
	private final NationalLibraryRegistry nationalLibraryRegistry;

	public LibraryAdminServiceImpl(BookRepository bookRepository,
								   ModelMapper modelMapper,
								   EuropeanLibraryRegistry europeanLibraryRegistry,
								   NationalLibraryRegistry nationalLibraryRegistry) {
		this.bookRepository = bookRepository;
		this.modelMapper = modelMapper;
		this.europeanLibraryRegistry = europeanLibraryRegistry;
		this.nationalLibraryRegistry = nationalLibraryRegistry;
	}

	@Override
	@Transactional
	public void addBook(Book book) {
		bookRepository.save(book);
		europeanLibraryRegistry.register(book);
		nationalLibraryRegistry.register(book);
	}

	@Override
	@Transactional
	public void addBooks(List<Book> books) {
		for(Book book : books){
			bookRepository.save(book);
			europeanLibraryRegistry.register(book);
			nationalLibraryRegistry.register(book);
		}
	}

	@Override
	@Transactional
	public void deleteBook(Book book) {
		bookRepository.delete(book);
	}

}
