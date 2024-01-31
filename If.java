public class If {

    Cond cond;
    StmtSeq stmt_seq1, stmt_seq2;

    void parse() {

        Parser.scanner.nextToken();

        cond = new Cond();
        cond.parse();

        if (!Parser.currentTokenIs(Core.THEN)) {
            System.out.println("ERROR: expected 'then'.");
            System.exit(0);
        }

        Parser.scanner.nextToken();

        stmt_seq1 = new StmtSeq();
        stmt_seq1.parse();

        if (Parser.currentTokenIs(Core.ELSE)) {
            Parser.scanner.nextToken();

            stmt_seq2 = new StmtSeq();
            stmt_seq2.parse();
        }

        if (!Parser.currentTokenIs(Core.END)) {
            System.out.println("ERROR: expected 'end'.");
            System.exit(0);
        }
    }

    void printer() {
        System.out.print("\tif ");
        cond.printer();
        System.out.println(" then");
        
        stmt_seq1.printer();

        if (stmt_seq2 != null) {
            System.out.println("\telse");
            stmt_seq2.printer();
        }

        System.out.println("end");
    }
}
