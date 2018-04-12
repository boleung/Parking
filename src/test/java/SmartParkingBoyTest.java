import ParkingException.ParkingLotFullException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SmartParkingBoyTest extends AbstractParkingBoyTest {
    private SmartParkingBoy smartParkingBoy;

    @Test
    public void should_park_1_car_in_lot_2_when_lot_2_has_more_available_lots() {
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1=addParkingLot(2);
        ParkingLot lot2=addParkingLot(3);
        smartParkingBoy=new SmartParkingBoy(parkingLotList);

        Car car1=new Car("1111");

         assertNotNull(smartParkingBoy.park(car1));
         assertEquals(2,lot1.getAvailableLot());
         assertEquals(2,lot2.getAvailableLot());
    }

    @Test
    public void should_park_1_car_in_lot_3_when_lot_3_has_more_available_lots() {
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1 = addParkingLot(2);
        ParkingLot lot2 = addParkingLot(3);
        ParkingLot lot3 = addParkingLot(4);
        smartParkingBoy=new SmartParkingBoy(parkingLotList);

        Car car1=new Car("1111");

        assertNotNull(smartParkingBoy.park(car1));
        assertEquals(2,lot1.getAvailableLot());
        assertEquals(3,lot2.getAvailableLot());
        assertEquals(3,lot3.getAvailableLot());
    }

    @Test
    public void should_park_1_car_in_lot_1_when_all_parkinglots_have_same_available_lots() {
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1=addParkingLot(2);
        ParkingLot lot2=addParkingLot(3);
        smartParkingBoy=new SmartParkingBoy(parkingLotList);

        Car car1=new Car("1111");
        lot2.park(car1);

        Car car2=new Car("2222");

        assertNotNull(smartParkingBoy.park(car2));
        assertEquals(1,lot1.getAvailableLot());
        assertEquals(2,lot2.getAvailableLot());
    }

    @Test
    public void should_park_1_car_in_lot_1_when_lot_1_has_more_available_lots() {
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1=addParkingLot(2);
        ParkingLot lot2=addParkingLot(3);
        smartParkingBoy=new SmartParkingBoy(parkingLotList);

        Car car1=new Car("1111");
        lot2.park(car1);

        Car car2=new Car("2222");
        lot2.park(car2);

        Car car3=new Car("3333");

        assertNotNull(smartParkingBoy.park(car3));
        assertEquals(1,lot1.getAvailableLot());
        assertEquals(1,lot2.getAvailableLot());
    }

    @Test(expected = ParkingLotFullException.class)
    public void should_fail_to_park_when_all_parking_lots_are_full() {
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1=addParkingLot(1);
        ParkingLot lot2=addParkingLot(1);
        smartParkingBoy=new SmartParkingBoy(parkingLotList);

        Car car1=new Car("1111");
        Car car2=new Car("2222");
        Car car3=new Car("3333");

        smartParkingBoy.park(car1);
        smartParkingBoy.park(car2);
        smartParkingBoy.park(car3);
    }
}
