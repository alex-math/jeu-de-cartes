package com.alexandremathonneau.cardgame.model;

import java.util.HashMap;
import java.util.Map;

public enum RangCarte {
	DEUX 	("2", 2),
	TROIS 	("3", 3),
	QUATRE 	("4", 4),
	CINQ 	("5", 5),
	SIX 	("6", 6),
	SEPT 	("7", 7),
	HUIT 	("8", 8),
	NEUF 	("9", 9),
	VALET 	("Valet", 10),
	REINE 	("Dame", 11),
	ROI 	("Roi", 12),
	AS 		("As", 13);

	final String nom;
	final int rang;
	
	RangCarte(String nom, int valeurCarte) {
		this.nom = nom;
		this.rang = valeurCarte;
	}

	public String getNom() {
		return nom;
	}
	
	public int getRang() {
		return rang;
	}

}
