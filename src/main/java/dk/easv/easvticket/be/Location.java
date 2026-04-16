package dk.easv.easvticket.be;

public class Location {
    private int id;
    private int postCode;
    private String roadName;
    private int roadNumber;

    public Location(int postCode, String roadName, int roadNumber) {
        this.postCode = postCode;
        this.roadName = roadName;
        this.roadNumber = roadNumber;
    }

    public Location(int id, int postCode, String roadName, int roadNumber) {
        this.id = id;
        this.postCode = postCode;
        this.roadName = roadName;
        this.roadNumber = roadNumber;
    }
    public int getId() {
        return id;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getRoadName() {
        return roadName;
    }


    public int getRoadNumber() {
        return roadNumber;
    }
}
