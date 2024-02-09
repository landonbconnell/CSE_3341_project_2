public class Decl {

    DeclInteger decl_int;
    DeclObj decl_obj;

    /**
     * Parses the <decl> non-terminal in the Core context-free-grammar, which is defined as:
     *      <decl> ::= <decl-integer> | <decl-obj>
     */
    void parse() {
        Parser.checkCurrentTokenIs(false, Core.INTEGER, Core.OBJECT);

        // <decl-integer> | <decl-obj>
        if (Parser.currentTokenIs(Core.INTEGER)) {
            // <decl-integer>
            decl_int = new DeclInteger();
            decl_int.parse();
        } else if (Parser.currentTokenIs(Core.OBJECT)) {
            // <decl-obj>
            decl_obj = new DeclObj();
            decl_obj.parse();
        }
    }

    void printer() {
        if (decl_int != null) {
            decl_int.printer();
        } else {
            decl_obj.printer();
        }
    }

    void check() {
        if (decl_int != null) {
            decl_int.check();
        } else {
            decl_obj.check();
        }
    }
}
