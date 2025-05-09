package miniprojetPOOpackage;

import java.util.Objects;

public class CoupleDeNoms {
    private Nom nom1;
    private Nom nom2;

    public CoupleDeNoms(Nom nom1, Nom nom2) {
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

}

