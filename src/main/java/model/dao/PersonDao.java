package model.dao;

import com.mysql.cj.util.StringUtils;
import model.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonDao {
    String query;

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/phone_book", "root", null);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Person> search(Person person) {
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        if (StringUtils.isNullOrEmpty(firstName) && StringUtils.isNullOrEmpty(lastName))
            return null;
        else {
            setQuery(firstName, lastName);
        }
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Person> resultPersons = new ArrayList<>();
            setResultPersons(resultSet, resultPersons);
            statement.close();
            connection.close();
            return resultPersons;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setResultPersons(ResultSet resultSet, List<Person> resultPersons) throws SQLException {
        while (resultSet.next()) {
            Person findPerson = new Person();
            findPerson.setFirstName(resultSet.getString(2));
            findPerson.setLastName(resultSet.getString(3));
            findPerson.setPhoneNumber(resultSet.getString(4));
            resultPersons.add(findPerson);
        }
    }


    private void setQuery(String firstName, String lastName) {
        query = "select * from person";
        if (!StringUtils.isNullOrEmpty(firstName)) {
            query = query + " as p where p.firstName like '" + firstName + "'";
            if (!StringUtils.isNullOrEmpty(lastName)) {
                query = query + " and p.lastName like '" + lastName + "'";
            }
        }
        else if (!StringUtils.isNullOrEmpty(lastName)) {
            query = query + " as p where p.lastName like '" + lastName +"'";
        }
    }
}
