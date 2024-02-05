import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Set;

public class SemanticChecker {

    public static Deque<Set<Variable>> scopes;

    public SemanticChecker() {
        scopes = new ArrayDeque<>();
    }

    public static Variable getVariable(String identifier) {
        Iterator<Set<Variable>> scopeIterator = scopes.iterator();
        
        while (scopeIterator.hasNext()) {
            Iterator<Variable> variableIterator = scopeIterator.next().iterator();

            while (variableIterator.hasNext()) {
                Variable variable = variableIterator.next();
                if (variable.identifier.equals(identifier)) return variable;
            }
        }

        return null;
    }

    public static boolean isInScope(String identifier) {
        return getVariable(identifier) != null;
    }

    public void run(Procedure procedure) {
        procedure.check();
    }
}
