package ite.librarymaster.service;

import ite.librarymaster.api.BookDTO;

import java.util.List;

public interface LibraryService {
	
	List<BookDTO> getAllBooks();
	BookDTO getBook(Long id) throws ItemNotFoundException;

	BookDTO saveBook(BookDTO book);

	void deleteBook(BookDTO book);


}
