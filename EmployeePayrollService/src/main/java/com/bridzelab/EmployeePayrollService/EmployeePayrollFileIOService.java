package com.bridzelab.EmployeePayrollService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EmployeePayrollFileIOService
{
    private static String FILE_NAME = "payroll-data.txt";

    /**
     * UC4
     *
     * @param employeeList
     */
    public void writeEmployeePayrollDate(List<EmployeePayrollData> employeeList)
    {
        StringBuffer employeeBuffer = new StringBuffer();
        employeeList.forEach(employee -> {
            String employeeString = employee.toString().concat("\n");
            employeeBuffer.append(employeeString);
        });
        try
        {
            Files.write(Paths.get(FILE_NAME), employeeBuffer.toString().getBytes());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printData()
    {
        try
        {
            Files.lines(new File(FILE_NAME).toPath()).forEach(System.out::println);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public long countEntries()
    {
        long entries = 0;
        try
        {
            entries = Files.lines(new File(FILE_NAME).toPath()).count();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return entries;
    }
}
