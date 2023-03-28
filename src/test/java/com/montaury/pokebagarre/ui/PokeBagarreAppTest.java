package com.montaury.pokebagarre.ui;

import java.util.concurrent.TimeUnit;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@ExtendWith(ApplicationExtension.class)
class PokeBagarreAppTest {
    private static final String IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_1 = "#nomPokemon1";
    private static final String IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_2 = "#nomPokemon2";
    private static final String IDENTIFIANT_BOUTON_BAGARRE = ".button";
    
    @Start
    private void start(Stage stage) {
        new PokeBagarreApp().start(stage);
    }

    @Test
    void premier_pokemon_saisi_deuxieme_non_saisi(FxRobot robot) {
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_1);
        robot.write("Pikachu");
        robot.clickOn(IDENTIFIANT_BOUTON_BAGARRE);

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() ->
                assertThat(getMessageErreur(robot)).isEqualTo("Erreur: Le second pokemon n'est pas renseigne")
        );
    }

    @Test
    void premier_pokemon_non_saisi_deuxieme_saisi(FxRobot robot) {
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_2);
        robot.write("Pikachu");
        robot.clickOn(IDENTIFIANT_BOUTON_BAGARRE);

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() ->
                assertThat(getMessageErreur(robot)).isEqualTo("Erreur: Le premier pokemon n'est pas renseigne")
        );
    }

    @Test
    void meme_pokemon_saisi(FxRobot robot) {
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_1);
        robot.write("Pikachu");
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_2);
        robot.write("Pikachu");
        robot.clickOn(IDENTIFIANT_BOUTON_BAGARRE);

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() ->
                assertThat(getMessageErreur(robot)).isEqualTo("Erreur: Impossible de faire se bagarrer un pokemon avec lui-meme")
        );
    }

    @Test
    void premier_pokemon_incorrect(FxRobot robot) {
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_1);
        robot.write("ArthurLeDauberman");
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_2);
        robot.write("Pikachu");
        robot.clickOn(IDENTIFIANT_BOUTON_BAGARRE);

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() ->
                assertThat(getMessageErreur(robot)).isEqualTo("Erreur: Impossible de recuperer les details sur 'ArthurLeDauberman'")
        );
    }

    @Test
    void second_pokemon_incorrect(FxRobot robot) {
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_1);
        robot.write("Pikachu");
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_2);
        robot.write("TitouCochTits");
        robot.clickOn(IDENTIFIANT_BOUTON_BAGARRE);

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() ->
                assertThat(getMessageErreur(robot)).isEqualTo("Erreur: Impossible de recuperer les details sur 'TitouCochTits'")
        );
    }

    @Test
    void premier_pokemon_meilleur(FxRobot robot) {
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_1);
        robot.write("Pikachu");
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_2);
        robot.write("Roucool");
        robot.clickOn(IDENTIFIANT_BOUTON_BAGARRE);

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() ->
                assertThat(getResultatBagarre(robot)).isEqualTo("Le vainqueur est: Pikachu")
        );
    }

    @Test
    void second_pokemon_meilleur(FxRobot robot) {
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_1);
        robot.write("Roucool");
        robot.clickOn(IDENTIFIANT_CHAMP_DE_SAISIE_POKEMON_2);
        robot.write("Pikachu");
        robot.clickOn(IDENTIFIANT_BOUTON_BAGARRE);

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() ->
                assertThat(getResultatBagarre(robot)).isEqualTo("Le vainqueur est: Pikachu")
        );
    }

    private static String getResultatBagarre(FxRobot robot) {
        return robot.lookup("#resultatBagarre").queryText().getText();
    }
    private static String getMessageErreur(FxRobot robot) {
        return robot.lookup("#resultatErreur").queryLabeled().getText();
    }
}
