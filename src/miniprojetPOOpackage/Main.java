package miniprojetPOOpackage;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.LinkedHashMap;

public class Main {
	
    public static void main(String[] args) {
    	
		Scanner scanner = new Scanner(System.in);
        int choix = -1;
        List<String> p=new ArrayList<String>();
    	p.add("Normalisation");
    	
        Configuration config=new Configuration(p, "Egalite exacte",
    			"GenerateurDeCandidatsAleatoire", "SelectionneurSeuil");
        config.setNombreMax(10);
        config.setSeuil(0.8);
        config.setPourcentage(50);

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
                    	afficherConfiguration(config);
                    	System.out.println("Saisir le nom à rechercher :");
                        String nomRecherche = scanner.nextLine();
                        Nom n=new Nom("1",nomRecherche);
                        System.out.println("Fournir le fichier CSV :");
                        String fichierRecherche = scanner.nextLine();
                        List<Nom> l=new ArrayList<Nom>();
                        RecuperateurDeNoms re = new RecuperateurCSV(fichierRecherche);
                        l= re.recuperer();
                        
                        List<Pretraiteur> listepretraiteur=new ArrayList<Pretraiteur>();
                    	for(int i=0;i<config.getNomsPretraiteurs().size();i++) {
                    		listepretraiteur.add(getPretraiteur(config.getNomsPretraiteurs().get(i)));}
                			
                        MoteurDeRecherche m=new MoteurDeRecherche(Main.getGenerateur(config.getNomGenerateur()), Main.getSelectionneur(config.getNomSelectionneur(),config),
                        		listepretraiteur, Main.getComparateur(config.getNomComparateur()), config,config.getSeuil(), config.getNombreMax(),config.getPourcentage());
                		List<CoupleDeNomsAvecScore> l9=new ArrayList<CoupleDeNomsAvecScore>();
                		l9=m.rechercher(l, n);
                		System.out.println("Nom recherché: " + n.getNom());
                        System.out.println("\nComparaison avec les noms du fichier :");

                        if (l9.isEmpty()) {

                            System.out.println("Aucun nom correspondant trouvé dans le fichier.");

                        } else {

                            for (CoupleDeNomsAvecScore c : l9) {

                                System.out.println(c.getNom1() + " : " + c.getScore());

                            }

                        }
                		
                        break;
             
                    case 2:
                        System.out.println("Fournir le premier fichier :");
                        String fichier1 = scanner.nextLine();
                        System.out.println("Fournir le second fichier :");
                        String fichier2 = scanner.nextLine();
                        List<Nom> l1=new ArrayList<Nom>();
                        List<Nom> l2=new ArrayList<Nom>();
                        RecuperateurDeNoms re1 = new RecuperateurCSV(fichier1);
                        RecuperateurDeNoms re2 = new RecuperateurCSV(fichier2);
                        l1= re1.recuperer();
                        l2= re2.recuperer();
                        
                        List<Pretraiteur> listepretraiteur1=new ArrayList<Pretraiteur>();
                    	for(int i=0;i<config.getNomsPretraiteurs().size();i++) {
                    		listepretraiteur1.add(getPretraiteur(config.getNomsPretraiteurs().get(i)));}
                			
                        MoteurDeRecherche m1=new MoteurDeRecherche(Main.getGenerateur(config.getNomGenerateur()), Main.getSelectionneur(config.getNomSelectionneur(),config),
                        		listepretraiteur1, Main.getComparateur(config.getNomComparateur()), config,0.7, 10,20);
                		List<CoupleDeNomsAvecScore> l10=new ArrayList<CoupleDeNomsAvecScore>();
                		l10=m1.comparer(l1, l2);
                		System.out.println(l10);
                        break;
                    case 3:
                        System.out.println("Fournir le fichier à traiter :");
                        String fichierADedupliquer = scanner.nextLine();
                        List<Nom> l3=new ArrayList<Nom>();
          
                        RecuperateurDeNoms re3 = new RecuperateurCSV(fichierADedupliquer);
                       
                        l3= re3.recuperer();
                        
                        
                        List<Pretraiteur> listepretraiteur2=new ArrayList<Pretraiteur>();
                    	for(int i=0;i<config.getNomsPretraiteurs().size();i++) {
                    		listepretraiteur2.add(getPretraiteur(config.getNomsPretraiteurs().get(i)));}
                			
                        MoteurDeRecherche m2=new MoteurDeRecherche(Main.getGenerateur(config.getNomGenerateur()), Main.getSelectionneur(config.getNomSelectionneur(),config),
                        		listepretraiteur2, Main.getComparateur(config.getNomComparateur()), config,0.7, 10,20);
                		List<Nom> l11=new ArrayList<Nom>();
                		System.out.println("Taille de la liste avant déduplication : " + l3.size());

                		l11=m2.dedupliquer(l3);
                		System.out.println("Taille de la liste après déduplication : " + l11.size());

                		System.out.println(l11);
                        break;
                    case 4:
                        configurerParametres(scanner,config);
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

    private static void configurerParametres(Scanner scanner,Configuration config) {
        int choix = -1;

        while (choix != 0) {
            System.out.println("\n=== Configuration des paramètres ===");
            System.out.println("1. Choisir les prétraitements");
            System.out.println("2. Choisir une mesure de comparaison");
            System.out.println("3. Choisir le generateur de candidats");
            System.out.println("4. Choisir le selectionneur");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        choisirPretraitements(scanner,config);
                        break;
                    case 2:
                    	choisirMesuresDeComparaison(scanner,config);
                        break;
                    
                    case 3:
                    	choisirgenerateur(scanner,config);
                        break;
                    case 4:
                    	choisirselectionneur(scanner,config);
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
    
     	private static void choisirPretraitements(Scanner scanner, Configuration config) {
            System.out.println("\n--- Choisir un pretraitement ---");
            System.out.println("1. Pretraiteur aleatoire");
            System.out.println("2. Normalisation");
            System.out.println("3. EncodagePhonetique");
            
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                try {
                    // Créer une liste pour stocker les choix
                    List<String> listedechoix = new ArrayList<String>();
                    
                    // Séparer les entrées par des virgules ou espaces
                    String[] choixsepares = input.trim().split("[,\\s]+");
                    
                    // Convertir en entiers et trier
                    List<Integer> choixList = new ArrayList<>();
                    for (String s : choixsepares) {
                        if (!s.isEmpty()) {
                            int choix = Integer.parseInt(s);
                            if (choix >= 1 && choix <= 3) {
                                choixList.add(choix);
                            }
                        }
                    }

                    Collections.sort(choixList);
                    
                    // Ajouter les prétraitements correspondants dans l'ordre
                    for (int choix : choixList) {
                        switch (choix) {
                            case 1:
                                listedechoix.add("Pretraiteur aleatoire");
                                break;
                            case 2:
                                listedechoix.add("Normalisation");
                                break;
                            case 3:
                                listedechoix.add("Encodage Phonétique");
                                break;
                        }
                    }
                    
                    // Si au moins un choix valide a été fait
                    if (!listedechoix.isEmpty()) {
                        config.setNomsPretraiteurs(listedechoix);
                        System.out.println("Prétraitements sélectionnés : " + listedechoix);
                    } else {
                        System.out.println("Aucun prétraitement valide sélectionné.");
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer des numéros valides (1, 2 ou 3) séparés par des virgules ou espaces.");
                }
            }
            }

           
    private static void choisirMesuresDeComparaison(Scanner scanner, Configuration config) {
        System.out.println("\n--- Choisir un comparateur ---");
        System.out.println("1. Egalité exacte");
        System.out.println("2. Mot décomposé");

        System.out.print("Entrez le numéro du comparateur à utiliser : ");
        String input = scanner.nextLine();

        if (!input.trim().isEmpty()) {
            try {
                int choix = Integer.parseInt(input.trim());

                switch (choix) {
                    case 1:
                        config.setNomComparateur("Egalite exacte");
                        System.out.println("Comparateur sélectionné : Egalité exacte");
                        break;

                    case 2:
                        // Si comparateur décomposé choisi, demander les sous-comparateurs
                        Map<Integer, String> optionsInternes = new LinkedHashMap<>();
                        optionsInternes.put(1, "Levenshtein");
                        optionsInternes.put(2, "JaroWinkler");

                        System.out.println("\n--- Comparateurs internes disponibles ---");
                        for (Map.Entry<Integer, String> entry : optionsInternes.entrySet()) {
                            System.out.println(entry.getKey() + ". " + entry.getValue());
                        }

                        System.out.print("Entrez les numéros des comparateurs internes à utiliser (ex: 1,2) : ");
                        String inputSousComp = scanner.nextLine();

                        List<String> choisis = new ArrayList<>();
                        String[] selections = inputSousComp.split(",");
                        for (String sel : selections) {
                            try {
                                int id = Integer.parseInt(sel.trim());
                                if (optionsInternes.containsKey(id)) {
                                    choisis.add(optionsInternes.get(id));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Entrée ignorée !!");
                            }
                        }

                        if (choisis.isEmpty()) {
                            System.out.println("Aucun comparateur interne sélectionné, retour.");
                            return;
                        }

                        // Crée un nom logique (tu peux adapter)
                        String nomComparateurCompose = "MotDecompose(" + String.join(",", choisis) + ")";
                        config.setNomComparateur(nomComparateurCompose);
                        System.out.println("Comparateur sélectionné : " + nomComparateurCompose);
                        break;

                    default:
                        System.out.println("Choix invalide.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrée non valide.");
            }
        } else {
            System.out.println("Comparateur inchangé.");
        }
    }
    private static void choisirselectionneur(Scanner scanner, Configuration config) {
        System.out.println("\n--- Choisir un selectionneur ---");
        System.out.println("1. SelectionneurAleatoire");
        System.out.println("2. SelectionneurNMeilleurs (paramètre : n)");
        System.out.println("3. SelectionneurPourcentage (paramètre : pourcentage)");
        System.out.println("4. SelectionneurSeuil (paramètre : seuil)");
        System.out.print("Entrez le numéro du sélectionneur à utiliser : ");

        String input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            try {
                int choix = Integer.parseInt(input.trim());
                
                switch (choix) {
                    case 1:
                        config.setNomSelectionneur("SelectionneurAleatoire");
                        System.out.println("Sélectionneur choisi : SelectionneurAleatoire");
                        break;
                        
                    case 2:
                        config.setNomSelectionneur("SelectionneurNMeilleurs");
                        System.out.print("Entrez la valeur de n (nombre de meilleurs éléments à sélectionner) : ");
                        int n = scanner.nextInt();
                        scanner.nextLine(); // consommer le retour à la ligne
                        config.setNombreMax(n);;
                        System.out.println("Sélectionneur choisi : SelectionneurNMeilleurs avec n = " + n);
                        break;
                        
                    case 3:
                        config.setNomSelectionneur("SelectionneurPourcentage");
                        System.out.print("Entrez le pourcentage (entre 0 et 100) : ");
                        double pourcentage = scanner.nextDouble();
                        scanner.nextLine(); // consommer le retour à la ligne
                        config.setPourcentage(pourcentage);
                        System.out.println("Sélectionneur choisi : SelectionneurPourcentage avec " + pourcentage + "%");
                        break;
                        
                    case 4:
                        config.setNomSelectionneur("SelectionneurSeuil");
                        System.out.print("Entrez le seuil (score minimum) : ");
                        double seuil = scanner.nextDouble();
                        scanner.nextLine(); // consommer le retour à la ligne
                        config.setSeuil(seuil);
                        System.out.println("Sélectionneur choisi : SelectionneurSeuil avec seuil = " + seuil);
                        break;
                        
                    default:
                        System.out.println("Numéro invalide. Veuillez choisir entre 1 et 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée non valide.");
            }
        } else {
            System.out.println("Comparateur inchangé.");
        }
}
    private static void choisirgenerateur(Scanner scanner, Configuration config) {
        System.out.println("\n--- Choisir un generateur de candidats ---");
        
        System.out.println("1. Generateur de candidats Aleatoire");
        System.out.println("2. Generateur de candidats Avec Index");
        System.out.println("2. Generateur de candidats Avec Taille");

        
        System.out.print("Entrez le numéro du sélectionneur à utiliser : ");

        String input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            try {
                int choix = Integer.parseInt(input.trim());
                
                switch (choix) {
                    case 1:
                        config.setNomGenerateur("GenerateurDeCandidatsAleatoire");
                        System.out.println(" Génerateur choisi : GenerateurDeCandidatsAleatoire");
                        break;
                        
                    case 2:
                        config.setNomGenerateur("GenerateurDeCandidatsAvecIndex");
                        
                        System.out.println("Génerateur choisi : GenerateurDeCandidatsAvecIndex");
                        break;
                    case 3:
                        config.setNomGenerateur("GenerateurDeCandidatsAvecTaille");
                        
                        System.out.println("Génerateur choisi : GenerateurDeCandidatsAvecTaille");
                        break;
                    
                    default:
                        System.out.println("Numéro invalide. Veuillez choisir entre 1 et 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée non valide.");
            }
        } else {
            System.out.println("generateur inchangé.");
        }
}
    public static void afficherConfiguration(Configuration config) {
	    System.out.println("\n=== Configuration actuelle ===");
	    System.out.println("Prétraiteurs : " + config.getNomsPretraiteurs());
	    System.out.println("Comparateur  : " + config.getNomComparateur());
	    System.out.println("Générateur   : " + config.getNomGenerateur());
	    System.out.println("Selectionneur: " + config.getNomSelectionneur());
	    System.out.println("Seuil        : " + config.getSeuil());
	    System.out.println("Max résultats: " + config.getNombreMax());
	    System.out.println("Pourcentage : " + config.getPourcentage());
	    
	}

	public static Pretraiteur getPretraiteur(String nom) {
        switch (nom) {
            case "Normalisation": return new Normalisation();
            case "Pretraiteur aleatoire": return new PretraiteurAleatoire();
            case "Encodage Phonétique": return new EncodagePhonetique();
            default: throw new IllegalArgumentException("Prétraiteur inconnu : " + nom);
        }
    }
    

    public static GenerateurDeCandidats getGenerateur(String nom) {
    	switch (nom) {
        case "GenerateurDeCandidatsAvecIndex": return new GenerateurDeCandidatsAvecIndex();
        case "GenerateurDeCandidatsAleatoire": return new GenerateurDeCandidatsAleatoire();
        case "GenerateurDeCandidatsAvecTaille": return new GenerateurDeCandidatsAvecTaille();

        default: throw new IllegalArgumentException("generateur inconnu : " + nom);
    }
    }

    public static SelectionneurDeResultats getSelectionneur(String nom,Configuration config) {
    	switch (nom) {
        case "SelectionneurAleatoire": return new SelectionneurAleatoire();
        case "SelectionneurNMeilleurs": return new SelectionneurNMeuilleurs(config);
        case "SelectionneurPourcentage": return new SelectionneurPourcentage(config);
        case "SelectionneurSeuil": return new SelectionneurSeuil(config);
        default: throw new IllegalArgumentException("selectionneur inconnu : " + nom);
    }
    }
    public static ComparateurNom getComparateur(String nom) {
    	    switch (nom) {
    	        case "Egalite exacte": return new ComparateurEgaliteExacte();}
    	        if (nom.startsWith("MotDecompose(") && nom.endsWith(")")) {
    	        String contenu = nom.substring("MotDecompose(".length(), nom.length() - 1);
    	        String[] nomdivise = contenu.split(",");

    	        List<String> comparateurs = new ArrayList<>();
    	        for (String s : nomdivise) {
    	            comparateurs.add(s.trim());
    	        }

    	        return new ComparateurNomDecompose(comparateurs);
    	    }
        throw new IllegalArgumentException("comparateur inconnu : " + nom);
    
    }
    public static ComparateurApproximatifdeChaine getComparateurDeChaine(String nom) {
        switch (nom) {
            case "Levenshtein": return new ComparateurLevenshtein();
            case "JaroWinkler": return new ComparateurJaroWinkler();
            default: throw new IllegalArgumentException("comparateur inconnu : " + nom);
        }
    }

}









