package sample;

import javafx.beans.property.StringProperty ;
import javafx.beans.property.SimpleStringProperty ;

public class Person {
    StringProperty ad,soyad,login_mail,personID;

    public Person(String ad, String soyad, String login_mail,String personID) {
        this.ad = new SimpleStringProperty(ad);
        this.soyad = new SimpleStringProperty(soyad);
        this.login_mail = new SimpleStringProperty(login_mail);
        this.personID = new SimpleStringProperty(personID);
    }

    public Person(String ad, String soyad, String login_mail) {
        this.ad = new SimpleStringProperty(ad);
        this.soyad = new SimpleStringProperty(soyad);
        this.login_mail = new SimpleStringProperty(login_mail);
        personID=new SimpleStringProperty("N/A");
    }

    public String getAd() {
        return ad.get();
    }

    public StringProperty adProperty() {
        return ad;
    }

    public String getSoyad() {
        return soyad.get();
    }

    public StringProperty soyadProperty() {
        return soyad;
    }

    public String getLogin_mail() {
        return login_mail.get();
    }

    public StringProperty login_mailProperty() {
        return login_mail;
    }

    public String getPersonID() {
        return personID.get();
    }

    public StringProperty personIDProperty() {
        return personID;
    }
}