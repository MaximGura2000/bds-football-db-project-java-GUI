package org.but.feec.footballdb.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FootballTeamView {
    private StringProperty teamName = new SimpleStringProperty();
    private LongProperty trophy = new SimpleLongProperty();

    public String getTeamName()
    {
        return teamNameProperty().get();
    }

    public void setTeamName (String team_name)
    {
        this.teamNameProperty().setValue(team_name);
    }

    public Long getTrophyNumber()
    {
        return trophyProperty().get();
    }

    public void setTrophyNumber(Long number_of_trophy)
    {
        this.trophyProperty().setValue(number_of_trophy);
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public LongProperty trophyProperty() {
        return trophy;
    }
}
