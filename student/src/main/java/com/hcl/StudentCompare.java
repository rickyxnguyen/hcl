package com.hcl;

import java.util.ArrayList;
import java.util.Collections;

public class StudentCompare {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student(420, "ricky", 22));
        students.add(new Student(969, "starr", 24));
        students.add(new Student(985, "christian", 45));
        
        System.out.println("Unsorted Version of List");
        students.forEach((n) -> System.out.println(n.getName()));

        Collections.sort(students, new SortByID());
        System.out.println("\nSorted by ID");
        students.forEach((n) -> System.out.println(n.getName()));

        Collections.sort(students, new SortByName());
        System.out.println("\nSorted by Name");
        students.forEach((n) -> System.out.println(n.getName()));

}
}