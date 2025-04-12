# Spring and JSR

* Java Bean Validation standard (JSR-380) is well integrated with Spring
* Standard *@Valid* annotation on method parameters and fields to tell Spring that we want a method parameter or field to be validated.
* Spring *@Validated* annotation is a class-level annotation that we can use to tell Spring to validate parameters that are passed into a method of the annotated class.
* Standard *@Valid* anotation does not support group validation
    * In such situations you need to use Spring *@Validated* annotation
* Spring Immediate validation    
* Spring validation using DataBinder (*binder.validate()*)

## @Valid
Standard Java Bean Validation annotation. Ensures cascading validation of method/constructor, 
or return values is performed. It performs the validation of the whole object graph.
You will typically use @Valid in web/rest controllers. Constraint violation results into *MethodArgumentNotValidException*.

```Java
public ResponseEntity processCreation(@RequestBody @Valid BookDTO book) {
    [...]
    return new ResponseEntity(HttpStatus.CREATED);
}
```

## @Validated
Spring specific *@Validated* annotation enables Spring-driven method validation on target class.
It provides more fine-grained control over the validation process. *@Validated* enables to use validation groups.
You will typically use *@Validated* in services. You can define custom exception handling.

```Java
@Service
@Validated
public class UserService {
    @NotNull(groups = {CreateUser.class})
    public User createUser(@Valid User user) {
        // Business logic to create a user
        return user;
    }
}
```

## Validation provider
Spring provides Bean Validation provider (Hibernate validator), so you can inject validation services, wherever you need them:

* jakarta.validation.ValidatorFactory
* jakarta.validation.Validator

Additionaly Spring provides its own Spring Validation API represented by:

* org.springframework.validation.Validator

It adapts standard ConstraintViolation to FieldError.



