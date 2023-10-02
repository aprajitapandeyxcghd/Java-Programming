import java.util.Scanner;
public class reverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = scanner.nextLine();
        // Create a new character array to store the reversed string
        char[] reversed = new char[str.length()];
        // Start from the last character of the string and copy each character to the reversed array
        for (int i = str.length() - 1, j = 0; i >= 0; i--, j++) {
            reversed[j] = str.charAt(i);
        }
        // Convert the character array to a string and print it
        String reversedStr = new String(reversed);
        System.out.println("Reversed string: " + reversedStr);
    }
}
