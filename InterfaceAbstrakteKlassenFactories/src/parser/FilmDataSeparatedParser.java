package parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import interfaces.FilmInterface;
import interfaces.FilmParserInterface;
import model.AnimationFilm;
import model.FilmMultiProperty;
import model.FilmSingleProperty;
import model.ThreeDeeFilm;

public class FilmDataSeparatedParser implements FilmParserInterface {
	private Scanner animationScanner;
	private String sep1;
	private String sep2;
	
	public FilmDataSeparatedParser(String url, String sep1, String sep2) throws MalformedURLException, IOException{
		this.animationScanner = new Scanner(new URL(url).openStream(), "UTF-8");
		this.sep1 = sep1;
		this.sep2 = sep2;
	}
	
	
	@Override
	public List<FilmInterface> contentToFilmList() throws ParseException {
		List<FilmInterface> filmList = new ArrayList<FilmInterface>();
		
		while(animationScanner.hasNextLine()){
			String[] values = animationScanner.nextLine().split(sep1);	
			FilmInterface film;
			
			if(values[0].contains("AnimationFilm")){
				film = new AnimationFilm(values[1], values[2]);
			}
			else{
				film = new ThreeDeeFilm(values[1], values[2]);
			}
			
			for (String value : values){
				String[] valueSplit = value.split(sep2);
				for(FilmMultiProperty property : FilmMultiProperty.values()){
					if(valueSplit[0].equals(property.name())){
						film.setMultiProperty(property, Arrays.asList(valueSplit[1].substring(1, valueSplit[1].length() - 1).split(", ")));
					}
				}
				for(FilmSingleProperty property : FilmSingleProperty.values()){
					if(property.name().equals(valueSplit[0])){
						film.setSingleProperty(property, valueSplit[1]);
					}
				}	
			}
			filmList.add(film);
		}
		
		return filmList;
	}
}
