# Introduction

The Bean Validation supports user input, or domain objects validation:

* Lets you express constraints on object models via annotations
* There are built-in constraints
* You can write custom constraints in an extensible way
* Provides the APIs to validate objects and object graphs
* Provides the APIs to validate parameters and return values of methods and constructors
* Reports the set of violations (localized)
* Runs in Java SE
* Integrated with Spring Framework (Spring provides Bean Validation provider)
* Hibernate Validator is reference implementation of [JSR380](https://beanvalidation.org/2.0-jsr380/)
* Refer [Spring documentation pages](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html) for more info

## Java Bean Validation (JSR 380)

* It is part of the Jakarta EE standard and provides a standardized way to validate JavaBeans. 
* Uses a set of annotations such as @NotNull, @Size, @Min, @Max, etc., to define constraints 
  directly on JavaBean properties. 
* It allows you to define custom validators by implementing the ConstraintValidator interface.
* Provides a wide range of built-in constraints to cover common validation scenarios.
* Easily integrates with various frameworks and libraries, such as JPA, JSF, and Spring, for validating data.

## Spring Validation

* Part of the Spring Framework and is designed to work seamlessly with Spring's dependency injection and other features.
* Instead of annotations, it uses the Validator interface that you can implement for custom validation logic.
* Specifically designed to work with Spring's data binding and form handling features, making it a natural fit 
  for web applications. 
* Although you can use standard Bean Validation annotations, Spring allows for custom annotations and validation logic.
* Offers convenience methods and classes like Errors, BindingResult, and ValidationUtils to simplify the 
  validation process.
