package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class MoteurDeRecherche {
	private ComparateurNom comparateurnom;
	private NomAvecScore m;
	private Configuration configuration;
	private GenerateurDeCandidats candidats;
	private RecuperateurDeNoms nomRecupere;
	private Pretraiteur pretraitement;
	private SelectionneurDeResultats selectionne;
	public MoteurDeRecherche(ComparateurNom comparateurnom, NomAvecScore m, Configuration configuration,
			GenerateurDeCandidats candidats, RecuperateurDeNoms nomRecupere, Pretraiteur pretraitement,
			SelectionneurDeResultats selectionne) {
		this.comparateurnom = comparateurnom;
		this.m = m;
		this.configuration = configuration;
		this.candidats = candidats;
		this.nomRecupere = nomRecupere;
		this.pretraitement = pretraitement;
		this.selectionne = selectionne;
	}
	
	
	public List<NomAvecScore> rechercher(List<Nom> L,Nom n) {
		 List<Nom> pretraiteListe = pretraitement.pretraiter(L);
		 List<NomAvecScore> L2 = new ArrayList<NomAvecScore>();
		 List<Nom> nomPretraiteListe = pretraitement.pretraiter(List.of(n));
		 Nom nomPretraite = nomPretraiteListe.get(0);
		 List<Couple> couples = candidats.generer(pretraiteListe, List.of(nomPretraite));
		 List<NomAvecScore> resultats = new ArrayList<NomAvecScore>();
		    for (Couple c : couples) {
		    	double r=comparateurnom.comparer(c.getNom1(), c.getNom2());
		    	m.setNom(c.getNom2());
		    	m.setScore(r);
		        resultats.add(m);
		    	
	
		    }
		    L2=selectionne.selectionner(resultats);

		
		
		
		
		
		return L2;
	}
	public List<Nom> dedupliquer(List<Nom> L) {
		List<Nom> L2 = new ArrayList<>();
		return L2;
	}
	public List<NomAvecScore> comparer(List<Nom> L,List<Nom> L1) {
		List<NomAvecScore> L2 = new ArrayList<>();
		return L2;
	}
	public ComparateurNom getComparateurnom() {
		return comparateurnom;
	}
	public void setComparateurnom(ComparateurNom comparateurnom) {
		this.comparateurnom = comparateurnom;
	}
	public NomAvecScore getM() {
		return m;
	}
	public void setM(NomAvecScore m) {
		this.m = m;
	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	public GenerateurDeCandidats getCandidats() {
		return candidats;
	}
	public void setCandidats(GenerateurDeCandidats candidats) {
		this.candidats = candidats;
	}
	public RecuperateurDeNoms getNomRecupere() {
		return nomRecupere;
	}
	public void setNomRecupere(RecuperateurDeNoms nomRecupere) {
		this.nomRecupere = nomRecupere;
	}
	public Pretraiteur getPretraitement() {
		return pretraitement;
	}
	public void setPretraitement(Pretraiteur pretraitement) {
		this.pretraitement = pretraitement;
	}
	public SelectionneurDeResultats getSelectionne() {
		return selectionne;
	}
	public void setSelectionne(SelectionneurDeResultats selectionne) {
		this.selectionne = selectionne;
	}
	
}
