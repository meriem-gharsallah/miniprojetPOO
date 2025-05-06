package miniprojetPOOpackage;

import java.util.Objects;

public class Couple {
    private Nom nom1;
    private Nom nom2;

    public Couple(Nom nom1, Nom nom2) {
        this.nom1 = nom1;
        this.nom2 = nom2;
    }

    public Nom getNom1() {
        return nom1;
    }

    public Nom getNom2() {
        return nom2;
    }

    @Override
    public String toString() {
        return "(" + nom1 + ", " + nom2 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Couple couple = (Couple) o;
        return Objects.equals(nom1, couple.nom1) &&
               Objects.equals(nom2, couple.nom2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom1, nom2);
    }
}

