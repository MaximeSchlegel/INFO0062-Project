public class Polygon {
    private int orientation;
    private int[] sideType;

    public Polygon(int[] side){
        //Create a polygon based on the type list of its side
        this.sideType = side;
        this.orientation = 0;
    }

    public void rotate() {
        orientation++;
    }

    public void reinitilize(){
        this.orientation =0;
    }

    public boolean completeRotation() {
        return this.orientation > this.sideType.length;
    }

    public int getSideType(int sideNumber) {
        return this.sideType[(this.orientation + sideNumber) % this.sideType.length];
    }

    public String toString() {
        String repr;
        if (this.sideType.length == 5) {
            repr = "Pentagon";
        } else if (this.sideType.length == 6) {
            repr = "Hexagon";
        } else {
            repr = "Polygon";
        }
        repr += "\n  Orientation : " + this.orientation + "\n  Sides : [";
        for(int i : this.sideType) {
            repr += " " + i + ",";
        }
        return repr + " ]";
    }
    public static void main(String[] args) {
        for (int[] i : Data.ELEMENTS_SIDES) {
            System.out.println(new Polygon(i));
        }
    }
}
