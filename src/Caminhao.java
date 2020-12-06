import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Caminhao implements Runnable{
    private Caminhoneiro caminhoneiro;
    private int capacidade;
    private List<Produto> produtos;
    private Celeiro celeiro;
    private Deposito deposito;
    private Semaphore semaforoCeleiro;
    private Semaphore semaforoDeposito;

    public Caminhao(Caminhoneiro caminhoneiro, int capacidade, Celeiro celeiro, Deposito deposito, Semaphore semaforoCeleiro, Semaphore semaforoDeposito) {
        this.caminhoneiro = caminhoneiro;
        this.capacidade = capacidade;
        this.celeiro = celeiro;
        this.deposito = deposito;
        this.semaforoCeleiro = semaforoCeleiro;
        this.semaforoDeposito = semaforoDeposito;
        this.produtos = new ArrayList<Produto>();
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
            semaforoCeleiro.acquire();
            celeiro.removerProduto(produto);
            produtos.add(produto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaforoCeleiro.release();
    }

    public void descarregarCaminhao(){
        while (!produtos.isEmpty()) {
            if (deposito.getProdutos().size()<deposito.getCapacidade()) {
                try {
                    semaforoDeposito.acquire();
                    Produto aux = produtos.get(0);
                    produtos.remove(aux);
                    deposito.adicionarProduto(aux);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaforoDeposito.release();
            }
        }
    }

    public void buscarProduto(){
        while (produtos.size()<capacidade) {
            if(celeiro.getProdutos().isEmpty() && !produtos.isEmpty())
                return;
            if (!celeiro.getProdutos().isEmpty())
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
            viajar();
        }
    }
}
