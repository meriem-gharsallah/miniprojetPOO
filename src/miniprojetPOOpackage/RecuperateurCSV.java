package miniprojetPOOpackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RecuperateurCSV implements RecuperateurDeNoms {
	
	private String cheminFichier;
	public RecuperateurCSV(String cheminFichier) {
		this.cheminFichier = cheminFichier;
	}

	@Override
	public List<Nom> recuperer() {
	try (Stream<String> lines = Files.lines(Paths.get(cheminFichier))) {
	return lines.skip(1) // Skip the header line
	.map(line -> line.split(","))
	.filter(values -> values.length >= 2)
	.map(values -> new Nom(values[0].trim(), values[1].trim()))
	.toList();
	} catch (IOException e) {
	e.printStackTrace();
	return List.of();
	}
	}

	public String getCheminFichier() {
		return cheminFichier;
	}

	public void setCheminFichier(String cheminFichier) {
		this.cheminFichier = cheminFichier;
	}
}