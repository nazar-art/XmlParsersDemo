package com.parsers;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author nlelyak
 * @version 1.00 2014-01-28.
 */
@XmlRootElement(name = "employee")
@XmlType(propOrder = {"name", "salary", "hireDay"})
public class Employee {

    private String name;
    private double salary;
    private Date hireDay;

    public Employee() {
    }

    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        // GregorianCalendar uses 0 for January
        hireDay = calendar.getTime();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }

    public void setHireDay(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        this.hireDay = calendar.getTime();
    }

    @Override
    public String toString() {
        return String.format("Employee { name=%s, salary=%s, hireDay=%s }", name, salary, hireDay);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
