import java.util.Scanner;

/**
 * Console-based Employee Payroll System.
 * This class demonstrates user interaction, control structures, and object creation.
 */
public class PayrollSystemApp {
    private static final int EXIT_CHOICE = 9;
    private static Scanner scanner;
    private static final PayrollManager payrollManager = new PayrollManager();

    public static void main(String[] args) {
        try (Scanner inputScanner = new Scanner(System.in)) {
            scanner = inputScanner;
            initializeData();
            runSystem();
        }
    }

    private static void initializeData() {
        System.out.println("============================================================");
        System.out.println("Welcome to Employee Payroll System");
        System.out.println("============================================================");
        if (readYesNo("Load sample employees for demo? (y/n): ")) {
            preloadSampleData();
            System.out.println("Sample data loaded successfully.");
        } else {
            System.out.println("Starting with an empty payroll list.");
        }
    }

    private static void runSystem() {
        int choice;

        do {
            displayMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addFullTimeEmployee();
                case 2 -> addPartTimeEmployee();
                case 3 -> addContractEmployee();
                case 4 -> payrollManager.displayAllEmployees();
                case 5 -> searchEmployee();
                case 6 -> updateEmployeeDepartment();
                case 7 -> payrollManager.generatePayrollSummary();
                case 8 -> deleteEmployee();
                case 9 -> System.out.println("Thank you for using the Employee Payroll System. Goodbye!");
                default -> System.out.println("Invalid choice. Please select between 1 and 9.");
            }
        } while (choice != EXIT_CHOICE);
    }

    private static void displayMenu() {
        System.out.println("\n============================================================");
        System.out.println("        EMPLOYEE PAYROLL SYSTEM (JAVA CONSOLE APP)");
        System.out.println("============================================================");
        System.out.println("1. Add Full-Time Employee");
        System.out.println("2. Add Part-Time Employee");
        System.out.println("3. Add Contract Employee");
        System.out.println("4. Display All Employees");
        System.out.println("5. Search Employee by ID");
        System.out.println("6. Update Employee Department");
        System.out.println("7. Generate Payroll Summary");
        System.out.println("8. Delete Employee");
        System.out.println("9. Exit");
        System.out.println("============================================================");
        System.out.println("Current Employee Count: " + payrollManager.getEmployeeCount());
    }

    private static void addFullTimeEmployee() {
        System.out.println("\n--- Add Full-Time Employee ---");
        String employeeId = readRequiredString("Enter employee ID: ");
        if (payrollManager.hasEmployeeId(employeeId)) {
            System.out.println("Employee ID already exists. Please use a unique ID.");
            return;
        }

        String fullName = readRequiredString("Enter full name: ");
        String department = readRequiredString("Enter department: ");
        double basicSalary = readDouble("Enter basic salary (RM): ");
        double allowance = readDouble("Enter monthly allowance (RM): ");
        double bonus = readDouble("Enter performance bonus (RM): ");
        double taxRate = readRate("Enter tax rate (example 0.08 for 8%): ");

        try {
            Employee employee = new FullTimeEmployee(employeeId, fullName, department,
                    basicSalary, allowance, bonus, taxRate);
            if (payrollManager.addEmployee(employee)) {
                System.out.println("Full-time employee added successfully.");
            } else {
                System.out.println("Employee ID already exists. Please use a unique ID.");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void addPartTimeEmployee() {
        System.out.println("\n--- Add Part-Time Employee ---");
        String employeeId = readRequiredString("Enter employee ID: ");
        if (payrollManager.hasEmployeeId(employeeId)) {
            System.out.println("Employee ID already exists. Please use a unique ID.");
            return;
        }

        String fullName = readRequiredString("Enter full name: ");
        String department = readRequiredString("Enter department: ");
        double hourlyRate = readDouble("Enter hourly rate (RM): ");
        int hoursWorked = readPositiveInt("Enter hours worked (must be greater than 0): ");

        try {
            Employee employee = new PartTimeEmployee(employeeId, fullName, department,
                    hourlyRate, hoursWorked);
            if (payrollManager.addEmployee(employee)) {
                System.out.println("Part-time employee added successfully.");
            } else {
                System.out.println("Employee ID already exists. Please use a unique ID.");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void addContractEmployee() {
        System.out.println("\n--- Add Contract Employee ---");
        String employeeId = readRequiredString("Enter employee ID: ");
        if (payrollManager.hasEmployeeId(employeeId)) {
            System.out.println("Employee ID already exists. Please use a unique ID.");
            return;
        }

        String fullName = readRequiredString("Enter full name: ");
        String department = readRequiredString("Enter department: ");
        double contractAmount = readDouble("Enter total contract amount (RM): ");
        int contractMonths = readPositiveInt("Enter contract duration (months): ");
        double taxRate = readRate("Enter withholding tax rate (example 0.03 for 3%): ");

        try {
            Employee employee = new ContractEmployee(employeeId, fullName, department,
                    contractAmount, contractMonths, taxRate);
            if (payrollManager.addEmployee(employee)) {
                System.out.println("Contract employee added successfully.");
            } else {
                System.out.println("Employee ID already exists. Please use a unique ID.");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void searchEmployee() {
        System.out.println("\n--- Search Employee ---");
        if (payrollManager.isEmpty()) {
            System.out.println("No employees available to search.");
            return;
        }

        String employeeId = readRequiredString("Enter employee ID to search: ");
        Employee employee = payrollManager.findEmployeeById(employeeId);

        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println("Employee found:");
            employee.displayInfo();
            System.out.println("------------------------------------------------------------");
        }
    }

    private static void updateEmployeeDepartment() {
        System.out.println("\n--- Update Employee Department ---");
        if (payrollManager.isEmpty()) {
            System.out.println("No employees available to update.");
            return;
        }

        String employeeId = readRequiredString("Enter employee ID: ");
        String newDepartment = readRequiredString("Enter new department: ");

        try {
            boolean updated = payrollManager.updateDepartment(employeeId, newDepartment);
            if (updated) {
                System.out.println("Department updated successfully.");
            } else {
                System.out.println("Employee not found. No update made.");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void deleteEmployee() {
        System.out.println("\n--- Delete Employee ---");
        if (payrollManager.isEmpty()) {
            System.out.println("No employees available to delete.");
            return;
        }

        String employeeId = readRequiredString("Enter employee ID to delete: ");
        Employee employee = payrollManager.findEmployeeById(employeeId);

        if (employee == null) {
            System.out.println("Employee not found. No deletion made.");
            return;
        }

        System.out.println("Employee found:");
        employee.displayInfo();
        System.out.println("------------------------------------------------------------");

        if (!readYesNo("Confirm deletion? (y/n): ")) {
            System.out.println("Delete cancelled.");
            return;
        }

        boolean deleted = payrollManager.deleteEmployeeById(employeeId);
        if (deleted) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found. No deletion made.");
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String readRequiredString(String prompt) {
        while (true) {
            String value = readString(prompt);
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Please enter a non-negative integer.");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter an integer greater than 0.");
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Please enter a non-negative number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static double readRate(String prompt) {
        while (true) {
            double value = readDouble(prompt);
            if (value <= 1) {
                return value;
            }
            System.out.println("Please enter a value between 0 and 1.");
        }
    }

    private static boolean readYesNo(String prompt) {
        while (true) {
            String value = readString(prompt).toLowerCase();
            if ("y".equals(value) || "yes".equals(value)) {
                return true;
            }
            if ("n".equals(value) || "no".equals(value)) {
                return false;
            }
            System.out.println("Invalid input. Enter 'y' for yes or 'n' for no.");
        }
    }

    private static void preloadSampleData() {
        // Preloaded sample data helps demonstrate polymorphism quickly during evaluation.
        payrollManager.addEmployee(new FullTimeEmployee("E001", "Alicia Tan", "Human Resources", 4200.00, 500.00, 300.00, 0.08));
        payrollManager.addEmployee(new PartTimeEmployee("E002", "Daniel Lim", "Sales", 25.00, 128));
        payrollManager.addEmployee(new ContractEmployee("E003", "Nur Aisyah", "IT Support", 18000.00, 6, 0.03));
    }
}
