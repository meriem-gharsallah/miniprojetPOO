package miniprojetPOOpackage;
import java.util.Objects;

public class Nom {
    private int id;
    private String nom;

    public Nom(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
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
        return id == n.id &&
               nom.equalsIgnoreCase(n.nom); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom.toLowerCase());
    }
}
