import ParkingException.CarAlreadyParkedException;
import ParkingException.ParkingLotFullException;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public ParkingBoy() {
    }

    public void addManagedParkingLot(List<ParkingLot> parkingLotList) {
        this.parkingLotList=parkingLotList;
    }

    public ParkingToken park(Car car) {
        if (isCarParked(car)){
            throw new CarAlreadyParkedException();
        }
        return parkingLotList.get(getAvailalbeParkingLot()).park(car);
    }

    private boolean isCarParked(Car car) {
        for (int i=0;i<parkingLotList.size()-1;i++){
            if (parkingLotList.get(i).isCarParked(car)){
                return true;
            }
        }

        return false;
    }

    private int getAvailalbeParkingLot() {
        int i;
        for (i=0;i<parkingLotList.size()-1;i++) {
            if (parkingLotList.get(i).getAvailableLot()>0) {
                break;
            }
        }

        if(i==parkingLotList.size()){
            throw new ParkingLotFullException();
        }
        return i;
    }

    public int getAvailableLotAmt(int parkingLotIndex) {
        return parkingLotList.get(parkingLotIndex).getAvailableLot();
    }

    public Car pickup(ParkingToken token) {
        return parkingLotList.get(getParkingLotIndex(token)).pickup(token);
    }

    private int getParkingLotIndex(ParkingToken token) {
        int i;
        for (i=0;i<parkingLotList.size()-1;i++){
            if (parkingLotList.get(i).isTokenValid(token)){
                break;
            }
        }
        return i;
    }
}
