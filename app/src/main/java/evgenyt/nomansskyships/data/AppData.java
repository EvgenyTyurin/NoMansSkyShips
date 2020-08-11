package evgenyt.nomansskyships.data;

import java.util.ArrayList;
import java.util.List;

public class AppData {
    private static AppData appData;
    private final List<NMSShip> shipList = new ArrayList<>();

    private AppData(){
        NMSShip testShip = new NMSShip("Test ship");
        testShip.setGalaxy("Euclid");
        shipList.add(testShip);
    }

    public static AppData getInstance() {
        if (appData == null) {
            appData = new AppData();
        }
        return appData;
    }

    public List<NMSShip> getShipList() {
        return shipList;
    }

    public NMSShip getShipByName(String name) {
        for (NMSShip ship : shipList) {
            if (ship.getName().equals(name))
                return ship;
        }
        return null;
    }


}
