class Worker {
    String name;
    double basic;

    public Worker(String name, double basic) {
        this.name = name;
        this.basic = basic;
    }

    public void display() {
        System.out.println("Worker Name: " + name);
        System.out.println("Basic Pay: " + basic);
    }
}

class Wages extends Worker {
    double hrs;
    double rate;
    double wage;

    public Wages(String name, double basic, double hrs, double rate) {
        super(name, basic);
        this.hrs = hrs;
        this.rate = rate;
    }

    public double overtime() {
        if (hrs > 40) {
            return (hrs - 40) * rate;
        }
        return 0;
    }

    public void display() {
        super.display();
        double overtimeAmount = overtime();
        wage = basic + overtimeAmount;
        System.out.println("Hours Worked: " + hrs);
        System.out.println("Rate Per Hour: " + rate);
        System.out.println("Overtime Amount: " + overtimeAmount);
        System.out.println("Monthly Wage: " + wage);
    }
}

public class Main {
    public static void main(String[] args) {
        Wages wages = new Wages("John Doe", 2000.0, 45.0, 20.0);

        wages.display();
    }
}
