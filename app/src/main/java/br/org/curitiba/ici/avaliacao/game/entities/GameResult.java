package br.org.curitiba.ici.avaliacao.game.entities;

public enum GameResult {
    WON("Vitória!!! Parabéns!!!"), LOST("Você perdeu, tente novamente!"), DRAW("Ih, empatou...");

    private final String value;
    GameResult(String thisValue) {
        value = thisValue;
    }

    public String getValue(){
        return value;
    }
}
