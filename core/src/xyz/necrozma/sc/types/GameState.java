package xyz.necrozma.sc.types;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameState {
    private String teamAName;
    private String teamBName;
    private int teamAScore;
    private int teamBScore;
    // TODO: Add more fields as needed

    public GameState() {
        this.teamAName = "Team A";
        this.teamBName = "Team B";
        this.teamAScore = 0;
        this.teamBScore = 0;
    }
}
