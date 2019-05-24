package task.rest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Path;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import task.data.Player;

@Path("players")
public class PlayersREST {

    private static final Log log = LogFactory.getLog(PlayersREST.class);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss.S");
    private final SimpleDateFormat seconds = new SimpleDateFormat("ss");

    public PlayersREST() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getALL(@Context HttpServletRequest request) throws ParseException {

        List<Player> plList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\kstei\\Desktop\\Athletes\\Athlete_Results.csv"))) {
            String line;
            Scanner scanner;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                Player pl = new Player();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    switch (index) {
                        case 0:
                            pl.setName(data);
                            break;
                        case 1:
                            pl.setFencing(Integer.parseInt(data));
                            break;
                        case 2:
                            pl.setSwimming(data);
                            break;
                        case 3:
                            pl.setKnockingDown(Integer.parseInt(data));
                            break;
                        case 4:
                            pl.setRefusal(Integer.parseInt(data));
                            break;
                        case 5:
                            pl.setDisobedienceLeading(Integer.parseInt(data));
                            break;
                        case 6:
                            pl.setShooting(Integer.parseInt(data));
                            break;
                        case 7:
                            pl.setRun(data);
                            break;
                        default:
                            log.warn("invalid data:" + data);
                            break;
                    }
                    index++;
                }
                index = 0;
                Integer rezultatas = rezultatoSkaiciavimas(pl.getKnockingDown(), pl.getRefusal(), pl.getDisobedienceLeading(), pl.getFencing(), pl.getShooting(), pl.getSwimming());

                pl.setRezultatas(rezultatas);
                plList.add(pl);

                concludingEvent(plList);
            }

        } catch (FileNotFoundException ex) {
            log.error("File not found", ex);
        } catch (IOException ex) {
            log.error("Error reading file", ex);
        }
        return plList;
    }

    private Integer rezultatoSkaiciavimas(Integer knockingDown, Integer refusal, Integer disobedienceLeading, Integer fencing, Integer shooting, String swimming) throws ParseException {
        Integer rezultatas = null;
        Integer ridingRezult = riding(knockingDown, refusal, disobedienceLeading);
        Integer fencingRezult = fencing(fencing);
        Integer swimmingRezult = swimming(swimming);
        Integer shootingRezult = shooting(shooting);

        rezultatas = ridingRezult + fencingRezult + swimmingRezult + shootingRezult;
        return rezultatas;
    }

    private Integer riding(Integer knockingDown, Integer refusal, Integer disobedienceLeading) {
        Integer ridingRezult = null;
        Integer faultFree = 1200;
        Integer knocks = 28;
        Integer refusals = 40;
        Integer disobidiences = 60;

        if (knockingDown != null && refusal != null && disobedienceLeading != null) {

            ridingRezult = faultFree - (knocks * knockingDown + refusals * refusal + disobidiences * disobedienceLeading);
        }
        return ridingRezult;
    }

    private Integer fencing(Integer fencing) {
        Integer defaultScore = 1000;
        int players = 10;
        Integer fencingRezult = null;
        Integer point = 40;

        int percent = (int) ((players - 1) * 0.7);

        if (fencing > percent) {
            fencingRezult = defaultScore + (point * (fencing - percent));
        } else {
            fencingRezult = defaultScore - (point * (percent - fencing));
        }

        return fencingRezult;
    }

    private Integer swimming(String swimming) throws ParseException {
        Integer defaultScore = 1000;
        Date defultTime = dateFormat.parse("2:30.0");
        Integer point = 4;
        Integer swimmingRezult = null;

        Date swimm = dateFormat.parse(swimming);

        if (defultTime.getTime() > swimm.getTime()) {
            Long finishTime = TimeUnit.MILLISECONDS.toSeconds((swimm.getTime() - defultTime.getTime()));
            Integer finishTimeInt = finishTime.intValue();
            swimmingRezult = defaultScore - ((finishTimeInt / 3) * point);

        } else if (defultTime.getTime() < swimm.getTime()) {
            Long finishTime = TimeUnit.MILLISECONDS.toSeconds((defultTime.getTime() - swimm.getTime()));
            Integer finishTimeInt = finishTime.intValue();
            swimmingRezult = defaultScore + ((finishTimeInt / 3) * point);

        } else {
            swimmingRezult = defaultScore;
        }

        return swimmingRezult;

    }

    public Integer shooting(Integer shooting) {
        Integer target = 172;
        Integer targetPoint = 1000;
        Integer point = 12;
        Integer shootingRezult = null;

        if (shooting != null) {
            if (shooting > target) {
                shootingRezult = (shooting - target) * point + targetPoint;
            } else if (shooting < target) {
                shootingRezult = targetPoint - (target - shooting) * point;
            } else {
                shootingRezult = targetPoint;
            }
        }

        return shootingRezult;
    }

    private List<Player> concludingEvent(List<Player> plList) throws ParseException {
        Collections.sort(plList);

        Collections.reverse(plList);

        Integer atsilikimas;
        Integer zeroTime = plList.get(0).getRezultatas();

        for (Player player : plList) {
            atsilikimas = (zeroTime - player.getRezultatas()) / 3;

            player.setAtsilikimas(-atsilikimas);

            String conv = Integer.toString(atsilikimas);
            Date con = seconds.parse(conv);
            Date runn = dateFormat.parse(player.getRun());

            Long galutinisRezult = runn.getTime() + con.getTime();
            String galutinisRezultFinal = dateFormat.format(new Date(galutinisRezult));

            player.setFinisoRezultatas(galutinisRezultFinal);
        }
        return plList;
    }

}
