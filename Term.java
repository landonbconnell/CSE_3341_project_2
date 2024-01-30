public class Term {
    
    Factor factor;
    char operator;
    Term term;

    void parse() {
        factor = new Factor();
        factor.parse();

        if (Parser.currentTokenIs(Core.MULTIPLY) || Parser.currentTokenIs(Core.DIVIDE)) {
            operator = Parser.currentTokenIs(Core.MULTIPLY) ? '*' : '/';
            
            Parser.scanner.nextToken();

            term = new Term();
            term.parse();
        }
    }
}
