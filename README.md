## Validator ##

Annotate member variables for validation

```java
public class User {
	@Alpha
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

Template - Get all possible validation messages

```java
validator.template();
```

## Alpha ##

Validates variable consists of only letters

```java
@Alpha
```

Data types supported
- String

## AlphaNumeric ##

Validates variable consists of only letters and/or numbers

```java
@AlphaNumeric
```

Data types supported
- String

## Custom ##

Validates against a custom class

```java
@CustomValidator(MyEvaluation.class)
```

Required
- value - Class that implements Evaluation

**Note:** Custom validation class must implement the Evaluation interface

## DateFormat ##

Validates a string is in a given date format

```java
@DateFormat("yyyy-MM-dd")
```

Data types supported
- String

Format used by java.text.SimpleDateFormat

## Email ##

Validates variable is an email address

```java
@Email
```

Data types supported
- String

## Exact Match ##

Validates variable equals given value

```java
@ExactMatch(match = "match")
@ExactMatch(match = "match", caseSensitive = false)
@ExactMatch(matches = {"PST", "MST", "CST", "EST"})
```

Options
- match - [String] Match given value
- matches - [String array] Match any of the given values
- caseSensitive - [boolean] Default: true

Data types supported
- String
- Number

**Note:** matches array will override match string

## Length ##

Validates variable is of given length

```java
@Length(4)
```

Required
- length - [int]

Data types supported
- String
- Integer
- Long

## Not Empty ##

Validates variable is not empty

```java
@NotEmpty
```

Data types supported
- String
- Array
- Collection
- Map

## Not Null ##

Validates variable is not null

```java
@NotNull
```

Data types supported
- Any Object

## Number ##

Validates if variable is a number

```java
@Number
```

Data types supported
- String

## Numeric ##

Validates variable consists of only numbers

```java
@Numeric
```

Data types supported
- String

## Optional ##

Will override all other validations if variable is null

```java
@Optional
```

Data types supported
- Any Object

## Regex ##

Validates variable matches given regex

```java
@Regex("^[a-zA-Z]+$")
```

Required
- regex - [String]

Data types supported
- String

## Size ##

Validates variable is of given size

```java
@Size(3)
```

Required
- Size - [int]

Data types supported
- Array
- Collection
- Map

## Between ##

Validates variable is between two given numbers

```java
@Between(minimum = 3.2, maximum = 4, inclusive = true)
```

Optional
- Inclusive - [boolean] Default: false

Required
- Minimum - [double]
- Maximum - [double]

Data types supported
- Number

## Greater Than ##

Validates variable is greater than given number

```java
@GreaterThan(3.2)
```

Required
- Minimum - [double]

Data types supported
- Number

## Greater Than or Equal to ##

Validates variable is greater than or equal to given number

```java
@GreaterThanOrEqualTo(3.2)
```

Required
- Minimum - [double]

Data types supported
- Number

## Less Than ##

Validates variable is less than given number

```java
@LessThan(3.2)
```

Required
- Maximum - [double]

Data types supported
- Number

## Less Than or Equal to ##

Validates variable is less than or equal to given number

```java
@LessThanOrEqualTo(3.2)
```

Required
- Maximum - [double]

Data types supported
- Number

## Not Between ##

Validates variable is not between two given numbers

```java
@NotBetween(minimum = 3.2, maximum = 4, inclusive = true)
```

Optional
- Inclusive - [boolean] Default: false

Required
- Minimum - [double]
- Maximum - [double]

Data types supported
- Number

## Contains ##

Validates variable contains a given value

```java
@Contains(text = "contain")
@Contains(startText = "contain", caseSensitive = false)
```

Options
- caseSensitive - [boolean] Default: true

Data types supported
- String

## Starts with ##

Validates variable starts with given value

```java
@StartsWith(startText = "Begins")
@StartsWith(startText = "Begins", caseSensitive = false)
```

Options
- caseSensitive - [boolean] Default: true

Data types supported
- String

## Ends with ##

Validates variable begins with given value

```java
@EndsWith(endText = "Begins")
@EndsWith(endText = "Begins", caseSensitive = false)
```

Options
- caseSensitive - [boolean] Default: true

Data types supported
- String

