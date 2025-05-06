package miniprojetPOOpackage;
import java.util.List;

public class PretraiteurAleatoire implements Pretraiteur {

	public List<Nom> pretraiter(List<Nom> noms) {
		return noms;
	}
	public String toString() {
        return "Prétraiteur Aléatoire";
    }

}
