package ite.librarymaster.api;

import ite.librarymaster.validation.CatalogueId;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class BookDTO implements Serializable {
    public Long id;
    @CatalogueId
    public String catId;
    @NotEmpty
    @Size(min=2, max=30)
    public String title;
    @NotNull
    public MediumAvailability availability;
    public String publisher;
    @NotEmpty
    public String author;
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$")
    public String isbn;
    public BookGenre genre;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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
}
