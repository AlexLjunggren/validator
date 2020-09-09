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

## Custom Validation ##

Validates variable against a custom class

```java
@CustomValidation(className = "com.ljunggren.validator.evaluation.AlphaEvaluation")
```

Required
- className - [String] Package and class name of custom validation class

**Note:** Custom validation class must implement the Evaluation interface

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
@ExactMatchValidation(match = "match")
@ExactMatchValidation(match = "match", caseSensitive = false)
@ExactMatchValidation(matches = {"PST", "MST", "CST", "EST"})
```

Options
- match - [String] Match given value
- matches - [String array] Match any of the given values
- caseSensitive - [boolean] Default: true

Data types supported
- String
- Number

**Note:** matches array will override match string

## Length Validation ##

Validates variable is of given length

```java
@LengthValidation(length = 4)
```

Required
- length - [int]

Data types supported
- String
- Integer
- Long

## Not Empty Validation ##

Validates variable is not empty

```java
@NotEmptyValidation
```

Data types supported
- String
- Array
- Collection
- Map

## Not Null Validation ##

Validates variable is not null

```java
@NotNullValidation
```

Data types supported
- Any Object

## Optional Validation ##

Will override all other validations if variable is null

```java
@OptionalValidation
```

Data types supported
- Any Object

## Regex Validation ##

Validates variable matches given regex

```java
@RegexValidation(regex = "^[a-zA-Z]+$")
```

Required
- regex - [String]

Data types supported
- String

## Size Validation ##

Validates variable is of given size

```java
@SizeValidation(size = 3)
```

Required
- Size - [int]

Data types supported
- Array
- Collection
- Map

## Between Validation ##

Validates variable is between two given numbers

```java
@BetweenValidation(minimum = 3.2, maximum = 4, inclusive = true)
```

Optional
- Inclusive - [boolean] Default: false

Required
- Minimum - [double]
- Maximum - [double]

Data types supported
- Number

## Greater Than Validation ##

Validates variable is greater than given number

```java
@GreaterThanValidation(minimum = 3.2)
```

Required
- Minimum - [double]

Data types supported
- Number

## Greater Than or Equal to Validation ##

Validates variable is greater than or equal to given number

```java
@GreaterThanOrEqualToValidation(minimum = 3.2)
```

Required
- Minimum - [double]

Data types supported
- Number

## Less Than Validation ##

Validates variable is less than given number

```java
@LessThanValidation(maximum = 3.2)
```

Required
- Maximum - [double]

Data types supported
- Number

## Less Than or Equal to Validation ##

Validates variable is less than or equal to given number

```java
@LessThanOrEqualToValidation(maximum = 3.2)
```

Required
- Maximum - [double]

Data types supported
- Number
