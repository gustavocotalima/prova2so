import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Caminhao implements Runnable{
    private Caminhoneiro caminhoneiro;
    private int capacidade;
    private List<Produto> produtos;
    private Celeiro celeiro;
    private Semaphore semaforo;

    public Caminhao(Caminhoneiro caminhoneiro, int capacidade, Semaphore semaforo) {
        this.caminhoneiro = caminhoneiro;
        this.capacidade = capacidade;
        this.produtos = new ArrayList<Produto>();
        this.semaforo = semaforo;
    }

    public Caminhoneiro getCaminhoneiro() {
        return caminhoneiro;
    }

    public void setCaminhoneiro(Caminhoneiro caminhoneiro) {
        this.caminhoneiro = caminhoneiro;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void carregarCaminhao(Produto produto){
        try {
            semaforo.acquire();
            celeiro.removerProduto(produto);
            produtos.add(produto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaforo.release();
    }

    public void descarregarCaminhao(){

    }

    public void buscarProduto(){
        while (produtos.size()<capacidade) {
            if(celeiro.getProdutos().isEmpty())
                return;
            carregarCaminhao(celeiro.getProdutos().get(0));
        }
    }

    public void viajar(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            buscarProduto();
            viajar();
            descarregarCaminhao();
        }
    }
}
