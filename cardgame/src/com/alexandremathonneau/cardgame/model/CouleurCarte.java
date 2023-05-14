package com.alexandremathonneau.cardgame.model;

public enum CouleurCarte {
	COEUR 	("coeur"),
	CARREAU ("carreau"),
	PIQUE 	("pique"),
	TREFLE 	("tr�fle");
	
	String couleur;
	
	private CouleurCarte(String valeurCouleur) {
		couleur = valeurCouleur;
	}
	
	public String valeurCouleur() {
		return couleur;
	}
}
