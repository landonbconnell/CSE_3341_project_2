public class Parser {

    public static Scanner scanner;
    public Procedure procedure;

    public Parser(String fileName) {
        scanner = new Scanner(fileName);
    }

    public static boolean currentTokenIs(Core token) {
        return scanner.currentToken() == token;
    }

    // may need to switch to varargs
    public static void checkCurrentTokenIs(Core... expectedTokens) {
        assert expectedTokens.length > 0 : "checkCurrentTokenIs expects 1 or more arguments.";

        if (expectedTokens.length == 1) {
            if (!currentTokenIs(expectedTokens[0])) {
                System.out.println("ERROR: expected '" + tokenToString(expectedTokens[0]) + "' but received '" + tokenToString(scanner.currentToken()) + "'.");
                System.exit(0);
            }
        } else {
            boolean expectedTokenDetected = false;
            
            // sets expectedTokenDetected flag to true if at least one of the expected tokens is the current token
            for (Core token : expectedTokens) if (currentTokenIs(token)) expectedTokenDetected = true;

            if (!expectedTokenDetected) {
                String expectedTokensList = "";

                if (expectedTokens.length == 2) {
                    // generates a list of expected tokens in the format "'token_1' or 'token_2',"
                    expectedTokensList = "'" + tokenToString(expectedTokens[0]) + "' or '" + tokenToString(expectedTokens[1]) + "',";
                } else {
                    // generates a list of expected tokens in the format "'token_1', 'token_2', ..., or 'token_n',"
                    for (int i = 0; i < expectedTokens.length - 2; i++) {
                        expectedTokensList += "'" + tokenToString(expectedTokens[i]) + "', ";
                    }
                    expectedTokensList += "or '" + tokenToString(expectedTokens[expectedTokens.length - 1]) + "',";
                }
                
                System.out.println("ERROR: expected " + expectedTokensList + " but received '" + tokenToString(scanner.currentToken()) + "'.");
                System.exit(0);
            }
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
