package miniprojetPOOpackage;


import java.util.Objects;

public class ComparateurEgaliteExacte implements ComparateurNom {
	private String refcomp;
	public ComparateurEgaliteExacte(String refcomp) {
		this.refcomp=refcomp;
	}
	//selon refcomp désignée par l'utilisateur on peut identifier le/les comparateurs de chaines qu'on va avoir besoin
	public double comparer(Nom n1, Nom n2) {
		if (n1.equals(n2)) {
			return 1;
		}
		else {
			return 0;
			
		}
		
		
	}
	public String getRefcomp() {
		return refcomp;
	}
	public void setRefcomp(String refcomp) {
		this.refcomp = refcomp;  
	}
	public String toString() {
        return "ComparateurEgaliteExacte[nom=" + refcomp + "]";
    }
	@Override
	public int hashCode() {
		return Objects.hash(refcomp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComparateurEgaliteExacte other = (ComparateurEgaliteExacte) obj;
		return Objects.equals(refcomp, other.refcomp);
	}
	
	
}

