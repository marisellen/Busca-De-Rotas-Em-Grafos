package heuristics;

import core.Node;

public class Manhattan implements Heuristic {
    public double calcular(Node atual, Node destino) {
        return Math.abs(destino.x - atual.x) + Math.abs(destino.y - atual.y);
    }
}
