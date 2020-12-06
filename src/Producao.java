public class Producao implements Runnable{
    private Fazendeiro fazendeiro;
    private Fruta fruta;
    private Status status;

    public Producao(Fazendeiro fazendeiro, Fruta fruta) {
        this.fazendeiro = fazendeiro;
        this.fruta = fruta;
        this.status = Status.Plantando;
    }

    public Fazendeiro getFazendeiro() {
        return fazendeiro;
    }

    public void setFazendeiro(Fazendeiro fazendeiro) {
        this.fazendeiro = fazendeiro;
    }

    public Fruta getFruta() {
        return fruta;
    }

    public void setFruta(Fruta fruta) {
        this.fruta = fruta;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private void plantando(){
        this.status = Status.Plantando;
        try {
            Thread.sleep(this.getFruta().getTempoPlantacao() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void crescendo(){
        this.status = Status.Crescendo;
        try {
            Thread.sleep(this.getFruta().getTempoCrescimento()*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void colhendo(){
        this.status = Status.Colhendo;
        try {
            Thread.sleep(this.getFruta().getTempoColheita()*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void vendendo(){
        this.status = Status.Vendendo;

    }

    @Override
    public void run() {
        while(true) {
            plantando();
            crescendo();
            colhendo();
        }
    }
}
