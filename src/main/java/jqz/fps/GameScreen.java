package jqz.fps;

import jqz.fps.DAO.DAOPokemon;
import jqz.fps.DTO.Pokemon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameScreen extends JFrame {
    private JPanel jpPrincipal;
    private JLabel jlPokemonIcon;
    private JLabel jlType2;
    private JLabel jlType1;
    private JLabel jlStarter;
    private JLabel jlBaby;
    private JLabel jlLegendary;
    private JLabel jlMythical;
    private JLabel jlPokemonName;
    private JLabel jlPokemonJPName;
    private JLabel jlPs;
    private JLabel jlAta;
    private JLabel jlDef;
    private JLabel jlSpAta;
    private JLabel jlSpDef;
    private JLabel jlVel;
    private JLabel jlCaptureRate;
    private JLabel jlGeneration;
    private JTextArea jtaDescription;
    private JScrollPane jspDescription;
    private JTextArea jtaAbilities;
    private JScrollPane jspAbilities;
    private JButton jbSelect;
    private JTextField jtfPokemonGuess;
    private JLabel jlAttempts;
    private JButton jbReturn;

    Random random = new Random();

    ArrayList<Pokemon> pokemons = DAOPokemon.select_all();

    Pokemon pokemon;

    boolean[] cluesShown = new boolean[]{
            false, false, false,
            false, false, false,
            false, false, false
    };
    byte attempts;

    public GameScreen(){
        start_form();
        start_game(); // The game starts
        jbSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                make_guess();
            }
        });
        jbReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
                dispose();
            }
        });
    }

    private void make_guess(){
        // Verify that the pokemon is in the database to make the guess
        boolean found = false;
        for (Pokemon poke : pokemons) {
            if (poke.getName().equalsIgnoreCase(jtfPokemonGuess.getText())) {
                found = true;
                break;
            }
        }
        if (!found) {
            // If it is not found, the player is notified.
            JOptionPane.showMessageDialog(jpPrincipal, "The pokemon wasn't found\nTry with another name", "Not found", JOptionPane.WARNING_MESSAGE);
        } else {
            if (!jtfPokemonGuess.getText().equalsIgnoreCase(pokemon.getName())) {
                attempts -= 1;
                jlAttempts.setText(String.valueOf(attempts));
                JOptionPane.showMessageDialog(jpPrincipal, "The pokemon wanted is not that one", "Fail", JOptionPane.ERROR_MESSAGE);
                verify_winner();
            } else {
                set_all_data();
                JOptionPane.showMessageDialog(jpPrincipal, "Congratulations, you won the game !", "Winner", JOptionPane.WARNING_MESSAGE);
            }
        }
        jtfPokemonGuess.setText("");
    }

    private void verify_winner(){
        if(attempts == 0){
            JOptionPane.showMessageDialog(jpPrincipal, "Don't have more attempts,\nyou lost the game", "Sorry", JOptionPane.ERROR_MESSAGE);
            set_all_data();
            jbSelect.setEnabled(false);
        } else show_clue();
    }

    private void start_game(){
        // Set random pokemon
        pokemon = pokemons.get(random.nextInt(pokemons.size()));
        // Clear data
        empty_data();
        // Set all clues shown in false
        Arrays.fill(cluesShown, false);
        // Restart attempts
        this.attempts = 8;
        jlAttempts.setText(String.valueOf(attempts));
        show_clue();
    }

    private void show_clue(){
        // So this method will show a new clue
        int randomClue = random.nextInt(9);
        // While the clue selected was already saw, a new one is selected
        while(cluesShown[randomClue]) randomClue = random.nextInt(9);
        switch (randomClue) {
            case 0: set_pokemon_icon(); break;
            case 1: set_pokemon_types_icons(); break;
            case 2: set_pokemon_japanese_name(); break;
            case 3: set_pokemon_generation(); break;
            case 4: set_pokemon_st_bb_leg_myt_icons(); break;
            case 5: set_pokemon_stats(); break;
            case 6: set_pokemon_capture_rate(); break;
            case 7: set_pokemon_abilities(); break;
            case 8: set_pokemon_description(); break;
        }
        // finally the clue now is true
        cluesShown[randomClue] = true;
    }

    private void set_pokemon_icon(){
        // Clue 1
        jlPokemonIcon.setIcon(Images.scale_image_icon(Images.get_pokemon_icon(pokemon.getId()), 200, 200));
        cluesShown[0] = true;
    }

    private void set_pokemon_types_icons(){
        // Clue 2
        jlType1.setIcon(Images.get_type_icon(pokemon.getTypes().get(0)));
        if(pokemon.getTypes().size() >= 2 && pokemon.getTypes().get(1) != null)
            jlType2.setIcon(Images.get_type_icon(pokemon.getTypes().get(1)));
        else {
            jlType2.setIcon(Images.get_type_icon("Nothing"));
        }
        cluesShown[1] = true;
    }

    private void set_pokemon_japanese_name(){
        // Clue 3
        jlPokemonJPName.setText(pokemon.getNameJapanese());
        cluesShown[2] = true;
    }

    private void set_pokemon_generation(){
        // Clue 4
        jlGeneration.setText(Convert.number_to_roman(pokemon.getGeneration()));
        cluesShown[3] = true;
    }

    private void set_pokemon_st_bb_leg_myt_icons(){
        // Clue 5
        jlStarter.setIcon(Images.get_check_icon(pokemon.isStarter() ? "Yes" : "No"));
        jlBaby.setIcon(Images.get_check_icon(pokemon.isBaby() ? "Yes" : "No"));
        jlLegendary.setIcon(Images.get_check_icon(pokemon.isLegendary() ? "Yes" : "No"));
        jlMythical.setIcon(Images.get_check_icon(pokemon.isMythical() ? "Yes" : "No"));
        cluesShown[4] = true;
    }

    private void set_pokemon_stats(){
        // Clue 6
        jlPs.setText("PS: " + pokemon.getStats()[0]);
        jlAta.setText("ATA: " + pokemon.getStats()[1]);
        jlDef.setText("DEF: " + pokemon.getStats()[2]);
        jlSpAta.setText("SP.ATA: " + pokemon.getStats()[3]);
        jlSpDef.setText("SP.DEF: " + pokemon.getStats()[4]);
        jlVel.setText("VEL: " + pokemon.getStats()[5]);
        cluesShown[5] = true;
    }

    private void set_pokemon_capture_rate(){
        // Clue 7
        jlCaptureRate.setText("Capture Rate: " + pokemon.getCaptureRate());
        cluesShown[6] = true;
    }

    private void set_pokemon_abilities(){
        // Clue 8
        StringBuilder sb = new StringBuilder();
        for(String ability : pokemon.getAbilities()){
            sb.append("- ").append(ability).append("\n");
        }
        jtaAbilities.setText(sb.toString());
        cluesShown[7] = true;
    }

    private void set_pokemon_description(){
        // Clue 9
        jtaDescription.setText(pokemon.getDescription());
        // bro i just saw the db and the "PokeApi"'s api doesn't
        // have the description of some pokemons :(
        cluesShown[8] = true;
    }

    private void set_pokemon_name(){
        jlPokemonName.setText("#" + pokemon.getId() + " - " + pokemon.getName());
        cluesShown[2] = true;
    }

    private void empty_data(){
        // Pokemon Icon
        jlPokemonIcon.setIcon(Images.scale_image_icon(Images.get_pokemon_icon(0), 200, 200));
        // ID and Name
        jlPokemonName.setText("?????????");
        // Generation
        jlGeneration.setText("?");
        // ST - BB - LEG - MYT
        jlStarter.setIcon(Images.get_check_icon("Hidden"));
        jlBaby.setIcon(Images.get_check_icon("Hidden"));
        jlLegendary.setIcon(Images.get_check_icon("Hidden"));
        jlMythical.setIcon(Images.get_check_icon("Hidden"));
        // Types
        jlType1.setIcon(Images.get_type_icon("Hidden"));
        if(pokemon.getTypes().size() >= 2 && pokemon.getTypes().get(1) != null)
            jlType2.setIcon(Images.get_type_icon("Hidden"));
        else {
            jlType2.setIcon(Images.get_type_icon("Nothing"));
        }
        // Japanese Name
        jlPokemonJPName.setText("???");
        // Capture Rate
        jlCaptureRate.setText("Capture Rate: ???");
        // Stats
        jlPs.setText("PS: ???");
        jlAta.setText("ATA: ???");
        jlDef.setText("DEF: ???");
        jlSpAta.setText("SP.ATA: ???");
        jlSpDef.setText("SP.DEF: ???");
        jlVel.setText("VEL: ???");
        // Abilities
        StringBuilder sbAb = new StringBuilder();
        for(String ability : pokemon.getAbilities()){
            sbAb.append("- ");
            sbAb.append(" ".repeat(ability.length()));
            sbAb.append("\n");
        }
        jtaAbilities.setText(sbAb.toString());
        // Description
        StringBuilder sbDes = new StringBuilder();
        for (char c : pokemon.getDescription().toCharArray()) {
            if (c == '\n') sbDes.append(c);
            else sbDes.append(" ");
        }
        jtaDescription.setText(sbDes.toString());
    }

    private void set_all_data(){
        set_pokemon_icon();
        set_pokemon_types_icons();
        set_pokemon_st_bb_leg_myt_icons();
        set_pokemon_name();
        set_pokemon_japanese_name();
        set_pokemon_stats();
        set_pokemon_capture_rate();
        set_pokemon_generation();
        set_pokemon_abilities();
        set_pokemon_description();
    }

    private void start_form(){
        setTitle("Game");
        setResizable(false);
        setContentPane(jpPrincipal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(800, 490);
        setLocationRelativeTo(null);
    }

}
