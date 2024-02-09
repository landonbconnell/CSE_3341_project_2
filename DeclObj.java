public class DeclObj {

    String identifier;

    /**
     * Parses the <decl-obj> non-terminal in the Core context-free-grammar, which is defined as:
     *      <decl-obj> ::= object id ;
     */
    void parse() {
        Parser.scanner.nextToken();

        Parser.checkCurrentTokenIs(false, Core.ID);

        // saves identifier for later use
        identifier = Parser.scanner.getId();
        Parser.scanner.nextToken();

        Parser.checkCurrentTokenIs(false, Core.SEMICOLON);
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