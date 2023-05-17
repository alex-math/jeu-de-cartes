package com.alexandremathonneau.cardgame.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.alexandremathonneau.cardgame.model.Carte;
import com.alexandremathonneau.cardgame.model.Paquet;
import com.alexandremathonneau.cardgame.model.Joueur;
import com.alexandremathonneau.cardgame.view.View;


public class GameController {
	
	enum EtatDuJeu {
		DebutDuJeu, PretAServir, CartesDistribuees, PartieTerminee
	}
	
	Paquet paquet;
	List<Joueur> joueurs;
	Joueur gagnantPli;
	LinkedHashMap<Joueur, Carte> pli = new LinkedHashMap<>();
	View view;
	
	EtatDuJeu etatDuJeu;
	
	public GameController(Paquet paquet, View view) {
		this.paquet = paquet;
		this.view = view;
		this.joueurs = new ArrayList<Joueur>();
		this.etatDuJeu = EtatDuJeu.DebutDuJeu;
		view.setController(this);
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
			case PartieTerminee:
				view.demandeNouvellePartie();
				break;
		}
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
			view.afficherToutesLesCartes(nomsCartes);
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
			Carte carte = joueur.jouerCarte(0);
			pli.put(joueur, carte);
			pliAAfficher.put(joueur.toString(), carte.toString());
		}
		view.afficherPli(pliAAfficher);

		calculerGagnantDuPli();
		view.afficherGagnantPli(gagnantPli.toString());

		ajouterPliAuGagnant();

		this.run();
	}

	private void ajouterPliAuGagnant() {
		pli.forEach((k,v)->gagnantPli.ramasserCarte(v));
	}

	/**
	 * Détermine la carte la plus forte du pli, et par conséquent désigne le gagnant du pli.<br>
	 * En cas d'égalité de rang entre plusieurs cartes du pli, nous laissons le hasard déterminer le gagnant du pli
	 *
	 */
	private void calculerGagnantDuPli() {

		List<Map.Entry<Joueur, Carte> > sortedPli = new ArrayList<>(pli.entrySet());
		Collections.sort(sortedPli, Comparator.comparing(Map.Entry::getValue));

		for (Map.Entry<Joueur, Carte> l : sortedPli) {
			System.out.println("Key ->"
					+ " " + l.getKey()
					+ ": Value ->"
					+ l.getValue());
		}
		List<Joueur> gagnants = new ArrayList<>();

		gagnants = pli.entrySet().stream()
				.filter(map -> sortedPli.get(0).getValue().equals(map.getValue()))
				.map(map -> map.getKey())
				.collect(Collectors.toList());

		Random r = new Random();
		gagnantPli = gagnants.get(r.nextInt(gagnants.size()));
	}

}
