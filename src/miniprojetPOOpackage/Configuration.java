package miniprojetPOOpackage;
public class Configuration {
    private String nomPretraiteur;
    private String nomIndexeur;
    private String nomComparateur;
    private String nomGenerateur;
    private String nomSelectionneur;
    private double seuil;
    private int nombreMax;

    public Configuration(String nomPretraiteur, String nomIndexe, String nomComparateur,
                         String nomGenerateur, String nomSelectionneur, double seuil, int nombreMax) {
        this.nomPretraiteur = nomPretraiteur;
        this.nomIndexeur = nomIndexe;
        this.nomComparateur = nomComparateur;
        this.nomGenerateur = nomGenerateur;
        this.nomSelectionneur = nomSelectionneur;
        this.seuil = seuil;
        this.nombreMax = nombreMax;
    }

    public Pretraiteur getPretraiteur() {
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
    }
}
