package com.alexandremathonneau.cardgame.games;

import com.alexandremathonneau.cardgame.controller.GameController;
import com.alexandremathonneau.cardgame.model.Paquet;
import com.alexandremathonneau.cardgame.view.View;

public class Games {

    public static void main (String[] args) {
        GameController gc = new GameController(new Paquet(), new View(), new HighCardGameEvaluator());
        gc.run();
    }
}
