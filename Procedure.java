public class Procedure {
    String procedureName;
    DeclSeq decl_seq;
    StmtSeq stmt_seq;

    void parse() {
        if (!Parser.currentTokenIs(Core.PROCEDURE)) {

            // TODO - these error messages are repeated a lot, maybe create a function

            System.out.println("ERROR: expected keyword 'procedure'.");
            System.exit(0);
        }

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
        // if (decl_seq != null) {
        //     decl_seq.printer();
        // }
    }
}