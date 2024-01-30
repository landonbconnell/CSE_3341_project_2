public class Parser {

    public static Scanner scanner;
    public Procedure procedure;

    public Parser(String fileName) {
        scanner = new Scanner(fileName);
    }

    public static boolean currentTokenIs(Core token) {
        return scanner.currentToken() == token;
    }

    void start() {
        procedure = new Procedure();
        procedure.parse();
    }
}
