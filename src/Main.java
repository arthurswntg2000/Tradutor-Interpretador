
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a expressão: ");
        String input = sc.nextLine();
        Parser parser = new Parser(input);
        parser.parse();
        sc.close();
    }
}
