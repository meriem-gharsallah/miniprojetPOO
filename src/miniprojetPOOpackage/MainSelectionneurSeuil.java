package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.List;

public class MainSelectionneurSeuil {
    public static void main(String[] args) {
        List<CoupleDeNomsAvecScore> l = new ArrayList<CoupleDeNomsAvecScore>();
        Nom n1=new Nom("1","meriem");
        Nom n2=new Nom("2","lina");
        CoupleDeNomsAvecScore c1=new CoupleDeNomsAvecScore(n1,n2,0.5);
        Nom n3=new Nom("3","meriem");
        Nom n4=new Nom("4","meriem gh");
        CoupleDeNomsAvecScore c2=new CoupleDeNomsAvecScore(n3,n4,0.8);
        l.add(c1);
        l.add(c2);
        SelectionneurSeuil s=new SelectionneurSeuil(0.9);
        System.out.println(s.selectionner(l));
        }
    }