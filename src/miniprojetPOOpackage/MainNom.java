package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.List;

public class MainNom {
    public static void main(String[] args) {
        Nom n1 = new Nom("NK-24isa6tapAYCqtEnqbUdzv", "DEMIDOVICH     VASILIJ fff   fg");

        TupleN A = n1.genererListeDeChaines(n1);
        System.out.println("TupleN extrait : " + A);

        List<String> motsModifiables = new ArrayList<>(A.getElements());
        List<String> B = Nom.genererToutesLesConcatenations(motsModifiables);

        System.out.println("Toutes les concaténations possibles :");
        for (String s : B) {
            System.out.println(s);
        }
    }
}
