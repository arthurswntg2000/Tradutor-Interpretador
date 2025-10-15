import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final Scanner scanner;
    private Token currentToken;
    private final List<String> output = new ArrayList<>();

    public Parser(byte[] input) {
        this.scanner = new Scanner(input);
        this.currentToken = scanner.nextToken();
    }

    private void match(TokenType expected) {
        if (currentToken.type == expected) {
            currentToken = scanner.nextToken();
        } else {
            throw new Error("Syntax error: expected " + expected + " but found " + currentToken.type);
        }
    }

    
    public String output() {
        return String.join(System.lineSeparator(), output);
    }

   
    public void parse() {
        statements();
    }

   
    private void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    
    private void statement() {
        if (currentToken.type == TokenType.LET) {
            letStatement();
        } else if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else {
            throw new Error("Syntax error at statement: unexpected token " + currentToken.type);
        }
    }

   
    private void letStatement() {
        match(TokenType.LET);
        String varName = currentToken.lexeme;
        match(TokenType.IDENTIFIER);
        match(TokenType.ASSIGN);
        expr();
        output.add("pop " + varName);
        match(TokenType.SEMICOLON);
    }

 
    private void printStatement() {
        match(TokenType.PRINT);
        expr();
        output.add("print");
        match(TokenType.SEMICOLON);
    }

   
    private void expr() {
        term();
        while (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
            Token op = currentToken;
            match(op.type);
            term();
            if (op.type == TokenType.PLUS) {
                output.add("add");
            } else {
                output.add("sub");
            }
        }
    }

  
    private void term() {
        if (currentToken.type == TokenType.NUMBER) {
            output.add("push " + currentToken.lexeme);
            match(TokenType.NUMBER);
        } else if (currentToken.type == TokenType.IDENTIFIER) {
            output.add("push " + currentToken.lexeme);
            match(TokenType.IDENTIFIER);
        } else {
            throw new Error("Syntax error: expected NUMBER or IDENTIFIER but found " + currentToken.type);
        }
    }
}
