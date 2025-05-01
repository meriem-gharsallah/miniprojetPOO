package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class Selectionneur_seuil implements SelectionneurDeResultats {

    private double s;
    public Selectionneur_seuil(double s) {
    	this.s=s;
    }
	public List<NomAvecScore> selectionner(List<NomAvecScore> noms) {
		List<NomAvecScore> L2 = new ArrayList<>();
		return L2;
	}

}
