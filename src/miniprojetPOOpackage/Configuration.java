package miniprojetPOOpackage;

public class Configuration {
	private Pretraiteur pretraiteur;
    private Indexe indexeur;
    private ComparateurNom comparateur;
    private GenerateurDeCandidats generateur;
    private SelectionneurDeResultats selectionneur;
    private double seuil;
    private int nombreMax;
	public Configuration(Pretraiteur pretraiteur, Indexe indexeur, ComparateurNom comparateur,
			GenerateurDeCandidats generateur, SelectionneurDeResultats selectionneur, double seuil, int nombreMax) {
		
		this.pretraiteur = pretraiteur;
		this.indexeur = indexeur;
		this.comparateur = comparateur;
		this.generateur = generateur;
		this.selectionneur = selectionneur;
		this.seuil = seuil;
		this.nombreMax = nombreMax;
	}
	public Pretraiteur getPretraiteur() {
		return pretraiteur;
	}
	public Indexe getIndexeur() {
		return indexeur;
	}
	public ComparateurNom getComparateur() {
		return comparateur;
	}
	public GenerateurDeCandidats getGenerateur() {
		return generateur;
	}
	public SelectionneurDeResultats getSelectionneur() {
		return selectionneur;
	}
	public double getSeuil() {
		return seuil;
	}
	public int getNombreMax() {
		return nombreMax;
	}
}
