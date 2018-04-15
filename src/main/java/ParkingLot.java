
import ParkingException.*;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int size;
    private Map<ParkingToken, Car> cars;

    ParkingLot(int totallots){
        this.size = totallots;
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
        return size == cars.size();
    }

    public Car pickup(ParkingToken token) {
        if (! isTokenValid(token)) {
            throw new CarNotParkedException();
        }
        return cars.remove(token);
    }

    public int getAvailableLot() {
        return size - cars.size();
    }

    public boolean isTokenValid(ParkingToken token){
        return cars.containsKey(token);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
