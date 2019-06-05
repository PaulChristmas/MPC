# Sum-application
Simple java application with using matlab model.

## Installation

Compiled matlab model with *.jar extension is already here (getsum.jar).
The only thin you should do:
specify your own absolute path of javabuilder from your matlab installation in pom.xml:

```xml

        <dependency>
        	<groupId>com.mathworks</groupId>
        	<artifactId>javabuilder</artifactId>
        	<systemPath>C:\Program Files\MATLAB\MATLAB Runtime\v96\toolbox\javabuilder\jar\javabuilder.jar</systemPath>
        	<version>96</version>
        	<scope>system</scope>
        </dependency>
```

If you don't have any matlab, you can try to use my javabuilder.
It is in /javabuilder folder.

## Usage

Wait a few seconds for matlab model initialization and type an expression like ' a + b':

```java
input
3 + 4.5
```

```java
output
3 + 4.5 = 7.5
```