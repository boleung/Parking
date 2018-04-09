import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {

    @Test
    public void parking_success(){
        ParkingLot parkinglot=new ParkingLot(1);
        assertTrue(parkinglot.park("1111"));
    }

    @Test
    public void should_fail_to_park_2_cars_in_1_Lot() {
        ParkingLot parkinglot=new ParkingLot(1);
        parkinglot.park("1111");
        assertFalse(parkinglot.park("2222"));
    }

    @Test
    public void should_success_to_park_2_cars_in_2_lot() {
        ParkingLot parkinglot=new ParkingLot(2);
        parkinglot.park("1111");
        assertTrue(parkinglot.park("2222"));
    }

    @Test
    public void should_success_to_pick_up_1_car() {
        ParkingLot parkinglot=new ParkingLot(2);
        parkinglot.park("1111");
        assertTrue(parkinglot.pickup("1111"));
    }

    @Test
    public void should_fail_to_pick_up_a_nonexisting_car() {
        ParkingLot parkinglot=new ParkingLot(2);
        parkinglot.park("1111");
        assertFalse(parkinglot.pickup("2222"));
    }

    @Test
    public void should_fail_to_pick_up_a_car_from_empty_lot() {
        ParkingLot parkinglot=new ParkingLot(2);
        assertFalse(parkinglot.pickup("2222"));
    }

    @Test
    public void should_get_1_available_lot_from_a_empty_parkinglot_with_1_lot() {
        ParkingLot parkinglot=new ParkingLot(1);
        assertEquals(1,parkinglot.getAvailableLot());
    }

    @Test
    public void should_get_0_available_lot_from_a_full_parkinglot() {
        ParkingLot parkinglot=new ParkingLot(1);
        parkinglot.park("1111");
        assertEquals(0,parkinglot.getAvailableLot());
    }

    @Test
    public void should_get_1_available_lot_from_a_parkinglot_with_2_lot_1_parked() {
        ParkingLot parkinglot=new ParkingLot(2);
        parkinglot.park("1111");
        assertEquals(1,parkinglot.getAvailableLot());
    }
}