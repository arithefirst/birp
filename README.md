# BIRP - Brainfuck Interpreter in Java

This is a simple Brainfuck interpreter implemented in Java. [Brainfuck](https://en.wikipedia.org/wiki/Brainfuck) is an esoteric programming language known for its minimalism, consisting of just eight commands.

## Features

- Interprets standard Brainfuck code
- Handles memory management automatically
- Provides clear error messages for common issues
- Supports file-based input

## Usage

You can run the interpreter by either building the project from source or downloading the pre-built JAR file from the [Releases](https://github.com/arithefirst/birp/releases) tab.

### Running from JAR

```bash
java -jar birp.jar test.bf
```

## Example

The following Brainfuck code:

```brainfuck
>++++++++[<+++++++++>-]<.
>++++[<+++++++>-]<+.
+++++++..
+++.
>>++++++[<+++++++>-]<++.
------------.
>++++++[<+++++++++>-]<+.
<.
+++.
------.
--------.
>>>++++[<++++++++>-]<+.
```

When interpreted, outputs:

```
Hello, World!
```

## Building the Project

You can build the project using Gradle:

```bash
./gradlew build
```

This will create a JAR file in the libs directory that can be executed to interpret Brainfuck code.

## Project Structure

- `src/main/java/com/arithefirst/Birp.java`: Main entry point
- `src/main/java/com/arithefirst/BfInterpreter.java`: Interpreter implementation