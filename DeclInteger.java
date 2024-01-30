public class DeclInteger {

    String identifier;

    void parse() {
        // current token has already been checked, it's integer
        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.ID)) {
            System.out.println("ERROR: expected identifier.");
            System.exit(0);
        }

        identifier = Parser.scanner.getId();

        Parser.scanner.nextToken();

        if (!Parser.currentTokenIs(Core.SEMICOLON)) {
            System.out.println("ERROR: expected ';'.");
            System.exit(0);
        }
    }
}
