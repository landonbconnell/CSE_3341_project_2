import java.util.Map;
import java.util.Set;

public class DeclObj {

    String identifier;

    void parse() {

        // current token has already been checked, it's object
        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.ID)) {
            System.out.println("ERROR: expected identifier.");
            System.exit(0);
        }

        identifier = Parser.scanner.getId();

        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.SEMICOLON)) {
            System.out.println("ERROR: expected ';'.");
            System.exit(0);
        }
    }

    void printer() {
        System.out.println("\tobject " + identifier + ";");
    }

    void check() {
        if (!SemanticChecker.isInScope(identifier)) {
            Set<Variable> currentScope = SemanticChecker.scopes.getFirst();
            currentScope.add(new Variable(identifier, Type.OBJECT));
        } else {
            System.out.println("ERROR: " + identifier + " already declared in scope.");
            System.exit(0);
        }
    }
}