import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa implements Runnable{
    List<Produto> produto;
    Deposito deposito;

    public Cliente(String nome, Deposito deposito) {
        super(nome);
        this.produto = new ArrayList<Produto>();
        this.deposito = deposito;
    }

    private void comprar(){
       produto.add(deposito.comprar());
    }

    private void tarefasVariadas(){
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            comprar();
            tarefasVariadas();
        }
    }
}
