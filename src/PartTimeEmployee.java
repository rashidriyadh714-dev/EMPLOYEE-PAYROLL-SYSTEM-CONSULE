/**
 * Demonstrates INHERITANCE and METHOD OVERRIDING.
 */
public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String employeeId, String fullName, String department,
                            double hourlyRate, int hoursWorked) {
        super(employeeId, fullName, department);
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public final void setHourlyRate(double hourlyRate) {
        validateNonNegative(hourlyRate, "Hourly rate");
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public final void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative.");
        }
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        double grossPay = roundMoney(hourlyRate * hoursWorked);
        double attendanceBonus = hoursWorked >= 120 ? 200.00 : 0.00;
        return roundMoney(grossPay + attendanceBonus);
    }

    @Override
    public String getEmployeeType() {
        return "Part-Time Employee";
    }

    @Override
    public String getPayrollDetails() {
        double attendanceBonus = hoursWorked >= 120 ? 200.00 : 0.00;
        return "Hourly Rate   : RM " + MONEY.format(hourlyRate) + "\n"
             + "Hours Worked  : " + hoursWorked + " hours\n"
             + "Bonus         : RM " + MONEY.format(attendanceBonus);
    }
}
