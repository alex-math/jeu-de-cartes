package com.alexandremathonneau.cardgame.model;

/**
 * 
 *
 */
public class Carte {
	
    static public final String[] NOMS_COULEURS = {"Coeur", "Carreau", "Pique", "Trefle"};

    static public final String[] NOMS_RANGS = {"2", "3", "4",
                     "5", "6", "7", "8", "9", "10", "Valet", "Reine", "Roi", "As"};

    private final CouleurCarte couleur;
    private final RangCarte 	  rang;
    private boolean 	  carteSurTapis;

    public Carte (CouleurCarte couleur, RangCarte rang) {
		this.couleur = couleur;
    	this.rang = rang;
    }

	public static String[] getNomsCouleurs() {
		return NOMS_COULEURS;
	}

	public static String[] getNomsRangs() {
		return NOMS_RANGS;
	}

	public CouleurCarte getCouleur() {
		return couleur;
	}

	public RangCarte getRang() {
		return rang;
	}

	public boolean isCarteSurTapis() {
		return carteSurTapis;
	}
    
	public boolean retourner() {
		carteSurTapis = !carteSurTapis;
		return carteSurTapis;
	}

	@Override
	public String toString() {
		return rang + " de " + couleur;
	}
}
