import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int totalLots;
    private int availableLot;
    List<String> carParked;

    ParkingLot(int totallots){
        this.totalLots = totallots;
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
            availableLot++;
            return true;
        }

        return false;
    }

    public int getAvailableLot() {
        return this.availableLot;
    }
}
