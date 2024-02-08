public class Cmpr {

    Expr expr1, expr2;
    String comparator;

    void parse() {

        expr1 = new Expr();
        expr1.parse();

        Parser.checkCurrentTokenIs(false, Core.EQUAL, Core.LESS);

        if (Parser.currentTokenIs(Core.EQUAL)) {
            comparator = "==";
        } else if (Parser.currentTokenIs(Core.LESS)) {
            comparator = "<";
        }

        Parser.scanner.nextToken();

        expr2 = new Expr();
        expr2.parse();
    }

    void printer() {
        expr1.printer();
        System.out.print(" " + comparator + " ");
        expr2.printer();
    }

    void check() {
        expr1.check();
        expr2.check();
    }
}
