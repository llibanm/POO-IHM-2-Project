package src.java.org.projet.model.modelMatrixGame;


import src.java.org.projet.interfaces.EffectOnPlayer;

class Case {

    // Item qui aura un effet spécifique sur le joueur lorsqu'ils sera proche de lui(volontairement ou non)
    // ex: augmenter/reduire ses pdv pour potion, lui faire changer de location,...
    // chaque item et charactère devra redéfinir la méthode effect() de l'interface EffectOnPlayer
    // pour définir son effet sur le joueur
    private EffectOnPlayer element;

    public boolean isOccupied() {
        return element != null;
    }

    public void setElement(EffectOnPlayer element) {
        this.element = element;
    }

    public void performEffectOnHero() {
        element.effect();
    }
}
