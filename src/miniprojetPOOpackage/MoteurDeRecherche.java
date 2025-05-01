package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class MoteurDeRecherche {
	private ComparateurNom comparateurnom;
	private Configuration configuration;
	private GenerateurDeCandidats candidats;
	private RecuperateurDeNoms nomRecupere;
	private Pretraiteur pretraitement;
	private SelectionneurDeResultats selectionne;
	public void MoteurDeRecherche(ComparateurNom comparateurnom,Configuration configuration,GenerateurDeCandidats candidats,RecuperateurDeNoms nomRecupere,Pretraiteur pretraitement,SelectionneurDeResultats selectionne) {
		this.comparateurnom=comparateurnom;
		this.configuration=configuration;
		this.candidats=candidats;
		this.nomRecupere=nomRecupere;
		this.pretraitement=pretraitement;
		this.selectionne=selectionne;
	}
	public List<Nom> rechercher(List<Nom> L,Nom n) {
		List<Nom> L2 = new ArrayList<>();
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
	
}
