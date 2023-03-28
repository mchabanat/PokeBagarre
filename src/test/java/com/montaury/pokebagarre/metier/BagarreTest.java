package com.montaury.pokebagarre.metier;

import com.montaury.pokebagarre.webapi.PokeBuildApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.concurrent.CompletableFuture;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class BagarreTest {
    @Test
    void poke1_nonsaisi_erreur() {
        var fausseApi = Mockito.mock(PokeBuildApi.class);
        Bagarre maBagarre = new Bagarre(fausseApi);

        String poke1 = "";
        String poke2 = "Dracaufeu";

        var throwable = catchThrowable(() -> maBagarre.demarrer(poke1,poke2));
        assertThat(throwable).hasMessage("Le premier pokemon n'est pas renseigne");
    }

    @Test
    void poke2_nonsaisi_erreur() {
        var fausseApi = Mockito.mock(PokeBuildApi.class);
        Bagarre maBagarre = new Bagarre(fausseApi);

        String poke1 = "Dracaufeu";
        String poke2 = "";

        var throwable = catchThrowable(() -> maBagarre.demarrer(poke1,poke2));
        assertThat(throwable).hasMessage("Le second pokemon n'est pas renseigne");
    }

    @Test
    void meme_poke_saisi_erreur() {
        var fausseApi = Mockito.mock(PokeBuildApi.class);
        Bagarre maBagarre = new Bagarre(fausseApi);

        String poke1 = "Dracaufeu";
        String poke2 = "Dracaufeu";

        var throwable = catchThrowable(() -> maBagarre.demarrer(poke1,poke2));
        assertThat(throwable).hasMessage("Impossible de faire se bagarrer un pokemon avec lui-meme");
    }

    @Test
    void poke1_inconnu_saisi_erreur() { // marche pas
        var fausseApi = Mockito.mock(PokeBuildApi.class);
        Bagarre maBagarre = new Bagarre(fausseApi);

        String poke1 = "ivan";
        String poke2 = "Dracaufeu";

        var throwable = catchThrowable(() -> maBagarre.demarrer(poke1,poke2));
        assertThat(throwable).hasMessage("Impossible de recuperer les details sur ivan");
    }
}