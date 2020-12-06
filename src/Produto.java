public class Produto {
    Fruta fruta;
    Fazendeiro fazendeiro;

    public Produto(Fruta fruta, Fazendeiro fazendeiro) {
        this.fruta = fruta;
        this.fazendeiro = fazendeiro;
    }

    public Fruta getFruta() {
        return fruta;
    }

    public void setFruta(Fruta fruta) {
        this.fruta = fruta;
    }

    public Fazendeiro getFazendeiro() {
        return fazendeiro;
    }

    public void setFazendeiro(Fazendeiro fazendeiro) {
        this.fazendeiro = fazendeiro;
    }
}
