## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# Tradução Dirigida por Sintaxe

Este projeto implementa um tradutor simples baseado em tradução dirigida por sintaxe (TDS), desenvolvido em Java como parte da disciplina de Compiladores.



## Analisador Léxico

Agora o tradutor suporta números com múltiplos dígitos e um analisador léxico dedicado (`Scanner.java`), responsável por gerar tokens (`Token.java`).

### Exemplo
Entrada: 45+89-876

## Analisador Léxico com Tokens Compostos

Nesta etapa, o Scanner foi aprimorado para reconhecer números com múltiplos dígitos
e classificar tokens em tipos distintos (PLUS, MINUS, NUMBER, EOF). O Token é agora
uma estrutura composta por tipo e lexema, o que permitirá ao Parser trabalhar
com maior abstração sintática.
