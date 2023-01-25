import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

/**
 * This example demonstrates using the RiotApi to request summoner information for a given summoner name
 */
public class game
 {

	public static void main(String[] args) throws RiotApiException {
		ApiConfig config = new ApiConfig().setKey("RGAPI-108141b5-f570-4278-ba25-495a73674783");
		RiotApi api = new RiotApi(config);

		Summoner summoner = api.getSummonerByName(Platform.NA, "8szymon27");
		System.out.println("Name: " + summoner.getName());
		System.out.println("Summoner ID: " + summoner.getId());
		System.out.println("Account ID: " + summoner.getAccountId());
		System.out.println("PUUID: " + summoner.getPuuid());
		System.out.println("Summoner Level: " + summoner.getSummonerLevel());
		System.out.println("Profile Icon ID: " + summoner.getProfileIconId());
	}
}