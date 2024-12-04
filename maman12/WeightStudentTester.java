
package maman12;

public class WeightStudentTester {
    public static void main(String[] args) {
        System.out.println("********** Test Weight - Started **********");
        System.out.println("\n1. Testing Constructors and toString:");
        Weight w1 = new Weight(3, 10);
        System.out.println("\tw1:" + w1);
        Weight w2 = new Weight(w1);
        System.out.println("\tw2:" + w2);
        Weight w3 = new Weight(4003);
        System.out.println("\tw3:" + w3);
        System.out.println("\n2. Testing accessors :");
        System.out.println("\tw1:" + w1);
        System.out.println("\tkilos of w1:" + w1.getKilos());
        System.out.println("\tgrams of w1:" + w1.getGrams());
        System.out.println("\n3. Testing equals method:");
        Weight w4 = new Weight(4, 3);
        System.out.println("\tw3:" + w3);
        System.out.println("\tw4:" + w4);
        if (w3.equals(w4))
            System.out.println("\tw3 is the same weight as w4");
        else
            System.out.println("\tw3 isn't the same weight as wd4");
        System.out.println("\n4. Testing lighter method:");
        if (w3.lighter(w1))
            System.out.println("\tw3 is lighter than w1");
        else
            System.out.println("\tw3 isn't lighter than w1");
        System.out.println("\n5. Testing heavier method:");
        if (w3.heavier(w1))
            System.out.println("\tw3 is heavier than w1");
        else
            System.out.println("\tw3 isn't heavier than w1");
        System.out.println("\n6. Testing add method:");
        System.out.println("\tw1:" + w1);
        System.out.println("\tAdding 500 grams to w1 returns the weight : " + w2.add(500));
        System.out.println("\n********** Test Weight - Finished **********\n");

    }
}