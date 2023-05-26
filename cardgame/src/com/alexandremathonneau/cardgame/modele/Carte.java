package com.alexandremathonneau.cardgame.modele;

/**
 *
 *
 */
public class Carte implements Comparable {

    private final CouleurCarte couleur;
    private final RangCarte 	  rang;

    public Carte (CouleurCarte couleur, RangCarte rang) {
		this.couleur = couleur;
    	this.rang = rang;
    }

	public CouleurCarte getCouleur() {
		return couleur;
	}

	public RangCarte getRang() {
		return rang;
	}

	@Override
	public String toString() {
		return rang.getNom()+" de "+couleur.valeurCouleur();
	}

	// Tri décroissant
	@Override
	public int compareTo(Object o) {
		return ((Carte) o).rang.getRang() - rang.getRang();
	}
}
