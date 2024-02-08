public class Factor {

    String identifier;
    Integer constant;
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

                Parser.scanner.nextToken();
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

            Parser.scanner.nextToken();

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
        } else {
            System.out.println("ERROR: expected identifier, constant, '(' or 'in()'.");
            System.exit(0);
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
                System.out.println("ERROR: " + identifier + " has not been declared.");
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
