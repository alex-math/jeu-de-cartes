package com.alexandremathonneau.cardgame.vue;

import com.alexandremathonneau.cardgame.controlleur.GameController;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VueLigneDeCommande implements VueDuJeu {


    Scanner keyboard = new Scanner(System.in);
    GameController controller;

    @Override
    public void afficherToutesLesCartes(List<String> listeCartes) {
        listeCartes.forEach(System.out::println);
    }

    @Override
    public void afficherPli(Map<String, String> pliAAfficher) {
        System.out.println("Appuyer sur Entree pour jouer un pli");
        keyboard.nextLine();
        String format = "%-20s%s%n";
        for (Map.Entry<String, String> entry : pliAAfficher.entrySet()) {
            System.out.printf(format, entry.getKey(), "=> " + entry.getValue());
        }
    }

    @Override
    public void afficherGagnantPli(String nomGagnantPli) {
        System.out.println("=> " + nomGagnantPli + " gagne le pli");
        System.out.println();
    }

    @Override
    public void afficherGagnantPartie(String nomGagnantPartie) {
        System.out.println(nomGagnantPartie + " gagne la partie");
    }

    @Override
    public void setController(GameController gc) {
        this.controller = gc;
    }

    @Override
    public void demandeNouvellePartie() {
        System.out.println("Souhaitez-vous recommencer une partie ? o/n (\"o\" pour recommencer) :");
        String reponse = keyboard.nextLine();
        if (reponse.contains("o")) {
            this.controller.recommencerLaPartie();
        } else {
            this.controller.exitGame();
        }

    }
}
