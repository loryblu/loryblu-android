<br/>

<p align="center">
  <a href="https://github.com/loryblu/loryblu-android">
    <img src="https://github-production-user-asset-6210df.s3.amazonaws.com/69876102/274274731-31580d3d-27d6-476b-b3c7-93cb61e4e3cc.png" alt="Logo" width="421" height="298">
  </a>




  <h3 align="center">Loryblu Android</h3>

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/loryblu/loryblu-android/blob/development/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/loryblu/loryblu-android/blob/development/README.pt.br.md)

  <p align="center">
O LoryBlu é um aplicativo pensado para ajudar pais e crianças diagnosticadas com TEA, principalmente em busca ou na espera por tratamento multidisciplinar, com um ambiente lúdico e interativo para contribuir de forma leve, inclusiva e funcional por meio de atividades, jogos de estimulação cognitivo e música.
  </p>

<br/>
<br/>

## Tabela de conteúdos

* [Sobre O Projeto](#sobre-o-projeto)
* [Desenvolvido Com](#desenvolvido-com)
* [Desenvolvimento](#desenvolvimento)
* [Licença](#licença)
* [Autores](#autores)

## Sobre O Projeto

![app_screen](https://github.com/loryblu/loryblu-android/assets/69876102/09b08090-ba70-4fee-940c-4562c50064ac)

O aplicativo tem diversas trilhas para ajudar os pais com organização, e diversos jogos para as crianças.

- Nós usamos Login para que o usuário possa utilizar o aplicativo em diversos aparelhos e não perder seus dados.
- Diário de Bordo: Tem o objetivo de ajudar os pais com a organização das tarefas do dia da criança.
  -  LoryEstudioso: Permite que o responsável salve as tarefas em relação aos estudos da criança.
  -  LoryRotina: Permite que o responsável organize as tarefas da criança, podendo selecionar o dia da semana, o turno do dia e a recorrência.
- Outras trilhas ainda estão em desenvolvimento.


## Desenvolvimento com

- Android Nativo com Kotlin
- Compose para todas as telas
- Koin para Injeção de Dependências
- Ktor para consumir a API
- Jetpack Navigation
- Multiplos módulos
- Clean architecture


## Desenvolvimento

Responsabilidades dos módulos

* **app**: Contém as classes de nível de aplicativo e scaffolding que vinculam o restante da base de código.

* **data**: abstração para o acesso à fontes de dados, remotos os local
  * ***auth***: Responsável pelos endpoints de autenticação. Usamos Ktor para consumir a API.

- **core**: Recursos que são úteis para diversos módulos
  - ***network***:  Implementação do Ktor e os modelos de retorno da API.

  - ***ui***: Recursos de Design que são utilizados em diversos módulos, como Strings, Drawables e Componentes.

  - ***util***: Extensions functions e alguns validadores de tipo que são usados em múltiplos módulos.

- **feature**: Contém todas as telas e suas lógicas.
- **buildSrc**: Configurações que são usadas em múltiplos módulos


## Licença

Distribuído sob a Licença MIT. Leia [LICENSE](https://github.com/loryblu/loryblu-android/blob/development/LICENSE) para mais informações.



## Autores

* **André Moraes Filho** - *Desenvolvedor Android* - [André Moraes Filho](https://github.com/softdevandre)
* **Jean Patrick Hartmann** - *Desenvolvedor Android* - [Jean Patrick Hartmann](https://github.com/hartmannjean)
* **Ruliam Santos Oliveira** - *Desenvolvedor Android* - [Ruliam Santos Oliveira](https://github.com/OdisBy)
