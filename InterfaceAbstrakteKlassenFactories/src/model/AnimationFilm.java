package model;

import interfaces.FilmInterface;


public class AnimationFilm extends AbstractFilm implements FilmInterface {
	
	public AnimationFilm(String germanTitle, String year) {
		super(germanTitle, year);
	}
}
