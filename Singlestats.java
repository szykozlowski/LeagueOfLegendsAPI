import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.match.Team;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class Singlestats {
    public static void main(String[] args) {
        Orianna.setRiotAPIKey("RGAPI-108141b5-f570-4278-ba25-495a73674783");
        Orianna.setDefaultPlatform(Platform.NORTH_AMERICA);

        Summoner summoner = Summoner.named("Shinigpiece").get();
        MatchHistory matches = summoner.matchHistory().get();
        Match lastMatch = matches.get(0);

        Participant Shinigpiece = lastMatch.getParticipants().find(Summoner.named("Shinigpiece").get());
        
        int kills = 0;
        int deaths = 0;
        int assists = 0;
        kills += Shinigpiece.getStats().getKills();
        System.out.println(kills);
        System.out.println(Shinigpiece.getChampion().getName());

    }
}