## Validator ##

Annotate member variables for validation

```java
public class User {
	@AlphaValidation
	private String firstName;
}
```

Instantiate

```java
Validator validator = new Validator(object);
```

Validate

```java
validator.validate();
```

Check validity of object

```java
validator.isValid();
```

Get invalid Items

```java
List<Item> items = validator.getInvalidItems();
```

Get member name for invalid Item

```java
items.get(0).getMemberName();
```

Get error message for invalid Item

```java
items.get(0).getErrorMessage();
```

## Alpha Validation ##

Validates variable consists of only letters

```java
@AlphaValidation
```

Data types supported
- String

## AlphaNumeric Validation ##

Validates variable consists of only letters and/or numbers

```java
@AlphaNumericValidation
```

Data types supported
- String

## Email Validation ##

Validates variable is an email address

```java
@EmailValidation
```

Data types supported
- String

## Exact Match Validation ##

Validates variable equals given value

```java
@ExactMatchValidation(match = "toMatch")
```

Data types supported
- String
- Integer
- Long

## Length Validation ##

Validates variable is of given length

```java
@LengthValidation(length = 4)
```

Data types supported
- String
- Integer
- Long
