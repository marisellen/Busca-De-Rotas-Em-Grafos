package core;

import java.util.Objects;

public class Node {
      public int x;
      public int y;
      public int custo;
      public Node pai;

      public Node(int x, int y) {
        this(x, y, 0, null);
      }

     public Node(int x, int y, int custo, Node pai) {
        this.x = x;
        this.y = y;
        this.custo = custo;
        this.pai = pai;
     }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Node)) return false;
        Node n = (Node) o;
        return x == n.x && y == n.y;
    }

    @Override
    public int hashCode() {
        return (x * 31) + y;
    }
}