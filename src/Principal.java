import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Principal {
    public static void main(String[]args){
        List<Thread> threads = new ArrayList<>();
        Semaphore semaforo = new Semaphore(1);

        Fruta melancia = new Fruta("melancia", 2, 10,3);
        Fruta uva = new Fruta("uva", 2, 9,3);
        Fruta morango = new Fruta("morango", 2,7,2);

        Fazendeiro tiago = new Fazendeiro("Tiago");
        Fazendeiro joao = new Fazendeiro("João");
        Fazendeiro alice = new Fazendeiro("Alice");

        List<Producao> producoes = new ArrayList<>();

        producoes.add(new Producao(tiago, melancia));
        producoes.add(new Producao(joao, uva));
        producoes.add(new Producao(alice, morango));

        for(Producao produtor : producoes) {
            threads.add(new Thread(produtor));
        }

        Comerciante manuel = new Comerciante("Manuel");

        Celeiro celeiro = new Celeiro(manuel, 10, producoes, semaforo);

        threads.add(new Thread(celeiro));

        for(Thread thread : threads) {
            thread.start();
        }
    }
}