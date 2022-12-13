import java.util.ArrayList;

public class Node{
    private String sequence;
    private Integer value;
    private ArrayList<Node> children = new ArrayList<Node>();

    public Node(String sequence, Integer value){
        this.sequence = sequence;
        this.value = value;
    }

    public Node(String sequence, Integer value, Node newNode) {
        this.sequence = sequence;
        this.value = value;
        children.add(newNode);
    }

    public Integer getValue(){return value;}

    public String getSequence(){return sequence;}

    public ArrayList<Node> getChildren(){return children;}

    public void setValue(String sequence, Integer value) {this.value = value; this.sequence=sequence;}

    public void setChildren(ArrayList<Node> children) {this.children = children;}

    public void addChildrenNode(Node node){
        children.add(node);
    }

    public void clearChildrenNode(){children.clear();}

    public boolean compareNode(String s1, Integer v1){
        return (sequence.equals(s1) && value==v1);
    }

} 