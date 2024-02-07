public class Term {
    
    Factor factor;
    char operator;
    Term term;

    void parse() {

        factor = new Factor();
        factor.parse();

        if (Parser.currentTokenIs(Core.MULTIPLY) || Parser.currentTokenIs(Core.DIVIDE)) {
            operator = Parser.currentTokenIs(Core.MULTIPLY) ? '*' : '/';
            
            Parser.scanner.nextToken();

            term = new Term();
            term.parse();
        }
    }

    void printer() {
        factor.printer();
        if (term != null) {
            System.out.print(" " + operator + " ");
            term.printer();
        }
    }

    void check() {
        factor.check();
        if (term != null) term.check();
    }
}
