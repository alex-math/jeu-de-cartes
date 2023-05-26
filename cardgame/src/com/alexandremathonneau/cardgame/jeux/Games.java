package com.alexandremathonneau.cardgame.jeux;

import com.alexandremathonneau.cardgame.controlleur.GameController;
import com.alexandremathonneau.cardgame.modele.Paquet;
import com.alexandremathonneau.cardgame.vue.VueLigneDeCommande;

public class Games {

    public static void main (String[] args) {
        GameController gc = new GameController(new Paquet(), new VueLigneDeCommande(), new HighCardGameEvaluator());
        gc.run();
    }
}
