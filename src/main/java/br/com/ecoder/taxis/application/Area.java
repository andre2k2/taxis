package br.com.ecoder.taxis.application;

public class Area {

    private static final String VALID_REGEX = "-*\\d+\\.\\d+\\,-*\\d+\\.\\d+";

    private Double lat1;
    private Double lng1;
    private Double lat2;
    private Double lng2;
    private Double centerLat;
    private Double centerLng;

    public Area(String sw, String ne) {

        validate(sw);
        validate(ne);

        Double[] pointsSW = parse(sw);
        Double[] pointsNE = parse(ne);

        this.lat1 = pointsSW[0];
        this.lng1 = pointsSW[1];
        this.lat2 = pointsNE[0];
        this.lng2 = pointsNE[1];

        this.centerLat = (lat1 + lat2) / 2.0;
        this.centerLng = (lng1 + lng2) / 2.0;
    }

    private void validate(String location) {

        if (location == null || !location.matches(VALID_REGEX)) {
            throw new IllegalArgumentException("A localização " + location + " não é válida.");
        }
    }

    private Double[] parse(String location) {

        String[] points = location.split("\\,");

        return new Double[] { Double.parseDouble(points[0]), Double.parseDouble(points[1]) };
    }

    public Double getLat1() {
        return lat1;
    }

    public Double getLng1() {
        return lng1;
    }

    public Double getLat2() {
        return lat2;
    }

    public Double getLng2() {
        return lng2;
    }

    public Double getCenterLat() {
        return centerLat;
    }

    public Double getCenterLng() {
        return centerLng;
    }

}
