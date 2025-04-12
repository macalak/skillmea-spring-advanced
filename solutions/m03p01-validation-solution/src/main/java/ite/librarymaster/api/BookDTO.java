package ite.librarymaster.api;

import ite.librarymaster.validation.CatalogueId;

import ite.librarymaster.validation.CreateBookGroup;
import ite.librarymaster.validation.UpdateBookGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BookDTO {
    public Long id;
    @CatalogueId(groups = {CreateBookGroup.class})
    public String catId;
    @NotEmpty
    @Size(min=2, max=30, groups = {CreateBookGroup.class, UpdateBookGroup.class})
    public String title;
    @NotNull(groups = {CreateBookGroup.class, UpdateBookGroup.class})
    public MediumAvailability availability;
    public String publisher;
    @Valid
    public AuthorDTO author;
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$")
    public String isbn;
    public BookGenre genre;

}
