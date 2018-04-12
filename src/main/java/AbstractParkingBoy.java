import ParkingException.CarAlreadyParkedException;

import java.util.List;

public abstract class AbstractParkingBoy {
    protected List<ParkingLot> parkingLotList;


    protected abstract int FindParkingLot();


    public AbstractParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList= parkingLotList;
    }


    public ParkingToken park(Car car) {
        if (isCarParked(car)){
            throw new CarAlreadyParkedException();
        }
        return parkingLotList.get(FindParkingLot()).park(car);
    }


    private boolean isCarParked(Car car) {
        for (int i=0;i<parkingLotList.size();i++){
            if (parkingLotList.get(i).isCarParked(car)){
                return true;
            }
        }

        return false;
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
