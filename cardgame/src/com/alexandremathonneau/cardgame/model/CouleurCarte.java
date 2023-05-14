package com.alexandremathonneau.cardgame.model;

public enum CouleurCarte {
	COEUR 	("coeur"),
	CARREAU ("carreau"),
	PIQUE 	("pique"),
	TREFLE 	("trèfle");
	
	String couleur;
	
	private CouleurCarte(String valeurCouleur) {
		couleur = valeurCouleur;
	}
	
	public String valeurCouleur() {
		return couleur;
	}
}
