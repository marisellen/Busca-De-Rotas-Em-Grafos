package heuristics;

import core.Node;

public interface Heuristic {
    double calcular(Node atual, Node destino);
}

