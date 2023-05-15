package com.alexandremathonneau.cardgame.controller;

import java.util.ArrayList;
import java.util.List;

import com.alexandremathonneau.cardgame.model.Carte;
import com.alexandremathonneau.cardgame.model.Paquet;
import com.alexandremathonneau.cardgame.model.Joueur;
import com.alexandremathonneau.cardgame.view.View;


public class GameController {
	
	enum EtatDuJeu {
		PretAServir, CartesDistribuees, GagnantPli, GagnantPartie
	}
	
	Paquet paquet;
	List<Joueur> joueurs;
	Joueur gagnantPli;
	List<Carte> pli;
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
	 * Pour faire un pli, nous prenons une carte à chaque joueur, la première si la main du joueur en contient plusieurs.
	 */
	public void faireUnPli() {
		pli.clear();
		for (Joueur joueur : joueurs) {
			pli.add(joueur.jouerCarte(0));
			// FIXME
			// carte.retourner();
		}
		view.afficherPli();
		calculerGagnantDuPli(pli);
		ajouterPliAuGagnant(pli);
		this.run();
	}

	private void ajouterPliAuGagnant(List<Carte> pli) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Détermine la carte la plus forte du pli, et par conséquent désigne le gagnant du pli.<br>
	 * En cas d'égalité de rang entre plusieurs cartes du pli, nous laissons le hasard déterminer le gagnant du pli
	 * @param pli
	 */
	private void calculerGagnantDuPli(List<Carte> pli) {
		int meilleurRang = -1;

		pli.stream().max(Carte::compareTo);

		for (Carte carte : pli) {

		}

		for (Joueur joueur : joueurs) {
			boolean nouveauGagnant = false;
		}
	}

}
