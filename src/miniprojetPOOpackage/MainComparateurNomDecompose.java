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
        Nom nom1 = new Nom("","huda ben amer");
        Nom nom2 = new Nom("","houda amer");

        System.out.println("JaroWinkler : " +c.comparer(nom2, nom1));
        System.out.println("Levenshtein : " + new ComparateurLevenshtein().comparer(nom1.getNom(), nom2.getNom()));;

        
        
    }
}

