public class DeclSeq {
    
    Decl decl;
    DeclSeq decl_seq;

    /**
     * Parses the <decl-seq> non-terminal in the Core context-free-grammar, which is defined as:
     *      <decl-seq> ::= <decl > | <decl><decl-seq> 
     */
    void parse() {

        // <decl>
        decl = new Decl();
        decl.parse();

        Parser.scanner.nextToken();

        // <decl><decl-seq>
        if (!Parser.currentTokenIs(Core.BEGIN)) {
            decl_seq = new DeclSeq();
            decl_seq.parse();
        }
    }

    void printer() {
        decl.printer();
        if (decl_seq != null) {
            decl_seq.printer();
        }
    }

    void check() {
        decl.check();
        if (decl_seq != null) {
            decl_seq.check();
        }
    }
}
