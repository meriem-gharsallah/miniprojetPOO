package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoteurDeRecherche {
    private GenerateurDeCandidats generateur;
    private SelectionneurDeResultats selectionneur;
    private List<Pretraiteur> listeDesPretraiteurs;
    private ComparateurNom comparateur;
    private Configuration configuration;
    private double seuil;
    private int nombreMax;
    private double pourcentage;

    public MoteurDeRecherche(GenerateurDeCandidats generateur,
                              SelectionneurDeResultats selectionneur,
                              List<Pretraiteur> p,
                              ComparateurNom comparateur,
                              Configuration configuration,
                              double seuil,
                              int nombreMax,
                              double pourcentage) {
        this.generateur = generateur;
        this.selectionneur = selectionneur;
        this.listeDesPretraiteurs = p;
        this.comparateur = comparateur;
        this.configuration = configuration;
        this.seuil = seuil;
        this.nombreMax = nombreMax;
        this.pourcentage=pourcentage;
    }
    private List<Nom> appliquerPretraiteurs(List<Nom> noms) {
        for (String nomPretraiteur : configuration.getNomsPretraiteurs()) {
            noms = Main.getPretraiteur(nomPretraiteur).pretraiter(noms);
        }
        return noms;

    }
    public List<CoupleDeNomsAvecScore> rechercher(List<Nom> liste, Nom n) {
    	long debut = System.nanoTime();
        List<Nom> listePretraitee = new ArrayList<>(liste);
        Nom copie = new Nom(n);  

        
        listePretraitee = appliquerPretraiteurs(listePretraitee);
        Nom copiePretraitee = appliquerPretraiteurs(List.of(copie)).get(0);

       
        List<CoupleDeNoms> couples = Main.getGenerateur(configuration.getNomGenerateur())
                                         .generer(listePretraitee, List.of(copiePretraitee));

        // Création du dictionnaire  ID ,Nom original
        Map<String, Nom> idToNomOriginal = new HashMap<>();
        for (Nom nomOriginal : liste) {
            idToNomOriginal.put(nomOriginal.getId(), nomOriginal);
        }

        List<CoupleDeNomsAvecScore> resultats = new ArrayList<>();

        for (CoupleDeNoms couple : couples) {
            Nom nomOriginal = idToNomOriginal.get(couple.getNom1().getId());

            if (nomOriginal == null) continue; 

            double score = Main.getComparateur(configuration.getNomComparateur())
                               .comparer(couple.getNom1(), couple.getNom2());

            resultats.add(new CoupleDeNomsAvecScore(nomOriginal, n, score));
        }
        List<CoupleDeNomsAvecScore> selection = Main.getSelectionneur(configuration.getNomSelectionneur(), configuration)
                .selectionner(resultats);
        long fin = System.nanoTime(); // ⏱️ Fin du chronomètre
        double tempsMs = (fin - debut) / 1_000_000.0;
        System.out.printf("⏱️ Temps d'exécution de rechercher pour %s : %.3f ms%n", n.getNom(), tempsMs);

        return selection;
    }

    public List<Nom> dedupliquer(List<Nom> L) {
        List<Nom> resultat = new ArrayList<>();

        // Appliquer les prétraiteurs à tous les noms
        List<Nom> LPretraite = appliquerPretraiteurs(new ArrayList<>(L));

        // index : taille :liste de noms prétraités
        Map<Integer, List<Nom>> index = new HashMap<>();

        Map<String, Nom> original = new HashMap<>();
        for (int i = 0; i < L.size(); i++) {
            original.put(LPretraite.get(i).getId(), L.get(i));
        }

        for (Nom nomPretraite : LPretraite) {
            int taille = nomPretraite.getNom().length();
            boolean estUnique = true;

            for (int t = taille - 2; t <= taille + 2; t++) {
                if (!index.containsKey(t)) continue;
                for (Nom autre : index.get(t)) {
                    double score = comparateur.comparer(nomPretraite, autre);
                    if (score >= 0.7) {
                        estUnique = false;
                        break;
                    }
                }
                if (!estUnique) break;
            }

            if (estUnique) {
                // Ajouter le nom original (non prétraité)
                resultat.add(original.get(nomPretraite.getId()));
                index.computeIfAbsent(taille, k -> new ArrayList<>()).add(nomPretraite);
            }
        }

        return resultat;
    }


	


    public List<CoupleDeNomsAvecScore> comparer(List<Nom> L, List<Nom> L1) {
        List<CoupleDeNomsAvecScore> resultats = new ArrayList<>();

        // prétraiter L1 une seule fois
        List<Nom> L1Pretraitee = appliquerPretraiteurs(new ArrayList<>(L1));
        Map<String, Nom> idToOriginalL1 = new HashMap<>();
        for (int i = 0; i < L1.size(); i++) {
            idToOriginalL1.put(L1Pretraitee.get(i).getId(), L1.get(i));
        }

        // indexer L1 par taille
        Map<Integer, List<Nom>> indexParTaille = new HashMap<>();
        for (Nom nom : L1Pretraitee) {
            int taille = nom.getNom().length();
            indexParTaille.computeIfAbsent(taille, k -> new ArrayList<>()).add(nom);
        }

        //  pour chaque nom de L, comparer uniquement à noms proches dans L1
        for (Nom nom : L) {
            Nom nomPretraite = appliquerPretraiteurs(List.of(nom)).get(0);
            int taille = nomPretraite.getNom().length();

            for (int t = taille - 2; t <= taille + 2; t++) {
                if (!indexParTaille.containsKey(t)) continue;
                for (Nom autreNom : indexParTaille.get(t)) {
                	double score = comparateur.comparer(autreNom, nomPretraite);
                	if (score >= 0.99) {
                	    Nom original = idToOriginalL1.get(autreNom.getId());
                	    resultats.add(new CoupleDeNomsAvecScore(original, nom, score));
                	}

                }
            }
        }

        return resultats;
    }

	
}
