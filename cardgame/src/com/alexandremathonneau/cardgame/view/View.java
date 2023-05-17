package com.alexandremathonneau.cardgame.view;

import com.alexandremathonneau.cardgame.controller.GameController;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

public class View {

    GameController controller;
    Scanner keyboard = new Scanner(System.in);

    public void afficherToutesLesCartes(List<String> listeCartes) {
        listeCartes.forEach(System.out::println);
    }

    public void afficherPli(Map<String, String> pliAAfficher) {
        System.out.println("Appuyer sur Entree pour jouer un pli");
        keyboard.nextLine();
        String format = "%-20s%s%n";
        for (Map.Entry<String, String> entry : pliAAfficher.entrySet()) {
            System.out.printf(format, entry.getKey(), "=> " + entry.getValue());
        }
        System.out.println();
    }

    public void afficherGagnantPli(String nomGagnantPli) {
        System.out.println(nomGagnantPli + " gagne le pli");
    }

    public void afficherGagnantPartie(String nomGagnantPartie) {
        System.out.println(nomGagnantPartie + " gagne la partie");
    }

    public void setController(GameController gc) {
        this.controller = gc;
    }

    public void demandeNouvellePartie() {
        System.out.println("exit...");
        System.exit(0);
    }
}
