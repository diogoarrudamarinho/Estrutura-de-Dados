public class Cluster {
    
    public Ponto pontos[];
    public Ponto centroide;
    public int size;

    public Cluster(Ponto p){

        this.pontos = new Ponto[1];
        this.pontos[0] = p;
        this.centroide = p;
        this.size = 1;
    }

    public Cluster(Cluster c1, Cluster c2){

        this.size = c1.size + c2.size;
        this.pontos = new Ponto[size];

        for (int i = 0; i < c1.size; i++)
            this.pontos[i] =   c1.pontos[i];  
        
        for (int i = c1.size, j = 0; j < c2.size; i++, j++) 
            this.pontos[i] = c2.pontos[j];
        
        atualizaCentroide();
    }

    public int getSize(){
        return size;
    }

    public void atualizaCentroide(){

        double sumX = 0;
        double sumY = 0;

        for (Ponto ponto : pontos)
        {
            sumX += ponto.getX();
            sumY += ponto.getY();
        }

        double mediaX = sumX / size;
        double mediaY = sumY / size;

        this.centroide = new Ponto(mediaX, mediaY);

    }

    public double calculaDistancia(Cluster c){

        return ( this.centroide.calculaDistancia(c.centroide) );
    }
}
