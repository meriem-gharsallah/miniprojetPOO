package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Nom {
    private String id;
    private String nom;

    public Nom(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Nom(Nom autreNom) {
        this.nom = autreNom.nom;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String toString() {
        return "ID: " + id + ", Nom: " + nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nom n = (Nom) o;
        return nom.equalsIgnoreCase(n.nom); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom.toLowerCase());
    }
    public TupleN genererListeDeChaines(Nom n) {
        Pretraiteur p = new PretraiteurAleatoire();
        Nom nomPretraite = p.pretraiter(List.of(n)).get(0);
        List<String> L = List.of(nomPretraite.getNom().split("\\s+"));
        return new TupleN(L);
    }
    public static List<String> genererToutesLesConcatenations(List<String> mots) {
        List<String> resultats = new ArrayList<>();
        genererPermutations(mots, 0, resultats);
        return resultats;
    }
    private static void genererPermutations(List<String> mots, int index, List<String> resultats) {
        if (index == mots.size()) {
            resultats.add(String.join(" ", mots)); // Concatène les mots avec un espace
            return;
        }

        for (int i = index; i < mots.size(); i++) {
            Collections.swap(mots, index, i);
            genererPermutations(mots, index + 1, resultats);
            Collections.swap(mots, index, i); // backtrack
        }
    }
}
