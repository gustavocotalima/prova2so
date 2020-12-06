import java.io.IOException;
import java.util.List;

public class Impressao implements Runnable{
    List<Producao> producoes;
    Celeiro celeiro;
    List<Caminhao> caminhoes;
    Deposito deposito;
    List<Cliente> clientes;

    public Impressao(List<Producao> producoes, Celeiro celeiro, List<Caminhao> caminhoes, Deposito deposito, List<Cliente> clientes) {
        this.producoes = producoes;
        this.celeiro = celeiro;
        this.caminhoes = caminhoes;
        this.deposito = deposito;
        this.clientes = clientes;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.out.println();
            for(Producao producao : producoes){
                System.out.println(producao.getFazendeiro().getNome()+": "+producao.getStatus() +" "+ producao.getFruta().getNome());
            }
            System.out.println();
            System.out.println("Estado Atual do Celeiro:" + celeiro.getProdutos().size());
            System.out.println();
            for(Caminhao caminhao : caminhoes){
                System.out.println(caminhao.getCaminhoneiro().getNome()+": "+caminhao.getProdutos().size());
            }

        }
    }
}
