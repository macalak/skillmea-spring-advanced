package ite.librarymaster.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * This class models a Book media type.
 * 
 * @author macalak@itexperts.sk
 *
 */

@Entity
public class Book extends Text {
	
	private String author;
	private String isbn;
	@Enumerated(EnumType.STRING)
	private BookGenre genre;
	
	public Book() {
		super();
	}
	
	public Book(Long id, String catId, String title, String publisher, String author, String isbn, BookGenre genre, MediumAvailability availability) {
		super(id, catId, title, publisher, availability);
		this.author = author;
		this.isbn = isbn;
		this.genre = genre;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public BookGenre getGenre() {
		return genre;
	}
	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Book{");
		sb.append("author='").append(author).append('\'');
		sb.append(", isbn='").append(isbn).append('\'');
		sb.append(", genre=").append(genre);
		sb.append(super.toString());
		sb.append('}');
		return sb.toString();
	}
}
