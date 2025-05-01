package miniprojetPOOpackage;

public class NomAvecScore {
    private Nom nom;
    private double score;

    public NomAvecScore(Nom nom, double score) {
        this.nom = nom;
        this.score = score;
    }

    public Nom getNom() {
        return nom;
    }

    public double getScore() {
        return score;
    }
    public void setNom(Nom nom) {
		this.nom = nom;
	}

	public void setScore(double score) {
		this.score = score;
	}


}
