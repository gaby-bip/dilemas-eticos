# Simulador de Dilemas Eticos Corporativos

Aplicacao de console em Java onde o jogador toma decisoes em dilemas eticos corporativos. As escolhas alteram etica, relacionamento, reputacao e desempenho, influenciam cenarios condicionais e determinam o final da carreira.

## Estrutura

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

## Como executar no IntelliJ IDEA

1. Abra esta pasta como projeto.
2. Configure um SDK Java 17 ou superior.
3. Marque a pasta `src` como Sources Root, se necessario.
4. Execute a classe `Main`.

## Como executar no Eclipse

1. Crie um Java Project apontando para esta pasta ou importe como projeto existente.
2. Use JDK 17 ou superior.
3. Execute `Main.java` como Java Application.

## Salvamento

O progresso e salvo automaticamente apos cada escolha em:

```text
src/dados/saves/save_NomeDoPersonagem.dat
```

Para continuar, escolha `2 - Continuar Jogo` no menu principal e informe o mesmo nome usado no novo jogo.
