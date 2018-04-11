import ParkingException.CarAlreadyParkedException;
import ParkingException.CarNotParkedException;
import ParkingException.ParkingLotFullException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingBoyTest {
    private ParkingBoy parkingBoy;

    @Before
    public void initialization(){
        parkingBoy=new ParkingBoy();

        List<ParkingLot> parkingLotList=new ArrayList<ParkingLot>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(2));

        parkingBoy.addManagedParkingLot(parkingLotList);
    }

    @Test
    public void should_park_1_car_in_first_parking_lot_when_2_empty_parking_lots() {
        Car car=new Car("1111");

        assertNotNull(parkingBoy.park(car));
        assertEquals(1,parkingBoy.getAvailableLotAmt(0));
        assertEquals(2,parkingBoy.getAvailableLotAmt(1));
    }

    @Test
    public void should_park_2_cars_in_first_Parking_lot_when_2_empty_parking_lots() {
        Car car1=new Car("1111");
        Car car2=new Car("2222");

        assertNotNull(parkingBoy.park(car1));
        assertNotNull(parkingBoy.park(car2));
        assertEquals(0,parkingBoy.getAvailableLotAmt(0));
        assertEquals(2,parkingBoy.getAvailableLotAmt(1));
    }

    @Test
    public void should_park_1_car_in_second_Parking_lot_when_first_lot_is_full() {
        Car car1=new Car("1111");
        Car car2=new Car("2222");
        Car car3=new Car("3333");

        assertNotNull(parkingBoy.park(car1));
        assertNotNull(parkingBoy.park(car2));
        assertNotNull(parkingBoy.park(car3));

        assertEquals(0,parkingBoy.getAvailableLotAmt(0));
        assertEquals(1,parkingBoy.getAvailableLotAmt(1));
    }

    @Test (expected = ParkingLotFullException.class)
    public void should_fail_to_park_car_when_all_lots_are_full() {
        Car car1=new Car("1111");
        Car car2=new Car("2222");
        Car car3=new Car("3333");
        Car car4=new Car("4444");
        Car car5=new Car("5555");

        assertNotNull(parkingBoy.park(car1));
        assertNotNull(parkingBoy.park(car2));
        assertNotNull(parkingBoy.park(car3));
        assertNotNull(parkingBoy.park(car4));

        assertEquals(0,parkingBoy.getAvailableLotAmt(0));
        assertEquals(0,parkingBoy.getAvailableLotAmt(1));

        ParkingToken token=parkingBoy.park(car5);
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
        assertEquals(2,parkingBoy.getAvailableLotAmt(0));
        assertEquals(2,parkingBoy.getAvailableLotAmt(1));
    }

    @Test
    public void should_pick_up_1_car_after_parking_in_lot_2() {
        Car car1=new Car("1111");
        ParkingToken token1=parkingBoy.park(car1);
        Car car2=new Car("2222");
        ParkingToken token2=parkingBoy.park(car2);
        Car car3=new Car("3333");
        ParkingToken token3=parkingBoy.park(car3);

        assertNotNull(parkingBoy.pickup(token3));
        assertEquals(0,parkingBoy.getAvailableLotAmt(0));
        assertEquals(2,parkingBoy.getAvailableLotAmt(1));
    }

    @Test
    public void should_pick_up_1_car_from_lot_1_after_parking_cars_in_lot_2() {
        Car car1=new Car("1111");
        ParkingToken token1=parkingBoy.park(car1);
        Car car2=new Car("2222");
        ParkingToken token2=parkingBoy.park(car2);
        Car car3=new Car("3333");
        ParkingToken token3=parkingBoy.park(car3);

        assertNotNull(parkingBoy.pickup(token2));
        assertEquals(1,parkingBoy.getAvailableLotAmt(0));
        assertEquals(1,parkingBoy.getAvailableLotAmt(1));
    }

    @Test
    public void should_park_in_lot_1_after_pick_up_1_car_from_lot_1_with_parking_cars_in_lot_2() {
        Car car1=new Car("1111");
        ParkingToken token1=parkingBoy.park(car1);
        Car car2=new Car("2222");
        ParkingToken token2=parkingBoy.park(car2);
        Car car3=new Car("3333");
        ParkingToken token3=parkingBoy.park(car3);
        Car car4=new Car("4444");

        Car car=parkingBoy.pickup(token2);
        assertNotNull(parkingBoy.park(car4));
        assertEquals(0,parkingBoy.getAvailableLotAmt(0));
        assertEquals(1,parkingBoy.getAvailableLotAmt(1));
    }

    @Test (expected = CarNotParkedException.class)
    public void should_fail_to_pick_up_not_parked_car() {
        Car car1=new Car("1111");
        ParkingToken token1=parkingBoy.park(car1);
        Car car2=new Car("2222");
        ParkingToken token2=parkingBoy.park(car2);
        Car car3=new Car("3333");
        ParkingToken token3=parkingBoy.park(car3);
        Car car4=new Car("4444");
        ParkingToken token4=parkingBoy.park(car4);
        Car car5=new Car("5555");

        parkingBoy.pickup(new ParkingToken(car5));

    }
}
