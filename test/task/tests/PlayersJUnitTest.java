/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.tests;


import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import task.rest.PlayersREST;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.junit.Test;
import static org.junit.Assert.*;
import task.data.Player;

public class PlayersJUnitTest {

    public PlayersJUnitTest() {
    }

    @Test
    public void TestPlayer() {

        String name = "jonas";
        String swimming = "60";
        Integer knockingDown = 12;
        Integer refusal = 45;
        Integer disobedienceLeading = 45;
        Integer shooting = 45;
        String run = "2:30";
        Integer fencing = 12;
        Integer rezultatas = 45;
        Integer atsilikimas = 56;
        String finisoRezultatas = "10:45";

        Player player = new Player(name, fencing, swimming, knockingDown, refusal, disobedienceLeading, shooting,
                run, rezultatas, atsilikimas, finisoRezultatas);

        Assert.assertTrue(player.getName().equals(name));
        Assert.assertTrue(player.getRun().equals(run));
        Assert.assertTrue(player.getKnockingDown().equals(knockingDown));
        Assert.assertTrue(player.getRefusal().equals(refusal));
        Assert.assertTrue(player.getDisobedienceLeading().equals(disobedienceLeading));
        Assert.assertTrue(player.getShooting().equals(shooting));
        Assert.assertTrue(player.getFencing().equals(fencing));
        Assert.assertTrue(player.getRezultatas().equals(rezultatas));
        Assert.assertTrue(player.getAtsilikimas().equals(atsilikimas));
        Assert.assertTrue(player.getFinisoRezultatas().equals(finisoRezultatas));
    }

    @Test
    public void testGetALL() throws Exception {
        System.out.println("getALL");
        HttpServletRequest request = null;
        PlayersREST instance = new PlayersREST();
        List<Player> expResult = null;
        List<Player> result = instance.getALL(request);
        assertEquals(expResult, result);

    }


    @Test
    public void testShooting() {
        System.out.println("shooting");
        Integer shooting = null;
        PlayersREST instance = new PlayersREST();
        Integer expResult = null;
        Integer result = instance.shooting(shooting);
        assertEquals(expResult, result);
    
    }

}
