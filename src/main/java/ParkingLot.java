import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int totallots;
    private int availableLot;
    List<String> carParked;

    ParkingLot(int totallots){
        this.totallots = totallots;
        this.availableLot = totallots;
        carParked = new ArrayList<String>();
    }


    public boolean park(String plateNumber) {
        if (availableLot > 0 && !carParked.contains(plateNumber)) {
           availableLot--;
           carParked.add(plateNumber);
           return true;
            }
        return false;
    }

    public boolean pickup(String plateNumber) {
        if (carParked.contains(plateNumber)) {
            carParked.remove(plateNumber);
            return true;
        }

        return false;
    }

    public int getAvailableLot() {
        return this.availableLot;
    }
}
