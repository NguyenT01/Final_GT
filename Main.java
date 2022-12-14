import java.util.ArrayList;

public class Main {
    
    // đếm số bit 1
    public static int countSetBit(Integer num){
        return Integer.bitCount(num);
    }

    public static void pDBV_FCSP(Tuple[] DB, int minsup){
        Tree tree = new Tree();

        ArrayList<Tuple> f1 = new ArrayList<Tuple>();

        for(Tuple s: DB){
            int supportOfItem = s.getValue();
            if(countSetBit(supportOfItem) >= minsup){
                System.out.println(supportOfItem +" "+ countSetBit(supportOfItem));
                f1.add(s);
                // System.out.println("-- "+countSetBit(supportOfItem) +" " +Integer.toString(s.getValue()));
            }
        }

        f1.sort(((o1, o2) -> countSetBit(o1.getValue())- countSetBit(o2.getValue())));

        for(Tuple t: f1){
            System.out.println(t.toString());
        }

    }

    public static void FCSP_Ext(Tree p, int minsup){
        System.out.println("This is sub function with p= ");
        System.out.println();
    }




    public static void main(String[] args) {
        String[] DB = {"CAAC(CADEF)D", "AB(AE)CB", "(BC)CABC", "ABBCA(BCE)", "BA(BCE)D", "AB(ADE)A"};

        Tuple[] DBV = {
            new Tuple("A", 63),
            new Tuple("B",62),
            new Tuple("C",31),
            new Tuple("D",49),
            new Tuple("E",59),
            new Tuple("F",1),
        };

        int minsup = 3;

        Tree tree = new Tree();

        pDBV_FCSP(DBV, minsup);

    }
}
