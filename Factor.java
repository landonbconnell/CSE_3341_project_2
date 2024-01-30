public class Factor {

    String identifier;
    int constant;
    Expr expr;

    void parse() {
        if (Parser.currentTokenIs(Core.ID)) {
            identifier = Parser.scanner.getId();

            Parser.scanner.nextToken();

            if (Parser.currentTokenIs(Core.LBRACE)) {
                Parser.scanner.nextToken();

                expr = new Expr();
                expr.parse();

                if (!Parser.currentTokenIs(Core.RBRACE)) {
                    System.out.println("ERROR: expected ']'.");
                    System.exit(0);
                }
            }

        } else if (Parser.currentTokenIs(Core.CONST)) {
            constant = Parser.scanner.getConst();
            Parser.scanner.nextToken();

        } else if (Parser.currentTokenIs(Core.LPAREN)) {
            Parser.scanner.nextToken();

            expr = new Expr();
            expr.parse();

            if (!Parser.currentTokenIs(Core.RPAREN)) {
                System.out.println("ERROR: expected ')'.");
                System.exit(0);
            }

        } else if (Parser.currentTokenIs(Core.IN)) {
            Parser.scanner.nextToken();

            if (!Parser.currentTokenIs(Core.LPAREN)) {
                System.out.println("ERROR: expected '('.");
                System.exit(0);
            }

            Parser.scanner.nextToken();

            if (!Parser.currentTokenIs(Core.RPAREN)) {
                System.out.println("ERROR: expected ')'.");
                System.exit(0);
            }

            Parser.scanner.nextToken();
        }
    }
}
