/**
 * Demonstrates INHERITANCE by extending Employee.
 */
public class FullTimeEmployee extends Employee implements Taxable {
    private double basicSalary;
    private double monthlyAllowance;
    private double performanceBonus;
    private double taxRate; // Example: 0.08 = 8%

    public FullTimeEmployee(String employeeId, String fullName, String department,
                            double basicSalary, double monthlyAllowance,
                            double performanceBonus, double taxRate) {
        super(employeeId, fullName, department);
        setBasicSalary(basicSalary);
        setMonthlyAllowance(monthlyAllowance);
        setPerformanceBonus(performanceBonus);
        setTaxRate(taxRate);
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public final void setBasicSalary(double basicSalary) {
        validateNonNegative(basicSalary, "Basic salary");
        this.basicSalary = basicSalary;
    }

    public double getMonthlyAllowance() {
        return monthlyAllowance;
    }

    public final void setMonthlyAllowance(double monthlyAllowance) {
        validateNonNegative(monthlyAllowance, "Monthly allowance");
        this.monthlyAllowance = monthlyAllowance;
    }

    public double getPerformanceBonus() {
        return performanceBonus;
    }

    public final void setPerformanceBonus(double performanceBonus) {
        validateNonNegative(performanceBonus, "Performance bonus");
        this.performanceBonus = performanceBonus;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public final void setTaxRate(double taxRate) {
        if (taxRate < 0 || taxRate > 1) {
            throw new IllegalArgumentException("Tax rate must be between 0 and 1.");
        }
        this.taxRate = taxRate;
    }

    @Override
    public double calculateTax() {
        double gross = roundMoney(basicSalary + monthlyAllowance + performanceBonus);
        return roundMoney(gross * taxRate);
    }

    @Override
    public double calculateSalary() {
        double gross = roundMoney(basicSalary + monthlyAllowance + performanceBonus);
        return roundMoney(gross - calculateTax());
    }

    @Override
    public String getEmployeeType() {
        return "Full-Time Employee";
    }

    @Override
    public String getPayrollDetails() {
        return "Basic Salary  : RM " + MONEY.format(basicSalary) + "\n"
             + "Allowance     : RM " + MONEY.format(monthlyAllowance) + "\n"
             + "Bonus         : RM " + MONEY.format(performanceBonus) + "\n"
             + "Tax Deduction : RM " + MONEY.format(calculateTax());
    }
}
