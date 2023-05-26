package com.alexandremathonneau.cardgame.jeux;

import com.alexandremathonneau.cardgame.modele.Carte;
import com.alexandremathonneau.cardgame.modele.Joueur;

import java.util.*;
import java.util.stream.Collectors;

public class HighCardGameEvaluator implements GameEvaluator{
    /**
     * La carte la plus forte du pli est la carte la plus élevée, conformément à l'ordre établi dans {@link com.alexandremathonneau.cardgame.modele.RangCarte RangCarte}.<br>
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

    /**
     * Le gagnant est le joueur qui a remporté le plus de plis<br>
     * En cas d'égalité entre plusieurs joueurs, nous laissons le hasard déterminer le gagnant de la partie
     * @param joueurs
     * @return
     */
    @Override
    public Joueur calculerGagnantDeLaPartie(List<Joueur> joueurs) {
        // Liste des joueurs ayant remporté le même nombre de carte durant la partie
        List<Joueur> gagnants = joueurs.stream()
                .filter(list -> joueurs.get(0).getCartesGagnees().equals(list.getCartesGagnees()))
                .collect(Collectors.toList());

        Random r = new Random();
        return gagnants.get(r.nextInt(0, gagnants.size()));
    }
}
