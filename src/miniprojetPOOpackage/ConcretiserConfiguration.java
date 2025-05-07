package miniprojetPOOpackage;
public class ConcretiserConfiguration {
    public static Pretraiteur getPretraiteur(String nom) {
        switch (nom) {
            case "Normalisation": return new Normalisation();
            case "Pretraiteur aleatoire": return new PretraiteurAleatoire();
            case "Encodage Phonétique": return new EncodagePhonetique();
            default: throw new IllegalArgumentException("Prétraiteur inconnu : " + nom);
        }
    }

    public static Indexe getIndexe(String nom) {
    	switch (nom) {
        case "Liste": return new IndexeList();
        case "Arbre": return new IndexeArbre();
        case "Dictionnaire": return new IndexeDict();
        default: throw new IllegalArgumentException("Indexe inconnu : " + nom);
    }
    }

    public static ComparateurNom getComparateur(String nom) {
    	switch (nom) {
        case "Egalite exacte": return new ComparateurEgaliteExacte();
        default: throw new IllegalArgumentException("comparateur inconnu : " + nom);
    }
    }

    public static GenerateurDeCandidats getGenerateur(String nom) {
    	switch (nom) {
        //case "GenerateurDeCandidatsAvecIndex": return new GenerateurDeCandidatsAvecIndex();
        case "GenerateurDeCandidatsAleatoire": return new GenerateurDeCandidatsAleatoire();
        default: throw new IllegalArgumentException("generateur inconnu : " + nom);
    }
    }

    public static SelectionneurDeResultats getSelectionneur(String nom) {
    	switch (nom) {
        case "SelectionneurAleatoire": return new SelectionneurAleatoire();
        case "SelectionneurNMeilleurs": return new SelectionneurNMeuilleurs(10);
        case "SelectionneurPourcentage": return new SelectionneurPourcentage(80);
        default: throw new IllegalArgumentException("selectionneur inconnu : " + nom);
    }
    }
}
