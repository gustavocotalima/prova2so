import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[]args){
        List<Thread> threads = new ArrayList<>();

        Fruta melancia = new Fruta("melancia", 2, 10,3);
        Fruta uva = new Fruta("uva", 2, 9,3);
        Fruta morango = new Fruta("morango", 2,7,2);

        Fazendeiro tiago = new Fazendeiro("Tiago");
        Fazendeiro joao = new Fazendeiro("João");
        Fazendeiro alice = new Fazendeiro("Alice");

        List<Producao> produtores = new ArrayList<>();

        produtores.add(new Producao(tiago, melancia));
        produtores.add(new Producao(joao, uva));
        produtores.add(new Producao(alice, morango));

        for(Producao produtor : produtores) {
            threads.add(new Thread(produtor));
        }

        Comerciante manuel = new Comerciante("Manuel");

        Celeiro celeiro = new Celeiro(manuel, 10, produtores);

        threads.add(new Thread(celeiro));

        for(Thread thread : threads) {
            thread.start();
        }
    }
}