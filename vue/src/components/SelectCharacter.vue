<template>
  <div id="main">
      <button v-on:click="reloadCharacters">Reload Characters</button>
      <div v-if="loaded">
          <div class="characters" v-for="character in characters" v-bind:key="character.playerId">
              Name: {{ character.name }}
              <br />
              Spent Experience {{ character.spentXp }}
              <br />
              <button v-on:click="selectCharacter(character.playerId)">Character Details</button>
              <button v-on:click="playAsCharacter">Play as Character</button>

          </div>
          <div class="character-info" v-if="selected">
              <p>Name: {{ characterSelected.name }}</p>
              <p>Max HP: {{ characterSelected.hpMax + (characterSelected.constitution - 10) * 5 + (characterSelected.profBonus * 5) }}</p>
              <p>Max Mana: {{ characterSelected.manaMax + (characterSelected.magic - 10) * 5 + (characterSelected.profBonus * 5) }}</p>
              <p>Armor: {{ characterSelected.baseAC + (characterSelected.dexterity - 10) / 2 + (characterSelected.profBonus) }}</p>
              <p>Proficiency Bonus: {{ characterSelected.profBonus }}</p>
              <p>Action Points: {{ 1.5 + Number(Math.round((characterSelected.profBonus - 1) / 2)) / 2 }}</p>
              <p>Strength: {{ characterSelected.strength }}</p>
              <p>Dexterity: {{ characterSelected.dexterity }}</p>
              <p>Constitution: {{ characterSelected.constitution }}</p>
              <p>Magical Ability: {{ characterSelected.magic }}</p>
              <p>Primary Specialization: {{ specialty1 }}</p>
              <p>Secondary Specialization: {{ specialty2 }}</p>
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
            selected: false,
            specialty1: '',
            specialty2: '',
            characters: [],
            characterSelected: {
                playerId: 0,
            },

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
        },
        selectCharacter(id) {
            this.selected = false;
            combatService.getPlayer(id).then((response) => {
                this.characterSelected = response.data;
                this.specialty1 = this.specialty(this.characterSelected.boost1);
                this.specialty2 = this.specialty(this.characterSelected.boost2);
                this.selected = true;
            })
        },
        deselectCharacter() {
            this.characterSelected = {
                playerId: 0,
            };
            this.specialty1 = '';
            this.specialty2 = '';
            this.selected = false;
        },
        specialty(id) {
            switch(id){
                case 1: return 'Strength';
                case 2: return 'Dexterity';
                case 3: return 'Constitution';
                case 4: return 'Magical';
                case 5: return 'Proficiency';
                case 6: return 'Health';
                case 7: return 'Mana';
                default: return 'None';
            }

        },
        playAsCharacter() {
            this.$store.commit('SET_CURRENT_PLAYER', this.characterSelected);
        }
    }
}
</script>

<style>

</style>