package ite.librarymaster.api;

import ite.librarymaster.validation.ValidEmail;
import jakarta.validation.constraints.NotEmpty;

public class AuthorDTO {
    @NotEmpty
    public String name;
    public String middleName;
    @NotEmpty
    public String surname;
    @ValidEmail
    public String email;
}
