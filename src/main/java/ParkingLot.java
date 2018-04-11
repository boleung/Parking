
import ParkingException.*;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int totalLots;
    private Map<ParkingToken, Car> cars;

    ParkingLot(int totallots){
        this.totalLots = totallots;
        cars = new HashMap<ParkingToken,Car>();
    }

    public ParkingToken park(Car car)  {
        if (isParkingLotFull()) {
            throw new ParkingLotFullException();
        }
        if (isCarParked(car)) {
            throw new CarAlreadyParkedException();
            }

        ParkingToken token = new ParkingToken(car);
        cars.put(token,car);
        return token;
    }

    public boolean isCarParked(Car car) {
        return cars.containsValue(car);
    }

    private boolean isParkingLotFull() {
        return totalLots == cars.size();
    }

    public Car pickup(ParkingToken token) {
        if (! isTokenValid(token)) {
            throw new CarNotParkedException();
        }
        return cars.remove(token);
    }

    public int getAvailableLot() {
        return totalLots - cars.size();
    }

    public boolean isTokenValid(ParkingToken token){
        return cars.containsKey(token);
    }
}
