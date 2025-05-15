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
    public List<String> genererListeDeChaines(Nom n) {
        Pretraiteur p = new PretraiteurAleatoire();
        Nom nomPretraite = p.pretraiter(List.of(n)).get(0);
        List<String> L = List.of(nomPretraite.getNom().split("\\s+"));
        return L;
    }
    public static List<String> genererToutesLesConcatenations(List<String> mots) {
        List<String> resultats = new ArrayList<>();
        int n = mots.size();
        int total = 1 << n; // 2^n combinaisons possibles

        for (int i = 1; i < total; i++) {
            List<String> combinaison = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    combinaison.add(mots.get(j));
                }
            }
            resultats.add(String.join(" ", combinaison));
        }

        return resultats;
    }


    }
