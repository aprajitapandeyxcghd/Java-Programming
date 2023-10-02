import java.util.Scanner;

public class word {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word:");
        String word = scanner.next();
        System.out.println("Enter a line:");
        String line = scanner.nextLine();
        scanner.close();

        System.out.println(word + " " + line);
    }
}