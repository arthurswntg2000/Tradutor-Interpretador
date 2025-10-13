
public class Parser {
    private Scanner scanner;
    private Token currentToken;

    public Parser(String input) {
        this.scanner = new Scanner(input);
        this.currentToken = scanner.nextToken();
    }

    private void eat(Token.Type type) {
        if (currentToken.getType() == type) {
            currentToken = scanner.nextToken();
        } else {
            throw new RuntimeException("Erro de sintaxe: esperado " + type + " mas encontrado " + currentToken);
        }
    }

    public void parse() {
        expr();
        if (currentToken.getType() != Token.Type.EOF) {
            throw new RuntimeException("Erro: entrada não consumida completamente.");
        }
    }

    
    private void expr() {
        number();
        oper();
    }

   
    private void oper() {
        if (currentToken.getType() == Token.Type.PLUS) {
            eat(Token.Type.PLUS);
            number();
            System.out.println("+ ");
            oper();
        } else if (currentToken.getType() == Token.Type.MINUS) {
            eat(Token.Type.MINUS);
            number();
            System.out.println("- ");
            oper();
        }
    }

    
    private void number() {
        if (currentToken.getType() == Token.Type.NUMBER) {
            System.out.println(currentToken.getValue() + " ");
            eat(Token.Type.NUMBER);
        } else {
            throw new RuntimeException("Erro: número esperado, encontrado " + currentToken);
        }
    }
}
