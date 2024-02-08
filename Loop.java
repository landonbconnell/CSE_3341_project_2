public class Loop {

    Cond cond;
    StmtSeq stmt_seq;

    void parse() {

        Parser.scanner.nextToken();

        cond = new Cond();
        cond.parse();

        Parser.checkCurrentTokenIs(true, Core.DO);

        stmt_seq = new StmtSeq();
        stmt_seq.parse();

        Parser.checkCurrentTokenIs(false, Core.END);
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
