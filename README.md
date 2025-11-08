# Busca de Rotas em Grafos

## **Descrição do Projeto**
Este projeto implementa e compara algoritmos clássicos de busca de rotas em grafos bidimensionais.  

- Implementação de algoritmos clássicos de busca;
- Aplicação de heurísticas para algoritmos informados (A* e Greedy Best-First Search);
- Avaliação de desempenho em termos de custo, nós expandidos e tempo de execução;
- Geração de relatórios e arquivos de saída padronizados.

## **Algoritmos Implementados**
- **BFS (Breadth-First Search)**
- **DFS (Depth-First Search)**
- **Dijkstra**
- **Greedy Best-First Search (GBFS)** com heurísticas:
  - Manhattan
  - Euclidiana
- **A*** com heurísticas:
  - Manhattan
  - Euclidiana
 
## **Arquivo de Entrada**
O arquivo de entrada contém uma matriz de adjacência representando o grafo.  
- Cada linha representa um nó de origem.  
- Cada coluna representa um nó de destino.  
- Valores:
  - **> 0**: custo da aresta entre os nós.
  - **≤ 0**: não há conexão direta. 

## **Execução**
No IntelliJ:
1. Clique em Run → Edit Configurations
2. Adicione argumentos do programa:
```bash
input/teste_5x5.txt 0,0 4,4
```
3. Execute o projeto.
