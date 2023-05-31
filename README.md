# helper-utils

Provide popular utility function(s) for application, so we just focus on logic, need not to rewrite utility class for each project

### Date

* Convert/Format a Date/LocalDate to String and vice versus by provided format
  * 2023-09-09 -> with MM/dd/yyyy - 09/09/2023
  * ...
```java
String dateStr = DateUtils.toString(LocalDate.now(), "MM/dd/yyyy");
```
* Format Quarter by some format
  * 03/31/2023 -> First Quarter, 2023 
```java
String quarter = DateUtils.formatQuarter(LocalDate.now(), "Quarter-YYYY");
```
### Number

* Mathematical operation - add/subtract/divide/multiple
* Rounding number with particular decimal places
  * EX: 123.44324 -> 123.44 
* Format number by a pattern
  * 123,456,789.78
* Format number to string with provide format type - currency, percent ...
  * $ 123,456,789.78
```java
String numberFmt = NumberUtils.formatNumber(BigDecimal.valueOf("1234"), 2, "NA");
```
### Others
#### List

* Merge multiple lists
* ... to be continued

#### Maps

* Convert list to map with provided key function
* Group data from a list by particular key

#### Object

* Clone an object
```java
    Course course = new Course();
    course.setName("Test");
    course.setCodes(Arrays.asList("CS"));
    Course cloneCourse = ObjectUtils.cloneObject(courseEntity, course); 
```
* Copy properties of object to another

```java
    Course course = new Course();
    course.setName("Test");
    course.setCodes(Arrays.asList("CS"));
    course.setEnrolledStudent("ST-1", new Student());
    CourseEntity courseEntity = new CourseEntity();
    ObjectUtils.copyObject(courseEntity, course); // copy course to courseEntity
```
This will copy the properties with the same name only