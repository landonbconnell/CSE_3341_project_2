public class Loop {

    Cond cond;
    StmtSeq stmt_seq;

    void parse() {

        Parser.scanner.nextToken();

        cond = new Cond();
        cond.parse();

        if (!Parser.currentTokenIs(Core.DO)) {
            System.out.println("ERROR: expected 'do'.");
            System.exit(0);
        }

        Parser.scanner.nextToken();

        stmt_seq = new StmtSeq();
        stmt_seq.parse();

        if (!Parser.currentTokenIs(Core.END)) {
            System.out.println("ERROR: expected 'end'.");
            System.exit(0);
        }
    }

    void printer() {
        System.out.print("while ");
        cond.printer();
        System.out.println(" do");

        stmt_seq.printer();

        System.out.println("end");
    }

    void check() {
        cond.check();
        stmt_seq.check();
    }
}
