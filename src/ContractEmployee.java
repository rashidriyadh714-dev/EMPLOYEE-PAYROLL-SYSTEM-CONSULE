/**
 * Another subclass to strengthen the polymorphism example.
 */
public class ContractEmployee extends Employee implements Taxable {
    private double contractAmount;
    private int contractMonths;
    private double withholdingTaxRate;

    public ContractEmployee(String employeeId, String fullName, String department,
                            double contractAmount, int contractMonths,
                            double withholdingTaxRate) {
        super(employeeId, fullName, department);
        setContractAmount(contractAmount);
        setContractMonths(contractMonths);
        setWithholdingTaxRate(withholdingTaxRate);
    }

    public double getContractAmount() {
        return contractAmount;
    }

    public final void setContractAmount(double contractAmount) {
        validateNonNegative(contractAmount, "Contract amount");
        this.contractAmount = contractAmount;
    }

    public int getContractMonths() {
        return contractMonths;
    }

    public final void setContractMonths(int contractMonths) {
        if (contractMonths <= 0) {
            throw new IllegalArgumentException("Contract months must be greater than 0.");
        }
        this.contractMonths = contractMonths;
    }

    public double getWithholdingTaxRate() {
        return withholdingTaxRate;
    }

    public final void setWithholdingTaxRate(double withholdingTaxRate) {
        if (withholdingTaxRate < 0 || withholdingTaxRate > 1) {
            throw new IllegalArgumentException("Withholding tax rate must be between 0 and 1.");
        }
        this.withholdingTaxRate = withholdingTaxRate;
    }

    public double calculateMonthlyPayment() {
        return roundMoney(contractAmount / contractMonths);
    }

    @Override
    public double calculateTax() {
        return roundMoney(calculateMonthlyPayment() * withholdingTaxRate);
    }

    @Override
    public double calculateSalary() {
        return roundMoney(calculateMonthlyPayment() - calculateTax());
    }

    @Override
    public String getEmployeeType() {
        return "Contract Employee";
    }

    @Override
    public String getPayrollDetails() {
        return "Contract Value : RM " + MONEY.format(contractAmount) + "\n"
             + "Duration      : " + contractMonths + " months\n"
             + "Monthly Pay   : RM " + MONEY.format(calculateMonthlyPayment()) + "\n"
             + "Tax Deduction : RM " + MONEY.format(calculateTax());
    }
}
