/*Purpose==>Create an Employee Payroll Service to Read and Write Employee Payroll to a Console
           - Create Employee Payroll Class of id, name and Salary
*Author==>Sushmitha
*Signin==>11-10--2022
*/
package com.bridzelab.EmployeePayrollService;

import java.util.ArrayList;//ArrayList in Java is used to store dynamically sized collection of elements.
import java.util.List;//represents an ordered sequence of objects.
import java.util.Scanner;// read input from the input stream

public class EmployeePayrollService
{
    //An enum can, just like a class , have attributes and methods.
    // The only difference is that enum constants are public , static and final
    public enum IOService{CONSOLE_IO, FILE_IO, REST_IO}
    private List<EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService()
    {
        employeePayrollList = new ArrayList<EmployeePayrollData>();
    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to Empolyee Payroll Services Program");
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData();
    }

    private void readEmployeePayrollData(Scanner consoleInputReader)
    {
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    private void writeEmployeePayrollData()
    {
        System.out.println("\nWriting Employee Payroll Data to Console\n" + employeePayrollList);
    }
}
