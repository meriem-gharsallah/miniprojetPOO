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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NomAvecScore n = (NomAvecScore) o;
        return score == n.score &&
               nom.equalsIgnoreCase(n.nom); // ignore case
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom.toLowerCase()); // cohérent avec equals
    }
}


}
