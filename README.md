# Employee Payroll System (Java Console Application)

A complete **Object-Oriented Programming Fundamentals in Java** assignment project based on the topic **Employee Payroll System**.

This project is designed to help you score well by clearly demonstrating:
- **Classes and Objects**
- **Encapsulation**
- **Inheritance**
- **Polymorphism**
- **Abstraction**
- **Java console programming using Scanner, if, loop, and switch**

---

## 1) Project Structure

```text
EmployeePayrollSystem/
├── src/
│   ├── Employee.java
│   ├── Taxable.java
│   ├── FullTimeEmployee.java
│   ├── PartTimeEmployee.java
│   ├── ContractEmployee.java
│   ├── PayrollManager.java
│   └── PayrollSystemApp.java
└── README.md
```

---

## 2) Features

The system allows users to:
1. Add a **Full-Time Employee**
2. Add a **Part-Time Employee**
3. Add a **Contract Employee**
4. Display all employee information
5. Search employee by ID
6. Update employee department
7. Generate payroll summary
8. Delete employee by ID (with confirmation)
9. Exit the program

---

## 3) How This Project Meets the Assignment Requirements

### Classes and Objects
- Uses more than **3 related classes**:
  - `Employee`
  - `FullTimeEmployee`
  - `PartTimeEmployee`
  - `ContractEmployee`
  - `PayrollManager`
  - `PayrollSystemApp`
- Objects are created dynamically using user input.

### Encapsulation
- All attributes are declared **private**.
- Access is controlled using **getters and setters**.
- Validation is added inside setters.

### Inheritance
- `FullTimeEmployee`, `PartTimeEmployee`, and `ContractEmployee` all **extend** the abstract superclass `Employee`.

### Polymorphism
- `calculateSalary()` is declared in the superclass and **overridden** in each subclass.
- Runtime polymorphism happens when `PayrollManager` stores all employee types in a `List<Employee>` and calls `calculateSalary()`.

### Abstraction
- `Employee` is an **abstract class**.
- `Taxable` is an **interface** used by taxable employee types.

### Basic Java Programming
- `Scanner` for user input
- `switch` for menu navigation
- `do-while` loop for repeating the menu
- `if` validation in input handling and update/search logic
- Formatted output using `printf` and `DecimalFormat`
- Optional startup preload for demo data
- Stronger input validation for required text, rates (0 to 1), and positive integer fields
- Employee listing sorted by Employee ID for consistent output
- Payroll summary includes highest and lowest salary employees

---

## 4) UML Class Diagram (Simple)

You can use this text-based UML in your report or redraw it in Draw.io / Lucidchart / StarUML.

```mermaid
classDiagram
    class Employee {
        -String employeeId
        -String fullName
        -String department
        +getEmployeeId() String
        +setEmployeeId(String) void
        +getFullName() String
        +setFullName(String) void
        +getDepartment() String
        +setDepartment(String) void
        +getEmployeeType() String
        +calculateSalary() double
        +getPayrollDetails() String
        +displayInfo() void
    }

    class Taxable {
        <<interface>>
        +calculateTax() double
    }

    class FullTimeEmployee {
        -double basicSalary
        -double monthlyAllowance
        -double performanceBonus
        -double taxRate
        +calculateTax() double
        +calculateSalary() double
        +getEmployeeType() String
        +getPayrollDetails() String
    }

    class PartTimeEmployee {
        -double hourlyRate
        -int hoursWorked
        +calculateSalary() double
        +getEmployeeType() String
        +getPayrollDetails() String
    }

    class ContractEmployee {
        -double contractAmount
        -int contractMonths
        -double withholdingTaxRate
        +calculateMonthlyPayment() double
        +calculateTax() double
        +calculateSalary() double
        +getEmployeeType() String
        +getPayrollDetails() String
    }

    class PayrollManager {
        -List~Employee~ employees
        +addEmployee(Employee) boolean
        +findEmployeeById(String) Employee
        +updateDepartment(String, String) boolean
        +deleteEmployeeById(String) boolean
        +displayAllEmployees() void
        +calculateTotalPayroll() double
        +calculateAverageSalary() double
        +generatePayrollSummary() void
    }

    class PayrollSystemApp {
        +main(String[]) void
    }

    Employee <|-- FullTimeEmployee
    Employee <|-- PartTimeEmployee
    Employee <|-- ContractEmployee
    Taxable <|.. FullTimeEmployee
    Taxable <|.. ContractEmployee
    PayrollManager "1" o-- "many" Employee
    PayrollSystemApp ..> PayrollManager
```

---

## 5) Compile and Run

### Compile
Open terminal inside the `src` folder and run:

```bash
javac *.java
```

### Run

If you are already inside `src`:

```bash
java PayrollSystemApp
```

If you are at the project root (`EmployeePayrollSystem`):

```bash
java -cp src PayrollSystemApp
```

When the app starts, it now asks whether to preload sample employee data for demo/testing.

---

## 6) Sample Output

```text
============================================================
Welcome to Employee Payroll System
============================================================
Load sample employees for demo? (y/n): y
Sample data loaded successfully.

============================================================
        EMPLOYEE PAYROLL SYSTEM (JAVA CONSOLE APP)
============================================================
1. Add Full-Time Employee
2. Add Part-Time Employee
3. Add Contract Employee
4. Display All Employees
5. Search Employee by ID
6. Update Employee Department
7. Generate Payroll Summary
8. Delete Employee
9. Exit
============================================================
Current Employee Count: 3
Enter your choice: 4
------------------------------------------------------------
Employee ID   : E001
Name          : Alicia Tan
Department    : Human Resources
Type          : Full-Time Employee
Basic Salary  : RM 4,200.00
Allowance     : RM 500.00
Bonus         : RM 300.00
Tax Deduction : RM 400.00
Net Salary    : RM 4,600.00
------------------------------------------------------------
```

---

## 7) Short Explanation for Your Report

### Introduction
This project is an Employee Payroll System developed using Java. The purpose of the system is to manage employees and calculate salaries using Object-Oriented Programming principles.

### Problem Description
Companies need a simple payroll system to manage different types of employees such as full-time, part-time, and contract employees. Since each employee type has a different salary calculation method, OOP is suitable because it improves code organization, reusability, and maintainability.

### OOP Concepts Used
- **Class**: Blueprint for objects, such as `Employee` and `PayrollManager`.
- **Object**: A real instance of a class, such as a full-time employee object.
- **Encapsulation**: Private fields and controlled access through setters/getters.
- **Inheritance**: Subclasses extend `Employee`.
- **Polymorphism**: Different employee types respond differently to `calculateSalary()`.
- **Abstraction**: `Employee` is abstract and hides implementation details.

### Conclusion
The Employee Payroll System successfully demonstrates the core OOP principles in Java. It shows how abstraction, inheritance, encapsulation, and polymorphism can be combined to build a structured, reusable, and scalable application.

---

## 8) GitHub Submission Tip

When uploading to GitHub:
1. Create a new repository named: `employee-payroll-system-java`
2. Upload all files from this ZIP.
3. Copy your GitHub repository URL into your report.

Example GitHub URL format:
```text
https://github.com/your-username/employee-payroll-system-java
```

---

## 9) Important Final Reminder

Before submitting to LMS, make sure your report includes:
- Full Name
- Student ID
- Class Code
- Program
- NRIC/Passport Number
- GitHub repository URL
- UML diagram
- Screenshots of program output
- Source code summary

Good luck — this project is already structured to align strongly with the rubric.
