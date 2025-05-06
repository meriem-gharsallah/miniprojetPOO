package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class SelectionneurPourcentage implements SelectionneurDeResultats {
    double p;
    public SelectionneurPourcentage(double p) {
    	this.p=p;
    }
	public List<NomAvecScore> selectionner(List<NomAvecScore> noms) {
		List<NomAvecScore> L2 = new ArrayList<>();
		return L2;
	}

}
