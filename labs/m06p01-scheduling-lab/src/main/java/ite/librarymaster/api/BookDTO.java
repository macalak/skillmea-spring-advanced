package ite.librarymaster.api;

import ite.librarymaster.validation.CatalogueId;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BookDTO {
    public Long id;
    @CatalogueId
    public String catId;
    @NotEmpty
    @Size(min=2, max=30)
    public String title;
    @NotNull
    public MediumAvailability availability;
    public String publisher;
    //@NotEmpty
    public String author;
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$")
    public String isbn;
    public BookGenre genre;
}
