package miniprojetPOOpackage;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {
    	List<Nom> l=new ArrayList<Nom>();
        Nom n1=new Nom(1,"meriem GHarsallah");
        Nom n2=new Nom(2,"Hamza SOUssi");
        Nom n3=new Nom(3,"MOHamed SALAH abiD");
        l.add(n1);
        l.add(n2);
        l.add(n3);
        List<Nom> l2=new ArrayList<Nom>();
        Nom n4=new Nom(1,"meriem GHarsallah");
        Nom n5=new Nom(5,"HamZA SOUSSI");
        Nom n6=new Nom(6,"MOHamed salah abiD");
        System.out.println(n1.equals(n4));
        l2.add(n4);
        l2.add(n5);
        l2.add(n6);
        for (Nom n : l) {
            System.out.println(n);
        };
        
        
        List<Nom> l1=new ArrayList<Nom>();
        List<Nom> l3=new ArrayList<Nom>();
        PretraiteurAleatoire p=new PretraiteurAleatoire();
        l1=p.pretraiter(l);
        l3=p.pretraiter(l2);
        System.out.println(l1);
        System.out.println(l3);
    	ComparateurAleatoireDeNom c=new ComparateurAleatoireDeNom("abc");
    	SelectionneurAleatoire s=new SelectionneurAleatoire();
    	RecuperateurListe r=new RecuperateurListe(l);
    	GenerateurDeCandidatsAleatoire g=new GenerateurDeCandidatsAleatoire();
    	IndexeList I=new IndexeList();
    	Configuration config=new Configuration(p, I, c,
    			g, s, 0.7, 10);
    	NomAvecScore n=new NomAvecScore(n1,2);
		System.out.println(c);
		
		for (Nom i:l1) {
			for (Nom j:l3) {
			    double a;
				a=c.comparer(i, j);
				System.out.println(a);
				
			}
			
		}
		MoteurDeRecherche m=new MoteurDeRecherche(c, n,config,g, r, p,s);
		List<NomAvecScore> l5=new ArrayList<NomAvecScore>();
		l5=m.rechercher(l1, n1);
		System.out.println(l5);
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









