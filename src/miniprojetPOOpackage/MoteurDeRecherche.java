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

    public MoteurDeRecherche(GenerateurDeCandidats generateur,
                              SelectionneurDeResultats selectionneur,
                              List<Pretraiteur> listeDesPretraiteurs,
                              ComparateurNom comparateur,
                              Configuration configuration,
                              double seuil,
                              int nombreMax) {
        this.generateur = generateur;
        this.selectionneur = selectionneur;
        this.listeDesPretraiteurs = listeDesPretraiteurs;
        this.comparateur = comparateur;
        this.configuration = configuration;
        this.seuil = seuil;
        this.nombreMax = nombreMax;
    }
    private List<Nom> appliquerPretraiteurs(List<Nom> noms) {
        for (String nomPretraiteur : configuration.getNomsPretraiteurs()) {
            noms = Main.getPretraiteur(nomPretraiteur).pretraiter(noms);
        }
        return noms;
    }
    public List<CoupleDeNomsAvecScore> rechercher(List<Nom> liste, Nom n) {
        List<Nom> listePretraitee = new ArrayList<>(liste);
        Nom copie = new Nom(n);
        listePretraitee = appliquerPretraiteurs(listePretraitee);
        copie = appliquerPretraiteurs(List.of(copie)).get(0);
        List<CoupleDeNoms> couples = Main.getGenerateur(configuration.getNomGenerateur()).generer(listePretraitee, List.of(copie));
        List<CoupleDeNomsAvecScore> resultats = new ArrayList<>();
        for (CoupleDeNoms couple : couples) {
            double score = Main.getComparateur(configuration.getNomComparateur())
                               .comparer(couple.getNom1(), couple.getNom2());
            resultats.add(new CoupleDeNomsAvecScore(couple.getNom1(),couple.getNom2(), score));
            System.out.println(couple);	
            System.out.println(score);	
        }
        return Main.getSelectionneur(configuration.getNomSelectionneur()).selectionner(resultats);
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
		List<Nom> l2=appliquerPretraiteurs(L);
		List<Nom> l3=appliquerPretraiteurs(L);
		List<CoupleDeNoms> l4=generateur.generer(l2, l3);
		List<CoupleDeNomsAvecScore> L2 = new ArrayList<>();
		for(CoupleDeNoms c:l4) {
			double comp=comparateur.comparer(c.getNom1(), c.getNom2());
			CoupleDeNomsAvecScore c1=new CoupleDeNomsAvecScore(c.getNom1(),c.getNom2(),comp);
			L2.add(c1);
			
		}
		return L2;
	}
	
}
