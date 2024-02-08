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
            System.out.println("ERROR: " + identifier + " already declared in current scope.");
            System.exit(0);
        }
    }
}
