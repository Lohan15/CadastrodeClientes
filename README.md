# Formulário de Cadastro de Clientes em Java Swing

Este projeto implementa um formulário de cadastro de clientes em Java Swing, seguindo rigorosamente o layout, os campos e os requisitos da atividade.

## Funcionalidades Atendidas

* **Interface Gráfica:** Formulário implementado em Java Swing (classe `FormularioClientes.java`) com layout aprimorado, ícones redimensionados e campos centralizados.
* **Campos Exatos:** Implementação de `JTextField`, `JComboBox`, `JFormattedTextField` (telefone) e `JRadioButton` (Status).
* **Validação de Campos:** Ao clicar em "Gravar Dados", o programa valida se **todos os campos** estão preenchidos (incluindo seleção de Estado e preenchimento completo do Telefone).
* **Mensagem de Erro:** Em caso de falha na validação, exibe uma `JOptionPane` informando quais campos devem ser preenchidos.

## Requisitos de Execução

* **Java Development Kit (JDK):** Versão 8 ou superior instalada (inclui o compilador `javac` e o executor `java`).
    * **Download do JDK (Recomendado):** Você pode baixar o instalador mais recente do OpenJDK (Eclipse Temurin) aqui:
      [https://adoptium.net/pt/temurin/releases/](https://adoptium.net/pt/temurin/releases/)
      *(Escolha a versão LTS, o instalador `.msi` para Windows x64.)*
* **Ícones:** É obrigatório possuir uma subpasta chamada **`icons`** dentro do diretório do projeto, contendo os seguintes arquivos `.png` para o funcionamento correto do código:
    * `gravar.png`
    * `cancelar.png`
    * `voltar.png`

## Estrutura do Projeto

/SeuProjetoGit/ ├── FormularioClientes.java <-- Arquivo principal ├── README.md <-- Este arquivo └── icons/ <-- Pasta OBRIGATÓRIA para os ícones ├── gravar.png ├── cancelar.png └── voltar.png

## Passo a Passo para Execução

Siga estas instruções no seu terminal (Prompt de Comando, PowerShell ou Terminal Linux/macOS):

### 1. Preparação

1.  **Crie a Estrutura:** Crie o diretório do projeto e, dentro dele, a subpasta `icons`.
2.  **Salve os Arquivos:**
    * Salve o código-fonte como `FormularioClientes.java` no diretório principal.
    * Coloque as imagens de ícones (`gravar.png`, `cancelar.png`, `voltar.png`) dentro da pasta `icons`.

### 2. Compilação e Execução

1.  **Navegue até o Diretório:** Abra seu terminal e use o comando `cd` para ir até a pasta que contém o `FormularioClientes.java`.

    ```bash
    cd /caminho/para/SeuProjetoGit
    ```

2.  **Compile o Código:** Use o compilador Java.

    ```bash
    javac FormularioClientes.java
    ```
    *Isso gerará o arquivo executável `FormularioClientes.class`.*

3.  **Execute o Programa:** Use o comando `java` para iniciar o formulário.

    ```bash
    java FormularioClientes
    ```

O formulário será exibido. Você pode testar a validação de campos ao clicar em "Gravar Dados" sem preencher nada.
