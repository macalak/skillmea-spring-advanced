package ite.librarymaster.application.service;

import ite.librarymaster.application.dto.BookDTO;
import ite.librarymaster.application.exception.ItemNotFoundException;

import java.util.List;

public interface LibraryService {
	
	List<BookDTO> getAllBooks();
	BookDTO getBookById(Long id) throws ItemNotFoundException;

}
