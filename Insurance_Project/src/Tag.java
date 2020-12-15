/**
 * Defines the Tag class. Each tag has a relation, name, ana value.
 */
class Tag {
    public enum Relation {
        SMALLER, LARGER, EQUAL
    }
    private Relation relation;
    private String name;
    private String value;

    /**
     * Constructor for tag. Based on tokens supplied, it created a name, relation
     * and value. The relation can only be SMALLER, LARGER, or EQUAL based on
     * '>', '<', or '='. Any other relation is invalid.
     */
    Tag(String[] tokens) {
        name = tokens[0];

        switch (tokens[1].charAt(0)) {
            case '<':
                relation = Relation.SMALLER;
                break;
            case '>':
                relation = Relation.LARGER;
                break;
            case '=':
                relation = Relation.EQUAL;
                break;
            default:
                throw new BadCommandException("Invalid tag: ill-defined bad relation.");
        }
        value = tokens[2];
    }

    /**
     * Getter for Relation.
     */
    public Relation getRelation() {
        return relation;
    }

    /**
     * Getter for name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for value.
     */
    public String getValue() {
        return value;
    }
}