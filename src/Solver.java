import java.util.Stack;


public class Solver {
    private Stack stack = new Stack();
    public Polygon[] pieces_available = new Polygon[32];
    private SoccerBall ball;

    public Solver() {
        int index = 0;
        for (int i = 0; i < Data.NB_ELEMENTS.length; i++) {
            for (int j = 0; j < Data.NB_ELEMENTS[i]; j++){
                pieces_available[index] = new Polygon(Data.ELEMENTS_SIDES[i]);
                index++;
            }
        }
    }

    public void solve(){
        // TODO
    }

    public static void main(String[] args){
        Solver solver = new Solver();
        for (int i = 0; i < solver.pieces_available.length; i++){
            System.out.println(solver.pieces_available[i]);
        }

    }



}