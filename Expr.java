public class Expr {

    Term term;
    char operator;
    Expr expr;

    /**
     * Parses the <expr> non-terminal in the Core context-free-grammar, which is defined as:
     *      <expr> ::= <term> | <term> + <expr> | <term> – <expr>
     */
    void parse() {

        // <term>
        term = new Term();
        term.parse();

        // <term> + <expr> | <term> – <expr>
        if (Parser.currentTokenIs(Core.ADD) || Parser.currentTokenIs(Core.SUBTRACT)) {
            operator = Parser.currentTokenIs(Core.ADD) ? '+' : '-';
            
            Parser.scanner.nextToken();

            expr = new Expr();
            expr.parse();
        }
    }

    void printer() {
        term.printer();
        if (expr != null) {
            System.out.print(" " + operator + " ");
            expr.printer();
        }
    }

    void check() {
        term.check();
        if (expr != null) expr.check();
    }
}
