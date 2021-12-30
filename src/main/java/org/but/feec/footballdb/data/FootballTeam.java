package org.but.feec.footballdb.data;

import org.but.feec.footballdb.api.*;
import org.but.feec.footballdb.config.DataSourceConfig;
import org.but.feec.footballdb.exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FootballTeam {

    public List<FootballTeamView> getTeamView()
    {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT team_name, number_of_trophy" +
                             " FROM public.football_team;");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            List<FootballTeamView> footballTeamViews = new ArrayList<>();
            while (resultSet.next()) {
                footballTeamViews.add(mapToFootballTeam(resultSet));
            }
            return footballTeamViews;
        } catch (SQLException e) {
            throw new DataAccessException("Persons basic view could not be loaded.", e);
        }
    }

    private FootballTeamView mapToFootballTeam(ResultSet rs) throws SQLException {
        FootballTeamView footballTeamView = new FootballTeamView();

        footballTeamView.setTeamName(rs.getString("team_name"));
        footballTeamView.setTrophyNumber(rs.getLong("number_of_trophy"));
        return footballTeamView;
    }
}
