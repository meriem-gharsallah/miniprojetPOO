package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.List;

public class MainNormalisation {
    public static void main(String[] args) {
        Nom n1 = new Nom("NK-24isa6tapAYCqtEnqbUdzv", "DEMIDOVICH     VASILIJ fff   fg");

            Normalisation n=new Normalisation();
            List<Nom> l=n.pretraiter(List.of(n1));
            System.out.println(l);
        }
    }


