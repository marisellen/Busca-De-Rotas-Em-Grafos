package heuristics;

import core.Node;

public class Euclidiana implements Heuristic {
    public double calcular(Node atual, Node destino) {
        return Math.sqrt(Math.pow(destino.x - atual.x, 2) + Math.pow(destino.y - atual.y, 2));
    }
}
