import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class EmployeePayRollService {
    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    private List<EmployeePayrollData> employeePayrollList;

    public EmployeePayRollService() {
    }

    public EmployeePayRollService(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    private void readEmployeePayrollData(Scanner consoleInputReader) {

        System.out.println("Enter the Employee Id : ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter the Employee Name : ");
        String name = consoleInputReader.next();
        System.out.println("Enter the Employee Salary : ");
        double salary = consoleInputReader.nextDouble();

        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    public void writeEmployeePayrollData(IOService ioService) {
        if(ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Employee Payroll  to Console\n" + employeePayrollList);

        else if(ioService.equals(IOService.FILE_IO))
            new EmployeePayRollFileIOService().writeData(employeePayrollList);
    }

    public void printData(IOService fileIo) {
        if(fileIo.equals(IOService.FILE_IO)) new EmployeePayRollFileIOService().printData();
    }


    public long countEntries(IOService fileIo) {
        if(fileIo.equals(IOService.FILE_IO))
            return new EmployeePayRollFileIOService().countEntries();

        return 0;
    }

    public long readDataFromFile(IOService fileIo) {

        List<String> employeePayrollFromFile = new ArrayList<String>();
        if(fileIo.equals(IOService.FILE_IO)) {
            System.out.println("Employee Details from payroll-file.txt");
            employeePayrollFromFile = new EmployeePayRollFileIOService().readDataFromFile();

        }
        return employeePayrollFromFile.size();
    }

    public static void main(String[] args) {

        System.out.println("Welcome To Employee Payroll Application");
        ArrayList<EmployeePayrollData> employeePayrollList  = new ArrayList<EmployeePayrollData>();
        EmployeePayRollService employeePayrollService = new EmployeePayRollService(employeePayrollList);
        Scanner consoleInputReader = new Scanner(System.in);

        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
    }
}
