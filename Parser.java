public class Parser {

    public static Scanner scanner;
    public Procedure procedure;

    public Parser(String fileName) {
        scanner = new Scanner(fileName);
    }

    public static boolean currentTokenIs(Core token) {
        return scanner.currentToken() == token;
    }

    public static void checkCurrentTokenIs(Core expected) {
        if (!currentTokenIs(expected)) {
            System.out.println("ERROR: expected '" + tokenToString(expected) + "' but received '" + tokenToString(scanner.currentToken()) + "'.");
            System.exit(0);
        }
    }

    void start() {
        procedure = new Procedure();
        procedure.parse();
    }

    private static String tokenToString(Core token) {
        switch (token) {
            case PROCEDURE:
            case BEGIN:
            case IS:
            case END:
            case IF:
            case ELSE:
            case IN:
            case INTEGER:
            case RETURN:
            case DO:
            case NEW:
            case NOT:
            case AND:
            case OR:
            case OUT:
            case OBJECT:
            case THEN:
            case WHILE:
            case ID:
            case CONST:
            case ERROR:
                return token.name().toLowerCase();
            case ADD:
                return "+";
            case SUBTRACT:
                return "-";
            case MULTIPLY:
                return "*";
            case DIVIDE:
                return "/";
            case ASSIGN:
                return "=";
            case EQUAL:
                return "==";
            case LESS:
                return "<";
            case COLON:
                return ":";
            case SEMICOLON:
                return ";";
            case PERIOD:
                return ".";
            case COMMA:
                return ",";
            case LPAREN:
                return "(";
            case RPAREN:
                return ")";
            case LBRACE:
                return "[";
            case RBRACE:
                return "]";
            case EOS:
                return "end-of-stream";
            default:
                return "";
        }
    }
}
