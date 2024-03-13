package jqz.fps.DTO;

import java.util.ArrayList;
import java.util.Arrays;

public class Pokemon {

    /**
     * This class will be used as a DTO (data transfer object) for store
     * the data of the database as objects.
     */

    private int id;
    private byte generation;
    private boolean starter;
    private boolean baby;
    private boolean legendary;
    private boolean mythical;
    private String name;
    private String nameJapanese;
    private ArrayList<String> abilities;
    private ArrayList<String> types;
    private int[] stats;
    private int captureRate;
    private String description;

    public Pokemon(int id, byte generation, boolean starter, boolean baby,
                   boolean legendary, boolean mythical, String name, String nameJapanese,
                   ArrayList<String> abilities, ArrayList<String> types, int[] stats,
                   int captureRate, String description) {
        this.id = id;
        this.generation = generation;
        this.starter = starter;
        this.baby = baby;
        this.legendary = legendary;
        this.mythical = mythical;
        this.name = name;
        this.nameJapanese = nameJapanese;
        this.abilities = abilities;
        this.types = types;
        this.stats = stats;
        this.captureRate = captureRate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public byte getGeneration() {
        return generation;
    }

    public boolean isStarter() {
        return starter;
    }

    public boolean isBaby() {
        return baby;
    }

    public boolean isLegendary() {
        return legendary;
    }

    public boolean isMythical() {
        return mythical;
    }

    public String getName() {
        return name;
    }

    public String getNameJapanese() {
        return nameJapanese;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public int[] getStats() {
        return stats;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", generation=" + generation +
                ", starter=" + starter +
                ", baby=" + baby +
                ", legendary=" + legendary +
                ", mythical=" + mythical +
                ", name='" + name + '\'' +
                ", nameJapanese='" + nameJapanese + '\'' +
                ", abilities=" + abilities +
                ", types=" + types +
                ", stats=" + Arrays.toString(stats) +
                ", captureRate=" + captureRate +
                ", description='" + description + '\'' +
                '}';
    }
}
