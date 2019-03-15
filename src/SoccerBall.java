import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SoccerBall {
    private Polygon[] face;
    private int[] faceSideNumber;
    private Map<Integer, int[][]> edgeNetwork; //Is it the best representation as we will check for confilct for a particular piece

    public SoccerBall(int[][] connection, int[][] elementSide, int[] elementNumber) {
        //Initialisation of the ScoccerBall
        this.face = new Polygon[connection.length];
        this.faceSideNumber = new int[connection.length];
        this.edgeNetwork = new HashMap<>();
        for (int i=0; i < connection.length; i++) {
            this.faceSideNumber[i] = connection[i].length;
            for (int j = 0; j < connection[i].length; j++) {
                if (!this.edgeNetwork.containsKey(connection[i][j])) {
                    int[][] edge = new int[2][2];
                    edge[0][0] = i;
                    edge[0][1] = j;
                    this.edgeNetwork.put(j, edge);
                } else {
                    this.edgeNetwork.get(connection[i][j])[1][0] = i;
                    this.edgeNetwork.get(connection[i][j])[1][1] = j;
                }
            }
        }

        //creation of the available pieces for construction
        ArrayList<ArrayList<Polygon>> availablePiece = new ArrayList<>();
        for (int i=0; i < elementSide.length; i++) {
            availablePiece.add(new ArrayList<>());
            for (int j=0; j < elementNumber[i]; j++) {
                availablePiece.get(i).add(new Polygon(elementSide[i]));
            }
        }

        //Construction of the soccer ball
        //TODO


    }

    private boolean hasConflict() {
        for ()
        return false;
    }

    public void display() {
        //TODO
    }
    public static void main(String[] args) {
        SoccerBall ball = new SoccerBall(Data.CONNECTIONS, Data.ELEMENTS_SIDES, Data.NB_ELEMENTS);
        ball.display();
    }
}
