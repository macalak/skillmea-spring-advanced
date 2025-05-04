package ite.librarymaster.model;

import java.io.Serializable;

/**
 * This class models a Book media type.
 * 
 * @author macalak@itexperts.sk
 *
 */

public class Book implements Serializable {

	private Long id;
	private String catId;
	private String title;
	private MediumAvailability availability;
	private String author;
	private String isbn;
	private BookGenre genre;
	private String publisher;

	public Book(Long id, String catId, String title, MediumAvailability availability, String author, String isbn, BookGenre genre, String publisher) {
		this.id = id;
		this.catId = catId;
		this.title = title;
		this.availability = availability;
		this.author = author;
		this.isbn = isbn;
		this.genre = genre;
		this.publisher = publisher;
	}

	public Book() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MediumAvailability getAvailability() {
		return availability;
	}

	public void setAvailability(MediumAvailability availability) {
		this.availability = availability;
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Book{");
		sb.append("id=").append(id);
		sb.append(", catId='").append(catId).append('\'');
		sb.append(", title='").append(title).append('\'');
		sb.append(", availability=").append(availability);
		sb.append(", author='").append(author).append('\'');
		sb.append(", isbn='").append(isbn).append('\'');
		sb.append(", genre=").append(genre);
		sb.append(", publisher='").append(publisher).append('\'');
		sb.append('}');
		return sb.toString();
	}
}