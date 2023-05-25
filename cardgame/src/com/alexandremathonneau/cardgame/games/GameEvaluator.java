package com.alexandremathonneau.cardgame.games;

import com.alexandremathonneau.cardgame.model.Carte;
import com.alexandremathonneau.cardgame.model.Joueur;

import java.util.*;
import java.util.stream.Collectors;

public interface GameEvaluator {

    /**
     * Détermine la carte la plus forte du pli, et par conséquent désigne le gagnant du pli.<br>
     *
     */
    public Joueur calculerGagnantDuPli(Map<Joueur, Carte> pli);
}
