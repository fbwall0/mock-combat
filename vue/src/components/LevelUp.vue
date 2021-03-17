<template>
  <div id="main">
      <div id="character-stat" v-if="futureCharacter.playerId != 0">
        <div id="current-character">
            <p>Current Character Stats</p>
            <p>Name: {{ $store.currentPlayer.name }}</p>
            <p>Max HP: {{ $store.currentPlayer.hpMax + ($store.currentPlayer.constitution - 10) * 5 + ($store.currentPlayer.profBonus * 5) }}</p>
            <p>Max Mana: {{ $store.currentPlayer.manaMax + ($store.currentPlayer.magic - 10) * 5 + ($store.currentPlayer.profBonus * 5) }}</p>
            <p>Armor: {{ $store.currentPlayer.baseAC + ($store.currentPlayer.dexterity - 10) / 2 + ($store.currentPlayer.profBonus) }}</p>
            <p>Proficiency Bonus: {{ $store.currentPlayer.profBonus }}</p>
            <p>Action Points: {{ 1.5 + Number(Math.round((characterSelected.profBonus - 1) / 2)) / 2 }}</p>
            <p>Strength: {{ $store.currentPlayer.strength }}</p>
            <p>Dexterity: {{ $store.currentPlayer.dexterity }}</p>
            <p>Constitution: {{ $store.currentPlayer.constitution }}</p>
            <p>Magical Ability: {{ $store.currentPlayer.magic }}</p>
            <p>Primary Specialization: {{ specialty1 }}</p>
            <p>Secondary Specialization: {{ specialty2 }}</p>
        </div>
        <br />
        <div id="stat-boosts">
            <p>Available Experience: {{ $store.futureCharacter.xp }}</p>

        </div>
        <br />
        <div id="future-character">
            <p>New Character Stats</p>
            <p>Name: {{ futureCharacter.name }}</p>
            <p>Max HP: {{ futureCharacter.hpMax + (futureCharacter.constitution - 10) * 5 + (futureCharacter.profBonus * 5) }}</p>
            <p>Max Mana: {{ futureCharacter.manaMax + (futureCharacter.magic - 10) * 5 + (futureCharacter.profBonus * 5) }}</p>
            <p>Armor: {{ futureCharacter.baseAC + (futureCharacter.dexterity - 10) / 2 + (futureCharacter.profBonus) }}</p>
            <p>Proficiency Bonus: {{ futureCharacter.profBonus }}</p>
            <p>Action Points: {{ 1.5 + Number(Math.round((futureCharacter.profBonus - 1) / 2)) / 2 }}</p>
            <p>Strength: {{ futureCharacter.strength }}</p>
            <p>Dexterity: {{ futureCharacter.dexterity }}</p>
            <p>Constitution: {{ futureCharacter.constitution }}</p>
            <p>Magical Ability: {{ futureCharacter.magic }}</p>
        </div>
      </div>


  </div>
</template>

<script>
import combatService from "@/services/CombatService";
export default {
    data() {
        return {
            spellsLoaded: false,
            attacksLoaded: false,
            characterLoaded: false,
            unknownSpells: [],
            unknownAttacks: [],
            futureCharacter: {
                playerId: 0
            },

        }
    },
    methods: {
        resetFutureCharacter() {
            this.futureCharacter = this.$store.currentPlayer;
        }
    },
    created() {
        this.resetFutureCharacter;
        combatService.getUnknownSpells(this.futureCharacter.playerId).then((response) => {
            this.unknownSpells = response.data;
            this.spellsLoaded = true;
        });
        combatService.getUnknownAttacks(this.futureCharacter.playerId).then((response) => {
            this.unknownAttacks = response.data;
            this.attacksLoaded = true;
        });
    }
}
</script>

<style>

</style>