public class Expr {

    Term term;
    char operator;
    Expr expr;

    void parse() {

        term = new Term();
        term.parse();

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
