package com.alexandremathonneau.cardgame.games;

import com.alexandremathonneau.cardgame.model.Carte;
import com.alexandremathonneau.cardgame.model.Joueur;

import java.util.*;
import java.util.stream.Collectors;

public class HighCardGameEvaluator implements GameEvaluator{
    /**
     * La carte la plus forte du pli est la carte la plus élevée, conformément à l'ordre établi dans {@link com.alexandremathonneau.cardgame.model.RangCarte RangCarte}.<br>
     * En cas d'égalité de rang entre plusieurs cartes du pli, nous laissons le hasard déterminer le gagnant du pli
     * @param pli
     * @return
     */
    @Override
    public Joueur calculerGagnantDuPli(Map<Joueur, Carte> pli) {
        // Tri décroissant du pli
        List<Map.Entry<Joueur, Carte> > sortedPli = new ArrayList<>(pli.entrySet());
        Collections.sort(sortedPli, Comparator.comparing(Map.Entry::getValue));

//		for (Map.Entry<Joueur, Carte> l : sortedPli) {
//			System.out.println("Key ->"
//					+ " " + l.getKey()
//					+ ": Value ->"
//					+ l.getValue());
//		}

        // Liste des joueurs ayant une carte de même valeur, de même rang
        List<Joueur> gagnants = pli.entrySet().stream()
                .filter(map -> sortedPli.get(0).getValue().getRang().equals(map.getValue().getRang()))
                .map(map -> map.getKey())
                .collect(Collectors.toList());

        Random r = new Random();
        return gagnants.get(r.nextInt(0, gagnants.size()));
    }
}
