public class Tuple {
    private String sequence;
    private Integer value;

    public Tuple(String sequence, Integer value) {
        this.sequence = sequence;
        this.value = value;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public String getSequence() {
        return sequence;
    }
    public Integer getValue() {
        return value;
    }

    public String toString() {
        return sequence+ " "+ Integer.toString(value);
    }
}
