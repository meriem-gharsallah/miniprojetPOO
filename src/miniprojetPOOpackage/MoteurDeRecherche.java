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
        List<Nom> listePretraitee = new ArrayList<>(liste);
        Nom copie = new Nom(n);  // Copie non modifiée

        // Prétraitement
        listePretraitee = appliquerPretraiteurs(listePretraitee);
        Nom copiePretraitee = appliquerPretraiteurs(List.of(copie)).get(0);

        // Génération des couples
        List<CoupleDeNoms> couples = Main.getGenerateur(configuration.getNomGenerateur())
                                         .generer(listePretraitee, List.of(copiePretraitee));

        // Création du dictionnaire d'accès rapide ID → Nom original
        Map<String, Nom> idToNomOriginal = new HashMap<>();
        for (Nom nomOriginal : liste) {
            idToNomOriginal.put(nomOriginal.getId(), nomOriginal);
        }

        List<CoupleDeNomsAvecScore> resultats = new ArrayList<>();

        for (CoupleDeNoms couple : couples) {
            Nom nomOriginal = idToNomOriginal.get(couple.getNom1().getId());

            if (nomOriginal == null) continue; // sécurité

            double score = Main.getComparateur(configuration.getNomComparateur())
                               .comparer(couple.getNom1(), couple.getNom2());

            resultats.add(new CoupleDeNomsAvecScore(nomOriginal, n, score));
        }

        return Main.getSelectionneur(configuration.getNomSelectionneur(), configuration)
                   .selectionner(resultats);
    }

    public List<Nom> dedupliquer(List<Nom> L) {
        List<Nom> L1 = new ArrayList<>();

        for (Nom nom : L) {
            List<CoupleDeNomsAvecScore> similaires = rechercher(L1, nom);
            if (similaires.isEmpty()) {
                L1.add(nom);
            }
        }

        return L1;
    }


	


	public List<CoupleDeNomsAvecScore> comparer(List<Nom> L,List<Nom> L1) {
		List<CoupleDeNomsAvecScore> L2 = new ArrayList<>();
		for (Nom n:L) {
			
			L2.addAll(rechercher(L1,n));
		}
	return Main.getSelectionneur(configuration.getNomSelectionneur(), configuration).selectionner(L2);
	}
	
}
