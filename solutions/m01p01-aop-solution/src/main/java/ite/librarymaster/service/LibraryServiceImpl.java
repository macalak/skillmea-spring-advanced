package ite.librarymaster.service;

import ite.librarymaster.aspect.Auditable;
import ite.librarymaster.repository.BookRepository;
import ite.librarymaster.model.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService{
	
	@Autowired
	@Qualifier("jpaBookRepository")
	private BookRepository bookRepository;

	@Override
	@Transactional(readOnly=true)
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

    @Override
    @Transactional(readOnly=true)
	@Auditable(full = true)
    public Book getBook(Long id) {
		Book book = bookRepository.findById(id);
		if (book == null){
			throw new RuntimeException("Book not found");
		}
		return book;
    }
}
