class Product {
    String name;
    int code;
    double amount;

    public Product(String n, int c, double p) {
        name = n;
        code = c;
        amount = p;
    }

    public void show() {
        System.out.println("Product Name: " + name);
        System.out.println("Product Code: " + code);
        System.out.println("Sale Amount: " + amount);
    }
}

class Sales extends Product {
    int day;
    double tax;
    double totamt;

    public Sales(String n, int c, double p, int day) {
        super(n, c, p);
        this.day = day;
        this.tax = 0.0;
        this.totamt = 0.0;
    }

    public void compute() {
        if (day > 30) {
            double fine = 0.025 * amount;
            totamt = amount + tax + fine;
        } else {
            totamt = amount + tax;
        }
    }

    public void show() {
        super.show();
        System.out.println("Number of Days Taken: " + day);
        System.out.println("Service Tax: " + tax);
        System.out.println("Total Amount Paid: " + totamt);
    }
}

public class Main {
    public static void main(String[] args) {
        Sales sales = new Sales("Product A", 123, 1000.0, 45);

        sales.tax = 0.124 * sales.amount; // Calculate service tax
        sales.compute();
        sales.show();
    }
}
