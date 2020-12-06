import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa implements Runnable{
    List<Produto> produto;
    Deposito deposito;
    tarefaCliente tarefa;

    public Cliente(String nome, Deposito deposito) {
        super(nome);
        this.produto = new ArrayList<Produto>();
        this.deposito = deposito;
        this.tarefa = tarefaCliente.Comprando;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

    public tarefaCliente getTarefa() {
        return tarefa;
    }

    public void setTarefa(tarefaCliente tarefa) {
        this.tarefa = tarefa;
    }

    private void comprar(){
        this.tarefa = tarefaCliente.Comprando;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        produto.add(deposito.comprar());
    }

    private void tarefasVariadas(){
        this.tarefa = tarefaCliente.TarefasVariadas;
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
