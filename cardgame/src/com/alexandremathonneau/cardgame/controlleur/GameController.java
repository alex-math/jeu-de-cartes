package com.alexandremathonneau.cardgame.controlleur;

import java.util.*;

import com.alexandremathonneau.cardgame.jeux.GameEvaluator;
import com.alexandremathonneau.cardgame.modele.Carte;
import com.alexandremathonneau.cardgame.modele.Paquet;
import com.alexandremathonneau.cardgame.modele.Joueur;
import com.alexandremathonneau.cardgame.vue.VueDuJeu;


public class GameController {

	enum EtatDuJeu {
		DebutDuJeu, PretAServir, CartesDistribuees, PartieEnCours, PartieTerminee
	}
	
	Paquet paquet;
	List<Joueur> joueurs;
	Joueur gagnantPli;
	Joueur gagnantPartie;
	Map<Joueur, Carte> pli = new HashMap<>();
	VueDuJeu vueDuJeu;
	
	EtatDuJeu etatDuJeu;

	GameEvaluator gameEvaluator;
	
	public GameController(Paquet paquet, VueDuJeu vueDuJeu, GameEvaluator gameEvaluator) {
		this.paquet = paquet;
		this.vueDuJeu = vueDuJeu;
		this.gameEvaluator = gameEvaluator;
		this.joueurs = new ArrayList<>();
		this.etatDuJeu = EtatDuJeu.DebutDuJeu;
		vueDuJeu.setController(this);
	}
	
	public void run() {
		
		switch (etatDuJeu) {
			case DebutDuJeu:
				creationDesJoueurs();
				break;
			case PretAServir:
				distribuerCartes();
				break;
			case CartesDistribuees:
				faireUnPli();
				break;
			case PartieEnCours:
				verifCartesEnMains();
				break;
			case PartieTerminee:
				gagnantPartie = gameEvaluator.calculerGagnantDeLaPartie(joueurs);
				vueDuJeu.afficherGagnantPartie(gagnantPartie.toString());
				vueDuJeu.demandeNouvellePartie();
				break;
		}
	}

	private void verifCartesEnMains() {
		etatDuJeu = EtatDuJeu.CartesDistribuees;
		for(Joueur joueur : joueurs) {
			if(joueur.getCartesEnMain().isEmpty()) {
				etatDuJeu = EtatDuJeu.PartieTerminee;
				break;
			}
		}
		this.run();
	}

	public void creationDesJoueurs() {
		for (Joueur.Nom nom : Joueur.Nom.values()) {
			joueurs.add(new Joueur(nom));
		}
		etatDuJeu = EtatDuJeu.PretAServir;
		this.run();
	}


	/**
	 * Constituer une main pour chaque joueur, avec un nombre égal de carte pour chacun d'entre eux
	 */
	public void distribuerCartes() {
		if (etatDuJeu != EtatDuJeu.CartesDistribuees) {
			List<String> nomsCartes = new ArrayList<>();
			for (Carte carte : paquet.getCartes()) {
				nomsCartes.add(carte.toString());
			}
			vueDuJeu.afficherToutesLesCartes(nomsCartes);
			paquet.battre();

			// Tant qu'il reste assez de cartes dans le paquet pour servir tous les joueurs
			while (!paquet.getCartes().isEmpty() && paquet.getCartes().size() >= joueurs.size()) {
				for (Joueur joueur : joueurs) {
					joueur.ajouterCarteALaMain(paquet.retirerCarteDuPaquet());
				}
			}
			etatDuJeu = EtatDuJeu.CartesDistribuees;
		}
		this.run();
	}
	
	/**
	 * Pour faire un pli, nous prenons une carte à chaque joueur, la première si la main du joueur en contient plusieurs.</br>
	 * Nous calculons le gagnant : celui qui détient la carte la plus forte.</br>
	 * Nous ajoutons le pli au total des cartes ramassées par le joueur
	 */
	public void faireUnPli() {
		pli.clear();
		LinkedHashMap<String, String> pliAAfficher = new LinkedHashMap<>();
		for (Joueur joueur : joueurs) {
			if (!joueur.getCartesEnMain().isEmpty()) {
				Carte carte = joueur.jouerCarte(0);
				pli.put(joueur, carte);
				pliAAfficher.put(joueur.toString(), carte.toString());
			}
		}
		vueDuJeu.afficherPli(pliAAfficher);

		gagnantPli = gameEvaluator.calculerGagnantDuPli(pli);
		vueDuJeu.afficherGagnantPli(gagnantPli.toString());

		ajouterPliAuGagnant();

		etatDuJeu = EtatDuJeu.PartieEnCours;

		this.run();
	}

	private void ajouterPliAuGagnant() {
		pli.forEach((k,v)->gagnantPli.ramasserCarte(v));
	}

	public void exitGame() {
		System.out.println("exit...");
		System.exit(0);
	}

	/**
	 * Pour recommencer la partie, nous remettons les cartes gagnées (par les joueurs dans la partie précédente) dans le paquet
	 */
	public void recommencerLaPartie() {
		joueurs.forEach(joueur -> paquet.remettreCarteDansLePaquet(joueur.getCartesGagnees()));

		etatDuJeu = EtatDuJeu.DebutDuJeu;

		this.run();
	}
}
