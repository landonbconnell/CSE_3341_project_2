public class Out {
    Expr expr;

    /**
     * Parses the <out> non-terminal in the Core context-free-grammar, which is defined as:
     *      <out> ::= out ( <expr> ); 
     */
    void parse() {
        Parser.scanner.nextToken();
        Parser.checkCurrentTokenIs(true, Core.LPAREN);

        expr = new Expr();
        expr.parse();

        Parser.checkCurrentTokenIs(true, Core.RPAREN);
        Parser.checkCurrentTokenIs(false, Core.SEMICOLON);
    }

    void printer() {
        System.out.print("out(");
        expr.printer();
        System.out.println(");");
    }

    void check() {
        expr.check();
    }
}
