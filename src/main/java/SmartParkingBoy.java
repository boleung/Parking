import java.util.List;

public class SmartParkingBoy extends AbstractParkingBoy{
    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    protected int FindParkingLot() {
        int maxAvailableParkingLotIndex=0, maxAvailableLotCount=0, currentLotAvailableCount;

        for (int i=0;i<parkingLotList.size();i++){
            currentLotAvailableCount= parkingLotList.get(i).getAvailableLot();

            if (maxAvailableLotCount<currentLotAvailableCount){
                maxAvailableLotCount=currentLotAvailableCount;
                maxAvailableParkingLotIndex=i;
            }
        }
        return maxAvailableParkingLotIndex;
    }
}
