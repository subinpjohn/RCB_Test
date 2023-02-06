package com.qa.rcb;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

public class Test {
	
@org.testng.annotations.Test
  public void  rcbTest(){
    JSONParser parser = new JSONParser();
    int nonIndianPlayersCount = 0;
    boolean isWicketKeeperPresent=false;

    try {
      Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"/src/test/resources/TeamRCB.json"));
      JSONObject jsonObject = (JSONObject) obj;
      JSONArray playersArray = (JSONArray) jsonObject.get("player");

      //To take the count foreign players
      for (Object player : playersArray) {
        JSONObject playerObject = (JSONObject) player;
        String country = (String) playerObject.get("country");
        if (!country.equals("India")) {
          nonIndianPlayersCount++;
        }
      }
      
      Assert.assertEquals(nonIndianPlayersCount, 4);
      
      
      //To check wicket-keeper is present
      for (Object player : playersArray) {
        JSONObject playerObject = (JSONObject) player;
        String role = (String) playerObject.get("role");
        if (role.equals("Wicket-keeper")) {
        	isWicketKeeperPresent=true;
        	break;

        }
      }
      
      Assert.assertTrue(isWicketKeeperPresent);
      
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }
}
