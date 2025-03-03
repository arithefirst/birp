package com.arithefirst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Birp {
    public static void main(String[] args) {
        System.out.println(readFile(args[0]));
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
