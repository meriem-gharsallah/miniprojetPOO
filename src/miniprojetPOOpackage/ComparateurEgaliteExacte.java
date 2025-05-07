package miniprojetPOOpackage;


import java.util.Objects;

public class ComparateurEgaliteExacte implements ComparateurNom {
	public double comparer(Nom n1, Nom n2) {
		if (n1.equals(n2)) {
			return 1;
		}
		else {
			return 0;
			
		}
		
		
	}
	
	//public String toString() {
        //return "ComparateurEgaliteExacte[nom=" + refcomp + "]";
    //}
	
	
	
}

