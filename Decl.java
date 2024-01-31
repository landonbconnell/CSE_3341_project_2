public class Decl {

    DeclInteger decl_int;
    DeclObj decl_obj;

    void parse() {

        if (Parser.currentTokenIs(Core.INTEGER)) {
            decl_int = new DeclInteger();
            decl_int.parse();
        } else if (Parser.currentTokenIs(Core.OBJECT)) {
            decl_obj = new DeclObj();
            decl_obj.parse();
        } else {
            System.out.println("ERROR: expected 'integer' or 'object'.");
            System.exit(0);
        }
    }

    void printer() {
        if (decl_int != null) {
            decl_int.printer();
        } else {
            decl_obj.printer();
        }
    }
}
