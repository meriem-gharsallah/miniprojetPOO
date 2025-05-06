package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class SelectionneurSeuil implements SelectionneurDeResultats {

    private double s;
    public SelectionneurSeuil(double s) {
    	this.s=s;
    }
	public List<NomAvecScore> selectionner(List<NomAvecScore> noms) {
		List<NomAvecScore> L2 = new ArrayList<>();
		return L2;
	}
	public double getS() {
		return s;
	}
	public void setS(double s) {
		this.s = s;
	}

}
