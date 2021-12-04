package com.pluralsight.tddjunits5.airport;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {
    @Test
    @DisplayName("Add and Remove Airport Test")
    public void testAirport() {

        Flight economyFlight = new EconomyFlight("1");
        Flight businessFlight = new BusinessFlight("2");

        Passenger john = new Passenger("John", true);
        Passenger mike = new Passenger("Miki", false);


        assertEquals(true, economyFlight.addPassenger(john));
        assertEquals(false, economyFlight.removePassenger(john));
        assertEquals(true, businessFlight.addPassenger(john));
        assertEquals(false, businessFlight.removePassenger(john));


        assertEquals(true, economyFlight.addPassenger(mike));
        assertEquals(true, economyFlight.removePassenger(mike));
        assertEquals(false, businessFlight.addPassenger(mike));
        assertEquals(false, businessFlight.removePassenger(mike));
    }

    @Test
    @DisplayName("EconomyFlight Test : add and remove")
    public void testEconomyFlightUsualPassenger() {
        Flight economyFlight = new EconomyFlight("1");
        Passenger mike = new Passenger("Miki", false);

        assertEquals("1", economyFlight.getId());
        assertEquals(true, economyFlight.addPassenger(mike));
        assertEquals(1, economyFlight.getPassengerList().size());
        assertEquals("Miki", economyFlight.getPassengerList().get(0).getName());

        assertEquals(true, economyFlight.removePassenger(mike));
        assertEquals(0, economyFlight.getPassengerList().size());


    }

    @Test
    @DisplayName("EconomyFlight Test : add and remove")
    public void testEconomyFlightUsualVipPassenger() {
        Flight economyFlight = new EconomyFlight("1");
        Passenger jhon = new Passenger("John", true);

        assertEquals("1", economyFlight.getId());
        assertEquals(true, economyFlight.addPassenger(jhon));
        assertEquals(1, economyFlight.getPassengerList().size());
        assertEquals("John", economyFlight.getPassengerList().get(0).getName());

        assertEquals(false, economyFlight.removePassenger(jhon));
        assertEquals(1, economyFlight.getPassengerList().size());


    }

    @Test
    @DisplayName("BusinessFlight Test : add and remove")
    public void testBusinessFlightUsualPassenger() {
        Flight businessFlight = new BusinessFlight("2");
        Passenger miki = new Passenger("Miki", false);

        assertEquals(false, businessFlight.addPassenger(miki));
        assertEquals(0, businessFlight.getPassengerList().size());
        assertEquals(false, businessFlight.removePassenger(miki));
        assertEquals(0, businessFlight.getPassengerList().size());

    }

    @Test
    @DisplayName("BusinessFlight Test : add and remove")
    public void testBusinessFlightVipPassenger() {
        Flight businessFlight = new BusinessFlight("2");
        Passenger john = new Passenger("John", true);

        assertEquals(true, businessFlight.addPassenger(john));
        assertEquals(1, businessFlight.getPassengerList().size());
        assertEquals(false, businessFlight.removePassenger(john));
        assertEquals(1, businessFlight.getPassengerList().size());

    }
    @Nested
    @DisplayName("Given there is a premium flight")
    public class PremiumFlightTest {
        private Flight premiumFlight;
        private Passenger miki;
        private Passenger john;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiumFlight("3");
            miki = new Passenger("Miki", false);
            john = new Passenger("John", true);
        }

        @Nested
        @DisplayName("When we have a usual passenger")
        class UsualPassenger{
            @Test
            @DisplayName("Then you cannot add or remove him from a premium flight")
            public void testPremiumFlightUsualPassenger(){
                assertAll(
                        "Verify all conditions for a usual passenger and a premium flight",
                        () -> assertEquals(false, premiumFlight.addPassenger(miki)),
                        () -> assertEquals(0, premiumFlight.getPassengerList().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(miki)),
                        () -> assertEquals(0, premiumFlight.getPassengerList().size())
                );
            }

            @DisplayName("Then you cannot add him to an economy flight more than one")
            @RepeatedTest(4)
            public void testEconomyFlightUsualPassengerAddedOnlyOne(RepetitionInfo repetitionInfo){
                for (int i= 0; i<repetitionInfo.getCurrentRepetition(); i++){
                    premiumFlight.addPassenger(miki);
                }
//                assertAll("Verify ausaual passenger can be added to an economy flight only one",
//                        () -> assertEquals(1, econm)
//                        );
            }
        }

        @Nested
        @DisplayName("When we have VIP passenger")
        class VipPassenger{
            @Test
            @DisplayName("Then you can add and remove him from a premium flight")
            public void testPremiumFlightUsualPassenger(){
                assertAll(
                        "Verify all conditions for a VIP passenger and premium flight",
                        () -> assertEquals(true, premiumFlight.addPassenger(john)),
                        () -> assertEquals(1, premiumFlight.getPassengerList().size()),
                        () -> assertEquals(true, premiumFlight.removePassenger(john)),
                        () -> assertEquals(0, premiumFlight.getPassengerList().size())
                );
            }
        }
    }

}
