public class Variable {
    String identifier;
    Type type;
    int value;

    public Variable(String identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
        this.value = -1;
    }
}
