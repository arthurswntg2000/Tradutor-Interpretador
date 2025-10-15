public class Scanner {

    private final byte[] input;
    private int pos = 0;

    public Scanner(byte[] input) {
        this.input = input;
    }

    // Avança um caractere
    private void advance() {
        pos++;
    }

    // Olha o próximo caractere sem consumir
    private char peek() {
        if (pos >= input.length) return '\0';
        return (char) input[pos];
    }

    // Pula espaços em branco e quebras de linha
    private void skipWhitespace() {
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            advance();
            ch = peek();
        }
    }

    // Retorna o próximo token
    public Token nextToken() {
        skipWhitespace();
        char ch = peek();

        if (ch == '\0') {
            return new Token(TokenType.EOF, "");
        }

        // Números
        if (Character.isDigit(ch)) {
            StringBuilder num = new StringBuilder();
            while (Character.isDigit(peek())) {
                num.append(peek());
                advance();
            }
            return new Token(TokenType.NUMBER, num.toString());
        }

        // Identificadores ou palavras reservadas
        if (Character.isLetter(ch)) {
            StringBuilder id = new StringBuilder();
            while (Character.isLetterOrDigit(peek())) {
                id.append(peek());
                advance();
            }

            String word = id.toString();

            switch (word) {
                case "let":
                    return new Token(TokenType.LET, word);
                case "print":
                    return new Token(TokenType.PRINT, word);
                default:
                    return new Token(TokenType.IDENTIFIER, word);
            }
        }

        // Símbolos e operadores
        switch (ch) {
            case '+':
                advance();
                return new Token(TokenType.PLUS, "+");
            case '-':
                advance();
                return new Token(TokenType.MINUS, "-");
            case '=':
                advance();
                return new Token(TokenType.ASSIGN, "=");
            case ';':
                advance();
                return new Token(TokenType.SEMICOLON, ";");
            default:
                throw new Error("Erro léxico: caractere inesperado '" + ch + "'");
        }
    }
}
