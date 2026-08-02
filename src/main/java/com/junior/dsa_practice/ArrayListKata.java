package com.junior.dsa_practice;

import java.util.ArrayList;

public class ArrayListKata {
    public static void main(String[] args) {
        ArrayList<String> subjects = new ArrayList<>();
        // 1. 
        subjects.add("Java");
        subjects.add("DSA");
        subjects.add("Git");

        // 2. 
        System.out.println("Contents of the Arrray List: ");
        System.out.println(subjects.toString());

       
        System.out.println("\n");

        // 3.
        System.out.println("Showing First -> " + subjects.getFirst().toString());

        System.out.println("\n");

        // 4. 
        subjects.set(2,"Debugging");
        System.out.println("Contents of the Arrray List with git changed: ");
        System.out.println(subjects.toString());

        // 5. 
        System.out.println("Looping through: ");
        for (String string : subjects) {
            System.out.println(string + ".");
        }

        // 6.
        System.out.println("\nDoes it contain Java? -> " + subjects.contains("Java"));
        
        // 7. 
        System.out.println("\nDoes it contain SQL? -> " + subjects.contains("SQL"));
        
        // 8.
        subjects.clear();
        System.out.println("This is the list after clearing it: " + subjects.toString());

        // 9.
        if (subjects.isEmpty()) {
            System.out.println("No subjects yet.");
        } else {
            System.out.println(subjects.toString());
        }

    }
}
