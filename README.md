# This is the sample code for refactoring and how to test private method

## Prerequisite
- Java 17
- maven 3

## Package structure
### before package
This package includes source code before refactoring.

### after package
This package includes source code after refactoring.

## What is refactored
- Change the way of dependency injection from field injection to constructor injection.
- Split the logic in `UserService#calculateTimeZoneAge` into some private methods.

## Purpose of refactoring
- Enable to inject mock from constructor
- Enable to test private method easily by using reflection.
