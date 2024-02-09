public class Loop {

    Cond cond;
    StmtSeq stmt_seq;

    /**
     * Parses the <loop> non-terminal in the Core context-free-grammar, which is defined as:
     *      <loop> ::= while <cond> do <stmt-seq> end
     */
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
