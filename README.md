# CinemApp - Programação Web e Mobile

## 🚀 Sobre o projeto

Este projeto de backend foi desenvolvido como parte das aulas de Programação Web e Mobile, ministradas pelo professor Bernardo Prates. Utilizamos o ambiente de desenvolvimento IntelliJ, a linguagem Kotlin, a ferramenta Postman e o MySQL Workbench para criar uma aplicação que gerencia um sistema de cinema básico, incluindo funcionalidades para sessões, ingressos e usuários.

## 🏗️ Estrutura do repositório 

O repositório está organizado de forma a facilitar a navegação e o acesso ao conhecimento adquirido ao longo do projeto. A estrutura do repositório é a seguinte:

- /Collection: Contém os arquivos necessários para a execução do projeto no Postman.
- /Project: Trata-se do código-fonte da aplicação que deve ser aberto e compilado no IntelliJ.
- /Model: Apresenta a modelagem inicial do projeto.

## 🔨 Instruções para configuração do ambiente

Crie uma pasta para armazenar os arquivos e abra um terminal do Git Bash a partir dessa pasta. Certifique-se de ter o Git instalado em seu computador.
   
- Use os seguintes comandos no terminal para clonar os arquivos:

```
   git clone https://github.com/brunabaldissera/CinemApp_WebMobile.git
```

- Você também pode baixar o arquivo zip e descompactar em sua pasta.


### MySQL Workbench

1. Certifique-se de ter o MySQL Workbench instalado em sua máquina. Caso não possua, o mesmo pode ser instalado [aqui](https://dev.mysql.com/downloads/).

2. Através do mesmo, crie um servidor local com as configurações padrão ou personalizadas, se necessário.

3. Após, crie uma nova base de dados com o nome "cinemapp". 

## 💻 Executando a aplicação

1. Realize a configuração correta do MySQL Workbench, conforme mostrado acima.

2. Abra e execute a aplicação através do ambiente IntelliJ, importando a pasta '/Project'.

3. Utilize os arquivos do Postman encontrados na pasta '/Collection' para fazer requisições e interagir com a aplicação.

4. Além disso, no Postman, localize as opções de Environment, presentes no canto superior direito da tela, e selecione a environment "Local".

OBS.: Algumas requisições exigem que o usuário logado seja administrador, por isso é necessário ir até o banco de dados, no MySQL Workbench, alterar a role do usuário desejado de "USER" para "ADMIN" e clicar em "Apply". Após, basta realizar o login pelo usuário administrador, no Postman.

## 🔧 Ferramentas e documentação

- [Postman](https://www.postman.com/)
- [IntelliJ](https://www.jetbrains.com/pt-br/idea/)
- [Kotlin](https://kotlinlang.org/)

## 	👨‍💻 Autores

- [Bruna Baldissera](https://github.com/brunabaldissera)
- [Dalton Oberdan Adiers](https://github.com/daltonadiers)
