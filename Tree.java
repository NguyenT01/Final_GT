import java.util.ArrayList;

public class Tree {
    private ArrayList<Node> head = new ArrayList<Node>();

    public Tree(){}

    public Tree(Node node){head.add(node);}

    public void addHead(Node headNode){
        if(this.head.size() == 0){
            this.head.add(headNode);
        }
        else{
            headNode.setChildren(head);
            head = new ArrayList<Node>();
            head.add(headNode);
        }
    }

    public void addNewNode(Node node){
        if(this.head.size() == 0){this.head.add(node);}
        else{
            
        }
    }


    public String printTree(){
        ArrayList<Node> list2 = new ArrayList<Node>(head);

        // while()
        return "";
    }
}
