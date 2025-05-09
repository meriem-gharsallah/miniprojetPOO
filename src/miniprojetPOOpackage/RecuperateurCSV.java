package miniprojetPOOpackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecuperateurCSV implements RecuperateurDeNoms {
	
	private String cheminFichier;
	public RecuperateurCSV(String cheminFichier) {
		this.cheminFichier = cheminFichier;
	}

    @Override
    public List<Nom> recuperer() {
        List<Nom> liste = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int id = 1;
            while ((ligne = br.readLine()) != null) {
                ligne = ligne.trim();
                if (!ligne.isEmpty()) {
                    Nom nom = new Nom(id++, ligne);
                    liste.add(nom);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }

        return liste;
    }

	public String getCheminFichier() {
		return cheminFichier;
	}

	public void setCheminFichier(String cheminFichier) {
		this.cheminFichier = cheminFichier;
	}
}