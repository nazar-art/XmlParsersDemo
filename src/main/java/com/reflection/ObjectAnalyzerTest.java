package com.reflection;

import com.generated.Staff;

import java.util.ArrayList;

public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        ArrayList<Integer> squares = new ArrayList<Integer>();
        for (int i = 1; i <= 5; i++) {
            squares.add(i * i);
        }

        System.out.println(new ObjectAnalyzer().toString(squares));
        System.out.println();

        ArrayList<Staff> staffs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            staffs.add(i, new Staff());
        }
        System.out.println(new ObjectAnalyzer().toString(staffs));
    }
}
