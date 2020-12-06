import java.util.ArrayList;
import java.util.List;

public class Celeiro implements Runnable{
    private Comerciante comerciante;
    private List<Fruta> frutas;
    private int capacidade;
    private List<Producao> produtores;

    public Celeiro(Comerciante comerciante, int capacidade, List<Producao> produtores) {
        this.comerciante = comerciante;
        this.frutas = new ArrayList<Fruta>();
        this.capacidade = capacidade;
        this.produtores = produtores;
    }

    public Comerciante getComerciante() {
        return comerciante;
    }

    public void setComerciante(Comerciante comerciante) {
        this.comerciante = comerciante;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void adicionarFruta(Fruta fruta){
        this.frutas.add(fruta);
    }

    public void removerFruta(Fruta fruta){
        this.frutas.remove(fruta);
    }

    @Override
    public void run() {

    }
}
