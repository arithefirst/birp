package com.arithefirst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Birp {
    public static void main(String[] args) {
        ArrayList<Integer> feed = new ArrayList<Integer>(0);
        int pointer = 0;

        String source = readFile(args[0]);
        for (int i = 0; i != source.length(); i++) {
            // Print start value the pointer
            System.out.print(String.format("Pointer: %d", pointer));
            try {
                switch (source.charAt(i)) {
                    case '>':
                        // Increment pointer, add 0 to feed if pointer is out of bounds
                        if (i + 1 > source.length()) {
                            feed.add(0);
                        }
                        pointer += 1;
                        break;
                    case '<':
                        // Decrement pointer, throw error if pointer is out of bounds
                        if (i - 1 < 0) {
                            throw new IndexOutOfBoundsException("Memory error: -1");
                        } else {
                            pointer -= 1;
                        }
                        break;
                    default:
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            // Print the end value of the pointer
            System.out.println(String.format(" %d", pointer));
        }
    }

    public static String readFile(String path) {
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
