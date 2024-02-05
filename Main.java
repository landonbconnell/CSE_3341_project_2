class Main {

    static Parser parser;
    static SemanticChecker semanticChecker;

    public static void main(String[] args) {
    /*
    *  1. Instantiate the scanner with the file name given as a command line argument. 
    *  2. Generate a parse tree for the input Core program using recursive descent.
    *  3. Perform semantic checks on the parse tree.
    *  4. Use recursive descent to print the Core program from the parse tree.
    */
        parser = new Parser(args[0]);
        semanticChecker = new SemanticChecker();

        parser.start();

        semanticChecker.run(parser.procedure);

        parser.procedure.printer();
    }
}