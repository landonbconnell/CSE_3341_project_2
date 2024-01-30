public class Expr {

    Term term;
    char operator;
    Expr expr;

    void parse() {
        term = new Term();
        term.parse();

        Parser.scanner.nextToken();

        if (Parser.currentTokenIs(Core.ADD) || Parser.currentTokenIs(Core.SUBTRACT)) {
            operator = Parser.currentTokenIs(Core.ADD) ? '+' : '-';
            
            Parser.scanner.nextToken();

            expr = new Expr();
            expr.parse();
        }
    }

    // String identifier;
    // int constant;

    // void parse() {
    //     if (Parser.currentTokenIs(Core.ID)) {
    //         identifier = Parser.scanner.getId();
    //     } else if (Parser.currentTokenIs(Core.CONST)) {
    //         constant = Parser.scanner.getConst();

    //     } else if (Parser.currentTokenIs(Core.LPAREN)) {
            
    //     } else if (Parser.currentTokenIs(Core.IN)) {

    //     } else {
    //         System.out.println("ERROR: expected an identifier, constant, '(', or 'in'.");
    //         System.exit(0);
    //     }
    // }
}
