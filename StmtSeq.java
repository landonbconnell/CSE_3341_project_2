public class StmtSeq {

    Stmt stmt;
    StmtSeq stmt_seq;

    void parse() {
        stmt = new Stmt();
        stmt.parse();

        Parser.scanner.nextToken();

        // syntax is <stmt><stmt-seq>
        if (!Parser.currentTokenIs(Core.END)) {
            stmt_seq = new StmtSeq();
            stmt_seq.parse();
        }
    }
}
