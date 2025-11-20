package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int[][] matriz;
    public int linhas;
    public int colunas;

        public void carregarMatriz(String arquivo) {
            try {
                List<int[]> lista = new ArrayList<>();
                BufferedReader br = new BufferedReader(new FileReader(arquivo));
                String linha;
                while((linha = br.readLine()) != null) {
                    String[] tokens = linha.trim().split("\\s+");
                    int[] valores = new int[tokens.length];
                    for(int i = 0; i < tokens.length; i++)
                        valores[i] = Integer.parseInt(tokens[i]);
                    lista.add(valores);
                }
                br.close();
                linhas = lista.size();
                colunas = lista.get(0).length;
                matriz = lista.toArray(new int[linhas][colunas]);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        public int getPeso(int xOrigem, int yOrigem, int xDestino, int yDestino) {
            if (xDestino < 0 || yDestino < 0 ||
                xDestino >= colunas ||
                yDestino >= linhas)
                return -1;

            int peso = matriz[yDestino][xDestino];
            if (peso < 0)
                return -1;

            return peso == 0 ? 1 : peso;
        }
}
