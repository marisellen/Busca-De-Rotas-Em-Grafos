# Busca-De-Rotas-Em-Grafos
## Visão Geral

Projeto em Java para implementar e comparar algoritmos clássicos de busca de rotas em grafos bidimensionais. Inclui algoritmos não informados (como BFS e DFS) e informados (como A* e Greedy), com métricas de desempenho para análise.

## Algoritmos Implementados

* BFS (Busca em Largura) — explora por camadas. 
* DFS (Busca em Profundidade) — explora profundamente antes de retroceder. 
* Dijkstra — calcula o menor caminho em grafos ponderados. 
* Greedy Best-First Search (GBFS) com heurísticas:
  * Manhattan
  * Euclidiana
* A* com heurísticas:
  * Manhattan
  * Euclidiana

## Estrutura do Projeto

src/ — código-fonte Java com a implementação dos algoritmos

input/ — arquivos de entrada contendo matrizes de adjacência

output/ — resultados gerados pela execução (custos, caminhos, tempo, nós expandidos)

relatorio/ — relatório para análise de desempenho

## Formato do Arquivo de Entrada

O grafo é representado por uma matriz de adjacência:
- Cada linha representa o nó de origem, cada coluna representa o nó de destino.
  - Valor > 0: custo da aresta entre os nós.
  - Valor ≤ 0: sem conexão direta.
Exemplo de argumento para rodar no IntelliJ:

```
input/teste_5x5.txt 0,0 4,4  
```

Onde 0,0 é o nó de origem e 4,4 o nó de destino.

## Métricas de Avaliação

O programa mede e reporta para cada execução:
* Custo do caminho encontrado
* Número de nós expandidos
* Tempo de execução

Esses dados permitem comparar a eficiência dos algoritmos em diferentes tipos de grafos e cenários.



