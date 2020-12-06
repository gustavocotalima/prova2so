
public class Fruta {

    private String nome;
    private int tempoPlantacao; //em dias
    private int tempoCrescimento; //em dias
    private int tempoColheita; //em dias

    public Fruta(String nome, int tempoPlantacao, int tempoCrescimento, int tempoColheita) {
        this.nome = nome;
        this.tempoPlantacao = tempoPlantacao;
        this.tempoCrescimento = tempoCrescimento;
        this.tempoColheita = tempoColheita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoPlantacao() {
        return tempoPlantacao;
    }

    public void setTempoPlantacao(int tempoPlantacao) {
        this.tempoPlantacao = tempoPlantacao;
    }

    public int getTempoCrescimento() {
        return tempoCrescimento;
    }

    public void setTempoCrescimento(int tempoCrescimento) {
        this.tempoCrescimento = tempoCrescimento;
    }

    public int getTempoColheita() {
        return tempoColheita;
    }

    public void setTempoColheita(int tempoColheita) {
        this.tempoColheita = tempoColheita;
    }
}
