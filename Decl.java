public class Decl {

    DeclInteger decl_int;
    DeclObj decl_obj;

    void parse() {

        Parser.checkCurrentTokenIs(Core.INTEGER, Core.OBJECT);

        if (Parser.currentTokenIs(Core.INTEGER)) {
            decl_int = new DeclInteger();
            decl_int.parse();
        } else if (Parser.currentTokenIs(Core.OBJECT)) {
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
