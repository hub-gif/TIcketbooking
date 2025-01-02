public class Flight {
    private String departure;
    private String destination;
    private String flightInfo; // 包含航班号、时间等信息

    public Flight(String departure, String destination, String flightInfo) {
        this.departure = departure;
        this.destination = destination;
        this.flightInfo = flightInfo;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getFlightInfo() {
        return flightInfo;
    }

    @Override
    public String toString() {
        return "Flight from " + departure + " to " + destination + ": " + flightInfo;
    }
}
