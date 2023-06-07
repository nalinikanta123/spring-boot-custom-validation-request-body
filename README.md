# spring-boot-custom-validation-request-body

```
Data validation is very important. 
It conforms to the expected format, type, range, and business rules, as well as identify and prevent errors, inconsistencies, and fraud. 
When we need to perform data validation that cannot be handled by the built-in validation annotations provided by Spring Framework, we can use custom validation to define our own rules and constraints. 
In this tutorial, I will show you how to implement custom Validation annotation in Spring Boot example (with Rest API).

```
```
We will implement 2 Spring Boot custom validation annotations:

@StrongPassword: check if string is 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters.
@PasswordMatching: check if the password and confirm password are the same.

```
```
Let me explain it briefly.

– StrongPassword.java and PasswordMatching.java define annotation interfaces for custom validation rules.
– StrongPasswordValidator.java and PasswordMatchingValidator.java define validator classes that implement the validation logic.
– SignupRequest is the payload class (or DTO) that uses the custom validation annotations.
– UserController is the Rest Controller that exports API endpoint for receiving SignupRequest payload.
– ValidationExceptionHandler handles error when the validation fails.

```

```
– The field message is for error message.
– The @Constraint annotation defines the actual validator implementation: StrongPasswordValidator and PasswordMatchingValidator.
– @Target({ ElementType.TYPE }) indicates that we will attach the validator to the whole class.
  We have to define the password and the confirmPassword field to tell the validator which fields we want to compare.

```
```
We use String matches() function with regular expression for a strong password:

^: the start of the string
(?=.*\d): at least one digit
(?=.*[a-z]): at least one lowercase letter
(?=.*[A-Z]): at least one uppercase letter
(?=.*[@#$%^&+=!*()]): at least one special character
.{8,}: at least 8 characters long
$: the end of the string

```

```
The validator can read password and confirmPassword in the initialize() method, then check them later on in the isValid().

```
```
Request Body:

{
    "username": "testuser",
    "email": "testuser@gmail.com",
    "password": "Silence@1234",
    "confirmPassword": "Silence@1234"
}
```

```
ResponseBody: 200 OK

User registered Successfully!

```

```
Request Body:
{
    "username": "testuser",
    "email": "testuser@gmail.com",
    "password": "Silence@1234",
    "confirmPassword": "ilence@1234"
}

```
```
Response Body - 400 BadRequest
{
    "errors": [
        "Password and Confirm Password must be matched!"
    ]
}
```
```
RequestBody
{
    "username": "testuser",
    "email": "testuser@gmail.com",
    "password": "Silence34",
    "confirmPassword": "ilence@1234"
}
```
```
ResponseBody - 400 BadRequest
{
    "errors": [
        "Password and Confirm Password must be matched!",
        "Must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters."
    ]
}
```
