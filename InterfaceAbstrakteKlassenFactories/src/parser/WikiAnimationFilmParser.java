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
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interfaces.FilmInterface;
import interfaces.FilmParserInterface;
import model.AnimationFilm;
import model.FilmMultiProperty;
import model.FilmSingleProperty;

public class WikiAnimationFilmParser implements FilmParserInterface {

	private Scanner animationScanner;
	private Pattern tableBeginn = Pattern
			.compile("<h2><span id=\"1995�2004\"></span>.*?</span></span></h2>");
	private Pattern aniFilmBeginn = Pattern
			.compile("<tr>\\s*<td colspan=\"6\" class=\"hintergrundfarbe6\"><b><a href=.*? title=.*?>", Pattern.MULTILINE | Pattern.DOTALL);
	private Pattern aniFilmLines = Pattern
			.compile("<tr>\\s*<td><b>(.*?)</b></td>\\s*<td>(.*?)</td>\\s*<td><.*?>(.*?)<.*?></td>\\s*<td>(.*?)</td>\\s*<td>(.*?)</td>\\s*<td>(.*?)</td>");
	
	/**
	 * Top-Level Methode, die die Datei mit Hilfe eines Scanners parsed und den
	 * Inhalt in eine Liste von AnimationFilm-Objekten überführt.
	 * 
	 * @return Liste von AnimationFilm-Objekten
	 * @throws ParseException
	 *             Tritt u.a. auf, wenn beim Parsen von Date Objekten ein Fehler
	 *             erzeugt wird.
	 */
	public List<FilmInterface> contentToFilmList() throws ParseException {
		List<FilmInterface> listFilme = new ArrayList<FilmInterface>();
		
		//"<h2><span id=\"1995�2004\"></span>.*?</span></span></h2>"
		animationScanner.useDelimiter(tableBeginn);
		if (animationScanner.hasNext()) {
			 animationScanner.next();
		}
		
		//<td colspan=\"6\" class=\"hintergrundfarbe6\"><b><a href=.*? title=.*?>
		animationScanner.useDelimiter(aniFilmBeginn);
		if (animationScanner.hasNext()) {
			 animationScanner.next();
		}
		
		while(animationScanner.hasNext()){
			String[] next = animationScanner.next().split("</a></b></td>\\s*</tr>");
			String year =  next[0];
			
			Matcher matcherEnum = aniFilmLines.matcher(next[1]);
			while (matcherEnum.find()) {
				Date veroeffentlicht = parseDate(matcherEnum.group(1), year);
				List <String> laender = new ArrayList<String>(Arrays.asList(clean(matcherEnum.group(2)).split(",")));
				String deutscherTitel = clean(matcherEnum.group(3));
				String originalTitel = clean(matcherEnum.group(4));
				List<String> studios = new ArrayList<String>(Arrays.asList(clean(matcherEnum.group(5))));
				String bemerkung = clean(matcherEnum.group(6));
				
				
				AnimationFilm einAnimFilm = new AnimationFilm(deutscherTitel, year);
				einAnimFilm.setSingleProperty(FilmSingleProperty.PUBLISHED, DateFormat.getDateInstance(DateFormat.LONG,Locale.GERMANY).format(veroeffentlicht));
				einAnimFilm.setSingleProperty(FilmSingleProperty.ORIG_TITEL, originalTitel);
				einAnimFilm.setSingleProperty(FilmSingleProperty.COMMENTS, bemerkung);
				einAnimFilm.setMultiProperty(FilmMultiProperty.COUNTRIES, laender);
				einAnimFilm.setMultiProperty(FilmMultiProperty.STUDIOS, studios);
				
				listFilme.add(einAnimFilm);
			}
		}
		return listFilme;
	}

	/**
	 * Gegeben
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
	
	private String clean(String str) {
		return str.replaceAll("<.*?>", "");
	}
	
	private Date parseDate(String dayAndMonth, String year) throws ParseException {
		Date date = DateFormat.getDateInstance(DateFormat.LONG,Locale.GERMANY).
				parse(String.format("%s. %s %s", dayAndMonth.replaceAll("[^0-9]", ""), dayAndMonth.replaceAll("[^a-zA-Z�]", ""), year));	
		return date;
	}
}
