public class Producao implements Runnable{
    private Fazendeiro fazendeiro;
    private Fruta fruta;
    private StatusProducao statusProducao;

    public Producao(Fazendeiro fazendeiro, Fruta fruta) {
        this.fazendeiro = fazendeiro;
        this.fruta = fruta;
        this.statusProducao = StatusProducao.Plantando;
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

    public StatusProducao getStatus() {
        return statusProducao;
    }

    public void setStatus(StatusProducao statusProducao) {
        this.statusProducao = statusProducao;
    }

    private void plantando(){
        this.statusProducao = StatusProducao.Plantando;
        try {
            Thread.sleep(this.getFruta().getTempoPlantacao() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void crescendo(){
        this.statusProducao = StatusProducao.Crescendo;
        try {
            Thread.sleep(this.getFruta().getTempoCrescimento()*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void colhendo(){
        this.statusProducao = StatusProducao.Colhendo;
        try {
            Thread.sleep(this.getFruta().getTempoColheita()*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void vendendo(){
        this.statusProducao = StatusProducao.Vendendo;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(this.statusProducao == StatusProducao.Vendendo);
    }

    @Override
    public void run() {
        while(true) {
            plantando();
            crescendo();
            colhendo();
            vendendo();
        }
    }
}
