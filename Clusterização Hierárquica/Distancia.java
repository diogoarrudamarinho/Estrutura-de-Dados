public class Distancia implements Comparable<Distancia>{
 
    private Cluster c1, c2;
    private double distancia;

    public Distancia(Cluster c1, Cluster c2){
        this.c1 = c1;
        this.c2 = c2;
        this.distancia = calculaDistancia();
    }

    public double calculaDistancia(){
        return Math.sqrt( 
                Math.abs(  
                    (c1.getCentroide().getX() - c2.getCentroide().getX()) + 
                    (c1.getCentroide().getY() - c2.getCentroide().getY()) 
                    ));
    }

    public int compareTo(Distancia d) {
        if (distancia == d.distancia)
            return 0;
        else if (distancia > d.distancia)
            return 1;
        else
            return -1;
    }
}
