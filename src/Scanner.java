
public class Scanner {
    private final String input;
    private int pos = 0;

    public Scanner(String input) {
        this.input = input;
    }

    public Token nextToken() {
        
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }

        
        if (pos >= input.length()) {
            return new Token(Token.Type.EOF, "");
        }

        char current = input.charAt(pos);

       
        if (Character.isDigit(current)) {
            StringBuilder number = new StringBuilder();
            while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                number.append(input.charAt(pos));
                pos++;
            }
            return new Token(Token.Type.NUMBER, number.toString());
        }

       
        if (current == '+') {
            pos++;
            return new Token(Token.Type.PLUS, "+");
        }

        if (current == '-') {
            pos++;
            return new Token(Token.Type.MINUS, "-");
        }

        throw new RuntimeException("Caractere inesperado: '" + current + "'");
    }
}
