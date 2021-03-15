<template>
  <div id="main">
      <button v-on:click="reloadCharacters">Reload Characters</button>
      <div v-if="loaded">
          <div class="characters" v-for="character in characters" v-bind:key="character.playerId">
              Name: {{ character.name }}
              <br />
              Spent Experience {{ character.spentXp }}
              <br />
              <button>Character Details</button>
              <button> Play as Character</button>

          </div>
      </div>
  </div>
</template>

<script>
import combatService from "@/services/CombatService";
export default {
    data() {
        return {
            loaded: false,
            characters: [],
        }
    },
    created() {
        combatService.getAllPlayers().then((response) => {
            this.characters = response.data;
            if (this.characters.length > 0 ) {
                this.loaded = true;
            }
        })
    },
    methods: {
        reloadCharacters() {
            this.loaded = false;
            combatService.getAllPlayers().then((response) => {
            this.characters = response.data;
            if (this.characters.length > 0 ) {
                this.loaded = true;
            }
        })
        }
    }
}
</script>

<style>

</style>