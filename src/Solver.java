//import java.util.Stack;
//
//
//public class Solver {
//    private Stack stackHexagon = new Stack();
//    private Stack stackPentagon = new Stack();
//    private SoccerBall ball;
//
//    private Solver() {
//        for (int i = 0; i < Data.NB_ELEMENTS.length; i++) {
//            for (int j = 0; j < Data.NB_ELEMENTS[i]; j++){
//                if (Data.ELEMENTS_SIDES[i].length == 6) {
//                    stackHexagon.push(new Polygon(i));
//                }
//                else {
//                    stackPentagon.push(new Polygon(i));
//                }
//
//            }
//        }
//    }
//
//    public void solve(){
//
//    }
//
//    public static void main(String[] args){
//        Solver solver = new Solver();
//        for (int i = 0; i < solver.pieces_available.length; i++){
//            System.out.println(solver.pieces_available[i]);
//        }
//
//    }
//
//
//
//}