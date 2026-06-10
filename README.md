# Simulador de Dilemas Éticos Corporativos

## Sobre o Projeto

Este projeto foi desenvolvido em Java como uma aplicação de console baseada em Programação Orientada a Objetos (POO).

O jogador assume o papel de um funcionário em uma empresa e enfrenta diversos dilemas éticos ao longo de sua carreira. Cada decisão tomada influencia atributos importantes do personagem, afetando o desenvolvimento da história e o resultado final.

## Funcionalidades

* Novo jogo
* Continuação de partidas salvas
* Salvamento automático do progresso
* Sistema de atributos do personagem
* Histórico de escolhas
* Cenários condicionais
* Múltiplos finais possíveis
* Persistência de dados em arquivo local

## Atributos do Personagem

* Ética
* Relacionamento
* Reputação
* Desempenho

Cada decisão pode aumentar ou diminuir esses atributos, influenciando os próximos eventos da narrativa.

## Tecnologias Utilizadas

* Java 17
* Programação Orientada a Objetos
* Serialização de Objetos
* Collections (ArrayList)
* Manipulação de Arquivos

## Estrutura do Projeto

```text
src/
├── Main.java
├── model/
│   ├── Personagem.java
│   ├── Cenario.java
│   └── Opcao.java
├── service/
│   ├── Jogo.java
│   └── GerenciadorArquivo.java
└── dados/
    └── saves/
```

## Como Executar

1. Instale o JDK 17 ou superior.
2. Abra o projeto em uma IDE Java (IntelliJ IDEA, Eclipse ou VS Code).
3. Execute a classe `Main.java`.
4. Escolha iniciar uma nova simulação ou continuar uma partida existente.

## Objetivo Acadêmico

Projeto desenvolvido para praticar conceitos de Programação Orientada a Objetos, persistência de dados, estruturação de projetos Java e desenvolvimento de aplicações interativas baseadas em decisões.
