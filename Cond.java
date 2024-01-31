public class Cond {
    
    Cond cond;
    Cmpr cmpr;
    String modifier;

    void parse() {

        if (Parser.currentTokenIs(Core.NOT)) {
            modifier = "not";

            Parser.scanner.nextToken();

            cond = new Cond();
            cond.parse();
            
        } else if (Parser.currentTokenIs(Core.LBRACE)) {
            Parser.scanner.nextToken();

            cond = new Cond();
            cond.parse();

            if (!Parser.currentTokenIs(Core.RBRACE)) {
                System.out.println("ERROR: expected ']'.");
                System.exit(0);
            }

            Parser.scanner.nextToken();

        } else {
            Parser.scanner.nextToken();

            cmpr = new Cmpr();
            cmpr.parse();

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
            }
        }
    }
}
