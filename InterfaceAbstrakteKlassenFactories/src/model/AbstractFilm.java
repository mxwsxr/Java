package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import interfaces.FilmInterface;

public abstract class AbstractFilm implements FilmInterface{
	private String germanTitle;
	private String year;
	private Map<FilmSingleProperty, String> singleProperties = new HashMap<>();
	private Map<FilmMultiProperty, List<String>> multiProperties = new HashMap<>();
	
	public AbstractFilm(String germanTitle, String year) {
		this.germanTitle = germanTitle;
		this.year = year;
	}
	
	@Override
	public String getGermanTitel() {
		return germanTitle;
	}
	
	@Override
	public String getYear() {
		return year;
	}

	@Override
	public String toString() {
		
		String prefix = this.getClass().getSimpleName()  + "|" + getGermanTitel() + "," + getYear();
		String single = Stream.of(FilmSingleProperty.values()).filter(x -> hasSingleProperty(x)).map(x -> getSingleProperty(x)).collect(Collectors.joining(","));
		prefix = single.isEmpty() ? prefix : prefix + "," + single;
		String multi = Stream.of(FilmMultiProperty.values()).filter(x -> hasMultiProperty(x)).map(x -> getMultiProperty(x).toString()).collect(Collectors.joining(","));

		return  prefix + (multi.isEmpty() ? "" : "," + multi) + "|";
	}
	
	@Override
	public String toSeparatedString(String sep1, String sep2) {	
		Stream<FilmSingleProperty> singlePropertyStream = Stream.of(FilmSingleProperty.values());
		Stream<FilmMultiProperty> multiPropertyStream = Stream.of(FilmMultiProperty.values());
		
		String singlePropertyString = singlePropertyStream.filter(x -> hasSingleProperty(x)).map(x -> x.name() + sep2 + getSingleProperty(x)).collect(Collectors.joining(sep1));
		String multiPropertyString = multiPropertyStream.filter(x -> hasMultiProperty(x)).map(x -> x.name() + sep2 + getMultiProperty(x)).collect(Collectors.joining(sep1));
		
		
		return this.getClass().getSimpleName() + "##" + getGermanTitel() + "##" + getYear()
				+ (singlePropertyString.isEmpty() ? "" : "##" + singlePropertyString)
				+ (multiPropertyString.isEmpty() ? "" : "##" + multiPropertyString);	
	}

	@Override
	public void setMultiProperty(FilmMultiProperty prop, List<String> multi) {
		multiProperties.put(prop, multi);
	}

	@Override
	public List<String> getMultiProperty(FilmMultiProperty prop) {
			return multiProperties.get(prop);
	}

	@Override
	public boolean hasMultiProperty(FilmMultiProperty prop) {
		return multiProperties.containsKey(prop);
	}
	
	@Override
	public String getSingleProperty(FilmSingleProperty prop) {
			return singleProperties.get(prop);
	}

	@Override
	public void setSingleProperty(FilmSingleProperty prop, String single) {
		singleProperties.put(prop, single);
	}

	

	@Override
	public boolean hasSingleProperty(FilmSingleProperty prop) {
		return singleProperties.containsKey(prop);
	}
	
}
