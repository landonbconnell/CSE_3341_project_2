public class If {

    Cond cond;
    StmtSeq stmt_seq1, stmt_seq2;

    /**
     * Parses the <if> non-terminal in the Core context-free-grammar, which is defined as:
     *      <if> ::= if <cond> then <stmt-seq> end | if <cond> then <stmt-seq> else <stmt-seq> end
     */
    void parse() {

        Parser.scanner.nextToken();

        cond = new Cond();
        cond.parse();

        Parser.checkCurrentTokenIs(true, Core.THEN);

        stmt_seq1 = new StmtSeq();
        stmt_seq1.parse();

        // if <cond> then <stmt-seq> else <stmt-seq> end
        if (Parser.currentTokenIs(Core.ELSE)) {
            Parser.scanner.nextToken();

            stmt_seq2 = new StmtSeq();
            stmt_seq2.parse();
        }

        Parser.checkCurrentTokenIs(false, Core.END);
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

    void check() {
        cond.check();
        stmt_seq1.check();

        if (stmt_seq2 != null) {
            stmt_seq2.check();
        }
    }
}
