package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class Selectionneur_pourcentage implements SelectionneurDeResultats {
    double p;
    public Selectionneur_pourcentage(double p) {
    	this.p=p;
    }
	public List<NomAvecScore> selectionner(List<NomAvecScore> noms) {
		List<NomAvecScore> L2 = new ArrayList<>();
		return L2;
	}

}
