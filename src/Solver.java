import java.util.Stack;


public class Solver {
    private Stack stack = new Stack();
    public Polygon[] pieces_available = new Polygon[32];
    private SoccerBall ball;

    public Solver() {
        index = 0
        for (int i = 0; i < NB_ELEMENTS.size(); i++) {
            for (int j = 0; j < NB_ELEMENTS[i]; j++){
                pieces_available[index] = new Polygon(ELEMENTS_SIDES[i]);
                index++;
            }
        }
    }

    public solve(){
        // TODO
    }

    public static void main(String[] args){
        solver = new Solver();
        System.out.println(solver.pieces_available);
    }



}