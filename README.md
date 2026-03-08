# Employee Payroll System (Java Console Application)

---

## 1. Project Overview
This project is a console-based Employee Payroll System developed in Java to demonstrate core Object-Oriented Programming (OOP) concepts and basic Java control structures.

The system supports payroll management for three employee categories:
- Full-Time Employee
- Part-Time Employee
- Contract Employee

---

## 2. Objectives
- Apply OOP principles in a real use case.
- Build a structured payroll program using Java classes and interfaces.
- Handle user input and menu-driven actions in a console environment.
- Perform salary calculations with consistent financial rounding.

---

## 3. Features Implemented
1. Add Full-Time Employee
2. Add Part-Time Employee
3. Add Contract Employee
4. Display All Employees (sorted by Employee ID)
5. Search Employee by ID
6. Update Employee Department
7. Generate Payroll Summary
8. Delete Employee by ID (with confirmation)
9. Exit Program

Additional quality improvements:
- Optional sample data preload at startup
- Input validation for empty fields, numeric values, rates, and positive integers
- Payroll summary includes total, average, highest salary, and lowest salary
- Currency calculations use 2-decimal financial rounding

---

## 4. OOP Concepts Demonstrated

### Class and Object
Multiple classes are used to model payroll operations and employee entities.

### Encapsulation
Fields are private and controlled via getters/setters with validation logic.

### Inheritance
`FullTimeEmployee`, `PartTimeEmployee`, and `ContractEmployee` inherit from abstract class `Employee`.

### Polymorphism
Each employee type overrides `calculateSalary()` and provides its own salary logic.

### Abstraction
`Employee` is abstract and defines common behavior for all employee types.

### Interface Usage
`Taxable` interface is implemented by taxable employee categories.

---

## 5. Project Structure (Source Code)

```text
Console-/
|- src/
|  |- Employee.java
|  |- Taxable.java
|  |- FullTimeEmployee.java
|  |- PartTimeEmployee.java
|  |- ContractEmployee.java
|  |- PayrollManager.java
|  |- PayrollSystemApp.java
|- README.md
```

---

## 6. Class Responsibilities
- `Employee.java`: Abstract base class with common fields and methods.
- `Taxable.java`: Interface defining tax behavior.
- `FullTimeEmployee.java`: Salary and tax calculation for full-time staff.
- `PartTimeEmployee.java`: Salary calculation based on hourly work.
- `ContractEmployee.java`: Monthly contract payment and withholding tax calculation.
- `PayrollManager.java`: Core business logic (add, search, update, delete, summary).
- `PayrollSystemApp.java`: Console menu and user interaction flow.

---

## 7. UML Class Diagram

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
		+hasEmployeeId(String) boolean
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

## 8. Compile and Run

### Compile
From the `src` folder:

```bash
javac *.java
```

### Run (Option 1)
From the `src` folder:

```bash
java PayrollSystemApp
```

### Run (Option 2)
From the project root:

```bash
java -cp src PayrollSystemApp
```

---

## 9. Sample Output (Complete Demonstration)

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

Enter your choice: 5

--- Search Employee ---
Enter employee ID to search: E002
Employee found:
------------------------------------------------------------
Employee ID   : E002
Name          : Daniel Lim
Department    : Sales
Type          : Part-Time Employee
Hourly Rate   : RM 25.00
Hours Worked  : 128 hours
Bonus         : RM 200.00
Net Salary    : RM 3,400.00
------------------------------------------------------------

Enter your choice: 6

--- Update Employee Department ---
Enter employee ID: E003
Enter new department: IT Operations
Department updated successfully.

Enter your choice: 7

================ PAYROLL SUMMARY ================
Total Employees   : 3
Full-Time         : 1
Part-Time         : 1
Contract          : 1
Total Payroll     : RM 10,910.00
Average Salary    : RM 3,636.67
Highest Salary    : Alicia Tan (E001) - RM 4,600.00
Lowest Salary     : Nur Aisyah (E003) - RM 2,910.00
===============================================

Enter your choice: 8

--- Delete Employee ---
Enter employee ID to delete: E003
Employee found:
------------------------------------------------------------
Employee ID   : E003
Name          : Nur Aisyah
Department    : IT Operations
Type          : Contract Employee
Contract Value : RM 18,000.00
Duration      : 6 months
Monthly Pay   : RM 3,000.00
Tax Deduction : RM 90.00
Net Salary    : RM 2,910.00
------------------------------------------------------------
Confirm deletion? (y/n): y
Employee deleted successfully.

Enter your choice: 7

================ PAYROLL SUMMARY ================
Total Employees   : 2
Full-Time         : 1
Part-Time         : 1
Contract          : 0
Total Payroll     : RM 8,000.00
Average Salary    : RM 4,000.00
Highest Salary    : Alicia Tan (E001) - RM 4,600.00
Lowest Salary     : Daniel Lim (E002) - RM 3,400.00
===============================================

Enter your choice: 9
Thank you for using the Employee Payroll System. Goodbye!
```

---

## 10. Conclusion
The Employee Payroll System successfully demonstrates OOP principles in Java through a practical and structured payroll application. The system is modular, readable, and suitable for academic submission with complete core functionality and validation.

---


