public class Cond {
    
    Cond cond;
    Cmpr cmpr;
    String modifier;

    /**
     * Parses the <cond> non-terminal in the Core context-free-grammar, which is defined as:
     *  <cond> ::= <cmpr> | not <cond> | [ <cond> ] | <cmpr> or <cond> | <cmpr> and <cond>
     */
    void parse() {

        // not <cond>
        if (Parser.currentTokenIs(Core.NOT)) {
            Parser.scanner.nextToken();

            modifier = "not";

            cond = new Cond();
            cond.parse();
        
        // [ <cond> ]
        } else if (Parser.currentTokenIs(Core.LBRACE)) {
            Parser.scanner.nextToken();

            cond = new Cond();
            cond.parse();

            Parser.checkCurrentTokenIs(true, Core.RBRACE);

        // <cmpr> | <cmpr> or <cond> | <cmpr> and <cond>
        } else {
            // <cmpr>
            cmpr = new Cmpr();
            cmpr.parse();

            // <cmpr> or <cond> | <cmpr> and <cond>
            if (Parser.currentTokenIs(Core.OR) || Parser.currentTokenIs(Core.AND)) {
                modifier = Parser.currentTokenIs(Core.OR) ? "or" : "and";

                cond = new Cond();
                cond.parse();
            }
        }
    }

    void printer() {

        if (cmpr == null && modifier == null) {

            System.out.print("[ ");
            cond.printer();
            System.out.print(" ]");

        } else {

            if (cmpr != null) {
                cmpr.printer();
            }
    
            if (modifier != null) {
                System.out.print((!modifier.equals("not") ? " " : "") + modifier + " ");
                cond.printer();
            }
        }
    }

    void check() {

        if (cmpr == null && modifier == null) {
            cond.check();
            
        } else {
            if (cmpr != null) {
                cmpr.check();
            }
    
            if (modifier != null) {
                cond.check();
            }
        }
    }
}
