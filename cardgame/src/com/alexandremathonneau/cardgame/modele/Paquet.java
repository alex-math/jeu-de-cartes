package com.alexandremathonneau.cardgame.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paquet {
	
	private List<Carte> cartes;
	
	public Paquet() {
		cartes = new ArrayList<>();
		for(CouleurCarte couleur : CouleurCarte.values()) {
			for(RangCarte rang : RangCarte.values()) {
				cartes.add(new Carte(couleur, rang));
//				System.out.println(rang.getNom()+" de "+couleur.valeurCouleur());
			}
		}
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

	public void remettreCarteDansLePaquet(List<Carte> cartes) {
		cartes.forEach(carte -> this.cartes.add(carte));
	}

	public List<Carte> getCartes() {
		return cartes;
	}
}
