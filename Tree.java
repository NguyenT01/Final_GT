import java.util.ArrayList;

public class Tree {
    private Node head;

    public Tree(){head = new Node(null, null);}

    public Tree(Node node){head = node;}

    public void addHead(Node headNode){
        head = headNode;
    }

    public void addChild(Node childNode){
        head.addChildrenNode(childNode);
    }


    // public void printTree(){
    //     ArrayList<Node> list2 = new ArrayList<Node>(head);

    //     for(int i=0; i<list2.size();i++){
    //         System.out.println(list2.get(i).toString());
    //     }

    //     // System.out.println(list2.size());
    // }

    public Node getHead(){
        return head;
    }

    // public void setHead(ArrayList<Node> head){this.head = head;}
}
