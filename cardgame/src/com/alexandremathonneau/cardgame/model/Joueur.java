package com.alexandremathonneau.cardgame.model;

import java.util.ArrayList;
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
	private List<Carte> cartesEnMain;
	private List<Carte> cartesGagnees;
	
	public Joueur(Nom nom) {
		this.nom = nom;
		cartesEnMain = new ArrayList<Carte>();
		cartesGagnees = new ArrayList<Carte>();
	}
	
	public void ajouterCarteALaMain(Carte carte) {
		cartesEnMain.add(carte);
	}
	
	public Carte jouerCarte(int index) {
		return cartesEnMain.remove(index);
	}

	public void ramasserCarte(Carte carte) {
		cartesGagnees.add(carte);
	}

	@Override
	public String toString() {
		return "Joueur {" + nom + '}';
	}
}
