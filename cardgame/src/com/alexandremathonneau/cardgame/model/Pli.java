package com.alexandremathonneau.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Pli {

    private List<Carte> pli;

    public Pli() {
        pli = new ArrayList<Carte>();
    }

    public void ajouterCarte(Carte carte) {
        pli.add(carte);
    }

    public void retirerCartesDuPli() {
        pli.clear();
    }

    public Carte getCarte(int index) {
        return pli.get(index);
    }
}
