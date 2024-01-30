public class Assign {
    
    String identifier1;
    String identifier2;
    Expr expr1;
    Expr expr2;

    void parse() {
        identifier1 = Parser.scanner.getId();

        Parser.scanner.nextToken();

        if (Parser.currentTokenIs(Core.EQUAL)) {
            Parser.scanner.nextToken();

            if (Parser.currentTokenIs(Core.NEW)) {
                Parser.scanner.nextToken();

                if (!Parser.currentTokenIs(Core.OBJECT)) {
                    System.out.println("ERROR: expected 'object'.");
                    System.exit(0);
                }

                Parser.scanner.nextToken();

                if (!Parser.currentTokenIs(Core.LPAREN)) {
                    System.out.println("ERROR: expected '('.");
                }

                Parser.scanner.nextToken();

                expr1 = new Expr();
                expr1.parse(); // should consume tokens until ')' or ';' is detected

                if (!Parser.currentTokenIs(Core.RPAREN)) {
                    System.out.println("ERROR: expected ')'.");
                    System.exit(0);
                }

                Parser.scanner.nextToken();
                
                if (!Parser.currentTokenIs(Core.SEMICOLON)) {
                    System.out.println("ERROR: expected ';'.");
                }
            }
        }
    }
}
