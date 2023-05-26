package com.alexandremathonneau.cardgame.vue;

import com.alexandremathonneau.cardgame.controlleur.GameController;

import java.util.List;
import java.util.Map;

public interface VueDuJeu {

    public void afficherToutesLesCartes(List<String> listeCartes);

    public void afficherPli(Map<String, String> pliAAfficher);

    public void afficherGagnantPli(String nomGagnantPli);

    public void afficherGagnantPartie(String nomGagnantPartie);

    public void setController(GameController gc);

    public void demandeNouvellePartie();
}
