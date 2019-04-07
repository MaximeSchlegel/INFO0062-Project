public class Polygon {
    private int type;
    private int[] sideType;
    private int orientation;

    public Polygon(int type){
        //Create a polygon based on the type list of its side
        this.type = type;
        this.sideType = Data.ELEMENTS_SIDES[type];
        this.orientation = 0;
    }

    public int getType () {
        return this.sideType.length;
    }

    public int getElementNumber() {
        return this.type;
    }

    public int getSideType(int sideNumber) {
        return this.sideType[(this.orientation + sideNumber) % this.sideType.length];
    }

    public void rotate() {
        this.orientation++;
    }

    public void reinitialize() {
        this.orientation = 0;
    }

    public boolean completeRotation() {
        return this.orientation >= this.sideType.length;
    }

    public String toString() {
        int o = (this.orientation % this.sideType.length);
        return "Element " + (this.type + 1) + " - Orientation " + o;
    }
}
