public class StmtSeq {

    Stmt stmt;
    StmtSeq stmt_seq;

    /**
     * Parses the <stmt-seq> non-terminal in the Core context-free-grammar, which is defined as:
     *      <stmt-seq> ::= <stmt> | <stmt><stmt-seq>
     */
    void parse() {

        stmt = new Stmt();
        stmt.parse();

        Parser.scanner.nextToken();

        // <stmt><stmt-seq>
        if (!Parser.currentTokenIs(Core.END) && !Parser.currentTokenIs(Core.ELSE)) {
            stmt_seq = new StmtSeq();
            stmt_seq.parse();
        }
    }

    void printer() {
        stmt.printer();
        if (stmt_seq != null) {
            stmt_seq.printer();
        }
    }

    void check() {
        stmt.check();
        if (stmt_seq != null) {
            stmt_seq.check();
        }
    }
}
