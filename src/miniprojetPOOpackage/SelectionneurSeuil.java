package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class SelectionneurSeuil implements SelectionneurDeResultats {

	private  Configuration config;

    public SelectionneurSeuil(Configuration config) {
    	this.config=config;
    }
    double s = config.getNombreMax();
    public List<CoupleDeNomsAvecScore> selectionner(List<CoupleDeNomsAvecScore> noms) {
    	
        List<CoupleDeNomsAvecScore> filtrés = new ArrayList<>();

        if (noms == null || noms.isEmpty()) {
            return filtrés;
        }

        for (CoupleDeNomsAvecScore nas : noms) {
            if (nas.getScore() >= s) {
                filtrés.add(nas);
            }
        }

        return filtrés;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }
}
