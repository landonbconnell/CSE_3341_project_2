public class DeclSeq {
    
    Decl decl;
    DeclSeq decl_seq;

    void parse() {
        decl = new Decl();
        decl.parse();

        Parser.scanner.nextToken();

        // syntax is <decl><decl-seq>
        if (!Parser.currentTokenIs(Core.BEGIN)) {
            decl_seq = new DeclSeq();
            decl_seq.parse();
        }
    }
}
