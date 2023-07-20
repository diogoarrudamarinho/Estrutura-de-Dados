package FilaPrioridade;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import FilaPrioridade.ClassesAuxiliares.HeapBinariaMinima;

public class ClusterizacaoFilaPrioridade {
    
    int tamanho; /* Tamanho do vetor de distancias */
    List<Cluster> clusters = new ArrayList<>(); /* Lista de clusters */
    HeapBinariaMinima distancias; /* Heap Mínima de distâncias */

    public ClusterizacaoFilaPrioridade(int entradas){

        this.tamanho = entradas * (entradas - 1) / 2;

        /* Criando os clusters */
        for (int i = 0; i < entradas; i++)//O(n)
            clusters.add(criaCluster(entradas));
            
        /* Criando a heap mínima de distâncias*/    
        distancias = calculaDistancia(clusters, tamanho);//O(n^2)

        /* Enquanto restar mais de um cluster, clusteriza */
        while (clusters.size() > 1)
        {
            /* Pega os clusters com a menor distancia e transforma num novo cluster */
            Distancia menorDistancia = distancias.min();//O(1)
            Cluster cluster = new Cluster(menorDistancia.getC1(), menorDistancia.getC2());//O(n)
        
            /* remove os dois com a menor distancia e junta em um novo */     
            clusters.remove(menorDistancia.getC1());//O(n)
            clusters.remove(menorDistancia.getC2());//O(n)

            /* Remove as distancias dos clusters antigos */
            distancias = removeDistancias(distancias, menorDistancia);//O(n)

            /* Calcula a distancia desse cluster com o resto e adiciona na lista de distancias */
            for (int i = 0; i < clusters.size(); i++) //O(n log n)
                distancias.insere(new Distancia(cluster, clusters.get(i)));
            
             /* Adiciona o novo cluster na lista de cluster */
            clusters.add(cluster);//O(1)
        }
    }

    /* Complexidade O(1) */
    public Cluster criaCluster(int entradas){

        Random random = new Random();

        /* Gera numeros aleatorios pro x e pro y */
        double x = random.nextInt(entradas) + 1;
        double y = random.nextInt(entradas) + 1;

        /* Cria um ponto com as coordenadas */
        Ponto p = new Ponto(x,y); 
        /* Coloca o ponto num cluster */
        Cluster c = new Cluster(p);
        
        return c;
    }

    /* Complexidade O(n^2) */
    public HeapBinariaMinima calculaDistancia(List<Cluster> clusters, int tamanho){
        
        /* Cria um vetor do tamanho da quantidade de distâncias */
        Distancia[] vetDistancias = new Distancia[tamanho];
        int indice = 0;

        /* Calcula as distâncias do primeiro elemento para todos, 
        *  depois do segundo para todos menos o primeiro... */

        for (int i = 0; i < clusters.size() - 1; i++) 
            for (int j = i + 1; j < clusters.size(); j++, indice++)
                vetDistancias[indice] = new Distancia(clusters.get(i), clusters.get(j));
                
        /* No final, retorna uma heap mínima para conseguir a menor distancia em O(1) */    
        return new HeapBinariaMinima(tamanho, tamanho, vetDistancias); // O(n)
        
    }

    /* Complexidade O(n) */
    public HeapBinariaMinima removeDistancias(HeapBinariaMinima distancias, Distancia menorDistancia){

        //Cria um vetor e percorre o vetor original
        Distancia[] vetor = distancias.getVetor();
        Distancia[] vetDistancias = new Distancia[distancias.getN()];
        int indice = 0;
        
        for (int i = 1; i <= distancias.getN(); i++)
        //Se a distancia tiver um dos clusters da menor distância, não adiciona no vetor
            if  ( (vetor[i].getC1().equals(menorDistancia.getC1()) || vetor[i].getC1().equals(menorDistancia.getC2()) ) 
                || 
                  (vetor[i].getC2().equals(menorDistancia.getC1()) || vetor[i].getC2().equals(menorDistancia.getC2()) )
                )
                continue;
            else
                vetDistancias[indice++] = vetor[i];

        if (indice == 0)
            return new HeapBinariaMinima(1);
        else
        /* Retorna uma nova heap sem as distancias dos clusters de menor valor */
        return new HeapBinariaMinima(distancias.getN(), indice, vetDistancias);
    }
}
