import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.match.Team;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.searchable.AbstractSearchableObject;
import java.util.Scanner;
public class Matches20 {
    int matchnum = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a name");
        String summonerName = input.nextLine();

        System.out.println("How many games?");
        int howManyGame = input.nextInt();

        String placehold = input.nextLine();

        System.out.println("ALL or RANKED");
        String gameType = input.nextLine();

        Orianna.setRiotAPIKey("RGAPI-ef5d0b49-ee9a-4c90-a3c8-c88a00977c86");
        Orianna.setDefaultPlatform(Platform.NORTH_AMERICA);

        double kills = 0;
        double deaths = 0;
        double assists = 0;
        int cs = 0;
        double gamecount = 0;
        int matchSearch = 0;
        int gameControl = 0;

        Summoner summoner = Summoner.named(summonerName).get();
        MatchHistory matches = summoner.matchHistory().get();
        
        final LeagueEntry rankedFivesEntries = summoner.getLeaguePosition(Queue.RANKED_SOLO);

        if(gameType.equals("ALL")){
            for(int i = 0;i<howManyGame;i++){
                Match lastMatch = matches.get(i);
                Participant username = lastMatch.getParticipants().find(Summoner.named(summonerName).get());
                System.out.println(lastMatch.getQueue().getId());
                System.out.println(lastMatch.getQueue());
                System.out.println(username.getChampion().getName());
                System.out.println(username.getStats().getKills() + "/" + username.getStats().getDeaths() + "/" + username.getStats().getAssists());
                System.out.println(username.getStats().getCreepScore() + " CS");
                System.out.println("");
                if(!(lastMatch.isRemake())){
                    kills += (username.getStats().getKills());
                    deaths += (username.getStats().getDeaths());
                    assists += (username.getStats().getAssists());
                    cs += (username.getStats().getCreepScore());
                    gamecount+= 1.0;
                }
            }
        }

        if(gameType.equals("RANKED")){
            while(matchSearch<howManyGame - 1 && gameControl < 1000){
                Match lastMatch = matches.get(gameControl);
                Participant username = lastMatch.getParticipants().find(Summoner.named(summonerName).get());
                if((lastMatch.getQueue().getId() == 420)){
                    System.out.println(username.getChampion().getName());
                    System.out.println(username.getStats().getKills() + "/" + username.getStats().getDeaths() + "/" + username.getStats().getAssists());
                    System.out.println(username.getStats().getCreepScore() + " CS");
                    System.out.println("");
                    if(!(lastMatch.isRemake())){
                        kills += (username.getStats().getKills());
                        deaths += (username.getStats().getDeaths());
                        assists += (username.getStats().getAssists());
                        cs += (username.getStats().getCreepScore());
                        gamecount+= 1.0;
                        matchSearch += 1;                       
                    }
                    gameControl += 1;
                }
                else{
                    gameControl += 1;
                    continue;
                }
            }
        }

        System.out.println(gamecount + " Games");
        System.out.println("Wins: " + rankedFivesEntries.getWins());
        System.out.println("Losses: " + rankedFivesEntries.getLosses());
        System.out.println("Average Kills: " + kills/gamecount);
        System.out.println("Average Deaths: " + deaths/gamecount);
        System.out.println("Average Assists: " + assists/gamecount);
        System.out.println("Average Creep Score: " + (double)(cs/gamecount));
        double KDA = ((kills+(assists/3))/deaths);
        System.out.println("TotalKDA: " + KDA);
        if(KDA<1.9){
            System.out.println("Try Normals.");
        }

    }
}