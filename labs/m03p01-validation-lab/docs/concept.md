# Concept
As you already know, validating data is a common task that occurs throughout an application.
You need to validate data on various layers of your application, from the presentation layer
to the persistence layer. To avoid duplication of these validations in each layer, we should 
try to implement validation logic in the domain model componnets. The JSR380 defines declarative
model of validation, but the programmatic validation code execution is supported as well.
The good news is, the Spring framework integrates this concept. 

## Constraint
The main building block of bean validation is Constraint. Constraint is defined by the combination
of a constraint annotation (you will use as class meta data) and a list of constraint validation implementations.
The constraint annotation can be is applied on:

* Types
* Fields
* Methods (static methods are ignored)
* Constructors
* Parameters (of constructors and methods)
* Other constraint annotations

The @Constraint (meta) annotation is used to define concrete constraint like this:

```Java
@Documented
@Constraint(validatedBy = CatalogueIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CatalogueId {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

* *@Constraint* defines the constraint validation implementation class which implements the *ConstraintValidator* interface defined by specification.
* The *@Target* defines where the *@CatalogueId* can be applied.
* You can define the default validation error message. The best practice is to define default *message* as resource bundle key.
* *group* defines the constraint processing groups (you can define various groups as par of multiple steps bean validation typical for wizards)

## Multi-valuded Constraint
The example of such constraint is built-in *@Pattern* constraint definition. You can use multiple *@Pattern* annotation on the same target (field). You can even put them into several constraints groups.

```Java
@Pattern(regexp = "(?=.*[0-9]).+", message = "Password must contain one digit."),
@Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain one lowercase letter."),
private String password;
```

The trick in such constraint definition is to add *List* annotation definition as shown below:

```Java
@Documented
@Constraint(validatedBy = CatalogueIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CatalogueId {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.METHOD, ElementType.FIELD })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CatalogueId[] value();
    }
}
```

## Constraint composition
You can compose constraints to create higher level constraints. In other words, you can anotate constraint definition by another constraint annotation. So you can re-use primitive constraints.

```Java
@Pattern(regexp = "[0-9]+")
@Documented
@Constraint(validatedBy = CatalogueIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CatalogueId {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

## Groups
Groups allow you to restrict the set of constraints applied during validation. Groups targeted are passed as parameters to the validation methods. In other words the validation groups enabled you to apply constraints on a certain set of fields of the bean, and then later you want to apply constraints on another set of fields of the same bean.

* You can use *@GroupSequence* to put multiple groups validations in order

## Constraint Violation
The *ConstraintViolation* is the class describing a single constraint failure as result of validation.
You can access:

* message
* method, or constructor parameters
* method return value
* invalid value being validated


## Container Constraint
Constraints can be applied to the elements of generic containers, e.g. *List*, *Map* or *Optional*. This is done by putting constraint annotations to the type arguments of such containers.

```Java
private List<@Email String> emails;
```

## Cross-param Constraint
Cross-parameter constraints allow to express constraints based on the value of several method parameters.

```Java
@ConsistentDateParameters
public void createEvent(
    String title,
    @NotNull Date startDate,
    @NotNull Date endDate) {
        [...]
    }  
```

```Java
@Constraint(validatedBy = ConsistentDateParameterValidator.class)
@Target({ METHOD, CONSTRUCTOR })
@Retention(RUNTIME)
@Documented
public @interface ConsistentDateParameters {
    String message() default
      "End date must be after begin date and both must be in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

```Java
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParameterValidator 
       implements ConstraintValidator<ConsistentDateParameters, Object[]> {

    @Override
    public boolean isValid( Object[] value, 
                            ConstraintValidatorContext context) {
        
        Date startDate = (Date) value[1];
        Date endDate = (Date) value[2];

        return endDate.toInstant().isAfter(startDate.toInstant());
      }
  }  
```

## Object validation
You can apply constraints on state of whole bean and implement business validations of your domain entities for instance. 
