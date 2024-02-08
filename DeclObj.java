public class DeclObj {

    String identifier;

    void parse() {

        // current token has already been checked, it's object
        Parser.scanner.nextToken();

        Parser.checkCurrentTokenIs(Core.ID);

        identifier = Parser.scanner.getId();

        Parser.scanner.nextToken();

        Parser.checkCurrentTokenIs(Core.SEMICOLON);
    }

    void printer() {
        System.out.println("\tobject " + identifier + ";");
    }

    void check() {
        if (!SemanticChecker.isInCurrentScope(identifier)) {
            SemanticChecker.addVariableToCurrentScope(identifier, Type.OBJECT);
        } else {
            System.out.println("ERROR: " + identifier + " already declared current in scope.");
            System.exit(0);
        }
    }
}