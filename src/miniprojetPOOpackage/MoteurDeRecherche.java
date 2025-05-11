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
        
        listePretraitee = appliquerPretraiteurs(listePretraitee);
        Nom copiePretraitee = appliquerPretraiteurs(List.of(copie)).get(0);
        
     
        List<CoupleDeNoms> couples = Main.getGenerateur(configuration.getNomGenerateur())
                                         .generer(listePretraitee, List.of(copiePretraitee));

        List<CoupleDeNomsAvecScore> resultats = new ArrayList<>();
        for (int i = 0; i < couples.size(); i++) {
            Nom nomOriginal = liste.get(i);  // nom original issu du fichier
            Nom nomPretraite = listePretraitee.get(i);
            Nom nomRecherchePretraite = copiePretraitee;

            double score = Main.getComparateur(configuration.getNomComparateur())
                               .comparer(nomPretraite, nomRecherchePretraite);
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
