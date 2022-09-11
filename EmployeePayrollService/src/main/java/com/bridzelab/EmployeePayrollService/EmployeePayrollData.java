package com.bridzelab.EmployeePayrollService;

public class EmployeePayrollData
{
    private int id;
    private String name;
    private double salary;

    //parameterized construtor to get values

    public EmployeePayrollData(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    //toString method to read odject o/p as string
    @Override
    public String toString() {
        return "EmployeePayrollData [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
}

