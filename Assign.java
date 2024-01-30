public class Assign {
    
    String identifier1;
    String identifier2;
    Expr expr1;
    Expr expr2;
    boolean isInstantiatingObject = false;

    void parse() {
        identifier1 = Parser.scanner.getId();

        Parser.scanner.nextToken();

        if (Parser.currentTokenIs(Core.ASSIGN)) {
            Parser.scanner.nextToken();

            if (!Parser.currentTokenIs(Core.NEW)) {
                expr1 = new Expr();
                expr1.parse(); // should consume tokens until ')' or ';' is detected

            } else {
                isInstantiatingObject = true;

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
            }
        } else if (Parser.currentTokenIs(Core.LBRACE)) {
            Parser.scanner.nextToken();

            expr1 = new Expr();
            expr1.parse();

            if (!Parser.currentTokenIs(Core.RBRACE)) {
                System.out.println("ERROR: expected ']'.");
                System.exit(0);
            }

            Parser.scanner.nextToken();

            if (!Parser.currentTokenIs(Core.ASSIGN)) {
                System.out.println("ERROR: expected '='.");
                System.exit(0);
            }

            Parser.scanner.nextToken();

            expr2 = new Expr();
            expr2.parse();

        } else if (Parser.currentTokenIs(Core.COLON)) {
            
            Parser.scanner.nextToken();
            
            if (!Parser.currentTokenIs(Core.ID)) {
                System.out.println("ERROR: expected an identifier.");
                System.exit(0);
            }

            identifier2 = Parser.scanner.getId();

            Parser.scanner.nextToken();

        } else {
            System.out.println("ERROR: expected '=', ':', or '['.");
            System.exit(0);
        }

        if (!Parser.currentTokenIs(Core.SEMICOLON)) {
            System.out.println("ERROR: expected ';'.");
            System.exit(0);
        }
    }

    void printer() {

        // id = <expr>; | id = new object( <expr> );
        if ((expr1 != null) && (expr2 == null)) {

            // id = <expr>;
            if (!isInstantiatingObject) {
                System.out.print("\t" + identifier1 + " = ");
                expr1.printer();
                System.out.println(";");

                // id = new object( <expr> );
            } else {
                System.out.print("\t" + identifier1 + " = new object( ");
                expr1.printer();
                System.out.println(" );");
            }

        // id [ <expr> ] = <expr>;
        } else if ((expr1 != null) && (expr2 != null)) {
            System.out.print("\t" + identifier1 + " [ ");
            expr1.printer();
            System.out.print(" ] = ");
            expr2.printer();
            System.out.println(";");

        // id : id;
        } else if (identifier2 != null) {
            System.out.println("\t" + identifier1 + " : " + identifier2 + ";");
        }
    }
}
