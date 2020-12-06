import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Principal {
    public static void main(String[]args){
        List<Thread> threads = new ArrayList<>();
        Semaphore semaforoCeleiro = new Semaphore(1);
        Semaphore semaforoDeposito = new Semaphore(1);

        Fruta melancia = new Fruta("melancia", 2, 1,3);
        Fruta uva = new Fruta("uva", 2, 2,3);
        Fruta morango = new Fruta("morango", 2,2,2);

        Fazendeiro tiago = new Fazendeiro("Tiago");
        Fazendeiro joao = new Fazendeiro("João");
        Fazendeiro alice = new Fazendeiro("Alice");

        List<Producao> producoes = new ArrayList<>();

        producoes.add(new Producao(tiago, melancia));
        producoes.add(new Producao(joao, uva));
        producoes.add(new Producao(alice, morango));

        for(Producao produtor : producoes)
            threads.add(new Thread(produtor));

        Comerciante manuel = new Comerciante("Manuel");

        Celeiro celeiro = new Celeiro(manuel, 10, producoes, semaforoCeleiro);

        threads.add(new Thread(celeiro));

        Gerente ragnar = new Gerente("Ragnar");

        Deposito deposito = new Deposito(ragnar, 20, semaforoDeposito);

        threads.add(new Thread(deposito));

        Caminhoneiro pedro = new Caminhoneiro("Pedro");
        Caminhoneiro bino = new Caminhoneiro("Bino");

        List<Caminhao> caminhoes = new ArrayList<Caminhao>();

        caminhoes.add(new Caminhao(pedro,3, celeiro, deposito, semaforoCeleiro, semaforoDeposito));
        caminhoes.add(new Caminhao(bino,2, celeiro, deposito, semaforoCeleiro, semaforoDeposito));

        for(Caminhao caminhao : caminhoes)
            threads.add(new Thread(caminhao));


        List<Cliente> clientes = new ArrayList<Cliente>();

        clientes.add(new Cliente("ladainha", deposito));
        clientes.add(new Cliente("gustavo", deposito));
        clientes.add(new Cliente("seguranca", deposito));

        for(Cliente cliente : clientes)
            threads.add(new Thread(cliente));

        Impressao impressao = new Impressao(producoes,celeiro,caminhoes,deposito,clientes);

        threads.add(new Thread(impressao));

        for(Thread thread : threads)
            thread.start();

    }
}