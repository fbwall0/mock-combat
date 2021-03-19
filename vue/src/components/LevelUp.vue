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
            <p>Available Experience: {{ futureCharacter.xp }}</p>
            <br />
            <div v-if="profIncreaseCost <= futureCharacter.xp">
                <button v-on:click="profIncrease">Increase Proficiency</button> Cost: {{ profIncreaseCost }}
            </div>
            <br />
            <div v-if="strIncreaseCost <= futureCharacter.xp">
                <button v-on:click="strIncrease">Increase Strength</button> Cost: {{ strIncreaseCost }}
            </div>
            <br />
            <div v-if="dexIncreaseCost <= futureCharacter.xp">
                <button v-on:click="dexIncrease">Increase Dexterity</button> Cost: {{ dexIncreaseCost }}
            </div>
            <br />
            <div v-if="conIncreaseCost <= futureCharacter.xp">
                <button v-on:click="conIncrease">Increase Constitution</button> Cost: {{ conIncreaseCost }}
            </div>
            <br />
            <div v-if="magIncreaseCost <= futureCharacter.xp">
                <button v-on:click="magIncrease">Increase Magic</button> Cost: {{ magIncreaseCost }}
            </div>
            <br />
            <div v-if="hpRegenIncreaseCost <= futureCharacter.xp">
                <button v-on:click="hpRegenIncrease">Increase Health Regeneration</button> Cost: {{ hpRegenIncreaseCost }}
            </div>
            <br />
            <div v-if="manaRegenIncreaseCost <= futureCharacter.xp">
                <button v-on:click="manaRegenIncrease">Increase Mana Regeneration</button> Cost: {{ manaRegenIncreaseCost }}
            </div>
            <br />
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
        },
        profIncrease() {
            this.spendXp(this.profIncreaseCost());
            this.futureCharacter.profBonus = this.futureCharacter.profBonus + 1;
        },
        strIncrease() {
            this.spendXp(this.strIncreaseCost());
            this.futureCharacter.strength = this.futureCharacter.strength + 1;
        },
        dexIncrease() {
            this.spendXp(this.dexIncreaseCost());
            this.futureCharacter.dexterity = this.futureCharacter.dexterity + 1;
        },
        conIncrease() {
            this.spendXp(this.conIncreaseCost());
            this.futureCharacter.constitution = this.futureCharacter.constitution + 1;
        },
        magIncrease() {
            this.spendXp(this.magfIncreaseCost());
            this.futureCharacter.magic = this.futureCharacter.magic + 1;
        },
        hpRegenIncrease() {
            this.spendXp(this.hpRegenIncreaseCost());
            this.futureCharacter.hpRegen = this.futureCharacter.hpRegen + 1;
        },
        manaRegenIncrease() {
            this.spendXp(this.manaRegenIncreaseCost());
            this.futureCharacter.manaRegen = this.futureCharacter.manaRegen + 1;
        },
        spendXp(experience) {
            this.futureCharacter.xp = this.futureCharacter.xp - experience;
            this.futureCharacter.spentXp = this.futureCharacter.spentXp + experience;
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
        //for testing
        this.futureCharacter.xp = 10000;
    },
    computed: {
        profIncreaseCost() {
            return (this.futureCharacter.profBonus - (this.$store.currentPlayer.bonus1 == 5 ? 1 : 0)) * 300;
        },
        strIncreaseCost() {
            return (this.futureCharacter.strength - 10 - (this.$store.currentPlayer.bonus1 == 1 ? 4 : 0) - (this.$store.currentPlayer.bonus2 == 1 ? 2 : 0)) * 200;
        },
        dexIncreaseCost() {
            return (this.futureCharacter.dexterity - 10 - (this.$store.currentPlayer.bonus1 == 2 ? 4 : 0) - (this.$store.currentPlayer.bonus2 == 2 ? 2 : 0)) * 200;
        },
        conIncreaseCost() {
            return (this.futureCharacter.constitution - 10 - (this.$store.currentPlayer.bonus1 == 3 ? 4 : 0) - (this.$store.currentPlayer.bonus2 == 3 ? 2 : 0)) * 200;
        },
        magIncreaseCost() {
            return (this.futureCharacter.magic - 10 - (this.$store.currentPlayer.bonus1 == 4 ? 4 : 0) - (this.$store.currentPlayer.bonus2 == 4 ? 2 : 0)) * 200;
        },
        hpRegenIncreaseCost() {
            return (this.futureCharacter.hpRegen + 1) ^ 2 * 500;
        },
        manaRegenIncreaseCost() {
            return (this.futureCharacter.manaRegen + 1) ^ 2 * 500;
        },
    }
}
</script>

<style>

</style>