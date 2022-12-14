import java.util.ArrayList;

public class Main {
    public static Tree tree = new Tree();
    
    // đếm số bit 1
    public static int countSetBit(Integer num){
        return Integer.bitCount(num);
    }

    public static void pDBV_FCSP(Tuple[] DB, int minsup){
        Tree root = new Tree();

        ArrayList<Tuple> f1 = new ArrayList<Tuple>();

        for(Tuple s: DB){
            int supportOfItem = s.getValue();
            if(countSetBit(supportOfItem) >= minsup){
                // System.out.println(supportOfItem +" "+ countSetBit(supportOfItem));
                f1.add(s);
                // System.out.println("-- "+countSetBit(supportOfItem) +" " +Integer.toString(s.getValue()));
            }
        }

        f1.sort(((o1, o2) -> countSetBit(o1.getValue())- countSetBit(o2.getValue())));

        for(Tuple t: f1){
            
            // root.addNewNode(new Node(t.getSequence(), t.getValue()));
            System.out.println(t.toString());
            root.addChild(new Node(t.getSequence(), t.getValue()));
        }

        // tree.printTree();
        ArrayList<Node> result = new ArrayList<Node>();

        result = FCSP_Ext(root.getHead(), minsup);

        System.out.println("---CLOSED ITEMSETS--");
        for(Node a: result){
            System.out.println(a.toString());
        }
    }

    public static ArrayList<Node> FCSP_Ext(Node p, int minsup){
        ArrayList<Node> close = new ArrayList<Node>();

        // System.out.println("This is sub function with p= "+p.getSequence());

        ArrayList<Node> ls = p.getChildren();

        for(Node Sx: ls){
            // System.out.println("^^^^^ "+Sx.has_Iterated+ " "+Sx.getValue());
            if(Sx.has_Iterated == true){continue;}
            
            for(Node Sy: ls){
                if(Sx.getSequence().equals(Sy.getSequence())){continue;}
                Node Sz = extendSequence(Sx, Sy);
                int sz_value = Sz.getValue();
                if(Integer.bitCount(sz_value) >= minsup){
                    // System.out.println("--1-- "+ Sx.getSequence()+ " "+Sy.getSequence());
                    // System.out.println("    ---- "+Sz.getSequence()+" "+sz_value+ " "+ countSetBit(Sz.getValue()));
                    Sx.addChildrenNode(Sz);
                }
            }

            // System.out.println("-> "+Sx.getChildren().size());


            if(Sx.getChildren().size() == 0 || Sx.getChildren() == null){
                // System.out.println("--> "+Sx.getSequence());
                Sx.has_Iterated = true;
                close.add(Sx);
            }

            close.addAll(FCSP_Ext(Sx, minsup));

        }

        return close;

    }

    public static Node extendSequence(Node Sx, Node Sy){
        String str_Sx = Sx.getSequence();
        String str_Sy = Sy.getSequence();

        ArrayList<Node> new_children_Sz = new ArrayList<Node>();

        String str_Sz = str_Sx + str_Sy.charAt(str_Sy.length() -1);

        int value_Sz = Sx.getValue() & Sy.getValue();

        // System.out.println(Sx.getValue()+ " "+ Sy.getValue()+ " "+ value_Sz);

        // System.out.println(Integer.toString(value_Sz));

        return new Node(str_Sz, value_Sz, new_children_Sz);
    }




    public static void main(String[] args) {
        Tuple[] DBV = {
            new Tuple("A", 63),
            new Tuple("B",54),
            new Tuple("C",31),
            new Tuple("D",49),
            new Tuple("E",59),
            new Tuple("F",1),
        };

        int minsup = 3;

        long startTime = System.nanoTime();
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // START FUNCTION
        pDBV_FCSP(DBV, minsup);

        long endTime = System.nanoTime();
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();


        double totalTime = (endTime - startTime)*1.00 / 1000 / 1000;
        double actualMemUsed= (afterUsedMem-beforeUsedMem)*1.00 / 1024 /1024;

        System.out.println("-------");
        System.out.println("Run time: "+totalTime + " ms");
        System.out.println("Mem used: "+actualMemUsed +" MB");

    }
}
