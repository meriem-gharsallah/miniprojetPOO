package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class SelectionneurSeuil implements SelectionneurDeResultats {

	private  Configuration config;

    public SelectionneurSeuil(Configuration config) {
    	this.config=config;
    }
    
    public List<CoupleDeNomsAvecScore> selectionner(List<CoupleDeNomsAvecScore> noms) {
    	double s = config.getSeuil();
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

   
}
