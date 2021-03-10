<template>
  <div id="main">
      <div class="character-creation-section" v-if="!characterCreated">
          <form v-on:submit.prevent="characterCreation">
              Character Name: <input id="character-name" v-model="player.name" />
              <br />
              Primary Stat: <select id="primary-stat" v-model="boost1">
                  <option value=0>Select One</option>
                  <option value=1>Strength</option>
                  <option value=2>Dexterity</option>
                  <option value=3>Constitution</option>
                  <option value=4>Magic</option>
                  <option value=5>Proficiency</option>
                  <option value=6>Health</option>
                  <option value=7>Mana</option>
                </select>
            <div v-if="boost1 > 0">
                Secondary Stat: <select id="secondary-stat" v-model="boost2">
                  <option value=0>Select One</option>
                  <option value=1 v-show="boost1 != 1">Strength</option>
                  <option value=2 v-show="boost1 != 2">Dexterity</option>
                  <option value=3 v-show="boost1 != 3">Constitution</option>
                  <option value=4 v-show="boost1 != 4">Magic</option>
                  <option value=6 v-show="boost1 != 6">Health</option>
                  <option value=7 v-show="boost1 != 7">Mana</option>
                </select>
            </div>
            <button v-if="boost2 > 0 && player.name.length > 0" id="character-creation-button">Create Character</button>
          </form>
      </div>
      <div class="character-details-section" v-if="characterCreated">
          <p>Is this the character you wanted?</p>
          <p>Name: {{ boostedPlayer.name }}</p>
          <p>Max Health: {{ boostedPlayer.hpMax + (boostedPlayer.constitution - 10) * 5 + (boostedPlayer.profBonus * 5) }}</p>
          <p>Max Mana: {{ boostedPlayer.manaMax + (boostedPlayer.magic - 10) * 5 + (boostedPlayer.profBonus * 5) }}</p>
          <p>Armor Class: {{ boostedPlayer.baseAC + (boostedPlayer.dexterity - 10) / 2 + (boostedPlayer.profBonus) }}</p>
          <p>Proficiency Bonus: {{ boostedPlayer.profBonus }}</p>
          <p>Strength: {{ boostedPlayer.strength }}</p>
          <p>Dexterity: {{ boostedPlayer.dexterity }}</p>
          <p>Constitution: {{ boostedPlayer.constitution }}</p>
          <p>Magical Ability: {{ boostedPlayer.magic }}</p>
          <button v-on:click="createCharacter">Yes, create character</button>
          <button v-on:click="remakeCharacter">No, remake it</button>
      </div>
  </div>
</template>

<script>
import combatService from "@/services/CombatService";
export default {
    data() {
        return {
            boost1: 0,
            boost2: 0,
            characterCreated: false,

            player: {
                playerId: 0,
                name: '',
                experience: 300,
                hpMax: 15,
                manaMax: 15,
                baseAC: 10,
                hpRegen: 0,
                manaRegen: 0,
                profBonus: 1,
                strength: 10,
                dexterity: 10,
                constitution: 10,
                magic: 10,
                spentXp: 0,
                boost1: 0, //Determines highest starting stat, affects upgrade cost
                boost2: 0, //Determines second highest starting stat, affects upgrade cost
            },
            boostedPlayer: {
                playerId: 0,
                name: '',
                experience: 300,
                hpMax: 15,
                manaMax: 15,
                baseAC: 10,
                hpRegen: 0,
                manaRegen: 0,
                profBonus: 1,
                strength: 10,
                dexterity: 10,
                constitution: 10,
                magic: 10,
                spentXp: 0,
                boost1: 0, //Determines highest starting stat, affects upgrade cost
                boost2: 0, //Determines second highest starting stat, affects upgrade cost
            }
        }
    },
    methods: {
        characterCreation() {
            this.boostedPlayer = this.player;
            console.log(this.boostedPlayer);
            this.boostedPlayer.boost1 = this.boost1;
            if (this.boost1 == 1) {
                this.boostedPlayer.strength = 14;
            } else if (this.boost1 == 2) {
                this.boostedPlayer.dexterity = 14;
            } else if (this.boost1 == 3) {
                this.boostedPlayer.constitution = 14;
            } else if (this.boost1 == 4) {
                this.boostedPlayer.magic = 14;
            } else if (this.boost1 == 5) {
                this.boostedPlayer.profBonus = 2;
            } else if (this.boost1 == 6) {
                this.boostedPlayer.hpMax = 25;
            } else if (this.boost1 == 7) {
                this.boostedPlayer.manaMax = 25;
            }
            this.boostedPlayer.boost2 = this.boost2;
            if (this.boost2 == 1) {
                this.boostedPlayer.strength = 12;
            } else if (this.boost2 == 2) {
                this.boostedPlayer.dexterity = 12;
            } else if (this.boost2 == 3) {
                this.boostedPlayer.constitution = 12;
            } else if (this.boost2 == 4) {
                this.boostedPlayer.magic = 12;
            } else if (this.boost2 == 6) {
                this.boostedPlayer.hpMax = 20;
            } else if (this.boost2 == 7) {
                this.boostedPlayer.manaMax = 20;
            }
            this.characterCreated = true;
        },
        createCharacter() {
            combatService.createPlayer(this.name).then((response) => {
                this.player = response.data;
                combatService.updatePlayer(this.boostedPlayer);
            })
        },
        remakeCharacter() {
            this.characterCreated = false;
        }
    }
}
</script>

<style>

</style>