package algorithms;

import core.Graph;
import core.Node;
import heuristics.Heuristic;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class AStar {
    private Graph grafo;
    private Heuristic heuristica;

    private List<Node> caminho;
    private Node origemNode;
    private Node destinoNode;
    private int custo;
    private int nosExpandidos;
    private long tempo;

    public AStar(Graph grafo, Heuristic h) {
        this.grafo = grafo;
        this.heuristica = h;
    }

    public void executar(int xOrig, int yOrig, int xDest, int yDest) {
        this.caminho = new ArrayList<>();
        this.origemNode = new Node(xOrig, yOrig, 0, null);
        this.destinoNode = new Node(xDest, yDest, 0, null);
        this.custo = 0;
        this.nosExpandidos = 0;
        this.tempo = 0;

        long start = System.currentTimeMillis();

        if (!validarCoordenada(xOrig, yOrig) || !validarCoordenada(xDest, yDest)) {
            this.tempo = System.currentTimeMillis() - start;
            return;
        }

        Node destino = new Node(xDest, yDest, 0, null);

        PriorityQueue<Node> fila = new PriorityQueue<>(Comparator.comparingDouble(n -> n.custo + heuristica.calcular(n, destino)));
        Map<Node, Integer> distancias = new HashMap<>();
        Set<Node> visitados = new HashSet<>();

        Node origem = new Node(xOrig, yOrig, 0, null);
        fila.add(origem);
        distancias.put(origem, 0);

        boolean encontrado = false;

        while (!fila.isEmpty()) {
            Node atual = fila.poll();

            if (visitados.contains(atual)) continue;

            if (!validarCoordenada(atual.x, atual.y)) continue;

            visitados.add(atual);
            nosExpandidos++;

            if (atual.x == xDest && atual.y == yDest) {
                this.caminho = reconstruirCaminho(atual);
                this.custo = atual.custo;
                encontrado = true;
                break;
            }

            for (Node vizinhoBase : gerarVizinhos(atual)) {
                int peso = grafo.getPeso(atual.x, atual.y, vizinhoBase.x, vizinhoBase.y);
                if (peso < 0) continue;

                int custoAcumulado = atual.custo + peso;

                Node vizinho = new Node(vizinhoBase.x, vizinhoBase.y, custoAcumulado, atual);

                if (!distancias.containsKey(vizinho) || custoAcumulado < distancias.get(vizinho)) {
                    distancias.put(vizinho, custoAcumulado);
                    fila.add(vizinho);
                }
            }
        }

        if (!encontrado) {
            this.caminho = new ArrayList<>();
            this.custo = 0;
        }

        this.tempo = System.currentTimeMillis() - start;
    }

    private boolean validarCoordenada(int x, int y) {
        if (x < 0 || y < 0) return false;
        if (grafo == null) return false;
        if (x >= grafo.colunas || y >= grafo.linhas) return false;
        return true;
    }

    private List<Node> gerarVizinhos(Node atual) {
        List<Node> vizinhos = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nx = atual.x + dx[i];
            int ny = atual.y + dy[i];

            if (!validarCoordenada(nx, ny)) continue;

            int peso = grafo.getPeso(atual.x, atual.y, nx, ny);
            if (peso > 0) {
                vizinhos.add(new Node(nx, ny));
            }
        }
        return vizinhos;
    }

    private List<Node> reconstruirCaminho(Node destino) {
        List<Node> caminho = new ArrayList<>();
        Node atual = destino;
        while (atual != null) {
            caminho.add(0, atual);
            atual = atual.pai;
        }
        return caminho;
    }

    public void gerarArquivoSaida(String arquivoEntrada, String heur) {
        try {
            File dir = new File("output");
            if (!dir.exists()) dir.mkdirs();

            String baseNome = new File(arquivoEntrada).getName();
            File arquivoSaida = new File(dir, baseNome + ".a." + heur.toLowerCase());

            PrintWriter pw = new PrintWriter(arquivoSaida);

            pw.println("ALGORITIMO: A*");
            pw.println("HEURISTICA: " + (heur.equalsIgnoreCase("manhattan") ? "Manhattan" : "Euclidiana"));

            if (caminho != null && caminho.size() > 0) {
                pw.println("ORIGEM: (" + caminho.get(0).x + "," + caminho.get(0).y + ")");
                pw.println("DESTINO: (" + caminho.get(caminho.size() - 1).x + "," + caminho.get(caminho.size() - 1).y + ")");
            } else {
                if (origemNode != null)
                    pw.println("ORIGEM: (" + origemNode.x + "," + origemNode.y + ")");
                else
                    pw.println("ORIGEM: ");

                if (destinoNode != null)
                    pw.println("DESTINO: (" + destinoNode.x + "," + destinoNode.y + ")");
                else
                    pw.println("DESTINO: ");
            }

            pw.print("CAMINHO: ");
            if (caminho != null && caminho.size() > 0) {
                for (int i = 0; i < caminho.size(); i++) {
                    pw.print("(" + caminho.get(i).x + "," + caminho.get(i).y + ")");
                    if (i < caminho.size() - 1) pw.print(" -> ");
                }
            } else {
                pw.print("N/I");
            }
            pw.println();

            pw.println("CUSTO: " + custo);
            pw.println("NOS EXPANDIDOS: " + nosExpandidos);
            pw.println("TEMPO (ms): " + tempo);

            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Node> getCaminho() { return caminho; }
    public int getCusto() { return custo; }
    public int getNosExpandidos() { return nosExpandidos; }
    public long getTempo() { return tempo; }
}