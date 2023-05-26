package com.alexandremathonneau.cardgame.jeux;

import com.alexandremathonneau.cardgame.modele.Carte;
import com.alexandremathonneau.cardgame.modele.Joueur;

import java.util.*;

public interface GameEvaluator {

    /**
     * Détermine la carte la plus forte du pli, et par conséquent désigne le gagnant du pli.<br>
     * @param pli
     * @return
     */
    public Joueur calculerGagnantDuPli(Map<Joueur, Carte> pli);

    /**
     * Détermine le gagnat à la fin d'une partie<br>
     * @param joueurs
     * @return
     */
    public Joueur calculerGagnantDeLaPartie(List<Joueur> joueurs);
}
