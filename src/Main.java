import algorithms.*;
import core.Graph;
import heuristics.Euclidiana;
import heuristics.Manhattan;


public class Main {
    public static void main(String[] args) {
            if (args.length != 3) {
                System.out.println("Uso: java Main <arquivo_entrada> <origem> <destino>");
                return;
            }

            String arquivoEntrada = args[0];
            String origemStr = args[1];
            String destinoStr = args[2];

            String[] origemTokens = origemStr.split(",");
            String[] destinoTokens = destinoStr.split(",");
            int origemX = Integer.parseInt(origemTokens[0]);
            int origemY = Integer.parseInt(origemTokens[1]);
            int destinoX = Integer.parseInt(destinoTokens[0]);
            int destinoY = Integer.parseInt(destinoTokens[1]);

            Graph grafo = new core.Graph();
            grafo.carregarMatriz(arquivoEntrada);

            BFS bfs = new BFS(grafo);
            bfs.executar(origemX, origemY, destinoX, destinoY);
            bfs.gerarArquivoSaida(arquivoEntrada);

            DFS dfs = new DFS(grafo);
            dfs.executar(origemX, origemY, destinoX, destinoY);
            dfs.gerarArquivoSaida(arquivoEntrada);

            Dijkstra dijkstra = new Dijkstra(grafo);
            dijkstra.executar(origemX, origemY, destinoX, destinoY);
            dijkstra.gerarArquivoSaida(arquivoEntrada);

            GreedyBestFirst gbsManhattan = new GreedyBestFirst(grafo, new Manhattan());
            gbsManhattan.executar(origemX, origemY, destinoX, destinoY);
            gbsManhattan.gerarArquivoSaida(arquivoEntrada, "manhattan");

            GreedyBestFirst gbsEuclidiana = new GreedyBestFirst(grafo, new Euclidiana());
            gbsEuclidiana.executar(origemX, origemY, destinoX, destinoY);
            gbsEuclidiana.gerarArquivoSaida(arquivoEntrada, "euclidiana");

            AStar aManhattan = new AStar(grafo, new Manhattan());
            aManhattan.executar(origemX, origemY, destinoX, destinoY);
            aManhattan.gerarArquivoSaida(arquivoEntrada, "manhattan");

            AStar aEuclidiana = new AStar(grafo, new Euclidiana());
            aEuclidiana.executar(origemX, origemY, destinoX, destinoY);
            aEuclidiana.gerarArquivoSaida(arquivoEntrada, "euclidiana");
    }
}