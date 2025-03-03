package com.arithefirst;

import java.util.ArrayList;
import java.util.Scanner;

public class BfInterpreter {
  private ArrayList<Integer> memory;
  private int pointer;
  private String source;

  public BfInterpreter(String source) {
    this.source = source;
    this.memory = new ArrayList<>();
    this.memory.add(0); // Initialize with one cell
    this.pointer = 0;
  }

  public void interpret() {
    for (int i = 0; i < source.length(); i++) {
      try {
        switch (source.charAt(i)) {
          case '[':
            // If current cell is 0, skip to matching ]
            if (memory.get(pointer) == 0) {
              int depth = 1;
              while (depth > 0) {
                i++;
                if (i >= source.length()) {
                  throw new RuntimeException("Unmatched [");
                }
                if (source.charAt(i) == '[') depth++;
                if (source.charAt(i) == ']') depth--;
              }
            }
            break;
          case ']':
            // If current cell is not 0, go back to matching [
            if (memory.get(pointer) != 0) {
              int depth = 1;
              while (depth > 0) {
                i--;
                if (i < 0) {
                  throw new RuntimeException("Unmatched ]");
                }
                if (source.charAt(i) == ']') depth++;
                if (source.charAt(i) == '[') depth--;
              }
            }
            break;
          case '>':
            // Increment pointer, add 0 to memory if pointer is out of bounds
            pointer += 1;
            if (pointer >= memory.size()) {
              memory.add(0);
            }
            break;
          case '<':
            // Decrement pointer, throw error if pointer is out of bounds
            if (pointer - 1 < 0) {
              throw new IndexOutOfBoundsException("Memory error: -1");
            } else {
              pointer -= 1;
            }
            break;
          case ',':
            // Read a value from STDIN and store it at the pointer
            System.out.print("Reading value from STDIN: ");
            Scanner reader = new Scanner(System.in);
            memory.set(pointer, reader.nextInt());
            break;
          case '+':
            // Increment value at pointer, reset to 0 if value is 255
            if (memory.get(pointer) == 255) {
              memory.set(pointer, 0);
            } else {
              memory.set(pointer, memory.get(pointer) + 1);
            }
            break;
          case '-':
            // Decrement value at pointer, reset to 255 if value is 0
            if (memory.get(pointer) == 0) {
              memory.set(pointer, 255);
            } else {
              memory.set(pointer, memory.get(pointer) - 1);
            }
            break;
          case '.':
            char c = (char) memory.get(pointer).intValue();
            System.out.print(c);
            break;
          default:
            break;
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println(e.getMessage());
        System.exit(1);
      }
    }
    System.out.print('\n');
  }
}