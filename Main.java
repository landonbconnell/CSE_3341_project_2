class Main {

    static Scanner scanner;

    public static void main(String[] args) {
    /*
    *  1. Instantiate the scanner with the file name given as a command line argument. 
    *  2. Generate a parse tree for the input Core program using recursive descent.
    *  3. Perform semantic checks on the parse tree.
    *  4. Use recursive descent to print the Core program from the parse tree.
    */
        Parser parser = new Parser(args[0]);
        parser.start();

        parser.procedure.printer();
    }
}