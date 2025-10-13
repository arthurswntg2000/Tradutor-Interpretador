// src/Parser.java
public class Parser {
    private String input;
    private int pos = 0;
    private char lookahead;

    public Parser(String input) {
        this.input = input;
        this.lookahead = input.charAt(0);
    }

    private void next() {
        pos++;
        if (pos < input.length())
            lookahead = input.charAt(pos);
        else
            lookahead = '$'; // fim de cadeia
    }

    private void match(char expected) {
        if (lookahead == expected) {
            next();
        } else {
            throw new RuntimeException("Erro de sintaxe: esperado '" + expected + "' mas encontrado '" + lookahead + "'");
        }
    }

    // Grammar: E -> T { (+|-) T }
    public void parse() {
        expr();
        if (lookahead != '$')
            throw new RuntimeException("Erro: entrada não consumida.");
    }

    private void expr() {
        term();
        while (lookahead == '+' || lookahead == '-') {
            char op = lookahead;
            next();
            term();
            System.out.println(op + " ");
        }
    }

    private void term() {
        if (Character.isDigit(lookahead)) {
            System.out.println(lookahead + " ");
            next();
        } else {
            throw new RuntimeException("Erro: dígito esperado, encontrado '" + lookahead + "'");
        }
    }
}
