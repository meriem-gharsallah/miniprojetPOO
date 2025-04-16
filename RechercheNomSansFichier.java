public class RechercheNomSansFichier {
    public static void main(String[] args) {
        String[] noms = {"Alice", "Bob", "Charlie"};
        String nomRecherche = "Alice";
        boolean trouve = false;

        for (String nom : noms) {
            if (nom.equalsIgnoreCase(nomRecherche)) {
                System.out.println("Nom trouvé !");
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            System.out.println("Nom non trouvé.");
        }
    }
}
