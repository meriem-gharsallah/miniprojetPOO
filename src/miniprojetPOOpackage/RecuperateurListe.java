package miniprojetPOOpackage;

import java.util.List;

public class RecuperateurListe implements RecuperateurDeNoms {

	public RecuperateurListe(List<Nom> l) {
		this.l = l;
	}
	private List<Nom> l;
	public  List<Nom> recuperer() {
		return l;
	}

}
