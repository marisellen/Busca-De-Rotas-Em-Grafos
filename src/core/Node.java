package core;

public class Node {
        public int x, y;
        public int custo;
        public Node pai;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.custo = 0;
            this.pai = null;
        }

        public Node(int x, int y, int custo, Node pai) {
            this.x = x;
            this.y = y;
            this.custo = custo;
            this.pai = pai;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node) {
                Node outro = (Node) obj;
                return this.x == outro.x && this.y == outro.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }
