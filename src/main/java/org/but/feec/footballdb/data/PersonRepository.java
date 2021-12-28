package org.but.feec.footballdb.data;

import org.but.feec.footballdb.api.*;
import org.but.feec.footballdb.config.DataSourceConfig;
import org.but.feec.footballdb.exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    public PersonAuthView findPersonByEmail(String email) {

        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT email, password" +
                             " FROM public.user u" +
                             " WHERE u.email = ?;")
        ) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToPersonAuth(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find person by ID with addresses failed.", e);
        }
        return null;
    }

    public PersonDetailView findPersonDetailedView(Long personId) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT u.user_id, email, firstname, surname, username, city, house_number, street" +
                             "FROM public.user u" +
                             "LEFT JOIN public.user_has_address h ON u.user_id = h.user_id" +
                             "LEFT JOIN public.address a ON h.address_id = a.address_id" +
                             "WHERE u.user_id = ?;")
        ) {
            preparedStatement.setLong(1, personId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToPersonDetailView(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find person by ID with addresses failed.", e);
        }
        return null;
    }

    public List<PersonBasicView> getPersonsBasicView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT u.user_id, email, firstname, surname, username, city" +
                             " FROM public.user u" +
                             " LEFT JOIN user_has_address h on u.user_id = h.user_id" +
                             " LEFT JOIN public.address a ON h.address_id = a.address_id;");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            List<PersonBasicView> personBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                personBasicViews.add(mapToPersonBasicView(resultSet));
            }
            return personBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("Persons basic view could not be loaded.", e);//tut1
        }
    }

    private PersonAuthView mapToPersonAuth(ResultSet rs) throws SQLException {
        PersonAuthView person = new PersonAuthView();
        person.setEmail(rs.getString("email"));
        person.setPassword(rs.getString("password"));
        return person;
    }

    private PersonBasicView mapToPersonBasicView(ResultSet rs) throws SQLException {
        PersonBasicView personBasicView = new PersonBasicView();
        personBasicView.setId(rs.getLong("user_id"));
        personBasicView.setEmail(rs.getString("email"));
        personBasicView.setGivenName(rs.getString("firstname"));
        personBasicView.setSurname(rs.getString("surname"));
        personBasicView.setUsername(rs.getString("username"));
        personBasicView.setCity(rs.getString("city"));
        return personBasicView;
    }

    private PersonDetailView mapToPersonDetailView(ResultSet rs) throws SQLException {
        PersonDetailView personDetailView = new PersonDetailView();
        personDetailView.setUserId(rs.getLong("id_person"));
        personDetailView.setEmail(rs.getString("email"));
        personDetailView.setFirstname(rs.getString("first_name"));
        personDetailView.setSurname(rs.getString("surname"));
        personDetailView.setUsername(rs.getString("nickname"));
        personDetailView.setCity(rs.getString("city"));
        personDetailView.sethouseNumber(rs.getString("house_number"));
        personDetailView.setStreet(rs.getString("street"));
        return personDetailView;
    }
}
