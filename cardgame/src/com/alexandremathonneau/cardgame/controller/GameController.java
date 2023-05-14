package com.alexandremathonneau.cardgame.controller;

import java.util.ArrayList;
import java.util.List;

import com.alexandremathonneau.cardgame.model.Paquet;
import com.alexandremathonneau.cardgame.model.Joueur;
import com.alexandremathonneau.cardgame.model.Pli;
import com.alexandremathonneau.cardgame.view.View;


public class GameController {
	
	enum EtatDuJeu {
		PretAServir, CartesDistribuees, GagnantPli, GagnantPartie
	}
	
	Paquet paquet;
	List<Joueur> joueurs;
	Joueur gagnant;
	Pli pli;
	View view;
	
	EtatDuJeu etatDuJeu;
	
	public GameController(Paquet paquet, View view) {
		this.paquet = paquet;
		this.view = view;
		this.joueurs = new ArrayList<Joueur>();
		this.etatDuJeu = EtatDuJeu.PretAServir;
		view.setController(this);
	}
	
	public void run() {
		
		switch (etatDuJeu) {
		case CartesDistribuees:
			view.afficherPli();
			break;
		case GagnantPli:
			view.afficherGagnantPli();
			break;
		case GagnantPartie:
			view.afficherGagnantPartie();
			break;
		}
	}
	
	public void creationDesJoueurs() {
		for (Joueur.Nom nom : Joueur.Nom.values()) {
			joueurs.add(new Joueur(nom));
		}
	}


	/**
	 * Constituer une main pour chaque joueur
	 */
	public void distribuerCartes() {
		if (etatDuJeu != EtatDuJeu.CartesDistribuees) {
			view.afficherToutesLesCartes();
			paquet.battre();
			boolean nbCartesSuffisantDansPaquet = !paquet.getCartes().isEmpty() || paquet.getCartes().size() < joueurs.size();

			while (nbCartesSuffisantDansPaquet) {
				// Tirer une carte pour chaque joueur
				for (Joueur joueur : joueurs) {
					joueur.ajouterCarteALaMain(paquet.retirerCarteDuPaquet());
				}
			}
			etatDuJeu = EtatDuJeu.CartesDistribuees;
		}
		this.run();
	}
	
	public void faireUnePartie() {
		faireUnPli();
		
	}
	
	/**
	 * 
	 */
	public void faireUnPli() {
		pli.retirerCartesDuPli();
		for (Joueur joueur : joueurs) {
			pli.ajouterCarte(joueur.getCarte(0));
			// FIXME
			// carte.retourner();
		}
		view.afficherPli();
		gagnant = calculerGagnantDuPli(pli);
		ajouterPliAuGagnant(pli);
		this.run();
	}

	private void ajouterPliAuGagnant(Pli pli) {
		// TODO Auto-generated method stub
		
	}

	private Joueur calculerGagnantDuPli(Pli pli) {
		Joueur gagnant = null;
		int meilleurRang = -1;

		for (Joueur joueur : joueurs) {
			boolean nouveauGagnant = false;
		}
		return gagnant;
	}

}
