package com.alexandremathonneau.cardgame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paquet {
	
	private List<Carte> cartes;
	
	public Paquet() {
		cartes = new ArrayList<Carte>();
		for(RangCarte rang : RangCarte.values()) {
			for(CouleurCarte couleur : CouleurCarte.values()) {
				cartes.add(new Carte(couleur, rang));
				System.out.println(rang+" de "+couleur);
			}
		}

		battre();
	}

	/**
	 * Mélanger les cartes
	 */
	public void battre() {
		Collections.shuffle(cartes);
	}

	public Carte retirerCarteDuPaquet() {
		return cartes.remove(0);
	}

	public List<Carte> getCartes() {
		return cartes;
	}
	
}
