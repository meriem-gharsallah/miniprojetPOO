package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;
public class Configuration {
    private List<String> nomsPretraiteurs;
    private String nomIndexeur;
    private String nomComparateur;
    private String nomGenerateur;
    private String nomSelectionneur;
    private double seuil;
    private int nombreMax;

    public Configuration(List<String> nomsPretraiteurs, String nomIndexe, String nomComparateur,
                         String nomGenerateur, String nomSelectionneur, double seuil, int nombreMax) {
        this.nomsPretraiteurs = nomsPretraiteurs;
        this.nomIndexeur = nomIndexe;
        this.nomComparateur = nomComparateur;
        this.nomGenerateur = nomGenerateur;
        this.nomSelectionneur = nomSelectionneur;
        this.seuil = seuil;
        this.nombreMax = nombreMax;
    }

	public List<String> getNomsPretraiteurs() {
		return nomsPretraiteurs;
	}

	public String getNomIndexeur() {
		return nomIndexeur;
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

    /*public Pretraiteur getPretraiteur() {
        return ConcretiserConfiguration.getPretraiteur(nomPretraiteur);
    }

    public Indexe getIndexe() {
        return ConcretiserConfiguration.getIndexe(nomIndexeur);
    }

    public ComparateurNom getComparateur() {
        return ConcretiserConfiguration.getComparateur(nomComparateur);
    }

    public GenerateurDeCandidats getGenerateur() {
        return ConcretiserConfiguration.getGenerateur(nomGenerateur);
    }

    public SelectionneurDeResultats getSelectionneur() {
        return ConcretiserConfiguration.getSelectionneur(nomSelectionneur);
    }

    public double getSeuil() {
        return seuil;
    }

    public int getNombreMax() {
        return nombreMax;
    }*/
}
