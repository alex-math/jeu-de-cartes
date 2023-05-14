package com.alexandremathonneau.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class CartesEnMain {

	private List<Carte> cartes;
	
	public CartesEnMain() {
		cartes = new ArrayList<Carte>();
	}
	
	public void ajouterCarte(Carte carte) {
		cartes.add(carte);
	}
	
	public Carte retirerCarte() {
		return cartes.remove(0);
	}
	
	public Carte getCarte(int index) {
		return cartes.get(index);
	}
}
