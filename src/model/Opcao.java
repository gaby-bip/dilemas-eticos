package model;

public class Opcao {
    private String descricao;
    private int impactoEtica;
    private int impactoRelacionamento;
    private int impactoReputacao;
    private int impactoDesempenho;

    public Opcao(String descricao, int impactoEtica, int impactoRelacionamento, int impactoReputacao, int impactoDesempenho) {
        this.descricao = descricao;
        this.impactoEtica = impactoEtica;
        this.impactoRelacionamento = impactoRelacionamento;
        this.impactoReputacao = impactoReputacao;
        this.impactoDesempenho = impactoDesempenho;
    }

    public void aplicarConsequencias(Personagem personagem) {
        personagem.alterarAtributos(impactoEtica, impactoRelacionamento, impactoReputacao, impactoDesempenho);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getImpactoEtica() {
        return impactoEtica;
    }

    public void setImpactoEtica(int impactoEtica) {
        this.impactoEtica = impactoEtica;
    }

    public int getImpactoRelacionamento() {
        return impactoRelacionamento;
    }

    public void setImpactoRelacionamento(int impactoRelacionamento) {
        this.impactoRelacionamento = impactoRelacionamento;
    }

    public int getImpactoReputacao() {
        return impactoReputacao;
    }

    public void setImpactoReputacao(int impactoReputacao) {
        this.impactoReputacao = impactoReputacao;
    }

    public int getImpactoDesempenho() {
        return impactoDesempenho;
    }

    public void setImpactoDesempenho(int impactoDesempenho) {
        this.impactoDesempenho = impactoDesempenho;
    }
}
