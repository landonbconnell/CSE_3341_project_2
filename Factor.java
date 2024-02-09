public class Factor {

    String identifier;
    Integer constant;
    Expr expr;

    void parse() {

        Parser.checkCurrentTokenIs(false, Core.ID, Core.CONST, Core.LPAREN, Core.IN);

        if (Parser.currentTokenIs(Core.ID)) {
            identifier = Parser.scanner.getId();

            Parser.scanner.nextToken();

            if (Parser.currentTokenIs(Core.LBRACE)) {
                Parser.scanner.nextToken();

                expr = new Expr();
                expr.parse();

                Parser.checkCurrentTokenIs(true, Core.RBRACE);
            }

        } else if (Parser.currentTokenIs(Core.CONST)) {
            constant = Parser.scanner.getConst();
            Parser.scanner.nextToken();

        } else if (Parser.currentTokenIs(Core.LPAREN)) {
            Parser.scanner.nextToken();

            expr = new Expr();
            expr.parse();

            Parser.checkCurrentTokenIs(true, Core.RPAREN);

        } else if (Parser.currentTokenIs(Core.IN)) {
            Parser.scanner.nextToken();

            Parser.checkCurrentTokenIs(true, Core.LPAREN);
            Parser.checkCurrentTokenIs(true, Core.RPAREN);
        }
    }

    void printer() {

        // id
        if (identifier != null) {
            System.out.print(identifier);

            // id [ <expr> ]
            if (expr != null) {
                System.out.print(" [");
                expr.printer();
                System.out.print(" ]");
            }

        // const
        } else if (constant != null) {
            System.out.print(constant);

        // ( <expr> )
        } else if (expr != null) {
            System.out.print("( ");
            expr.printer();
            System.out.print(" )");

        // in ();
        } else {
            System.out.print("in()");
        }
    }

    void check() {
        // id
        if (identifier != null) {
            if (!SemanticChecker.isInScope(identifier)) {
                System.out.println("ERROR: '" + identifier + "' has not been declared.");
                System.exit(0);
            }

            // id [ <expr> ]
            if (expr != null) {
                Variable variable = SemanticChecker.getVariable(identifier);
                if (variable.type != Type.OBJECT) {
                    System.out.print("ERROR: the statement '" + identifier + "[");
                    expr.printer();
                    System.out.print("]' cannot be used on a variable with type 'integer'.");

                    System.exit(0);
                }
            }
        }
    }
}
