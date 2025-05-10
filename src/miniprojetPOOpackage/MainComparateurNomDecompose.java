package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.List;

public class MainComparateurNomDecompose {
    public static void main(String[] args) {
        Nom n1 = new Nom("NK-24isa6tapAYCqtEnqbUdzv", "DEMIDOVICH     VASILIJ ");
        Nom n2 = new Nom("NK-24isa6tapAYCqtEnqbUdzv", "DEMIDOVICH     VASILIJ jj hhh");
        List<String> listeDesNomsComparateursDeChaines=new ArrayList<String>();
        listeDesNomsComparateursDeChaines.add("Levenshtein");
        listeDesNomsComparateursDeChaines.add("JaroWinkler");
        
        ComparateurNomDecompose c=new ComparateurNomDecompose(listeDesNomsComparateursDeChaines);
        
        System.out.println(c.comparer(n1, n2));

        
        
    }
}

