package miniprojetPOOpackage;
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
}
