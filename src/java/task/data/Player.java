package task.data;

public class Player implements Comparable<Player> {

    private String name;

    private String swimming;
    private Integer knockingDown;
    private Integer refusal;
    private Integer disobedienceLeading;
    private Integer shooting;
    private String run;
    private Integer fencing;
    private Integer rezultatas;
    private Integer atsilikimas;
    private String finisoRezultatas;

    public Player(String name, Integer fencing, String swimming, Integer knockingDown, Integer refusal, Integer disobedienceLeading, Integer shooting, 
            String run, Integer rezultatas, Integer atsilikimas, String finisoRezultatas) {
        this.name = name;
        this.fencing = fencing;
        this.swimming = swimming;
        this.knockingDown = knockingDown;
        this.refusal = refusal;
        this.disobedienceLeading = disobedienceLeading;
        this.shooting = shooting;
        this.run = run;
        this.rezultatas = rezultatas;
        this.atsilikimas = atsilikimas;
        this.finisoRezultatas = finisoRezultatas;
    }

    public Player() {
    }

 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwimming() {
        return swimming;
    }

    public void setSwimming(String swimming) {
        this.swimming = swimming;
    }

    public Integer getKnockingDown() {
        return knockingDown;
    }

    public void setKnockingDown(Integer knockingDown) {
        this.knockingDown = knockingDown;
    }

    public Integer getRefusal() {
        return refusal;
    }

    public void setRefusal(Integer refusal) {
        this.refusal = refusal;
    }

    public Integer getDisobedienceLeading() {
        return disobedienceLeading;
    }

    public void setDisobedienceLeading(Integer disobedienceLeading) {
        this.disobedienceLeading = disobedienceLeading;
    }

    public Integer getShooting() {
        return shooting;
    }

    public void setShooting(Integer shooting) {
        this.shooting = shooting;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public Integer getFencing() {
        return fencing;
    }

    public void setFencing(Integer fencing) {
        this.fencing = fencing;
    }

    public Integer getRezultatas() {
        return rezultatas;
    }

    public void setRezultatas(Integer rezultatas) {
        this.rezultatas = rezultatas;
    }

    public Integer getAtsilikimas() {
        return atsilikimas;
    }

    public void setAtsilikimas(Integer atsilikimas) {
        this.atsilikimas = atsilikimas;
    }

    public String getFinisoRezultatas() {
        return finisoRezultatas;
    }

    public void setFinisoRezultatas(String finisoRezultatas) {
        this.finisoRezultatas = finisoRezultatas;
    }

    @Override
    public int compareTo(Player u) {
        if (getRezultatas() == null || u.getRezultatas() == null) {
            return 0;
        }
        return getRezultatas().compareTo(u.getRezultatas());
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", swimming=" + swimming + ", knockingDown=" + knockingDown + ", refusal=" + refusal + ", disobedienceLeading=" + disobedienceLeading + ", shooting=" + shooting + ", run=" + run + ", fencing=" + fencing + ", rezultatas=" + rezultatas + ", atsilikimas=" + atsilikimas + ", finisoRezultatas=" + finisoRezultatas + '}';
    }

}
