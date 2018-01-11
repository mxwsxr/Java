package parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

public class WikiAnimationFilmParserMain {

	/*
	 * Gegeben: main zum Starten des Programmss
	 */
	public static void main(String[] args) throws MalformedURLException, IOException, ParseException {

		String fileName = "animation.html";
		
		/*
		 * Damit das Programm auch von der Console korrekt startet, muss an erster Stelle des classpath immer
		 * der Pfad zum bin Verzeichnis des Projekts der Aufgabe 2 stehen !!!!!
		 */
		Path animationPath = Paths.get(System.getProperty("java.class.path").split(";")[0].trim(), fileName);
		
		WikiAnimationFilmParser parser = new WikiAnimationFilmParser("file:///" + animationPath.toAbsolutePath());
		ppList(parser.contentToFilmList());
	}

	/*
	 * Zeilenweise Ausgabe einer Liste
	 * Gegeben
	 */
	private static void ppList(List<?> list) {
		for (Object o : list) {
			System.out.println(o);
		}

	}
	
	/**
	 * Untersuchen der Systemumgebung
	 */
	private static void showProps() {
		Properties props = System.getProperties();
		for (Object key : props.keySet()) {
			System.out.println(key + ":" + props.getProperty((String) key));
		}
	}

}
