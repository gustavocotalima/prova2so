import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Caminhao implements Runnable{
    private Caminhoneiro caminhoneiro;
    private int capacidade;
    private int tempoViagem;
    private List<Produto> produtos;
    private Celeiro celeiro;
    private Deposito deposito;
    private Semaphore semaforoCeleiro;
    private Semaphore semaforoDeposito;
    private StatusCaminhao statusCaminhao;

    public Caminhao(Caminhoneiro caminhoneiro, int tempoViagem, int capacidade, Celeiro celeiro, Deposito deposito, Semaphore semaforoCeleiro, Semaphore semaforoDeposito) {
        this.caminhoneiro = caminhoneiro;
        this.tempoViagem = tempoViagem;
        this.capacidade = capacidade;
        this.celeiro = celeiro;
        this.deposito = deposito;
        this.semaforoCeleiro = semaforoCeleiro;
        this.semaforoDeposito = semaforoDeposito;
        this.produtos = new ArrayList<Produto>();
        this.statusCaminhao = StatusCaminhao.Carregando;
    }

    public int getTempoViagem() {
        return tempoViagem;
    }

    public void setTempoViagem(int tempoViagem) {
        this.tempoViagem = tempoViagem;
    }

    public Caminhoneiro getCaminhoneiro() {
        return caminhoneiro;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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

    public StatusCaminhao getStatusCaminhao() {
        return statusCaminhao;
    }

    public void setStatusCaminhao(StatusCaminhao statusCaminhao) {
        this.statusCaminhao = statusCaminhao;
    }

    private void carregarCaminhao() {
        statusCaminhao=StatusCaminhao.Carregando;
        Produto aux = celeiro.getProdutos().get(0);
        celeiro.removerProduto(aux);
        produtos.add(aux);
    }

    public void descarregarCaminhao(){
        statusCaminhao=StatusCaminhao.Descarregando;
        while (!produtos.isEmpty()) {
            if (deposito.getProdutos().size()<deposito.getCapacidade()) {
                Produto aux = produtos.get(0);
                produtos.remove(aux);
                deposito.adicionarProduto(aux);
            }
        }
    }

    public void viajar(){
        statusCaminhao=StatusCaminhao.Viajando;
        try {
            Thread.sleep(tempoViagem*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                semaforoCeleiro.acquire();
                while (celeiro.getProdutos().size()>0){
                    if(produtos.size()==capacidade)
                        break;
                    carregarCaminhao();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaforoCeleiro.release();
            viajar();
            try {
                semaforoDeposito.acquire();
                    descarregarCaminhao();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaforoDeposito.release();
            viajar();
        }
    }


}
