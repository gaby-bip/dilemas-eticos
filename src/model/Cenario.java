package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Cenario {
    private String titulo;
    private String descricao;
    private ArrayList<Opcao> opcoes;
    private Predicate<Personagem> condicaoParaAparecer;

    public Cenario(String titulo, String descricao) {
        this(titulo, descricao, personagem -> true);
    }

    public Cenario(String titulo, String descricao, Predicate<Personagem> condicaoParaAparecer) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.opcoes = new ArrayList<>();
        this.condicaoParaAparecer = condicaoParaAparecer;
    }

    public void adicionarOpcao(Opcao opcao) {
        opcoes.add(opcao);
    }

    public boolean podeAparecer(Personagem personagem) {
        return condicaoParaAparecer.test(personagem);
    }

    public void exibirCenario() {
        System.out.println("\n=================================");
        System.out.println(titulo.toUpperCase());
        System.out.println("=================================");
        System.out.println(descricao);
        System.out.println();

        for (int i = 0; i < opcoes.size(); i++) {
            System.out.println((i + 1) + " - " + opcoes.get(i).getDescricao());
        }
    }

    public void processarEscolha(Personagem personagem, Scanner scanner) {
        int escolha = lerEscolha(scanner);
        Opcao opcaoSelecionada = opcoes.get(escolha - 1);

        opcaoSelecionada.aplicarConsequencias(personagem);
        personagem.adicionarHistorico(titulo, opcaoSelecionada.getDescricao());
    }

    private int lerEscolha(Scanner scanner) {
        while (true) {
            System.out.print("\nDigite sua escolha: ");
            String entrada = scanner.nextLine().trim();

            try {
                int escolha = Integer.parseInt(entrada);
                if (escolha >= 1 && escolha <= opcoes.size()) {
                    return escolha;
                }
                System.out.println("Opcao invalida. Escolha um numero entre 1 e " + opcoes.size() + ".");
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite apenas numeros.");
            }
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Opcao> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(ArrayList<Opcao> opcoes) {
        this.opcoes = opcoes;
    }

    public Predicate<Personagem> getCondicaoParaAparecer() {
        return condicaoParaAparecer;
    }

    public void setCondicaoParaAparecer(Predicate<Personagem> condicaoParaAparecer) {
        this.condicaoParaAparecer = condicaoParaAparecer;
    }
}
