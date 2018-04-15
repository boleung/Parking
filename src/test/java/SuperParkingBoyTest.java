import ParkingException.CarNotParkedException;
import ParkingException.ParkingLotFullException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SuperParkingBoyTest extends AbstractParkingBoyTest {
    private SuperParkingBoy superParkingBoy;

    @Test
    public void should_park_in_lot_1_when_same_available_rate() {
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1=addParkingLot(2);
        ParkingLot lot2=addParkingLot(3);
        superParkingBoy =new SuperParkingBoy(parkingLotList);

        Car car1=new Car("1111");

         assertNotNull(superParkingBoy.park(car1));
         assertEquals(1,lot1.getAvailableLot());
         assertEquals(3,lot2.getAvailableLot());
    }

    @Test
    public void should_park_in_lot_2_when_lot_2_has_higher_available_rate() {
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1 = addParkingLot(2);
        ParkingLot lot2 = addParkingLot(3);
        superParkingBoy =new SuperParkingBoy(parkingLotList);

        Car car1=new Car("1111");
        lot1.park(car1);

        Car car2=new Car("2222");

        assertNotNull(superParkingBoy.park(car2));
        assertEquals(1,lot1.getAvailableLot());
        assertEquals(2,lot2.getAvailableLot());
    }


    @Test
    public void should_park_in_lot_1_when_lot_1_has_higher_available_rate() {
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1=addParkingLot(2);
        ParkingLot lot2=addParkingLot(3);
        superParkingBoy =new SuperParkingBoy(parkingLotList);

        Car car1=new Car("1111");
        lot2.park(car1);

        Car car2=new Car("2222");

        assertNotNull(superParkingBoy.park(car2));
        assertEquals(1,lot1.getAvailableLot());
        assertEquals(2,lot2.getAvailableLot());
    }

    @Test
    public void should_park_in_lot_3_when_lot_3_has_higher_available_rate() {
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1=addParkingLot(2);
        ParkingLot lot2=addParkingLot(3);
        ParkingLot lot3=addParkingLot(1);
        superParkingBoy =new SuperParkingBoy(parkingLotList);

        Car car1=new Car("1111");
        lot1.park(car1);

        Car car2=new Car("2222");
        lot2.park(car2);

        Car car3=new Car("3333");

        assertNotNull(superParkingBoy.park(car3));
        assertEquals(1,lot1.getAvailableLot());
        assertEquals(2,lot2.getAvailableLot());
        assertEquals(0,lot3.getAvailableLot());
    }

    @Test
    public void should_pick_up_successfully_after_parking_a_car(){
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1=addParkingLot(2);
        ParkingLot lot2=addParkingLot(3);
        superParkingBoy =new SuperParkingBoy(parkingLotList);

        Car car1=new Car("1111");
        ParkingToken token=superParkingBoy.park(car1);

        assertNotNull(superParkingBoy.pickup(token));
        assertEquals(2,lot1.getAvailableLot());
        assertEquals(3,lot2.getAvailableLot());
    }

    @Test (expected = CarNotParkedException.class)
    public void should_fail_to_pick_up_a_car_not_parked(){
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1=addParkingLot(2);
        ParkingLot lot2=addParkingLot(3);
        superParkingBoy =new SuperParkingBoy(parkingLotList);

        Car car1=new Car("1111");
        ParkingToken token=new ParkingToken(car1);

        superParkingBoy.pickup(token);
    }


    @Test(expected = ParkingLotFullException.class)
    public void should_fail_to_park_when_all_parking_lots_are_full() {
        parkingLotList=new ArrayList<ParkingLot>();
        ParkingLot lot1=addParkingLot(1);
        ParkingLot lot2=addParkingLot(2);
        superParkingBoy =new SuperParkingBoy(parkingLotList);

        Car car1=new Car("1111");
        Car car2=new Car("2222");
        Car car3=new Car("3333");

        superParkingBoy.park(car1);
        superParkingBoy.park(car2);
        superParkingBoy.park(car3);

        Car car4=new Car("4444");
        superParkingBoy.park(car4);
    }

}
