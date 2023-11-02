class Stock {
    String item;
    int qt;
    double rate;
    double amt;

    public Stock(String item, int qt, double rate) {
        this.item = item;
        this.qt = qt;
        this.rate = rate;
        this.amt = qt * rate;
    }

    public void display() {
        System.out.println("Item: " + item);
        System.out.println("Quantity in Stock: " + qt);
        System.out.println("Unit Price: " + rate);
        System.out.println("Total Stock Value: " + amt);
    }
}

class Purchase extends Stock {
    int pqt;
    double prate;

    public Purchase(String item, int qt, double rate, int pqt, double prate) {
        super(item, qt, rate);
        this.pqt = pqt;
        this.prate = prate;
    }

    public void update() {
        qt += pqt; // Update quantity in stock
        if (rate != prate) {
            rate = prate; // Replace unit price if it's different
        }
        amt = qt * rate; // Update stock value
    }

    public void display() {
        super.display();
        System.out.println("Purchased Quantity: " + pqt);
        System.out.println("Purchase Unit Price: " + prate);
    }
}

public class Main {
    public static void main(String[] args) {
        Stock stock = new Stock("Item A", 100, 10.0);
        Purchase purchase = new Purchase("Item A", 50, 12.0, 30, 11.0);

        System.out.println("Before Purchase:");
        stock.display();

        System.out.println("\nPurchase Details:");
        purchase.display();

        purchase.update();

        System.out.println("\nAfter Purchase:");
        stock.display();
    }
}
