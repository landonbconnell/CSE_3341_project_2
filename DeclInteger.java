public class DeclInteger {

    String identifier;

    void parse() {

        // current token has already been checked, it's integer
        Parser.scanner.nextToken();

        Parser.checkCurrentTokenIs(false, Core.ID);

        identifier = Parser.scanner.getId();
        Parser.scanner.nextToken();

        Parser.checkCurrentTokenIs(false, Core.SEMICOLON);
    }

    void printer() {
        System.out.println("\tinteger " + identifier + ";");
    }

    void check() {
        if (!SemanticChecker.isInCurrentScope(identifier)) {
            SemanticChecker.addVariableToCurrentScope(identifier, Type.INTEGER);
        } else {
            System.out.println("ERROR: '" + identifier + "' is declared multiple times in the same scope.");
            System.exit(0);
        }
    }
}
