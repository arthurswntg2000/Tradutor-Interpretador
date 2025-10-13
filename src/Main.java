
public class Main {
    public static void main(String[] args) {
        String input = "9-5+2";
        Parser parser = new Parser(input);
        parser.parse();
    }
}
