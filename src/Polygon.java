public class Polygon {
    private int orientation;
    private int sideType;

    public Polygon(int side){
        //Create a polygon based on the type list of its side
        this.sideType = side;
        this.orientation = 0;
    }

    public void rotate() {
        orientation = orientation++;
    }

    public void reinitilize(){
        this.orientation = 0;
    }

    public boolean completeRotation() {
        return this.orientation > Data.ELEMENTS_SIDES[this.sideType].length;
    }

    public int getSideType(int sideNumber) {
        int[] side = Data.ELEMENTS_SIDES[this.sideType];
        return side[(this.orientation + sideNumber) % side.length];
    }

    public String toString() {
        int o = this.orientation % Data.ELEMENTS_SIDES[this.sideType].length;
        return "Element " + this.sideType + " - Orientation " + o;
    }

    public static void main(String[] args) {
        for (int i=0; i < Data.ELEMENTS_SIDES.length; i++) {
            System.out.println(new Polygon(i));
        }
    }
}
