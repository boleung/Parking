import ParkingException.CarAlreadyParkedException;
import ParkingException.ParkingLotFullException;

import java.util.List;

public class ParkingBoy extends AbstractParkingBoy{

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    protected int FindParkingLot() {
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


}
