package algorithms;

import core.Graph;
import core.Node;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class BFS {
    private Graph grafo;
    private List<Node> caminho;
    private int custo;
    private int nosExpandidos;
    private long tempo;

    public BFS(Graph grafo) {
        this.grafo = grafo;
    }

    public void executar(int xOrig, int yOrig, int xDest, int yDest) {

        long start = System.currentTimeMillis();

        Queue<Node> fila = new LinkedList<>();
        Set<Node> visitados = new HashSet<>();

        Node origem = new Node(xOrig, yOrig);
        fila.add(origem);
        visitados.add(origem);

        boolean encontrado = false;

        while(!fila.isEmpty()) {

            Node atual = fila.poll();
            nosExpandidos++;

            if(atual.x == xDest && atual.y == yDest) {
                caminho = reconstruirCaminho(atual);
                custo = atual.custo;
                encontrado = true;
                break;
            }

            for(Node vizinho : gerarVizinhos(atual)) {

                if(!visitados.contains(vizinho)) {
                    visitados.add(vizinho);
                    fila.add(vizinho);
                }
            }
        }

        if(!encontrado) {
            caminho = new ArrayList<>();
            custo = 0;
        }

        tempo = System.currentTimeMillis() - start;
    }

    private List<Node> gerarVizinhos(Node atual) {

        List<Node> vizinhos = new ArrayList<>();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for(int i = 0; i < 4; i++) {

            int nx = atual.x + dx[i];
            int ny = atual.y + dy[i];

            int peso = grafo.getPeso(atual.x, atual.y, nx, ny);

            if(peso > 0) {
                vizinhos.add(
                        new Node(
                                nx,
                                ny,
                                atual.custo + peso,
                                atual
                        )
                );
            }
        }

        return vizinhos;
    }

    private List<Node> reconstruirCaminho(Node destino) {

        List<Node> caminho = new ArrayList<>();
        Node atual = destino;

        while(atual != null) {
            caminho.add(0, atual);
            atual = atual.pai;
        }

        return caminho;
    }

    public void gerarArquivoSaida(String arquivoEntrada) {
        try {

            File dir = new File("output");
            if(!dir.exists()) dir.mkdirs();

            String baseNome = new File(arquivoEntrada).getName();
            File arquivoSaida = new File(dir, baseNome + ".bfs");

            PrintWriter pw = new PrintWriter(arquivoSaida);

            pw.println("ALGORITMO: BFS");
            pw.println("HEURISTICA: N/A");

            if(caminho.size() > 0) {
                pw.println("ORIGEM: (" + caminho.get(0).x + "," + caminho.get(0).y + ")");
                pw.println("DESTINO: (" + caminho.get(caminho.size()-1).x + "," + caminho.get(caminho.size()-1).y + ")");
            } else {
                pw.println("ORIGEM: ");
                pw.println("DESTINO: ");
            }

            pw.print("CAMINHO: ");
            for(int i = 0; i < caminho.size(); i++) {
                pw.print("(" + caminho.get(i).x + "," + caminho.get(i).y + ")");
                if(i < caminho.size()-1) pw.print(" -> ");
            }
            pw.println();

            pw.println("CUSTO: " + custo);
            pw.println("NOS EXPANDIDOS: " + nosExpandidos);
            pw.println("TEMPO (ms): " + tempo);

            pw.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Node> getCaminho() { return caminho; }
    public int getCusto() { return custo; }
    public int getNosExpandidos() { return nosExpandidos; }
    public long getTempo() { return tempo; }
}