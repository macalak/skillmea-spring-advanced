# Validation Example
Validation implementation in spring is provided by the [Hibernate Validator](http://hibernate.org/validator/),
which is the reference implementation of the [Bean Validation Specification](https://beanvalidation.org/).
You can validate beans at:
* Application layer
* Service layer
* Domain layer
* Persistence layer

Spring MVC translates the MethodArgumentNotValidException into HTTP 400 Bad Request.
The ConstraintViolationException is not translated by Spring. You need to implement exception mapper.

```java
@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation violation: ex.getConstraintViolations()){
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return errors;
    }
```

## Programmatically invoked validation

```java
class ProgrammaticallyValidatingService {
  
  void validateInput(Input input) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Input>> violations = validator.validate(input);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
  
}
```

Spring provides Validator you can inject.

```java
@Service
class ProgrammaticallyValidatingService {

  private Validator validator;

  ProgrammaticallyValidatingService(Validator validator) {
    this.validator = validator;
  }

  void validateInputWithInjectedValidator(Input input) {
    Set<ConstraintViolation<Input>> violations = validator.validate(input);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
```