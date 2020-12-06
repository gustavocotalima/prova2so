import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Deposito implements Runnable{
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void adicionarProduto(Produto produto){
        this.produtos.add(produto);
    }

    public void removerProduto(Produto produto){
        this.produtos.remove(produto);
    }

    public Produto comprar() {
        Produto p = produtos.get(0);
        produtos.remove(0);
        return p;
    }

    @Override
    public void run() {
        while(true){

        }
    }


}
