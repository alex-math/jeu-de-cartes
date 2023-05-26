package com.alexandremathonneau.cardgame.modele;

public enum CouleurCarte {
	COEUR 	("coeur"),
	CARREAU ("carreau"),
	PIQUE 	("pique"),
	TREFLE 	("tr�fle");
	
	final String couleur;
	
	CouleurCarte(String valeurCouleur) {
		couleur = valeurCouleur;
	}
	
	public String valeurCouleur() {
		return couleur;
	}
}
