import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Handles business logic for the payroll system.
 */
public class PayrollManager {
    private final List<Employee> employees;

    public PayrollManager() {
        employees = new ArrayList<>();
    }

    public boolean addEmployee(Employee employee) {
        if (hasEmployeeId(employee.getEmployeeId())) {
            return false;
        }
        employees.add(employee);
        return true;
    }

    public boolean hasEmployeeId(String employeeId) {
        return findEmployeeById(employeeId) != null;
    }

    public Employee findEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equalsIgnoreCase(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    public boolean updateDepartment(String employeeId, String newDepartment) {
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            return false;
        }
        employee.setDepartment(newDepartment);
        return true;
    }

    public boolean deleteEmployeeById(String employeeId) {
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            return false;
        }
        return employees.remove(employee);
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees available in the payroll system.");
            return;
        }

        employees.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeId, String.CASE_INSENSITIVE_ORDER))
                .forEach(Employee::displayInfo);

        System.out.println("------------------------------------------------------------");
    }

    public double calculateTotalPayroll() {
        BigDecimal total = BigDecimal.ZERO;
        for (Employee employee : employees) {
            total = total.add(BigDecimal.valueOf(employee.calculateSalary()));
        }
        return total.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double calculateAverageSalary() {
        if (employees.isEmpty()) {
            return 0.0;
        }
        BigDecimal total = BigDecimal.valueOf(calculateTotalPayroll());
        return total.divide(BigDecimal.valueOf(employees.size()), 2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public void generatePayrollSummary() {
        if (employees.isEmpty()) {
            System.out.println("No employees available to generate payroll summary.");
            return;
        }

        int fullTimeCount = countEmployeesByType(FullTimeEmployee.class);
        int partTimeCount = countEmployeesByType(PartTimeEmployee.class);
        int contractCount = countEmployeesByType(ContractEmployee.class);
        Employee highestPaid = getHighestPaidEmployee();
        Employee lowestPaid = getLowestPaidEmployee();

        System.out.println("================ PAYROLL SUMMARY ================");
        System.out.println("Total Employees   : " + getEmployeeCount());
        System.out.println("Full-Time         : " + fullTimeCount);
        System.out.println("Part-Time         : " + partTimeCount);
        System.out.println("Contract          : " + contractCount);
        System.out.printf("Total Payroll     : RM %,.2f%n", calculateTotalPayroll());
        System.out.printf("Average Salary    : RM %,.2f%n", calculateAverageSalary());

        if (highestPaid != null) {
            System.out.printf("Highest Salary    : %s (%s) - RM %,.2f%n",
                    highestPaid.getFullName(),
                    highestPaid.getEmployeeId(),
                    highestPaid.calculateSalary());
        }

        if (lowestPaid != null) {
            System.out.printf("Lowest Salary     : %s (%s) - RM %,.2f%n",
                    lowestPaid.getFullName(),
                    lowestPaid.getEmployeeId(),
                    lowestPaid.calculateSalary());
        }

        System.out.println("===============================================");
    }

    private int countEmployeesByType(Class<? extends Employee> employeeType) {
        int count = 0;
        for (Employee employee : employees) {
            if (employeeType.isInstance(employee)) {
                count++;
            }
        }
        return count;
    }

    private Employee getHighestPaidEmployee() {
        Employee highest = null;
        double highestSalary = Double.NEGATIVE_INFINITY;

        for (Employee employee : employees) {
            double salary = employee.calculateSalary();
            if (salary > highestSalary) {
                highestSalary = salary;
                highest = employee;
            }
        }

        return highest;
    }

    private Employee getLowestPaidEmployee() {
        Employee lowest = null;
        double lowestSalary = Double.POSITIVE_INFINITY;

        for (Employee employee : employees) {
            double salary = employee.calculateSalary();
            if (salary < lowestSalary) {
                lowestSalary = salary;
                lowest = employee;
            }
        }

        return lowest;
    }
}
