package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by enesb on 5.05.2017.
 */
public class Car {

    private StringProperty plaka,sahibiAd,sahibiSoyad,sahipID;

    public Car(String plaka, String sahibiAd, String sahibiSoyad,String sahipID) {
        this.plaka = new SimpleStringProperty(plaka);
        this.sahibiAd = new SimpleStringProperty(sahibiAd);
        this.sahibiSoyad = new SimpleStringProperty(sahibiSoyad);
        this.sahipID = new SimpleStringProperty(sahipID);
    }


    public String getPlaka() {
        return plaka.get();
    }

    public StringProperty plakaProperty() {
        return plaka;
    }

    public String getSahibiAd() {
        return sahibiAd.get();
    }

    public StringProperty sahibiAdProperty() {
        return sahibiAd;
    }

    public String getSahibiSoyad() {
        return sahibiSoyad.get();
    }

    public StringProperty sahibiSoyadProperty() {
        return sahibiSoyad;
    }

    public String getSahipID() {
        return sahipID.get();
    }

    public StringProperty sahipIDProperty() {
        return sahipID;
    }
}
