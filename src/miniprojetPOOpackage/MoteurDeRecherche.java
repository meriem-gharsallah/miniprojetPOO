package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.List;

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
        // Copier la liste de noms pour appliquer les prétraiteurs sans modifier l'original
        List<Nom> listePretraitee = new ArrayList<>(liste);
        Nom copie = new Nom(n);  // Copie du nom recherché (non modifié)
        
        // Appliquer les prétraiteurs
        listePretraitee = appliquerPretraiteurs(listePretraitee);
        Nom copiePretraitee = appliquerPretraiteurs(List.of(copie)).get(0);
        
        // Générer les couples pour comparaison
        List<CoupleDeNoms> couples = Main.getGenerateur(configuration.getNomGenerateur())
                                         .generer(listePretraitee, List.of(copiePretraitee));

        List<CoupleDeNomsAvecScore> resultats = new ArrayList<>();
        
        // Affichage : nom recherché avant prétraitement

        for (int i = 0; i < couples.size(); i++) {
            Nom nomOriginal = liste.get(i);  // nom original issu du fichier
            Nom nomPretraite = listePretraitee.get(i);
            Nom nomRecherchePretraite = copiePretraitee;

            double score = Main.getComparateur(configuration.getNomComparateur())
                               .comparer(nomPretraite, nomRecherchePretraite);

            // Créer et ajouter le couple avec score
            resultats.add(new CoupleDeNomsAvecScore(nomOriginal, n, score));

            /*Affichage ligne par ligne
            System.out.println("Nom (non prétraité) : " + nomOriginal.getNom());
            System.out.println("Score avec le nom recherché : " + score);
            System.out.println("----------------------------------------");
        }*/
        }
        // Appliquer le sélectionneur
        return Main.getSelectionneur(configuration.getNomSelectionneur(), configuration)
                   .selectionner(resultats);
    }

	public List<Nom> dedupliquer(List<Nom> L) {
	    List<Nom> L1 = new ArrayList<Nom>();
	    

	    for (int i = 0; i < L.size(); i++) {
	        boolean estDoublon = false;

	        for (int j = 0; j < L1.size(); j++) {
	            if (Main.getComparateur(configuration.getNomComparateur()).comparer(L.get(i), L1.get(j)) >= configuration.getSeuil()) {
	                estDoublon = true;
	                break;
	            }
	        }

	        if (!estDoublon) {
	            L1.add(L.get(i));
	        }
	    }

	    return L1;
	}

	


	public List<CoupleDeNomsAvecScore> comparer(List<Nom> L,List<Nom> L1) {
		//List<Nom> l2=appliquerPretraiteurs(L1);
		List<CoupleDeNomsAvecScore> L2 = new ArrayList<>();
		for (Nom n:L) {
			
			L2.addAll(rechercher(L1,n));
		}
	return Main.getSelectionneur(configuration.getNomSelectionneur(), configuration).selectionner(L2);
	}
	
}
