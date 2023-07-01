public class Ponto {
    
    private double x;
    private double y;

    public Ponto(double x, double y){
        this.x = x;
        this.y = x;
    }

    public double calculaDistancia(Ponto p){
        return ( Math.sqrt( (x - p.x) + (y - p.y) ) );
    }

    public Ponto retornaMedia(Ponto p){

        double x = (this.x + p.x) / 2;
        double y = (this.y + p.y) / 2;

        return new Ponto(x, y);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
