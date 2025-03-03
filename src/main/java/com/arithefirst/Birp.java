package com.arithefirst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Birp {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Error: Please provide a Brainfuck file to interpret");
      System.exit(1);
    }

    String source = readFile(args[0]);
    BfInterpreter interpreter = new BfInterpreter(source);
    interpreter.interpret();
  }

  private static String readFile(String path) {
    String data = "";
    try {
      File bfile = new File(path);
      Scanner reader = new Scanner(bfile);
      while (reader.hasNextLine()) {
        String read = reader.nextLine();
        data += read;
      }
      reader.close();
      return data;
    } catch (FileNotFoundException e) {
      System.out.println(String.format("No such file exists: \"%s\".\n", path));
      e.printStackTrace();
      System.exit(2);
      return "";
    }
  }
}