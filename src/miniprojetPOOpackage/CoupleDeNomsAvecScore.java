package miniprojetPOOpackage;

//import java.util.Objects;

public class CoupleDeNomsAvecScore {
    private Nom nom1;
    private Nom nom2;
    private double score;

    public CoupleDeNomsAvecScore(Nom nom1, Nom nom2, double score) {
        this.nom1 = nom1;
        this.nom2 = nom2;
        this.score = score;
    }

    @Override
    public String toString() {
        return "(" + nom1 + ", " + nom2 + ", score=" + score + ")";
    }

    public Nom getNom1() {
        return nom1;
    }

    public Nom getNom2() {
        return nom2;
    }

    public double getScore() {
        return score;
    }

    public void setNom1(Nom nom1) {
        this.nom1 = nom1;
    }

    public void setNom2(Nom nom2) {
        this.nom2 = nom2;
    }

    public void setScore(double score) {
        this.score = score;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoupleDeNomAvecScore that = (CoupleDeNomAvecScore) o;

        return Double.compare(that.score, score) == 0 &&
               Objects.equals(nom1, that.nom1) &&
               Objects.equals(nom2, that.nom2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom1, nom2, score);
    }*/
}

