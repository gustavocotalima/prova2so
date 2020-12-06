import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Celeiro implements Runnable{
    private Comerciante comerciante;
    private List<Producao> producoes;
    private List<Produto> produtos;
    private int capacidade;
    private Semaphore semaforo;

    public Celeiro(Comerciante comerciante, int capacidade, List<Producao> producoes, Semaphore semaforo) {
        this.comerciante = comerciante;
        this.produtos = new ArrayList<Produto>();
        this.capacidade = capacidade;
        this.producoes = producoes;
        this.semaforo = semaforo;
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

    private void checarProducao(){
        for (Producao producao : producoes) {
            if (producao.getStatus() == Status.Vendendo) {
                if (produtos.size()<capacidade) {
                    try {
                        semaforo.acquire();
                        adicionarProduto(new Produto(producao.getFruta(), producao.getFazendeiro()));
                        producao.setStatus(Status.Plantando);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaforo.release();
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            checarProducao();
        }
    }
}
