public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy() {
    }

    @Override
    public ParkingToken park(Car car) {
        int maxParkinglotIndex = getMaxParkingLotIndex();
        return parkingLotList.get(maxParkinglotIndex).park(car);
    }

    private int getMaxParkingLotIndex() {
        int maxAvailableParkingLotIndex=0, maxAvailableLotCount=0, currentLotAvailableCount;

        for (int i=0;i<parkingLotList.size();i++){
            currentLotAvailableCount=getAvailableLotAmt(i);

            if (maxAvailableLotCount<currentLotAvailableCount){
                maxAvailableLotCount=currentLotAvailableCount;
                maxAvailableParkingLotIndex=i;
            }
        }
        return maxAvailableParkingLotIndex;
    }
}
