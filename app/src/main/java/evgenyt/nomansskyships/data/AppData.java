package evgenyt.nomansskyships.data;

import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class AppData {
    private static AppData appData;
    private final List<NMSShip> shipList = new ArrayList<>();

    private AppData(AppCompatActivity activity){
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            InputStream xmlFile = activity.getAssets().open("ships_info.xml");
            Document document = saxBuilder.build(xmlFile);
            List<Element> shipElements = document.getRootElement().getChildren("ship");
            for (Element shipElement : shipElements) {
                NMSShip ship = new NMSShip(shipElement.getAttribute("name").getValue());
                ship.setGalaxy(shipElement.getAttribute("galaxy").getValue());
                shipList.add(ship);
            }
        } catch (JDOMException e) {
            System.out.println("JDOM Error: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        }


    }

    public static AppData getInstance(AppCompatActivity activity) {
        if (appData == null) {
            appData = new AppData(activity);
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
