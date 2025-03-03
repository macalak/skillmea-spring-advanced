package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.model.Book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService{
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	@Transactional(readOnly=true)
	public List<Book> getAllBooks() {
		List<Book> result = new ArrayList<Book>();
		bookRepository.findAll().iterator().forEachRemaining(result::add);
		return result;
	}

    @Override
    @Transactional(readOnly=true)
    public Book getBook(Long id) {
		return bookRepository.findById(id).orElse(null);
    }

}
