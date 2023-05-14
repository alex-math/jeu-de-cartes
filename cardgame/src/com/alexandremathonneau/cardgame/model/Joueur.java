package com.alexandremathonneau.cardgame.model;

import java.util.List;

public class Joueur {
	public enum Nom {
		UN 		(1),
		DEUX 	(2),
		TROIS 	(3),
		QUATRE 	(4);
		
		int nom;
		
		private Nom(int valeurNom) {
			nom = valeurNom;
		}
		
		public int valeurNom() {
			return nom;
		}
	}
	
	private Nom nom;
	private CartesEnMain main;
	private List<Pli> plis;
	
	public Joueur(Nom nom) {
		this.nom = nom;
		main = new CartesEnMain();
	}
	
	public void ajouterCarteALaMain(Carte carte) {
		main.ajouterCarte(carte);
	}
	
	public Carte retirerCarteDeLaMain() {
		return main.retirerCarte();
	}
	
	public Carte getCarte(int index) {
		return main.getCarte(index);
	}

	public void ajouterPli(Pli pli) {
		plis.add(pli);
	}

	@Override
	public String toString() {
		return "Joueur{" + nom + '}';
	}
}
