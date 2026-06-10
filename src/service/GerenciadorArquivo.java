package service;

import model.Personagem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GerenciadorArquivo {
    private static final String DIRETORIO_SAVES = "src/dados/saves";

    public void salvar(Personagem personagem) {
        criarDiretorioSeNecessario();
        File arquivo = obterArquivoSave(personagem.getNome());

        // Serializa o personagem completo, incluindo atributos, historico e progresso.
        try (ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            saida.writeObject(personagem);
            System.out.println("\nProgresso salvo em: " + arquivo.getPath());
        } catch (IOException e) {
            System.out.println("Erro ao salvar progresso: " + e.getMessage());
        }
    }

    public Personagem carregar(String nome) {
        File arquivo = obterArquivoSave(nome);

        if (!arquivo.exists()) {
            System.out.println("Nenhum save encontrado para o nome informado.");
            return null;
        }

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Personagem) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar progresso: " + e.getMessage());
            return null;
        }
    }

    private void criarDiretorioSeNecessario() {
        File diretorio = new File(DIRETORIO_SAVES);
        if (!diretorio.exists() && !diretorio.mkdirs()) {
            System.out.println("Aviso: nao foi possivel criar o diretorio de saves.");
        }
    }

    private File obterArquivoSave(String nome) {
        // Evita caracteres invalidos no nome do arquivo de save.
        String nomeSeguro = nome.trim().replaceAll("[^a-zA-Z0-9_-]", "_");
        return new File(DIRETORIO_SAVES, "save_" + nomeSeguro + ".dat");
    }
}
