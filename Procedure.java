public class Procedure {
    String procedureName;
    DeclSeq decl_seq;
    StmtSeq stmt_seq;

    void parse() {

        Parser.checkCurrentTokenIs(Core.PROCEDURE);

        Parser.scanner.nextToken();

        Parser.checkCurrentTokenIs(Core.ID);

        procedureName = Parser.scanner.getId();
        Parser.scanner.nextToken();

        Parser.checkCurrentTokenIs(Core.IS);

        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.BEGIN)) {
            decl_seq = new DeclSeq();
            decl_seq.parse();

            Parser.checkCurrentTokenIs(Core.BEGIN);
        }

        Parser.scanner.nextToken();

        stmt_seq = new StmtSeq();
        stmt_seq.parse();

        Parser.checkCurrentTokenIs(Core.END);

        Parser.scanner.nextToken();

        Parser.checkCurrentTokenIs(Core.EOS);
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