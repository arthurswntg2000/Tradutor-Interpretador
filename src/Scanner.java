public class Scanner {

    private byte[] input;
    private int pos = 0;

    public Scanner(byte[] input) {
        this.input = input;
    }

    private char peek() {
        if (pos >= input.length) return '\0';
        return (char) input[pos];
    }

    private void advance() {
        pos++;
    }

    
    private void skipWhitespace() {
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            advance();
            ch = peek();
        }
    }

    public Token nextToken() {

        skipWhitespace(); 

        char ch = peek();
        if (ch == '\0') {
            return new Token(TokenType.EOF, "");
        } else if (Character.isDigit(ch)) {
            StringBuilder sb = new StringBuilder();
            while (Character.isDigit(peek())) {
                sb.append(peek());
                advance();
            }
            return new Token(TokenType.NUMBER, sb.toString());
        } else if (ch == '+') {
            advance();
            return new Token(TokenType.PLUS, "+");
        } else if (ch == '-') {
            advance();
            return new Token(TokenType.MINUS, "-");
        } else {
            throw new Error("lexical error: unexpected character '" + ch + "'");
        }
    }
}
