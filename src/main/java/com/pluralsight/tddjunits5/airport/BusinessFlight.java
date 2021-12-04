package com.pluralsight.tddjunits5.airport;

public class BusinessFlight extends Flight {

    public BusinessFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passenger.isVip()) {
            return passengerList.add(passenger);
        }
        return false;
    }

    public boolean removePassenger(Passenger passenger) {
        return false;
    }
}
