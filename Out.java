public class Out {
    Expr expr;

    void parse() {
        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.LPAREN)) {
            System.out.println("ERROR: expected '('.");
            System.exit(0);
        }

        Parser.scanner.nextToken();

        expr = new Expr();
        expr.parse();

        if (!Parser.currentTokenIs(Core.RPAREN)) {
            System.out.println("ERROR: expected ')'.");
            System.exit(0);
        }

        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.SEMICOLON)) {
            System.out.println("ERROR: expected ';'.");
            System.exit(0);
        }
    }

    void printer() {
        System.out.print("out(");
        expr.printer();
        System.out.println(");");
    }
}
