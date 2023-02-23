package com.montaury.pokebagarre.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    @Test
    void meilleure_attaque_gagne() {
        // GIVEN
        Pokemon poke1 = new Pokemon("ArthurLeDaub", "qzdqzd",new Stats(10,20));
        Pokemon poke2 = new Pokemon("IvanLeDaub", "qzdqzd",new Stats(5,20));

        // WHEN THEN
        assertEquals(true,poke1.estVainqueurContre(poke2));
    }

    @Test
    void moins_bonne_attaque_perd() {
        // GIVEN
        Pokemon poke1 = new Pokemon("ArthurLeDaub", "qzdqzd",new Stats(5,20));
        Pokemon poke2 = new Pokemon("IvanLeDaub", "qzdqzd",new Stats(10,20));

        // WHEN THEN
        assertEquals(false,poke1.estVainqueurContre(poke2));
    }

    @Test
    void meme_attaque_meilleure_defense_gagne() {
        // GIVEN
        Pokemon poke1 = new Pokemon("ArthurLeDaub", "qzdqzd",new Stats(10,25));
        Pokemon poke2 = new Pokemon("IvanLeDaub", "qzdqzd",new Stats(10,20));

        // WHEN THEN
        assertEquals(true,poke1.estVainqueurContre(poke2));
    }

    @Test
    void meme_attaque_moins_bonne_defense_perd() {
        // GIVEN
        Pokemon poke1 = new Pokemon("ArthurLeDaub", "qzdqzd",new Stats(10,15));
        Pokemon poke2 = new Pokemon("IvanLeDaub", "qzdqzd",new Stats(10,20));

        // WHEN THEN
        assertEquals(false,poke1.estVainqueurContre(poke2));
    }

    @Test
    void meme_stats_premier_saisi_gagne() {
        // GIVEN
        Pokemon poke1 = new Pokemon("ArthurLeDaub", "qzdqzd",new Stats(10,20));
        Pokemon poke2 = new Pokemon("IvanLeDaub", "qzdqzd",new Stats(10,20));

        // WHEN THEN
        assertEquals(true,poke1.estVainqueurContre(poke2));
    }
}