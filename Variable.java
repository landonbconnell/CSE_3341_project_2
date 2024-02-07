public class Variable {
    String identifier;
    Type type;
    Integer intValue;
    Integer[] objValue;

    public Variable(String identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
        this.intValue = null;
        this.objValue = null;
    }
}
