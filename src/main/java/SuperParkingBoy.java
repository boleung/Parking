import java.util.List;

public class SuperParkingBoy extends AbstractParkingBoy{
    public SuperParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    protected int FindParkingLot() {
        int i, maxIndex=0;
        double maxAvailableRate=0.0;

        for (i=0;i<parkingLotList.size();i++) {
            if (getAvailableRate(i) > maxAvailableRate) {
                maxAvailableRate=getAvailableRate(i);
                maxIndex=i;
            }
        }

        return maxIndex;
    }

    private double getAvailableRate(int i) {
        return (double) parkingLotList.get(i).getAvailableLot() / (double) parkingLotList.get(i).getSize();
    }
}
