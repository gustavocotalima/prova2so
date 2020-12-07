import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Principal {
    public static void main(String[]args){
        Scanner s = new Scanner(System.in);
        List<Thread> threads = new ArrayList<>();
        Semaphore semaforoCeleiro = new Semaphore(1);
        Semaphore semaforoDeposito = new Semaphore(1);

        System.out.print("Insira o tempo de plantacao (s):");
        int tempoPlantacao=s.nextInt();
        System.out.print("Insira o tempo de crescimento (s):");
        int tempoCrescimento = s.nextInt();
        System.out.print("Insira o tempo de colheita (s):");
        int tempoColheita = s.nextInt();

        System.out.print("Insira a capacidade maxima do celeiro: ");
        int capacidadeCeleiro = s.nextInt();

        System.out.print("Insira a capacidade maxima do caminhao: ");
        int capacidadeCaminhao = s.nextInt();

        System.out.print("Insira o tempo de viajem do caminhao (s): ");
        int tempoViagem = s.nextInt();

        System.out.print("Insira a capacidade maxima do deposito: ");
        int capacidadeDeposito = s.nextInt();

        System.out.print("Insira a quantidade de clientes: ");
        int quantidadeClientes = s.nextInt();

        System.out.print("Insira o tempo de tarefas do cliente: (s)");
        int tempoTarefa = s.nextInt();

        Fruta melancia = new Fruta("melancia", tempoPlantacao, tempoCrescimento,tempoColheita);
        Fruta uva = new Fruta("uva", tempoPlantacao, tempoCrescimento,tempoColheita);
        Fruta morango = new Fruta("morango", tempoPlantacao,tempoCrescimento,tempoColheita);

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

        Celeiro celeiro = new Celeiro(manuel, capacidadeCeleiro, producoes, semaforoCeleiro);

        threads.add(new Thread(celeiro));

        Gerente ragnar = new Gerente("Ragnar");

        Deposito deposito = new Deposito(ragnar, capacidadeDeposito, semaforoDeposito);

        threads.add(new Thread(deposito));

        Caminhoneiro pedro = new Caminhoneiro("Pedro");
        Caminhoneiro bino = new Caminhoneiro("Bino");

        List<Caminhao> caminhoes = new ArrayList<Caminhao>();

        caminhoes.add(new Caminhao(pedro, tempoViagem,capacidadeCaminhao, celeiro, deposito, semaforoCeleiro, semaforoDeposito));
        caminhoes.add(new Caminhao(bino, tempoViagem,capacidadeCaminhao, celeiro, deposito, semaforoCeleiro, semaforoDeposito));

        for(Caminhao caminhao : caminhoes)
            threads.add(new Thread(caminhao));


        List<Cliente> clientes = new ArrayList<Cliente>();

        for (int i = 0; i < quantidadeClientes; i++) {
            if (i < 28)
                clientes.add(new Cliente((String.valueOf((char) (i + 65))+String.valueOf((char) (i + 65))), deposito, tempoTarefa));
            else
                clientes.add(new Cliente(Integer.toString(i), deposito, tempoTarefa));
        }

        for(Cliente cliente : clientes)
            threads.add(new Thread(cliente));

        Impressao impressao = new Impressao(producoes,celeiro,caminhoes,deposito,clientes);

        threads.add(new Thread(impressao));

        for(Thread thread : threads)
            thread.start();

    }
}