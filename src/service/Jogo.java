package service;

import model.Cenario;
import model.Opcao;
import model.Personagem;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private ArrayList<Cenario> cenarios;
    private GerenciadorArquivo gerenciadorArquivo;
    private Scanner scanner;

    public Jogo() {
        this.cenarios = new ArrayList<>();
        this.gerenciadorArquivo = new GerenciadorArquivo();
        this.scanner = new Scanner(System.in);
        carregarCenarios();
    }

    public void executar() {
        boolean executando = true;

        while (executando) {
            exibirMenuPrincipal();
            int opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    iniciarNovoJogo();
                    break;
                case 2:
                    continuarJogo();
                    break;
                case 3:
                    executando = false;
                    System.out.println("Encerrando o simulador. Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }

    public void iniciarNovoJogo() {
        System.out.print("\nDigite o nome do personagem: ");
        String nome = scanner.nextLine().trim();

        while (nome.isEmpty()) {
            System.out.print("O nome nao pode ficar vazio. Digite novamente: ");
            nome = scanner.nextLine().trim();
        }

        Personagem personagem = new Personagem(nome);
        System.out.println("\nBem-vindo(a), " + personagem.getNome() + ".");
        executarFluxoHistoria(personagem);
    }

    public void continuarJogo() {
        System.out.print("\nDigite o nome do personagem salvo: ");
        String nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("Nome invalido.");
            return;
        }

        Personagem personagem = gerenciadorArquivo.carregar(nome);
        if (personagem != null) {
            System.out.println("\nSave carregado com sucesso.");
            executarFluxoHistoria(personagem);
        }
    }

    private void executarFluxoHistoria(Personagem personagem) {
        while (personagem.getIndiceCenarioAtual() < cenarios.size()) {
            int indiceAtual = personagem.getIndiceCenarioAtual();
            Cenario cenario = cenarios.get(indiceAtual);

            // Cenarios condicionais sao pulados quando o personagem nao atende aos requisitos.
            if (!cenario.podeAparecer(personagem)) {
                personagem.setIndiceCenarioAtual(indiceAtual + 1);
                continue;
            }

            cenario.exibirCenario();
            cenario.processarEscolha(personagem, scanner);

            personagem.setIndiceCenarioAtual(indiceAtual + 1);
            personagem.exibirStatus();
            gerenciadorArquivo.salvar(personagem);
            pausar();
        }

        gerarFinal(personagem);
        personagem.exibirHistorico();
        gerenciadorArquivo.salvar(personagem);
    }

    public void gerarFinal(Personagem personagem) {
        System.out.println("\n=================================");
        System.out.println("FINAL DA CARREIRA");
        System.out.println("=================================");

        // O final considera os atributos acumulados durante toda a simulacao.
        int etica = personagem.getEtica();
        int relacionamento = personagem.getRelacionamento();
        int reputacao = personagem.getReputacao();
        int desempenho = personagem.getDesempenho();

        if (etica <= 25 || reputacao <= 20) {
            System.out.println("FINAL 4");
            System.out.println("Suas decisoes antieticas ou sua reputacao fragilizada resultaram em demissao por justa causa.");
        } else if (etica >= 75 && reputacao >= 70 && relacionamento >= 65) {
            System.out.println("FINAL 1");
            System.out.println("Voce se tornou diretor(a) da empresa gracas a sua integridade e lideranca etica.");
        } else if (desempenho >= 80 && reputacao >= 65 && relacionamento < 45) {
            System.out.println("FINAL 2");
            System.out.println("Voce alcancou uma posicao elevada, mas perdeu a confianca de muitos colegas no caminho.");
        } else if (relacionamento >= 80 && desempenho < 70) {
            System.out.println("FINAL 3");
            System.out.println("Seu excelente relacionamento fez de voce um(a) lider admirado(a), mesmo sem grande destaque profissional.");
        } else {
            System.out.println("FINAL 5");
            System.out.println("Voce construiu uma carreira equilibrada e respeitada, aprendendo a ponderar resultados e principios.");
        }
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n===== SIMULADOR DE DILEMAS ETICOS CORPORATIVOS =====");
        System.out.println("1 - Novo Jogo");
        System.out.println("2 - Continuar Jogo");
        System.out.println("3 - Sair");
    }

    private int lerOpcaoMenu() {
        while (true) {
            System.out.print("Escolha uma opcao: ");
            String entrada = scanner.nextLine().trim();

            try {
                int opcao = Integer.parseInt(entrada);
                if (opcao >= 1 && opcao <= 3) {
                    return opcao;
                }
                System.out.println("Digite 1, 2 ou 3.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite apenas numeros.");
            }
        }
    }

    private void pausar() {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    private void carregarCenarios() {
        // Cada cenario possui tres opcoes com impactos diferentes nos atributos.
        Cenario relatorioManipulado = new Cenario(
                "Relatorio Manipulado",
                "Seu chefe pede que voce altere dados financeiros para agradar um cliente importante. Ele afirma que todos sairao ganhando se a reuniao for bem-sucedida, mas voce sabe que os numeros ficariam incorretos.");
        relatorioManipulado.adicionarOpcao(new Opcao("Recusar e informar ao RH", 18, -8, 12, -4));
        relatorioManipulado.adicionarOpcao(new Opcao("Alterar os dados conforme solicitado", -25, 8, -18, 10));
        relatorioManipulado.adicionarOpcao(new Opcao("Propor uma solucao transparente", 12, 5, 10, 4));
        cenarios.add(relatorioManipulado);

        Cenario conflitoColegas = new Cenario(
                "Conflito Entre Colegas",
                "Dois colegas discutem publicamente sobre a responsabilidade por uma falha no projeto. A equipe esta dividida e a entrega pode atrasar se ninguem intervier.");
        conflitoColegas.adicionarOpcao(new Opcao("Mediar a situacao e buscar fatos", 10, 15, 8, 3));
        conflitoColegas.adicionarOpcao(new Opcao("Apoiar o colega mais influente", -8, 7, -6, 4));
        conflitoColegas.adicionarOpcao(new Opcao("Ignorar o conflito para focar nas suas tarefas", -3, -12, -4, 8));
        cenarios.add(conflitoColegas);

        Cenario projetoUrgente = new Cenario(
                "Projeto Urgente",
                "A diretoria exige uma entrega em tempo recorde. Para cumprir o prazo, parte da equipe sugere pular testes importantes e corrigir possiveis problemas depois.");
        projetoUrgente.adicionarOpcao(new Opcao("Defender os testes essenciais, mesmo reduzindo o escopo", 12, 2, 8, 6));
        projetoUrgente.adicionarOpcao(new Opcao("Pular os testes para entregar tudo no prazo", -14, -2, -8, 12));
        projetoUrgente.adicionarOpcao(new Opcao("Negociar prazo e dividir responsabilidades com a equipe", 8, 10, 6, 8));
        cenarios.add(projetoUrgente);

        Cenario presenteFornecedor = new Cenario(
                "Presente do Fornecedor",
                "Um fornecedor que participa de uma concorrencia envia um presente caro para sua casa. A politica da empresa permite brindes simples, mas este item claramente ultrapassa o limite.");
        presenteFornecedor.adicionarOpcao(new Opcao("Devolver o presente e registrar o ocorrido", 16, -3, 12, 0));
        presenteFornecedor.adicionarOpcao(new Opcao("Aceitar o presente em silencio", -22, 5, -15, 3));
        presenteFornecedor.adicionarOpcao(new Opcao("Consultar compliance antes de agir", 12, 4, 9, 1));
        cenarios.add(presenteFornecedor);

        Cenario dadosCliente = new Cenario(
                "Dados de Cliente",
                "Voce descobre que uma planilha com dados sensiveis de clientes esta circulando em um grupo interno sem protecao. Resolver o problema pode expor falhas do seu setor.");
        dadosCliente.adicionarOpcao(new Opcao("Reportar o vazamento e propor medidas de seguranca", 18, 0, 14, 2));
        dadosCliente.adicionarOpcao(new Opcao("Apagar as mensagens e fingir que nada aconteceu", -20, -5, -18, 2));
        dadosCliente.adicionarOpcao(new Opcao("Avisar apenas seu gestor direto", 5, 4, 2, 4));
        cenarios.add(dadosCliente);

        Cenario programaCompliance = new Cenario(
                "Programa de Compliance",
                "Sua postura etica chamou a atencao da empresa. Voce foi convidado(a) a ajudar na criacao de um programa interno para orientar decisoes responsaveis.",
                personagem -> personagem.getEtica() >= 70);
        programaCompliance.adicionarOpcao(new Opcao("Aceitar e liderar treinamentos praticos", 10, 8, 12, 3));
        programaCompliance.adicionarOpcao(new Opcao("Aceitar apenas para ganhar visibilidade", -4, 3, 6, 2));
        programaCompliance.adicionarOpcao(new Opcao("Recusar para priorizar suas entregas tecnicas", 0, -3, -2, 8));
        cenarios.add(programaCompliance);

        Cenario investigacaoInterna = new Cenario(
                "Investigacao Interna",
                "Sua reputacao caiu e seu nome surgiu em uma investigacao sobre decisoes questionaveis. A empresa pede explicacoes formais sobre suas atitudes recentes.",
                personagem -> personagem.getReputacao() <= 30);
        investigacaoInterna.adicionarOpcao(new Opcao("Assumir erros e apresentar um plano de reparacao", 15, 2, 10, -3));
        investigacaoInterna.adicionarOpcao(new Opcao("Culpar colegas para proteger sua imagem", -18, -15, -12, 1));
        investigacaoInterna.adicionarOpcao(new Opcao("Entregar documentos e colaborar com a apuracao", 12, 0, 8, 0));
        cenarios.add(investigacaoInterna);

        Cenario liderancaEquipe = new Cenario(
                "Lideranca de Equipe",
                "Seu bom relacionamento fez a gerencia convidar voce para liderar uma equipe temporaria. O grupo tem pessoas experientes, mas tambem conflitos antigos.",
                personagem -> personagem.getRelacionamento() >= 80);
        liderancaEquipe.adicionarOpcao(new Opcao("Criar combinados claros e ouvir a equipe", 8, 10, 8, 6));
        liderancaEquipe.adicionarOpcao(new Opcao("Evitar conversas dificeis para manter todos satisfeitos", -4, 6, -2, -8));
        liderancaEquipe.adicionarOpcao(new Opcao("Centralizar decisoes para acelerar entregas", -6, -10, 2, 10));
        cenarios.add(liderancaEquipe);

        Cenario projetoEstrategico = new Cenario(
                "Projeto Estrategico",
                "Seu desempenho colocou voce em um projeto de alto impacto. A pressao e grande, e a diretoria quer resultados que possam mudar o futuro da empresa.",
                personagem -> personagem.getDesempenho() >= 75);
        projetoEstrategico.adicionarOpcao(new Opcao("Planejar riscos e comunicar limites realisticos", 8, 4, 10, 8));
        projetoEstrategico.adicionarOpcao(new Opcao("Prometer resultados acima do possivel", -12, -3, -10, 6));
        projetoEstrategico.adicionarOpcao(new Opcao("Formar uma equipe diversa para dividir decisoes", 6, 10, 7, 7));
        cenarios.add(projetoEstrategico);

        Cenario denunciaAnonima = new Cenario(
                "Denuncia Anonima",
                "Voce recebe uma denuncia anonima indicando favorecimento em uma contratacao. O assunto envolve pessoas poderosas, e investigar pode afetar sua rotina e suas relacoes.");
        denunciaAnonima.adicionarOpcao(new Opcao("Encaminhar a denuncia pelos canais oficiais", 15, -2, 11, -1));
        denunciaAnonima.adicionarOpcao(new Opcao("Guardar a informacao para evitar problemas", -13, 0, -9, 3));
        denunciaAnonima.adicionarOpcao(new Opcao("Buscar evidencias antes de encaminhar", 10, 2, 7, 4));
        cenarios.add(denunciaAnonima);

        Cenario avaliacaoDesempenho = new Cenario(
                "Avaliacao de Desempenho",
                "Na avaliacao anual, voce percebe que pode omitir uma falha relevante do seu relatorio pessoal. A omissao aumentaria suas chances de promocao.");
        avaliacaoDesempenho.adicionarOpcao(new Opcao("Relatar a falha e explicar o aprendizado", 14, 3, 10, 2));
        avaliacaoDesempenho.adicionarOpcao(new Opcao("Omitir a falha para proteger sua promocao", -18, 0, -12, 8));
        avaliacaoDesempenho.adicionarOpcao(new Opcao("Mencionar a falha de forma parcial", -6, 1, -3, 5));
        cenarios.add(avaliacaoDesempenho);

        Cenario promocaoFinal = new Cenario(
                "Promocao Decisiva",
                "A empresa abre uma vaga de lideranca permanente. A decisao final depende de seus resultados, da confianca que voce construiu e da forma como voce defende seus valores.");
        promocaoFinal.adicionarOpcao(new Opcao("Apresentar resultados com transparencia e reconhecer a equipe", 12, 10, 12, 6));
        promocaoFinal.adicionarOpcao(new Opcao("Exagerar seus resultados e minimizar a ajuda recebida", -16, -12, -8, 8));
        promocaoFinal.adicionarOpcao(new Opcao("Defender um plano equilibrado para o futuro da area", 8, 6, 8, 8));
        cenarios.add(promocaoFinal);
    }
}
