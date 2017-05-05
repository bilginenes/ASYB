package sample;
import java.sql.*;

import java.util.List ;
import java.util.ArrayList ;

public class DatabaseConnector {

    // in real life, use a connection pool....
    private Connection connection ;

    public DatabaseConnector(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL,user,password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<Person> getPersonList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from employee");
        ){
            List<Person> personList = new ArrayList<>();
            while (rs.next()) {
                String firstName = rs.getString("ad");
                String lastName = rs.getString("soyad");
                String email = rs.getString("login_mail");
                String personID = rs.getString("id");
                Person person = new Person(firstName, lastName, email, personID);
                personList.add(person);
            }
            return personList ;
        }
    }


    public List<Car> getCarList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from araba, employee where employee.id = araba.sahibi;");
        ){
            List<Car> carList = new ArrayList<>();
            while (rs.next()) {
                String plaka = rs.getString("plaka");
                String sahibiAd = rs.getString("ad");
                String sahibiSoyad = rs.getString("soyad");
                String sahipID = rs.getString("id");
                Car car = new Car(plaka, sahibiAd, sahibiSoyad, sahipID);
                carList.add(car);
            }
            return carList ;
        }
    }

    public void insertPerson(Person person) throws SQLException
    {
        PreparedStatement pst;
        String s = "INSERT INTO employee(ad, soyad, login_mail) VALUES(?,?,?);";
        System.out.print(s);
        pst = connection.prepareStatement(s);

        pst.setString(1, person.getAd()); // replace question mark 1 with value of 'name'
        pst.setString(2, person.getSoyad()); // ditto, 2 and 'address'
        pst.setString(3, person.getLogin_mail()); // ditto, 3 and 'phone'
        pst.executeUpdate();
    }

    public Person getPerson(String personID) throws SQLException
    {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from employee where employee.id = "+ personID+";");
        ){
            Person person = null;
            if (rs.next()) {
                String firstName = rs.getString("ad");
                String lastName = rs.getString("soyad");
                String email = rs.getString("login_mail");
                person = new Person(firstName, lastName, email, personID);
            }
            return person ;
        }
    }

    public void insertCar(Car car) throws SQLException
    {
        PreparedStatement pst;
        String s = "INSERT INTO araba(plaka,sahibi) VALUES(?,?);";
        System.out.print(s);
        pst = connection.prepareStatement(s);

        pst.setString(1, car.getPlaka()); // replace question mark 1 with value of 'name'
        pst.setString(2, car.getSahipID()); // ditto, 2 and 'address'
        pst.executeUpdate();
    }

    // other methods, eg. addPerson(...) etc
}