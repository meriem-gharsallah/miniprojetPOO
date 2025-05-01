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
}
