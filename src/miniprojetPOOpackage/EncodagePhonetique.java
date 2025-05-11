package miniprojetPOOpackage;

import java.text.Normalizer;
import java.util.*;
import org.apache.commons.codec.language.Soundex;

public class EncodagePhonetique implements Pretraiteur {

    private Soundex soundex = new Soundex();

    @Override
    public List<Nom> pretraiter(List<Nom> noms) {
        
    	List<Nom> resultat = new ArrayList<>();
    	
        // Regrouper par code phonétique
        Map<String, String> codeToStandard = new HashMap<>();

        for (Nom nomAvecId : noms) {
            String texteOriginal = nomAvecId.getNom();
    

            // Encoder chaque mot séparément
            StringBuilder codeComplet = new StringBuilder();
            for (String mot : texteOriginal	.split(" ")) {
                if (!mot.isEmpty()) {
                    codeComplet.append(soundex.encode(mot)).append(" ");
                }
            }

            String code = codeComplet.toString().trim();

            // Si ce code n’a pas encore de nom standard, on utilise le texte original
            codeToStandard.putIfAbsent(code, texteOriginal);

            // Récupérer le nom standardisé pour ce code
            String nomStandard = codeToStandard.get(code);

            // Créer un nouvel objet Nom avec le texte standard
            Nom nouv = new Nom(nomAvecId.getId(), nomStandard);
            resultat.add(nouv);
        }

        return resultat;
    }
    @Override
    public String toString() {
        return "Encodage Phonétique";
    }
}
