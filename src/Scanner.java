import java.util.HashMap;
import java.util.Map;

public class Scanner {

    private final byte[] input;
    private int current = 0;

    
    private static final Map<String, TokenType> keywords;
    static {
        keywords = new HashMap<>();
        keywords.put("let", TokenType.LET);
    }

   
    public Scanner(byte[] input) {
        this.input = input;
    }

    
    private char peek() {
        if (current >= input.length) return '\0';
        return (char) input[current];
    }

    private void advance() {
        if (current < input.length) current++;
    }

    private void skipWhitespace() {
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            advance();
            ch = peek();
        }
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || Character.isDigit(c);
    }

    
    private Token number() {
        int start = current;
        while (Character.isDigit(peek())) {
            advance();
        }

        String n = new String(input, start, current - start);
        return new Token(TokenType.NUMBER, n);
    }

   
    private Token identifier() {
        int start = current;
        while (isAlphaNumeric(peek())) advance();

        String id = new String(input, start, current - start);
        TokenType type = keywords.get(id);
        if (type == null) type = TokenType.IDENT;
        return new Token(type, id);
    }

   
    public Token nextToken() {
        skipWhitespace();

        char ch = peek();

        
        if (ch == '\0') {
            return new Token(TokenType.EOF, "EOF");
        }


        if (ch == '0') {
            advance();
            return new Token(TokenType.NUMBER, "0");
        } else if (Character.isDigit(ch)) {
            return number();
        }

      
        if (isAlpha(ch)) {
            return identifier();
        }

        
        switch (ch) {
            case '+':
                advance();
                return new Token(TokenType.PLUS, "+");
            case '-':
                advance();
                return new Token(TokenType.MINUS, "-");
            case '=':
                advance();
                return new Token(TokenType.EQ, "=");
            case ';':
                advance();
                return new Token(TokenType.SEMICOLON, ";");
            default:
                throw new Error("lexical error at " + ch);
        }
    }

   
    public static void main(String[] args) {
        String input = "let a = 42 + preco - 876;";
        Scanner scan = new Scanner(input.getBytes());

        for (Token tk = scan.nextToken(); tk.type != TokenType.EOF; tk = scan.nextToken()) {
            System.out.println(tk);
        }
    }
}
