package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;
public class ComparateurMotDecompose {
	private List<String> listeDesNomsComparateursDeChaines;
	public ComparateurMotDecompose(List<String> listeDesNomsComparateursDeChaines) {
		this.listeDesNomsComparateursDeChaines = listeDesNomsComparateursDeChaines;
	}
	public class ComparateurEgaliteExacte implements ComparateurNom {
		public double comparer(Nom n1, Nom n2) {
			if (n1.equals(n2)) {
				return 1;
			}
			else {
				return 0;
				
			}
			
			
		}
	}
	public List<String> getListeDesNomsComparateursDeChaines() {
		return listeDesNomsComparateursDeChaines;
	}
	public void setListeDesNomsComparateursDeChaines(List<String> listeDesNomsComparateursDeChaines) {
		this.listeDesNomsComparateursDeChaines = listeDesNomsComparateursDeChaines;
	}}

