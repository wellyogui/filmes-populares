# Filmes Populares

### **Considerações gerais** ###

Optei pela modularização do app, assim fazendo um módulo para o serviço, para a lista de filmes e o detalhe do filme. Segui para a idéia de fazer o app como Single-Activity Android Application, assim sendo a MainActivity sendo a unica activity do aplicativo.

A arquitetura utilizada foi a MVC, a opção de utilizar o MVC foi para ter uma cobertura maior de teste para a controller (fragment).

Nas chamadas utilizei o retrofit com Rx para fazer atividades assincronas e controlando-as com mutable live data. Criei uma classe utilizando generics para passar status das chamadas para os lives data. Assim sendo uma função para o loading que recebe um boolean para que o observer decida se irá mostrar ou esconder o loading, uma função para o sucesso recebendo o retorno da chamada (se tiver), e uma função de erro que recebe uma string e um high order function.

### **Ideias** ###

* Criação de genero - Iria criar um carrossel para cada categoria e assim mostrando a lista de filmes daquela categoria
* Adição de favoritos - Iria adicionar um botão no item da lista de filmes e no detalhe do filme para salvar aquele filme em uma lista de favoritos, iria guardar localmente utilizando o room.
