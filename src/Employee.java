import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstract superclass that demonstrates ABSTRACTION and ENCAPSULATION.
 * All employee types share these common attributes and behaviors.
 */
public abstract class Employee {
    private String employeeId;
    private String fullName;
    private String department;

    protected static final DecimalFormat MONEY = new DecimalFormat("#,##0.00");

    public Employee(String employeeId, String fullName, String department) {
        setEmployeeId(employeeId);
        setFullName(fullName);
        setDepartment(department);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public final void setEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be empty.");
        }
        this.employeeId = employeeId.trim().toUpperCase();
    }

    public String getFullName() {
        return fullName;
    }

    public final void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty.");
        }
        this.fullName = fullName.trim();
    }

    public String getDepartment() {
        return department;
    }

    public final void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
        this.department = department.trim();
    }

    public abstract String getEmployeeType();

    /**
     * Abstract method to demonstrate polymorphism.
     * Each subclass calculates salary differently.
     */
    public abstract double calculateSalary();

    /**
     * Subclasses can override this to display their own extra details.
     */
    public String getPayrollDetails() {
        return "No additional payroll details.";
    }

    public void displayInfo() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Employee ID   : " + employeeId);
        System.out.println("Name          : " + fullName);
        System.out.println("Department    : " + department);
        System.out.println("Type          : " + getEmployeeType());
        System.out.println(getPayrollDetails());
        System.out.println("Net Salary    : RM " + MONEY.format(calculateSalary()));
    }

    protected void validateNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
    }

    /**
     * Applies standard financial rounding for all payroll values.
     */
    protected double roundMoney(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
