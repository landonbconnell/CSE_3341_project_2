public class Procedure {
    String procedureName;
    DeclSeq decl_seq;
    StmtSeq stmt_seq;

    void parse() {

        Parser.checkCurrentTokenIs(Core.PROCEDURE);

        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.ID)) {
            System.out.println("ERROR: expected identifier.");
            System.exit(0);
        }

        procedureName = Parser.scanner.getId();
        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.IS)) {
            System.out.println("ERROR: expected keyword 'is'.");
            System.exit(0);
        }

        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.BEGIN)) {
            decl_seq = new DeclSeq();
            decl_seq.parse();

            if (!Parser.currentTokenIs(Core.BEGIN)) {
                System.out.println("ERROR: expected keyword 'begin'.");
                System.exit(0);
            }
        }

        Parser.scanner.nextToken();

        stmt_seq = new StmtSeq();
        stmt_seq.parse();

        if (!Parser.currentTokenIs(Core.END)) {
            System.out.println("ERROR: expected keyword 'end'.");
            System.exit(0);
        }

        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.EOS)) {
            System.out.println("ERROR: expected end-of-stream.");
        }
    }

    void printer() {
        System.out.println("procedure " + procedureName + " is ");
        
        if (decl_seq != null) {
            decl_seq.printer();
        }

        System.out.println("begin");

        stmt_seq.printer();

        System.out.println("end");
    }

    void check() {
        SemanticChecker.pushNewScope();

        if (decl_seq != null) {
            decl_seq.check();
        }

        SemanticChecker.pushNewScope();

        stmt_seq.check();
    }
}