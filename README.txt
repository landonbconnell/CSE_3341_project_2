Landon Connell

Assign.java - Has code for parsing, printing, and performing semantic checks on the <assign> non-terminal.
Cmpr.java - Has code for parsing, printing, and performing semantic checks on the <cmpr> non-terminal.
Cond.java - Has code for parsing, printing, and performing semantic checks on the <cond> non-terminal.
Core.java - A file containing enums that serve as tokens.
Decl.java - Has code for parsing, printing, and performing semantic checks on the <decl> non-terminal.
DeclInteger.java - Has code for parsing, printing, and performing semantic checks on the <decl-integer> non-terminal.
DeclObj.java - Has code for parsing, printing, and performing semantic checks on the <decl-obj> non-terminal.
DeclSeq.java - Has code for parsing, printing, and performing semantic checks on the <decl-seq> non-terminal.
Expr.java - Has code for parsing, printing, and performing semantic checks on the <expr> non-terminal.
Factor.java - Has code for parsing, printing, and performing semantic checks on the <factor> non-terminal.
If.java - Has code for parsing, printing, and performing semantic checks on the <if> non-terminal.
Loop.java - Has code for parsing, printing, and performing semantic checks on the <loop> non-terminal.
Main.java - A program that drives a compiler process which involves scanning an input file, generating a parse tree, 
    performing semantic checking, and then reconstructing the program from the parse tree to output the Core program.
Out.java - Has code for parsing, printing, and performing semantic checks on the <out> non-terminal.
Parser.java - This class initiates the parsing process by invoking the 'parse()' method on a 'Procedure' object and
    provides helper methods to validate token correctness during the parsing of the Core programming language.
Procedure.java - Has code for parsing, printing, and performing semantic checks on the <procedure> non-terminal.
Scanner.java - A class defining a "Scanner" object, which takes source code as input, and outputs a stream of tokens.
SemanticChecker.java - This class manages variable scopes and declarations for semantic checking, providing functionality 
    to add, check, and retrieve variables within nested scopes.
Stmt.java - Has code for parsing, printing, and performing semantic checks on the <stmt> non-terminal.
StmtSeq.java - Has code for parsing, printing, and performing semantic checks on the <stmt-seq> non-terminal.
Term.java - Has code for parsing, printing, and performing semantic checks on the <term> non-terminal.
Type.java - A file containing enums symbolizing valid Core language variable types (integer and object).
Variable.java - A class defining a "Variable" object which contains 'identifier' and 'type' members.



Special Features:

While working on the recursive descent parser, I observed a recurring code pattern: 
    'if the currentToken isn't x, y, or z, display an error; else, move the scanner forward or perform another action.'
To make my code more efficient, I created a 'checkCurrentTokenIs' method in Parser.java. This method verifies if the current token
is among the expected ones, signals an error if not, and proceeds to the next token when the advanceScanner flag is true.

There was more variance in the types of error messages that semantic checking would generate, so I kept those in the non-terminal
classes instead of putting them in SemanticChecker.



Parser Design:

For the parser design, I developed a Parser class equipped with a scanner for the source code file, a member of type Procedure, and various
methods aiding in recursive descent parsing. The class includes a 'run' method that initializes 'procedure' as the top-level non-terminal in the 
parse tree and invokes its .parse() method.

The 'Procedure.parse()' method scans the file token by token, aligning each with the valid tokens specified by the Core language's context-free grammar.
Upon encountering an unexpected token, it prints a detailed error message and terminates the program. If the token is an identifier, it's retained as a
String member for future access during the '.printer()' method execution. When a non-terminal token is anticipated, the method instantiates the 
corresponding non-terminal class, stores it as a class member, and calls its '.parse()' method.

This parsing strategy extends to all non-terminals in the grammar, each represented by a class with its own .parse() method that mirrors the
Procedure.parser's logic. This process is repeated recursively throughout parse tree, ensuring every token is eventually paired with a terminal
in the grammar, thus effectively parsing the entire program's structure.



Parser Testing and Bugs

To test the Parser, I ran the provided ./tester.sh script and verified it passed all test cases. Recognizing that the original test suite didn't cover
every possible syntax error, I modified Correct/1.code to include a broader range of errors, retesting each time to confirm failure and validate the
error messages.

Thanks to my rigorous testing, I'm fairly confident my code is bug-free (though I do recognize how naive that statement could be).
