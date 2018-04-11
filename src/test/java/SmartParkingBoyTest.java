import ParkingException.ParkingLotFullException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SmartParkingBoyTest {
    private SmartParkingBoy smartParkingBoy;
    @Before
    public void initialization(){
        smartParkingBoy=new SmartParkingBoy();

        List<ParkingLot> parkingLotList=new ArrayList<ParkingLot>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(3));

        smartParkingBoy.addManagedParkingLot(parkingLotList);
    }

    @Test
    public void should_park_1_car_in_lot_2_when_lot_2_has_more_available_lots() {
        Car car1=new Car("1111");

         assertNotNull(smartParkingBoy.park(car1));
         assertEquals(2,smartParkingBoy.getAvailableLotAmt(0));
         assertEquals(2,smartParkingBoy.getAvailableLotAmt(1));
    }

    @Test
    public void should_park_1_car_in_lot_3_when_lot_3_has_more_available_lots() {
        List<ParkingLot> parkingLotList=new ArrayList<ParkingLot>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(3));
        parkingLotList.add(new ParkingLot(4));

        smartParkingBoy.addManagedParkingLot(parkingLotList);

        Car car1=new Car("1111");

        assertNotNull(smartParkingBoy.park(car1));
        assertEquals(2,smartParkingBoy.getAvailableLotAmt(0));
        assertEquals(3,smartParkingBoy.getAvailableLotAmt(1));
        assertEquals(3,smartParkingBoy.getAvailableLotAmt(2));
    }

    @Test
    public void should_park_1_car_in_lot_1_when_all_parkinglots_have_same_available_lots() {
        Car car1=new Car("1111");
        Car car2=new Car("2222");

        smartParkingBoy.park(car1);

        assertNotNull(smartParkingBoy.park(car2));
        assertEquals(1,smartParkingBoy.getAvailableLotAmt(0));
        assertEquals(2,smartParkingBoy.getAvailableLotAmt(1));
    }

    @Test
    public void should_park_1_car_in_lot_1_when_lot_1_has_more_available_lots() {
        Car car1=new Car("1111");
        Car car2=new Car("2222");
        Car car3=new Car("3333");
        Car car4=new Car("4444");

        smartParkingBoy.park(car1);
        ParkingToken token2=smartParkingBoy.park(car2);
        smartParkingBoy.park(car3);

        smartParkingBoy.pickup(token2);

        assertNotNull(smartParkingBoy.park(car4));
        assertEquals(1,smartParkingBoy.getAvailableLotAmt(0));
        assertEquals(1,smartParkingBoy.getAvailableLotAmt(1));
    }

    @Test(expected = ParkingLotFullException.class)
    public void should_fail_to_park_when_all_parking_lots_are_full() {
        Car car1=new Car("1111");
        Car car2=new Car("2222");
        Car car3=new Car("3333");
        Car car4=new Car("4444");
        Car car5=new Car("4444");
        Car car6=new Car("4444");

        smartParkingBoy.park(car1);
        smartParkingBoy.park(car2);
        smartParkingBoy.park(car3);
        smartParkingBoy.park(car4);
        smartParkingBoy.park(car5);
        smartParkingBoy.park(car6);
    }
}
