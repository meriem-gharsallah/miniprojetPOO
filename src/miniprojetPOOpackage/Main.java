package miniprojetPOOpackage;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;

public class Main {
	
	public static Pretraiteur getPretraiteur(String nom) {
        switch (nom) {
            case "Normalisation": return new Normalisation();
            case "Pretraiteur aleatoire": return new PretraiteurAleatoire();
            case "Encodage Phonétique": return new EncodagePhonetique();
            default: throw new IllegalArgumentException("Prétraiteur inconnu : " + nom);
        }
    }
    public static ComparateurNom getComparateur(String nom) {
    	switch (nom) {
        case "Egalite exacte": return new ComparateurEgaliteExacte();
        
        
        case "Nom Decompose":List<String> listeDesNomsComparateursDeChaines=new ArrayList<String>();
        listeDesNomsComparateursDeChaines.add("Levenshtein");
        listeDesNomsComparateursDeChaines.add("JaroWinkler");
        	return new ComparateurNomDecompose(listeDesNomsComparateursDeChaines);
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
        case "SelectionneurSeuil": return new SelectionneurSeuil(0.7);
        default: throw new IllegalArgumentException("selectionneur inconnu : " + nom);
    }
    }
    public static ComparateurApproximatifdeChaine getComparateurDeChaine(String nom) {
        switch (nom) {
            case "Levenshtein": return new ComparateurLevenshtein();
            case "JaroWinkler": return new ComparateurJaroWinkler();
            default: throw new IllegalArgumentException("Prétraiteur inconnu : " + nom);
        }
    }

    public static void main(String[] args) {
    	List<Nom> l=new ArrayList<Nom>();
        Nom n1=new Nom("NK-24isa6tapAYCqtEnqbUdzv","DEMIDOVICH VASILIJ");
        RecuperateurCSV r = new RecuperateurCSV("fichier.csv");
        l= r.recuperer();
        System.out.println("Répertoire courant : " + System.getProperty("user.dir"));

        for (Nom n : l) {
            System.out.println(n.getId() +" " + n.getNom());
            
        }
        List<Nom> l2=new ArrayList<Nom>();
        r.setCheminFichier("fichier2.csv");
        l2 = r.recuperer();
        ComparateurEgaliteExacte c =new ComparateurEgaliteExacte();
    	SelectionneurAleatoire s=new SelectionneurAleatoire();
    	//RecuperateurListe r=new RecuperateurListe(l);
    	GenerateurDeCandidatsAleatoire g=new GenerateurDeCandidatsAleatoire();
    	/*System.out.println(g.generer(l1, List.of(n1)));*/
    	List<String> p=new ArrayList<String>();
    	p.add("Pretraiteur aleatoire");
    	p.add("Pretraiteur aleatoire");

    	Configuration config=new Configuration(p, "Liste", "Nom Decompose",
    			"GenerateurDeCandidatsAleatoire", "SelectionneurSeuil", 0.7, 10);
    	NomAvecScore n=new NomAvecScore(n1,2);
    	List<Pretraiteur> liste=new ArrayList<Pretraiteur>();
    	for(int i=0;i<config.getNomsPretraiteurs().size();i++) {
			liste.add(getPretraiteur(config.getNomsPretraiteurs().get(i)));
			
			
		}
		MoteurDeRecherche m=new MoteurDeRecherche(Main.getGenerateur(config.getNomGenerateur()), Main.getSelectionneur(config.getNomSelectionneur()),
				liste, Main.getComparateur(config.getNomComparateur()), config,0.7, 10);
		List<CoupleDeNomsAvecScore> l5=new ArrayList<CoupleDeNomsAvecScore>();
		List<Nom> l6=new ArrayList<Nom>();	
		l5=m.rechercher(l, n1);
		System.out.println(l5);
		l6=m.dedupliquer(l);
		System.out.println(l6);
		//System.out.println(m.comparer(l,l2));
		Scanner scanner = new Scanner(System.in);
        int choix = -1;

        while (choix != 5) {
            System.out.println("=== MENU ===");
            System.out.println("1. Effectuer une recherche");
            System.out.println("2. Comparer deux listes");
            System.out.println("3. Dédupliquer une liste");
            System.out.println("4. Configurer les paramètres");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        System.out.println("Saisir le nom à rechercher :");
                        String nomRecherche = scanner.nextLine();
                        System.out.println("Fournir le fichier CSV :");
                        String fichierRecherche = scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Fournir le premier fichier :");
                        String fichier1 = scanner.nextLine();
                        System.out.println("Fournir le second fichier :");
                        String fichier2 = scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("Fournir le fichier à traiter :");
                        String fichierADedupliquer = scanner.nextLine();
                        break;
                    case 4:
                        configurerParametres(scanner);
                        break;
                    case 5:
                        System.out.println("Au revoir !");
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                        break;
                }

            } else {
                System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                scanner.nextLine(); 
            }
        }

        scanner.close();
    }

    private static void configurerParametres(Scanner scanner) {
        int choix = -1;

        while (choix != 0) {
            System.out.println("\n=== Configuration des paramètres ===");
            System.out.println("1. Choisir les prétraitements");
            System.out.println("2. Choisir une structure d'index");
            System.out.println("3. Choisir une mesure de comparaison");
            System.out.println("4. Définir le seuil ou nombre de résultats");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        choisirPretraitements(scanner);
                        break;
                    case 2:
                        System.out.println("Structures d'index disponibles : ...");
                        break;
                    case 3:
                        System.out.println("Mesures de comparaison disponibles : ...");
                        break;
                    case 4:
                        System.out.println("Saisir un seuil (ex: 0.8) ou nombre de résultats :");
                        String seuil = scanner.nextLine();
                        break;
                    case 0:
                        System.out.println("Retour au menu principal.");
                        break;
                    default:
                        System.out.println("Choix invalide.");
                        break;
                }
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                scanner.nextLine(); 
            }
        }
    }
    private static void choisirPretraitements(Scanner scanner) {
        Map<Integer, Pretraiteur> pretraitementsDisponibles = new LinkedHashMap<>();
        pretraitementsDisponibles.put(1, new Normalisation());
        pretraitementsDisponibles.put(2, new EncodagePhonetique());
     

        List<Pretraiteur> pretraitementsChoisis = new ArrayList<Pretraiteur>();

        System.out.println("\n--- Liste des prétraitements disponibles ---");
        for (Map.Entry<Integer, Pretraiteur> entry : pretraitementsDisponibles.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }

        System.out.println("Entrez les numéros des prétraitements à appliquer, séparés par des virgules (ex: 1,2) :");
        String input = scanner.nextLine();
        String[] selections = input.split(",");

        for (String sel : selections) {
            try {
                int id = Integer.parseInt(sel.trim());
                if (pretraitementsDisponibles.containsKey(id)) {
                    pretraitementsChoisis.add(pretraitementsDisponibles.get(id));
                } else {
                    System.out.println("Prétraitement inconnu : " + id);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide ignorée : " + sel);
            }
        }

        System.out.println("Prétraitements choisis : " + pretraitementsChoisis);
     
    }
    
  
    


    
}









