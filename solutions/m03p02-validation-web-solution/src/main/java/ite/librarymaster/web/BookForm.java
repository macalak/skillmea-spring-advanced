package ite.librarymaster.web;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BookForm {
    private Long id;
    @Pattern(regexp = "^LM-[0-9]+")
    private String catId;
    @NotEmpty
    @Size(min=1, max=60)
    private String title;
    @NotNull
    private MediumAvailability availability = MediumAvailability.Available;
    @NotEmpty
    private String publisher;
    @NotEmpty
    private String author;
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$")
    private String isbn;
    @NotNull
    private BookGenre genre;

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
