package parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.AnimationFilm;

public class WikiAnimationFilmParser {
	private Scanner animationScanner;

	public void echoPage() {
		while (animationScanner.hasNextLine()) {
			System.out.println(animationScanner.nextLine());
		}
	}

	/**
	 * Top-Level Methode, die die Datei mit Hilfe eines Scanners parsed und den
	 * Inhalt in eine Liste von AnimationFilm-Objekten Ã¼berfÃ¼hrt.
	 * 
	 * @return Liste von AnimationFilm-Objekten
	 * @throws ParseException
	 *             Tritt u.a. auf, wenn beim Parsen von Date Objekten ein Fehler
	 *             erzeugt wird.
	 */
	public List<AnimationFilm> contentToFilmList() throws ParseException {
		ArrayList<AnimationFilm> listFilme = new ArrayList<>();
		String anfang = "title=\"Filmjahr.*?\">";
		Pattern year = Pattern.compile("(\\d{4})");
		Pattern element = Pattern.compile("(?:<td>(.*?)</td>)(?:<td>(.*?)</td>)"
				+ "(?:<td>(.*?)</td>)(?:<td>(.*?)</td>)" + "(?:<td>(.*?)</td>)(?:<td>(.*?)</td>)?",
				Pattern.MULTILINE | Pattern.DOTALL);
		Pattern ende = Pattern.compile("<div style=\"background-color:#808080; height:1px; width:8em;\"></div>");
		animationScanner.useDelimiter(anfang);
		if (animationScanner.hasNext())
			animationScanner.next();
		animationScanner.useDelimiter(ende);
		if (animationScanner.hasNext()) {
			animationScanner = new Scanner(animationScanner.next());
		}
		animationScanner.useDelimiter(anfang);
		while (animationScanner.hasNext()) {
			String data = animationScanner.next();
			data = clean(data);
			// System.out.println("" + data);
			Matcher yearmatch = year.matcher(data);
			Matcher elemmatch = element.matcher(data);
			if (yearmatch.find()) {
				String jahresZahl = yearmatch.group(1);
				while (elemmatch.find()) {
					listFilme.add(new AnimationFilm(elemmatch.group(3), elemmatch.group(4),
							parseDate(elemmatch.group(1), jahresZahl), Arrays.asList(elemmatch.group(2).split(",")),
							Arrays.asList(elemmatch.group(5).split(",")),
							(elemmatch.group(6) == null) ? "" : elemmatch.group(6)));
				}
			}
		}
		return listFilme;
	}

	/**
	 * LÃ¶scht alle HTML-Tags aus der Zeichenkette und ersetzt HTML Sonderzeichen
	 * (z.B. &amp;) durch lesbare Zeichen
	 * 
	 * @param str
	 *            : Zeichenkette, die noch HTML Tags oder HTML Sonderzeichen
	 *            enthÃ¤lt
	 * @return Zeichenkette ohne HTML-Tags und mit lesbaren Varianten fÃ¼r die
	 *         Sonderzeichen
	 */
	private String clean(String str) {
		return str.replaceAll("<a.*?>", "").replaceAll("</a>", "").replaceAll("</?b>", "").replaceAll("</?i>", "")
				.replaceAll("</?small>", "").replaceAll("</?p>", "").replaceAll("&amp;", "&")
				.replaceAll("<span.*?>", "").replaceAll("</span>", "").replaceAll("<br\\s?/?>", "")
				// .replaceAll("&lt;","<")
				.replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("\n", "").replaceAll("\r", "")
				.replaceAll("&nbsp;", " ");
	}

	/**
	 * Erzeugt aus einer Zeichenkette, die Tag und Monatsangaben enthÃ¤lt, und einer
	 * Zeichenkette, die die Jahreszahl enthÃ¤lt, ein Datumsobjekt.
	 * 
	 * @param dayAndMonth
	 *            Zeichenkette, die Tag und Monatsangaben enthÃ¤lt. Zeichenkette ist
	 *            ggf. nicht konform zur Darstellung von deutschen Tag und
	 *            Monatsangaben in der Langform und muss daher hier in eine gÃ¼ltige
	 *            Form Ã¼berfÃ¼hrt werden.
	 * @param year
	 *            Zeichenkette, die die Jahresangabe als 4-stellige Ziffer enthÃ¤lt.
	 *            Zeichenkette ist ggf. nicht konform zur Darstellung von
	 *            4-stelligen Jahresangaben und muss daher hier in eine gÃ¼ltige
	 *            Form Ã¼berfÃ¼hrt werden.
	 * @return Date - Objekt fÃ¼r die Datumsangaben in dayAndMonth und year
	 * @throws ParseException
	 */
	private Date parseDate(String dayAndMonth, String year) throws ParseException {
		return DateFormat.getDateInstance(DateFormat.LONG)
				.parse(String.format("%s %s",
						dayAndMonth.replaceAll(",", ".").replaceAll("\r.*", "").replaceAll("(\\d{1,2})\\s", "$1. ")
								.replaceAll("(\\d{1,2}\\.)(\\S)", "$1 $2").replaceAll("\\(", " ")
								.replaceAll("(\\d{1,2}.\\s[A-Za-z0-9_äÄöÖüÜß]*).*", "$1"),
						year));
	}

	/**
	 * GEGEBEN
	 * 
	 * @param url
	 *            Zeichenkette, die die URL-Ressource korrekt beschreibt
	 * @throws MalformedURLException
	 *             URL im falschen Format
	 * @throws IOException
	 *             kein Zugriff auf die Ressource
	 */
	public WikiAnimationFilmParser(String url) throws MalformedURLException, IOException {
		this.animationScanner = new Scanner(new URL(url).openStream(), "UTF-8");
	}
}