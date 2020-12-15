/**
 * Defines the RangeCriterion class. Each criterion has a max and min value.
 */
class RangeCriterion {
    private long maxValue = Long.MAX_VALUE;
    private long minValue = Long.MIN_VALUE;

    /**
     * Method for changing the minimum and maximum values based on a given tag.
     * Checks the tag relation, and sets the values based on its value.
     */
    void addCriterion(Tag tag) {
        if (tag.getRelation() == Tag.Relation.LARGER) {
            minValue = Math.max(minValue, Long.parseLong(tag.getValue()));
        }
        if (tag.getRelation() == Tag.Relation.SMALLER) {
            maxValue = Math.min(maxValue, Long.parseLong(tag.getValue()));
        }
    }

    /**
     * Basic boolean method to determine whether a value is within the range.
     */
    boolean isInRange(long value) {
        if (value < maxValue && value > minValue)
            return true;
        return false;
    }
}
