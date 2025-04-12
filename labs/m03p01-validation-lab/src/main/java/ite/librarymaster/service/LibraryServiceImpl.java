package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService{
	
    private BookRepository bookRepository;

    public LibraryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
	@Transactional(readOnly=true)
	public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
	}

    @Override
    @Transactional(readOnly=true)
    public Book getBook(Long id) throws ItemNotFoundException {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            throw new ItemNotFoundException("Book with id="+id+" not found.");
        }
        return book;
    }

    @Override
    public Book saveBook(Book book) {
        bookRepository.save(book);
        return book;
    }
}
