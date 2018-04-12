import java.util.List;

public abstract class AbstractParkingBoyTest {
    protected List<ParkingLot> parkingLotList;

    protected ParkingLot addParkingLot(int totallots) {
        ParkingLot parkingLot=new ParkingLot(totallots);
        parkingLotList.add(parkingLot);
        return parkingLot;
    }
}
