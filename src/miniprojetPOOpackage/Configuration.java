package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;
public class Configuration {
    private List<String> nomsPretraiteurs;
    private String nomComparateur;
    private String nomGenerateur;
    private String nomSelectionneur;
    private double seuil;
    private int nombreMax;
    private double pourcentage;

    public Configuration(List<String> nomsPretraiteurs, String nomComparateur,
                         String nomGenerateur, String nomSelectionneur) {
        this.nomsPretraiteurs = nomsPretraiteurs;
        this.nomComparateur = nomComparateur;
        this.nomGenerateur = nomGenerateur;
        this.nomSelectionneur = nomSelectionneur;
        /*this.seuil = seuil;
        this.nombreMax = nombreMax;
        this.pourcentage = pourcentage;*/
    }

	public double getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public void setNomsPretraiteurs (List<String> nomsPretraiteurs) {
		this.nomsPretraiteurs = nomsPretraiteurs;
	}



	public void setNomComparateur(String nomComparateur) {
		this.nomComparateur = nomComparateur;
	}

	public void setNomGenerateur(String nomGenerateur) {
		this.nomGenerateur = nomGenerateur;
	}

	public void setNomSelectionneur(String nomSelectionneur) {
		this.nomSelectionneur = nomSelectionneur;
	}

	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}

	public void setNombreMax(int nombreMax) {
		this.nombreMax = nombreMax;
	}

	public List<String> getNomsPretraiteurs() {
		return nomsPretraiteurs;
	}


	public String getNomComparateur() {
		return nomComparateur;
	}

	public String getNomGenerateur() {
		return nomGenerateur;
	}

	public String getNomSelectionneur() {
		return nomSelectionneur;
	}

	public double getSeuil() {
		return seuil;
	}

	public int getNombreMax() {
		return nombreMax;
	}

    
}
