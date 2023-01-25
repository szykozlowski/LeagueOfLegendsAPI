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
public class matchHistory {
    int matchnum = 0;
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize(1000,1100);
        StdDraw.setScale(0,1100);

        System.out.println("Enter a name");
        String summonerName = input.nextLine();


        System.out.println("ALL or RANKED");
        String gameType = input.nextLine();

        Orianna.setRiotAPIKey("RGAPI-d6fb1eaf-346c-4260-ac64-28d68bef49b1");
        Orianna.setDefaultPlatform(Platform.NORTH_AMERICA);

        double kills = 0;
        double deaths = 0;
        double assists = 0;
        int cs = 0;
        double gamecount = 0;
        int matchSearch = 0;
        int gameControl = 0;
        double x = 0;
        double y = 0;
        int rectWidth = 1100;
        int rectHeight = 200;

        Summoner summoner = Summoner.named(summonerName).get();
        MatchHistory matches = summoner.matchHistory().get();
        
        final LeagueEntry rankedFivesEntries = summoner.getLeaguePosition(Queue.RANKED_SOLO);

        if(gameType.equals("ALL")){
            for(int i = 0;i<6;i++){
                Match lastMatch = matches.get(i);
                Participant username = lastMatch.getParticipants().find(Summoner.named(summonerName).get());
                System.out.println(lastMatch.getQueue().getId());
                System.out.println(lastMatch.getQueue());
                System.out.println(username.getChampion().getName());
                System.out.println(username.getStats().getKills() + "/" + username.getStats().getDeaths() + "/" + username.getStats().getAssists());
                System.out.println(username.getStats().getCreepScore() + " CS");
                System.out.println("");
                if(!(lastMatch.isRemake())){
                    StdDraw.setPenColor(153,255,255);
                    //StdDraw.save(username.getChampion().getImage().getFull());
                    //StdDraw.picture(x +(rectWidth/6),y+(rectHeight/2),username.getChampion().getImage().getFull());
                    StdDraw.filledRectangle(x +(rectWidth/2),y+(rectHeight/2),rectWidth/2,rectHeight/2);
                    StdDraw.setPenColor(0,0,0);
                    StdDraw.text(x +(rectWidth/4),y+(rectHeight/2),username.getChampion().getName());
                    StdDraw.text(x +(rectWidth/2),y+(rectHeight/2),String.valueOf(username.getStats().getKills() + "/" + (username.getStats().getDeaths() + "/" + username.getStats().getAssists())));
                    System.out.println(y);
                    System.out.println();
                    kills += (username.getStats().getKills());
                    deaths += (username.getStats().getDeaths());
                    assists += (username.getStats().getAssists());
                    cs += (username.getStats().getCreepScore());
                    gamecount+= 1.0;
                    y += 220;
                }
            }
        }

        if(gameType.equals("RANKED")){
            while(matchSearch<5 - 1 && gameControl < 1000){
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

    }
}