package parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interfaces.FilmInterface;
import interfaces.FilmParserInterface;

public class Wikipedia3DFilmParser implements FilmParserInterface {
	/*
	 * Regul�re Ausdr�cke f�r das Parsen der Datei
	 */
	private Pattern threeDeeBegin = Pattern
			.compile("<h2><span class=\"mw-headline\" id=\"3D-Filme\">3D-Filme</span>.*?</h2>");
	private Pattern threeDeeEnd = Pattern
			.compile("<h2><span class=\"mw-headline\" id=\"4D-Filme\">4D-Filme</span>.*?</h2>");
	// Die Option Pattern.MULTILINE|Pattern.DOTALL bewirkt, dass auch Zeilenumbr�che mit dem . matchen
	private Pattern threeDeeEnum = Pattern.compile(
			"<h3>.*?<span class=\"mw-headline\" id=.*?>(.*?)</span>.*?</h3>(.*?)</ul>",
			Pattern.MULTILINE | Pattern.DOTALL);
	private Scanner wiki3DFilmScanner = null;
	private Pattern liPattern = Pattern.compile("<li>(.*?)</li>");
	// private Pattern liPattern = Pattern.compile("<li>(?:<a .+?>(.+?)</a>)?(.*?)</li>");
	// private Pattern liPattern = Pattern.compile("<li>(?:<a .+?>(.+?)</a>)?(.+?)?(?:<a .+?>(.+?)</a>(.+?))?</li>");
	// private Pattern liPattern = Pattern.compile("<li>(?:<a .+?>(.+?)</a>)?(.+?)?(?:<a .+?>(.+?)</a>(.+?))?(?:<a .+?>(.+?)</a>(.+?))?</li>");

	/**
	 * Konstruktor �ffnet einen InputStream f�r eine URL und erzeugt einen
	 * Scanner f�r das Lesen der Quelle. Hier handelt es sich um eine HTML
	 * formatierte Seite. Das Encoding wird auf UTF-8 gesetzt. (Encodings k�nnen
	 * in dem Metainformationen einer HTML-Seite nachgesehen werden).
	 * 
	 * @param uri
	 *            eine URI als Zeichenkette dargestellt.
	 * @throws MalformedURLException:
	 *             uri ist eine ung�ltige Darstellung einer URL
	 * @throws IOException:
	 *             Beim �ffnen des InputStreams einer URL ist ein Fehler
	 *             aufgetreten
	 */
	public Wikipedia3DFilmParser(String uri) throws MalformedURLException, IOException {
		wiki3DFilmScanner = new Scanner(new URL(uri).openStream(), "UTF-8");
	}

	/**
	 * Konstruktor delegiert an den ersten Konstruktor und wandelt zuvor den
	 * Pfad in eine absoluten Pfad und dann einen Zeichenkette um.
	 * 
	 * @param path
	 *            eine Pfaddarstellung einer Ressource im Dateisystem
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public Wikipedia3DFilmParser(Path path) throws MalformedURLException, IOException {
		this(path.toAbsolutePath().toString());
	}

	/**
	 * Liest den Inhalt einer Ressource zeilenweise und gibt diesen auf der
	 * Konsole aus. Dies ist sinnvoll, um sich einen �berblick �ber den Aufbau
	 * der Seite zu verschaffen.
	 * 
	 * @return void
	 */

	public void echoPage() {
		while (wiki3DFilmScanner.hasNextLine()) {
			System.out.println(wiki3DFilmScanner.nextLine());
		}
	}

	/**
	 * Liest den Inhalt der Wiki 3D Filmseite und wandelt diesen in ein
	 * "3D-Film-Verzeichnis" um. Positioniert den Scanner vor dem Inhalt, der
	 * die Aufz�hlungen f�r die 3D-Filme enth�lt. Beendet das Parsen am Ende
	 * dieser Aufz�hlungen.
	 * 
	 * Gelesen wird "in memory": Der Bereich, der zwischen Beginn und Ende der
	 * Aufz�hlungen f�r die 3D Filme steht wird einmal in eine Zeichenkette
	 * gelesen, die dann mit Pattern-Matchern weiterverarbeitet wird.
	 * 
	 * @return Verzeichnis, das einzelnen Jahren oder Intervallen von Jahren die
	 *         Liste der in diesen Zeitr�umen erschienenen 3D File zuordnet.
	 */
	public Map<String, List<String>> contentTo3DFilmsPerYear() {
		Map<String, List<String>> threeDeeMap = new HashMap<>();
		// Positionieren des Scanners vor dem Pattern threeDeeBegin.
		// "<h2><span class=\"mw-headline\"
		// id=\"3D-Filme\">3D-Filme</span>.*?</h2>"
		wiki3DFilmScanner.useDelimiter(threeDeeBegin);
		if (wiki3DFilmScanner.hasNext()) {
			wiki3DFilmScanner.next();
		}
		// Lesen des Bereichs bis zum Ende der Aufz�hlung, das durch das Pattern
		// threeDeeEnd markiert wird.
		// "<h2><span class=\"mw-headline\"
		// id=\"4D-Filme\">4D-Filme</span>.*?</h2>"
		wiki3DFilmScanner.useDelimiter(threeDeeEnd);
		if (wiki3DFilmScanner.hasNext()) {
			String filmsPerYearEnumeration = wiki3DFilmScanner.next();
			// System.out.println(filmsPerYearEnumeration);
			// Extrahieren der Aufz�hlung nach Jahren / Zeitspannen. Pattern
			// threeDeeEnum
			// "<h3>.*?<span class=\"mw-headline\"
			// id=.*?>(.*?)</span>.*?</h3>(.*?)</ul>"
			Matcher matcherEnum = threeDeeEnum.matcher(filmsPerYearEnumeration);
			while (matcherEnum.find()) {
				// Lesen der Jahresangabe (Gruppe 1)
				String currentDate = matcherEnum.group(1);
				if (!threeDeeMap.containsKey(currentDate)) {
					threeDeeMap.put(currentDate, new ArrayList<>());
					// Extrahieren des Bereichs der ListItems, (Gruppe 2)
					String filmListContent = matcherEnum.group(2);
					// Parsen der ListItems und Einf�gen in das Verzeichnis
					contentTo3DFilmList(filmListContent, currentDate, threeDeeMap);
				}
			}
		}
		return threeDeeMap;
	}

	/**
	 * Parst aus einem String mit ListItems die Filminformation zu einer
	 * Zeitangabe und tr�gt diese in das "3D-Film-Verzeichnis" ein.
	 * 
	 * @param ulList
	 *            Zeichenkette, die die ListItems enth�lt
	 * @param currentDate
	 *            Zeitangabe, der Schl�ssel f�r das Verzeichnis
	 * @param threeDeeMap
	 *            Verzeichnis, in das zu dem Schl�ssel die Liste der geparsten
	 *            Filme eingetragen werden
	 */
	private void contentTo3DFilmList(String ulList, String currentDate, Map<String, List<String>> threeDeeMap) {
		// Erzeugen eines Matchers f�r das Extrahieren der HTML ListItems
		// (Pattern liPattern)
		// "<li>(.*)?</li>"
		Matcher liMatcher = liPattern.matcher(ulList);
		// Partielles Matching des liPatterns, f�r alle Items der Liste
		while (liMatcher.find()) {
			String liContent = liMatcher.group(1);
			liContent = liContent.replaceAll("<a.*?>|</a>|<i>|</i>", "");
			liContent.replaceAll("&amp;", "&");
			threeDeeMap.get(currentDate).add(liContent);
		}
	}
	
	/**
	 * Parst aus einem String mit ListItems die Filminformation zu einer
	 * Zeitangabe und tr�gt diese in das "3D-Film-Verzeichnis" ein.
	 * Alternative mit kompliziertem Pattern.
	 * 
	 * @param ulList
	 *            Zeichenkette, die die ListItems enth�lt
	 * @param currentDate
	 *            Zeitangabe, der Schl�ssel f�r das Verzeichnis
	 * @param threeDeeMap
	 *            Verzeichnis, in das zu dem Schl�ssel die Liste der geparsten
	 *            Filme eingetragen werden
	 */
	private void contentTo3DFilmList2(String ulList, String currentDate, Map<String, List<String>> threeDeeMap) {
		// Erzeugen eines Matchers f�r das Extrahieren der HTML ListItems
		// (Pattern liPattern)
		// "<li>(?:<a .+?>(.+?)</a>)?(.+?)?(?:<a .+?>(.+?)</a>(.+?))?(?:<a
		// .+?>(.+?)</a>(.+?))?</li>"
		Matcher liMatcher = liPattern.matcher(ulList);
		// Partielles Matching des liPatterns, f�r alle Items der Liste
		while (liMatcher.find()) {
			String concat = "";
			// Extraktion der Info f�r einen Film mit Hilfe der Gruppen des
			// liPattern
			for (int i = 1; i <= liMatcher.groupCount(); i++) {
				if (liMatcher.group(i) != null) {
					concat += liMatcher.group(i);
				}
			}
			// Hinzuf�gen eines Films in die Liste der Filme
			threeDeeMap.get(currentDate).add(concat);
		}
	}

	@Override
	public List<FilmInterface> contentToFilmList() throws ParseException {
		
		return null;
	}
}
