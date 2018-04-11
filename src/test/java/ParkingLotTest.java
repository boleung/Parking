import ParkingException.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {

    @Test
    public void should_park_1_car_in_1_empty_lot(){
        ParkingLot parkinglot=new ParkingLot(1);
        Car car=new Car("1111");
        assertNotNull(parkinglot.park(car));
    }

    @Test (expected = ParkingLotFullException.class)
    public void should_fail_to_park_car_when_no_empty_Lot() {
        ParkingLot parkinglot=new ParkingLot(1);
        Car car1=new Car("1111");
        ParkingToken token1= parkinglot.park(car1);
        Car car2=new Car("2222");
        ParkingToken token2= parkinglot.park(car2);
    }

   @Test
    public void should_success_to_park_2_cars_in_2_lot() {
        ParkingLot parkinglot=new ParkingLot(2);
        Car car1=new Car("1111");
        Car car2=new Car("2222");
        parkinglot.park(car1);
        assertNotNull(parkinglot.park(car2));
    }

    @Test (expected = CarAlreadyParkedException.class)
    public void should_fail_to_park_the_same_car() {
        ParkingLot parkinglot=new ParkingLot(2);
        Car car1=new Car("1111");

        ParkingToken token1 = parkinglot.park(car1);
        ParkingToken token2 = parkinglot.park(car1);
    }

    @Test
    public void should_success_to_pick_up_1_parked_car() {
        ParkingLot parkinglot=new ParkingLot(2);
        Car car1=new Car("1111");
        ParkingToken token = parkinglot.park(car1);
        assertNotNull(parkinglot.pickup(token));
    }

    @Test (expected = CarNotParkedException.class)
    public void should_fail_to_pick_up_a_nonexisting_car() {
        ParkingLot parkinglot=new ParkingLot(2);
        Car car1=new Car("1111");
        ParkingToken token = new ParkingToken(car1);
        car1=parkinglot.pickup(token);
    }

    @Test
    public void should_get_1_available_lot_from_a_empty_parkinglot_with_1_lot() {
        ParkingLot parkinglot=new ParkingLot(1);
        assertEquals(1,parkinglot.getAvailableLot());
    }


    @Test
    public void should_get_0_available_lot_from_a_full_parkinglot() {
        ParkingLot parkinglot=new ParkingLot(1);
        Car car1=new Car("1111");
        ParkingToken token = parkinglot.park(car1);
        assertEquals(0,parkinglot.getAvailableLot());
    }

    @Test
    public void should_get_1_available_lot_from_a_parkinglot_with_2_lot_1_parked() {
        ParkingLot parkinglot=new ParkingLot(2);
        Car car1=new Car("1111");
        ParkingToken token = parkinglot.park(car1);
        assertEquals(1,parkinglot.getAvailableLot());
    }

    @Test
    public void should_get_2_available_lot_after_pick_up_1_car_from_a_parkinglot_with_2_lot_1_parked() {
        ParkingLot parkinglot=new ParkingLot(2);
        Car car1=new Car("1111");
        ParkingToken token = parkinglot.park(car1);
        car1 = parkinglot.pickup(token);
        assertEquals(2,parkinglot.getAvailableLot());
    }
}