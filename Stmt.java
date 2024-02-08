public class Stmt {
    
    Assign assign;
    If if_stmt;
    Loop loop;
    Out out;
    Decl decl;

    void parse() {

        Parser.checkCurrentTokenIs(Core.ID, Core.IF, Core.WHILE, Core.OUT, Core.INTEGER, Core.OBJECT);

        if (Parser.currentTokenIs(Core.ID)) {
            assign = new Assign();
            assign.parse();
        } else if (Parser.currentTokenIs(Core.IF)) {
            if_stmt = new If();
            if_stmt.parse();
        } else if (Parser.currentTokenIs(Core.WHILE)) {
            loop = new Loop();
            loop.parse();
        } else if (Parser.currentTokenIs(Core.OUT)) {
            out = new Out();
            out.parse();
        } else if (Parser.currentTokenIs(Core.INTEGER) || Parser.currentTokenIs(Core.OBJECT)) {
            decl = new Decl();
            decl.parse();
        }
    }

    void printer() {
        if (assign != null) {
            assign.printer();
        } else if (if_stmt != null) {
            if_stmt.printer();
        } else if (loop != null) {
            loop.printer();
        } else if (out != null) {
            out.printer();
        } else if (decl != null) {
            decl.printer();
        }
    }

    void check() {
        if (assign != null) {
            assign.check();
        } else if (if_stmt != null) {
            SemanticChecker.pushNewScope();
            if_stmt.check();
            SemanticChecker.popScope();
        } else if (loop != null) {
            SemanticChecker.pushNewScope();
            loop.check();
            SemanticChecker.popScope();
        } else if (out != null) {
            out.check();
        } else if (decl != null) {
            decl.check();
        }
    }
}
