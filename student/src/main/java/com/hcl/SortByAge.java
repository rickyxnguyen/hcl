package com.hcl;
import java.util.Comparator;

public class SortByAge implements Comparator<Student> {
    public int compare(Student a, Student b){
        return a.getAge() - b.getAge();
    }
}
