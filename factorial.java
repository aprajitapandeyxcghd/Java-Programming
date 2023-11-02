class Number {
    int n;

    public Number(int nn) {
        n = nn;
    }

    public int factorial(int a) {
        if (a <= 1) {
            return 1;
        }
        return a * factorial(a - 1);
    }

    public void display() {
        System.out.println("Number: " + n);
    }
}

class Series extends Number {
    int sum;

    public Series(int nn) {
        super(nn);
        sum = 0;
    }

    public void calsum() {
        for (int i = 1; i <= n; i++) {
            sum += factorial(i);
        }
    }

    public void display() {
        super.display();
        System.out.println("Sum of the series: " + sum);
    }
}

public class Main {
    public static void main(String[] args) {
        Series series = new Series(5);

        series.calsum();
        series.display();
    }
}
