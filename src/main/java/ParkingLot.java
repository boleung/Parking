
import ParkingException.*;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int totalLots;
    Map<ParkingToken, Car> cars;

    ParkingLot(int totallots){
        this.totalLots = totallots;
        cars = new HashMap<ParkingToken,Car>();
    }

    public ParkingToken park(Car car)  {
        if (isParkingLotFull()) {
            throw new ParkingLotFullException();
        }
        if (cars.containsValue(car)) {
            throw new CarAlreadyParkedException();
            }

        ParkingToken token = new ParkingToken(car);
        cars.put(token,car);
        return token;
    }

    private boolean isParkingLotFull() {
        return totalLots == cars.size();
    }

    public Car pickup(ParkingToken token) {
        if (!cars.containsKey(token)) {
            throw new CarNotParkedException();
        }
        return cars.remove(token);
    }

    public int getAvailableLot() {
        return totalLots - cars.size();
    }
}
