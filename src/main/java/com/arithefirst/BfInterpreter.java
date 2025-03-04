package com.arithefirst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BfInterpreter {
  private ArrayList<Integer> memory;
  private HashMap<Integer, Integer> loopMap;
  private int pointer;
  private String source;

  public BfInterpreter(String source) {
    this.source = source;
    this.memory = new ArrayList<>();
    this.loopMap = new HashMap<>();
    this.memory.add(0); // Initialize with one cell
    this.pointer = 0;

    preprocessLoops();
  }

  private void preprocessLoops() {
    int length = source.length();
    int[] stack = new int[length]; // stack to track loop starts
    int top = -1;

    for (int i = 0; i < length; i++) {
      char c = source.charAt(i);
      if (c == '[') {
        // push
        stack[++top] = i;
      } else if (c == ']') {
        if (top == -1)
          throw new RuntimeException("Unmatched ] at index " + i);

        // pop
        int start = stack[top--];
        loopMap.put(start, i); // [ -> ]
        loopMap.put(i, start); // ] -> [
      }
    }

    if (top != -1)
      throw new RuntimeException("Unmatched [ at index " + stack[top]);
  }

  public void interpret() {
    int idx = 0;
    while (idx < source.length()) {
      try {
        switch (source.charAt(idx)) {
          case '[':
            // If current cell is 0, skip to matching ]
            if (memory.get(pointer) == 0) idx = loopMap.get(idx);
            break;
          case ']':
            // If current cell is not 0, go back to matching [
            if (memory.get(pointer) != 0) idx = loopMap.get(idx);
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
            // Increment value at pointer, wrap around u8 boundary
            memory.set(pointer, (memory.get(pointer) + 1) & 255);
            break;
          case '-':
            // Decrement value at pointer, wrap around u8 boundary
            memory.set(pointer, (memory.get(pointer) - 1) & 255);
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

      idx += 1;
    }

    System.out.print('\n');
  }
}
