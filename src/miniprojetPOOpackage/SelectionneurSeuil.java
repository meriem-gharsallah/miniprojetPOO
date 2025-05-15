package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class SelectionneurSeuil implements SelectionneurDeResultats {
	private double seuil;
	

    public SelectionneurSeuil(double seuil) {
    	this.seuil=seuil;
    }
    
    public List<CoupleDeNomsAvecScore> selectionner(List<CoupleDeNomsAvecScore> noms) {
    	//double s = config.getSeuil();
        List<CoupleDeNomsAvecScore> filtrés = new ArrayList<>();

        if (noms == null || noms.isEmpty()) {
            return filtrés;
        }

        for (CoupleDeNomsAvecScore nas : noms) {
            if (nas.getScore() >= seuil) {
                filtrés.add(nas);
            }
        }
        filtrés.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
        return filtrés;
    }

	public double getSeuil() {
		return seuil;
	}

	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}

   
}
