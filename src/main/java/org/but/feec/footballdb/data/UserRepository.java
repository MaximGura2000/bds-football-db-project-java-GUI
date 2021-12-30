package org.but.feec.footballdb.data;

import org.but.feec.footballdb.api.*;
import org.but.feec.footballdb.config.DataSourceConfig;
import org.but.feec.footballdb.exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public UserAuthView findUserByEmail(String email) {

        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT email, password" +
                             " FROM public.user u" +
                             " WHERE u.email = ?;")
        ) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToUserAuth(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find person by ID with addresses failed.", e);
        }
        return null;
    }

    public UserDetailView findPersonDetailedView(Long user_id) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT u.user_id, email, firstname, surname, username, city, house_number, street" +
                             "FROM public.user u" +
                             "LEFT JOIN public.user_has_address h ON u.user_id = h.user_id" +
                             "LEFT JOIN public.address a ON h.address_id = a.address_id" +
                             "WHERE u.user_id = ?;")
        ) {
            preparedStatement.setLong(1, user_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToUserDetailView(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find person by ID with addresses failed.", e);
        }
        return null;
    }

    public List<UserBasicView> getUserBasicView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT u.user_id, email, firstname, surname, username, city" +
                             " FROM public.user u" +
                             " LEFT JOIN user_has_address h on u.user_id = h.user_id" +
                             " LEFT JOIN public.address a ON h.address_id = a.address_id;");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            List<UserBasicView> userBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                userBasicViews.add(mapToUserBasicView(resultSet));
            }
            return userBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("Persons basic view could not be loaded.", e);
        }
    }

    public void createUser(UserCreateView userCreateView) {
        //String insertUserSQL = "INSERT INTO public.user (email, firstname, username, surname, password) VALUES (?,?,?,?,?)";
        System.out.println("Check");
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO" +
                     "public.user (email, firstname, username, surname, password) VALUES ('?','?','?','?','?');"))
        {

            preparedStatement.setString(1, userCreateView.getEmail());
            preparedStatement.setString(2, userCreateView.getFirstname());
            preparedStatement.setString(3, userCreateView.getUsername());
            preparedStatement.setString(4, userCreateView.getSurname());
            preparedStatement.setString(5, String.valueOf(userCreateView.getPassword()));
            System.out.println("Check2");

            System.out.println(preparedStatement);
            preparedStatement.execute();

            //int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Check3");
            int a=2;

            if (a==1) {
                throw new DataAccessException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }

    private UserAuthView mapToUserAuth(ResultSet rs) throws SQLException {
        UserAuthView person = new UserAuthView();
        person.setEmail(rs.getString("email"));
        person.setPassword(rs.getString("password"));
        return person;
    }

    private UserBasicView mapToUserBasicView(ResultSet rs) throws SQLException {
        UserBasicView userBasicView = new UserBasicView();

        userBasicView.setId(rs.getLong("user_id"));
        userBasicView.setEmail(rs.getString("email"));
        userBasicView.setGivenName(rs.getString("firstname"));
        userBasicView.setSurname(rs.getString("surname"));
        userBasicView.setUsername(rs.getString("username"));
        userBasicView.setCity(rs.getString("city"));
        return userBasicView;
    }

    private UserDetailView mapToUserDetailView(ResultSet rs) throws SQLException {
        UserDetailView userDetailView = new UserDetailView();
        userDetailView.setUserId(rs.getLong("user_id"));
        userDetailView.setEmail(rs.getString("email"));
        userDetailView.setFirstname(rs.getString("firstname"));
        userDetailView.setSurname(rs.getString("surname"));
        userDetailView.setUsername(rs.getString("nickname"));
        userDetailView.setCity(rs.getString("city"));
        userDetailView.sethouseNumber(rs.getString("house_number"));
        userDetailView.setStreet(rs.getString("street"));
        return userDetailView;
    }

    public boolean UserDelete (String email)
    {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM public.user u" +
                             " WHERE u.email = ?;")
        ) {
            preparedStatement.setString(1, email);
            int deleteTry = preparedStatement.executeUpdate();
            if (deleteTry == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            throw new DataAccessException("Find user by ID with addresses failed.", e);
        }
    }
}
