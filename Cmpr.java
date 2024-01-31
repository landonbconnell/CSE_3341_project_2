public class Cmpr {

    Expr expr1, expr2;
    String comparator;

    void parse() {

        expr1 = new Expr();
        expr1.parse();

        if (Parser.currentTokenIs(Core.EQUAL)) {
            comparator = "==";
        } else if (Parser.currentTokenIs(Core.LESS)) {
            comparator = "<";
        } else {
            System.out.println("ERROR: expected '==' or '<'.");
            System.exit(0);
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
}
