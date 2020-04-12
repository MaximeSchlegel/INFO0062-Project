import java.util.HashMap;
import java.util.Map;

public class SoccerBall {
    private Polygon[]face;
    private int[] faceSideNumber;
    private Map<Integer, int[][]> edgeNetwork;
    private int nextFace;

    public SoccerBall(Data data) {
        //Initialisation of the ScoccerBall
        this.face = new Polygon[data.CONNECTIONS.length];
        this.faceSideNumber = new int[data.CONNECTIONS.length];
        this.edgeNetwork = new HashMap<>();

        for (int i=0; i < data.CONNECTIONS.length; i++) {
            this.faceSideNumber[i] = data.CONNECTIONS[i].length;
            for (int j = 0; j < data.CONNECTIONS[i].length; j++) {
                if (!this.edgeNetwork.containsKey(Data.CONNECTIONS[i][j])) {
                    int[][] edge = new int[2][2];
                    edge[0][0] = i;
                    edge[0][1] = j;
                    this.edgeNetwork.put(data.CONNECTIONS[i][j], edge);
                } else {
                    this.edgeNetwork.get(data.CONNECTIONS[i][j])[1][0] = i;
                    this.edgeNetwork.get(data.CONNECTIONS[i][j])[1][1] = j;
                }
            }

        }
        this.nextFace = 0;
    }

    public int nextFaceType() {
        return this.faceSideNumber[this.nextFace];
    }

    public boolean testNextFace(Polygon polygon) throws Exception {
        this.addFace(polygon);
        boolean conflict = this.hasConflictTopFace();
        this.popFace();
        return conflict;
    }

    public void addFace(Polygon p) throws Exception{
        if (p.getType() != this.nextFaceType()) {
            throw new Exception("Face type does not match");
        }
        this.face[this.nextFace] = p;
        this.nextFace++;
    }

    public Polygon popFace() throws Exception{
        if (this.nextFace == 0) {
            throw new Exception("Nothing to pop");
        }
        Polygon p = this.face[this.nextFace - 1];
        this.face[this.nextFace - 1] = null;
        this.nextFace--;
        return p;
    }

    public void rotateTopFace() {
        this.face[this.nextFace - 1].rotate();
    }

    public boolean fullRotationTopFace() {
        return this.face[this.nextFace - 1].completeRotation();
    }

    public boolean hasConflictTopFace() {
        for (int link : Data.CONNECTIONS[this.nextFace - 1]) {
            int[][] edge = this.edgeNetwork.get(link);
            if (this.face[edge[0][0]] != null && this.face[edge[1][0]] != null
                    && this.face[edge[0][0]].getSideType(edge[0][1]) == this.face[edge[1][0]].getSideType(edge[1][1])) {
                return true;
            }
        }
        return false;
    }

    public boolean isComplete() {
        return this.nextFace == this.face.length;
    }

    public String toString() {
        StringBuilder repr = new StringBuilder();
        for (int i=0; i < this.face.length; i++) {
            repr.append("Position " + (i + 1) + " - ");
            repr.append(this.face[i]);
            repr.append("\n");
        }
        return repr.toString();
    }

    public static void main(String[] args) throws Exception {
        Data data = new Data();
        SoccerBall ball2 = new SoccerBall(data);
        Solver s = new Solver(ball2);
        s.solve();
        System.out.println(ball2);
    }
}
