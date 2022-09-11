/*Purpose==>Create an Employee Payroll Service to store Employee Payroll into a File
 - Create Employee Payroll Class of id, name and Salary
 - Populate Test Employee Payroll Object
 - Write Employee Payroll to File Use File IO
 - Count Number of Entries to ensure the Operation worked
*Author==>Sushmitha
*Signin==>11-09--2022
*/
package com.bridzelab.EmployeePayrollService;

import java.io.File;//provides a set of input streams and a set of output streams
                    // used to read and write data to files or other input and output sources.
import java.util.ArrayList;//ArrayList in Java is used to store dynamically sized collection of elements.
import java.util.List;//represents an ordered sequence of objects.
import java.util.Scanner;// read input from the input stream

public class EmployeePayrollService
{
    //An enum can, just like a class , have attributes and methods.
    // The only difference is that enum constants are public , static and final
    public enum IOService {
        CONSOLE_IO, FILE_IO, REST_IO
    };

    private List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();

    public EmployeePayrollService(List<EmployeePayrollData> emList) {
        employeePayrollList = emList;
    }

    public static void main(String[] args) {

    }

    public void readEmployeePayrollData() {
        Scanner consoleInputReader = new Scanner(System.in);
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
        consoleInputReader.close();
    }

    /**
     * UC4
     *
     * @param writeTo
     */
    public void writeEmployeePayrollData(IOService writeTo) {
        if (writeTo.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Employee Payroll Data to Console\n" + employeePayrollList);
        else if (writeTo.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().writeEmployeePayrollDate(employeePayrollList);
        }
    }

    public void printData(IOService printTo) {
        if (printTo.equals(IOService.FILE_IO))
            new EmployeePayrollFileIOService().printData();
    }

    public long countEntries() {
        return new EmployeePayrollFileIOService().countEntries();
    }


    public static boolean deleteFiles(File contentsToDelete) {
        File[] allContents = contentsToDelete.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteFiles(file);
            }
        }
        return contentsToDelete.delete();
    }


}