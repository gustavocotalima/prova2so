import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Deposito {
    private Gerente gerente;
    private List<Produto> produtos;
    private int capacidade;
    private Semaphore semaforo;

    public Deposito(Gerente gerente, int capacidade, Semaphore semaforo) {
        this.gerente = gerente;
        this.capacidade = capacidade;
        this.semaforo = semaforo;
        this.produtos = new ArrayList<Produto>();
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
