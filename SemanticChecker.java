import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SemanticChecker {

    public static Deque<Set<Variable>> scopes;

    public SemanticChecker() {
        scopes = new ArrayDeque<>();
    }

    public static Variable getVariable(String identifier) {
        Variable variable = null;
        Iterator<Set<Variable>> scopeIterator = scopes.iterator();
        
        while (scopeIterator.hasNext()) {
            Iterator<Variable> variableIterator = scopeIterator.next().iterator();

            while (variableIterator.hasNext()) {
                Variable currentVariable = variableIterator.next();
                if (currentVariable.identifier.equals(identifier)) variable = currentVariable;
            }
        }

        return variable;
    }

    public static boolean isInScope(String identifier) {
        return getVariable(identifier) != null;
    }

    public static boolean isInCurrentScope(String identifier) {
        boolean isInCurrentScope = false;
        Set<Variable> currentScope = scopes.peekFirst();
        Iterator<Variable> it = currentScope.iterator();

        while (it.hasNext()) {
            Variable variable = it.next();
            if (variable.identifier.equals(identifier)) isInCurrentScope = true;
        }

        return isInCurrentScope;
    }

    public static void addVariableToCurrentScope(String identifier, Type type) {
        scopes.getFirst().add(new Variable(identifier, type));
    }

    public static void pushNewScope() {
        scopes.addFirst(new HashSet<>());
    }

    public static void popScope() {
        scopes.pop();
    }

    public void run(Procedure procedure) {
        procedure.check();
    }
}
