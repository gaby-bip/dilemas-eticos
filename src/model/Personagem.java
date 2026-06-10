package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Personagem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int etica;
    private int relacionamento;
    private int reputacao;
    private int desempenho;
    private int indiceCenarioAtual;
    private ArrayList<String> historicoEscolhas;

    public Personagem(String nome) {
        this.nome = nome;
        this.etica = 50;
        this.relacionamento = 50;
        this.reputacao = 50;
        this.desempenho = 50;
        this.indiceCenarioAtual = 0;
        this.historicoEscolhas = new ArrayList<>();
    }

    public void alterarAtributos(int impactoEtica, int impactoRelacionamento, int impactoReputacao, int impactoDesempenho) {
        this.etica = limitarValor(this.etica + impactoEtica);
        this.relacionamento = limitarValor(this.relacionamento + impactoRelacionamento);
        this.reputacao = limitarValor(this.reputacao + impactoReputacao);
        this.desempenho = limitarValor(this.desempenho + impactoDesempenho);
    }

    // Mantem os atributos dentro da escala usada pelo jogo: 0 a 100.
    private int limitarValor(int valor) {
        if (valor < 0) {
            return 0;
        }
        if (valor > 100) {
            return 100;
        }
        return valor;
    }

    public void exibirStatus() {
        System.out.println("\n===== STATUS DO PERSONAGEM =====");
        System.out.println("Nome: " + nome);
        System.out.println("Etica: " + etica);
        System.out.println("Relacionamento: " + relacionamento);
        System.out.println("Reputacao: " + reputacao);
        System.out.println("Desempenho: " + desempenho);
    }

    public void adicionarHistorico(String tituloCenario, String descricaoOpcao) {
        historicoEscolhas.add(tituloCenario + "\n-> " + descricaoOpcao);
    }

    public void exibirHistorico() {
        System.out.println("\n===== HISTORICO =====");
        if (historicoEscolhas.isEmpty()) {
            System.out.println("Nenhuma decisao registrada.");
            return;
        }

        for (String escolha : historicoEscolhas) {
            System.out.println("\n" + escolha);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEtica() {
        return etica;
    }

    public void setEtica(int etica) {
        this.etica = limitarValor(etica);
    }

    public int getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(int relacionamento) {
        this.relacionamento = limitarValor(relacionamento);
    }

    public int getReputacao() {
        return reputacao;
    }

    public void setReputacao(int reputacao) {
        this.reputacao = limitarValor(reputacao);
    }

    public int getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(int desempenho) {
        this.desempenho = limitarValor(desempenho);
    }

    public int getIndiceCenarioAtual() {
        return indiceCenarioAtual;
    }

    public void setIndiceCenarioAtual(int indiceCenarioAtual) {
        this.indiceCenarioAtual = indiceCenarioAtual;
    }

    public ArrayList<String> getHistoricoEscolhas() {
        return historicoEscolhas;
    }

    public void setHistoricoEscolhas(ArrayList<String> historicoEscolhas) {
        this.historicoEscolhas = historicoEscolhas;
    }
}
