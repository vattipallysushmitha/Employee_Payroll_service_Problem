package com.bridzelab.EmployeePayrollService;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EmployeePayrollServiceTest {

    //UC4

    @Test
    public void whenThreeEmployeeAddedShouldReturnCountOfEmployees()
    {
        EmployeePayrollData[] employeeList = { new EmployeePayrollData(1, "sushmitha", 50000),
                new EmployeePayrollData(2, "akhila", 50000), new EmployeePayrollData(3, "nani", 50000) };
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(Arrays.asList(employeeList));
        employeePayrollService.writeEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
        employeePayrollService.printData(EmployeePayrollService.IOService.FILE_IO);
        long entries = employeePayrollService.countEntries();
        assertEquals(3, entries);
    }
}