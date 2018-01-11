package factories;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import interfaces.FilmParserInterface;
import parser.FilmDataSeparatedParser;
import parser.WikiAnimationFilmParser;
import parser.Wikipedia3DFilmParser;

public class FilmParserFactory {
	public static FilmParserInterface get3DFilmParserInstance(String fileName) throws MalformedURLException, IOException{
		Path animationPath = Paths.get(System.getProperty("java.class.path").split(";")[0].trim(), fileName);
		FilmParserInterface ThreeDeeFilmParser = new Wikipedia3DFilmParser("file:///" + animationPath.toAbsolutePath());
		return ThreeDeeFilmParser;
	}
	public static FilmParserInterface getAnimationFilmParserInstance(String fileName) throws MalformedURLException, IOException{
		Path animationPath = Paths.get(System.getProperty("java.class.path").split(";")[0].trim(), fileName);
		FilmParserInterface animationFilmParser = new WikiAnimationFilmParser("file:///" + animationPath.toAbsolutePath());
		return animationFilmParser;
	}
	public static FilmParserInterface getFilmDataSeparatedParserInstance(String fileName, String sep1, String sep2) throws MalformedURLException, IOException{
		Path animationPath = Paths.get(System.getProperty("java.class.path").split(";")[0].trim(), fileName);
		FilmParserInterface filmDataSeperatedParser = new FilmDataSeparatedParser("file:///" + animationPath.toAbsolutePath(), sep1, sep2);
		return filmDataSeperatedParser;
	}
}
