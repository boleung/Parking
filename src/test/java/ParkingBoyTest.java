import ParkingException.CarAlreadyParkedException;
import ParkingException.CarNotParkedException;
import ParkingException.ParkingLotFullException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParkingBoyTest extends AbstractParkingBoyTest {
    private ParkingBoy parkingBoy;
    private ParkingLot lot1,lot2;
    @Before
    public void initialization(){
        parkingLotList=new ArrayList<ParkingLot>();
        lot1 = addParkingLot(2);
        lot2= addParkingLot(2);
        parkingBoy = new ParkingBoy(parkingLotList);
    }

    @Test
    public void should_park_in_lot_1_when_2_empty_parking_lots() {
        Car car=new Car("1111");

        assertNotNull(parkingBoy.park(car));
        assertEquals(1,lot1.getAvailableLot());
        assertEquals(2,lot2.getAvailableLot());
    }

    @Test
    public void should_park_in_lot_1_when_lot_1_has_empty_parking_lots() {

        Car car1=new Car("1111");
        Car car2=new Car("2222");

        assertNotNull(parkingBoy.park(car1));
        assertNotNull(parkingBoy.park(car2));
        assertEquals(0,lot1.getAvailableLot());
        assertEquals(2,lot2.getAvailableLot());
    }

    @Test
    public void should_park_in_lot_2_when_lot_1_is_full() {

        lot1.park(new Car("1111"));
        lot1.park(new Car("2222"));

        assertNotNull(parkingBoy.park(new Car("3333")));
        assertEquals(0,lot1.getAvailableLot());
        assertEquals(1,lot2.getAvailableLot());
    }

    @Test (expected = ParkingLotFullException.class)
    public void should_fail_to_park_car_when_all_lots_are_full() {
        lot1.park(new Car("1111"));
        lot1.park(new Car("2222"));
        lot2.park(new Car("3333"));
        lot2.park(new Car("4444"));

        ParkingToken token=parkingBoy.park(new Car("5555"));
    }

    @Test (expected = CarAlreadyParkedException.class)
    public void should_fail_to_park_the_same_car() {
        Car car1=new Car("1111");
        Car car2=new Car("2222");

        ParkingToken token1= parkingBoy.park(car1);
        ParkingToken token2= parkingBoy.park(car2);

        ParkingToken token3=parkingBoy.park(car1);
    }

    @Test
    public void should_pick_up_1_car_after_parking_in_lot_1() {
        Car car1=new Car("1111");
        ParkingToken token1=parkingBoy.park(car1);

        assertNotNull(parkingBoy.pickup(token1));
        assertEquals(2, parkingBoy.parkingLotList.get(0).getAvailableLot());
        assertEquals(2, parkingBoy.parkingLotList.get(1).getAvailableLot());
    }

    @Test
    public void should_pick_up_1_car_after_parking_in_lot_2() {
        ParkingToken token1=parkingBoy.park(new Car("1111"));
        ParkingToken token2=parkingBoy.park(new Car("2222"));
        ParkingToken token3=parkingBoy.park(new Car("3333"));

        assertNotNull(parkingBoy.pickup(token3));
        assertEquals(0, parkingBoy.parkingLotList.get(0).getAvailableLot());
        assertEquals(2, parkingBoy.parkingLotList.get(1).getAvailableLot());
    }

    @Test
    public void should_pick_up_1_car_from_lot_1_after_parking_cars_in_lot_2() {
        ParkingToken token1=parkingBoy.park(new Car("1111"));
        ParkingToken token2=parkingBoy.park(new Car("2222"));
        ParkingToken token3=parkingBoy.park(new Car("3333"));

        assertNotNull(parkingBoy.pickup(token2));
        assertEquals(1, parkingBoy.parkingLotList.get(0).getAvailableLot());
        assertEquals(1, parkingBoy.parkingLotList.get(1).getAvailableLot());
    }

    @Test
    public void should_park_in_lot_1_after_pick_up_1_car_from_lot_1_with_parking_cars_in_lot_2() {
        ParkingToken token1=parkingBoy.park(new Car("1111"));
        ParkingToken token2=parkingBoy.park(new Car("2222"));
        ParkingToken token3=parkingBoy.park(new Car("3333"));

        Car car=parkingBoy.pickup(token2);
        assertNotNull(parkingBoy.park(new Car("4444")));
        assertEquals(0, parkingBoy.parkingLotList.get(0).getAvailableLot());
        assertEquals(1, parkingBoy.parkingLotList.get(1).getAvailableLot());
    }

    @Test (expected = CarNotParkedException.class)
    public void should_fail_to_pick_up_not_parked_car() {
        ParkingToken token1=parkingBoy.park(new Car("1111"));
        ParkingToken token2 = new ParkingToken(new Car("2222"));

        parkingBoy.pickup(token2);
    }
}
