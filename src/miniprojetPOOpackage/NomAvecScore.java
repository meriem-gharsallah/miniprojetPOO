package miniprojetPOOpackage;

import java.util.Objects;

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
	public String toString() {
        return "Nom: " + nom + ", Score:" + score ;
    }
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NomAvecScore that = (NomAvecScore) o;
        return Double.compare(that.score, score) == 0 &&
               Objects.equals(nom, that.nom); // utilise equals() de Nom
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, score); // corrélé avec equals
    }
}




