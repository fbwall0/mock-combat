<template>
  <div id="main">
      <div id="character-stat" v-if="characterLoaded">
        <div id="current-character">
            <p>Current Character Stats</p>
            <p>Name: {{ $store.state.player.name }}</p>
            <p>Max HP: {{ $store.state.player.hpMax + ($store.state.player.constitution - 10) * 5 + ($store.state.player.profBonus * 5) }}</p>
            <p>Max Mana: {{ $store.state.player.manaMax + ($store.state.player.magic - 10) * 5 + ($store.state.player.profBonus * 5) }}</p>
            <p>Armor: {{ $store.state.player.baseAC + ($store.state.player.dexterity - 10) / 2 + ($store.state.player.profBonus) }}</p>
            <p>Proficiency Bonus: {{ $store.state.player.profBonus }}</p>
            <p>Action Points: {{ 1.5 + Number(Math.round(($store.state.player.profBonus - 1) / 2)) / 2 }}</p>
            <p>Strength: {{ $store.state.player.strength }}</p>
            <p>Dexterity: {{ $store.state.player.dexterity }}</p>
            <p>Constitution: {{ $store.state.player.constitution }}</p>
            <p>Magical Ability: {{ $store.state.player.magic }}</p>
            <p>Primary Specialization: {{ specialty1 }}</p>
            <p>Secondary Specialization: {{ specialty2 }}</p>
        </div>
        <br />
        <div id="stat-boosts" v-if="!midLevel">
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
                <button v-on:click="hpRegenIncrease(hpRegenIncreaseCost)">Increase Health Regeneration</button> Cost: {{ hpRegenIncreaseCost }}
            </div>
            <br />
            <div v-if="manaRegenIncreaseCost <= futureCharacter.xp">
                <button v-on:click="manaRegenIncrease(manaRegenIncreaseCost)">Increase Mana Regeneration</button> Cost: {{ manaRegenIncreaseCost }}
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
      <button v-on:click="resetFutureCharacter">Reset Level Up</button>
      <button v-if="!baseLoaded" v-on:click="setBase">Set Base</button>


  </div>
</template>

<script>
import combatService from "@/services/CombatService";
export default {
    data() {
        return {
            midLevel: false,
            spellsLoaded: false,
            attacksLoaded: false,
            characterLoaded: false,
            unknownSpells: [],
            unknownAttacks: [],
            baseLoaded: false,
            baseCharacter: {},
            futureCharacter: {
                playerId: 0
            },

        }
    },
    methods: {
        setBase() {
            this.baseLoaded = false;
            this.baseCharacter = this.$store.state.player;
            this.baseLoaded = true;
        },
        resetFutureCharacter() {
            this.characterLoaded = false;
            this.midLevel = true,
            this.futureCharacter = this.baseCharacter;
            combatService.getUnknownSpells(this.$store.state.player.playerId).then((response) => {
                this.unknownSpells = response.data;
                this.spellsLoaded = true;
            });
            combatService.getUnknownAttacks(this.$store.state.player.playerId).then((response) => {
                this.unknownAttacks = response.data;
                this.attacksLoaded = true;
            });
            //for testing
            this.futureCharacter.xp = 10000;
            this.characterLoaded = true;
            this.midLevel = false;
        },
        profIncrease() {
            this.midLevel = true;
            this.spendXp(this.profIncreaseCost());
            this.futureCharacter.profBonus = this.futureCharacter.profBonus + 1;
            this.midLevel = false;
        },
        strIncrease() {
            this.midLevel = true;
            this.spendXp(this.strIncreaseCost());
            this.futureCharacter.strength = this.futureCharacter.strength + 1;
            this.midLevel = false;
        },
        dexIncrease() {
            this.midLevel = true;
            this.spendXp(this.dexIncreaseCost());
            this.futureCharacter.dexterity = this.futureCharacter.dexterity + 1;
            this.midLevel = false;
        },
        conIncrease() {
            this.midLevel = true;
            this.spendXp(this.conIncreaseCost());
            this.futureCharacter.constitution = this.futureCharacter.constitution + 1;
            this.midLevel = false;
        },
        magIncrease() {
            this.midLevel = true;
            this.spendXp(this.magIncreaseCost());
            this.futureCharacter.magic = this.futureCharacter.magic + 1;
            this.midLevel = false;
        },
        hpRegenIncrease(hpRegenIncreaseCost) {
            this.midLevel = true;
            this.spendXp(hpRegenIncreaseCost);
            this.futureCharacter.hpRegen = this.futureCharacter.hpRegen + 1;
            this.midLevel = false;
        },
        manaRegenIncrease(manaRegenIncreaseCost) {
            this.midLevel = true;
            this.spendXp(manaRegenIncreaseCost);
            this.futureCharacter.manaRegen = this.futureCharacter.manaRegen + 1;
            this.midLevel = false;
        },
        spendXp(experience) {
            this.futureCharacter.xp = this.futureCharacter.xp - experience;
            this.futureCharacter.spentXp = this.futureCharacter.spentXp + experience;
        }
    },
    created() {
        this.resetFutureCharacter;
        this.baseCharacter = this.$store.state.player;
        
        
    },
    computed: {
        profIncreaseCost() {
            return (this.futureCharacter.profBonus - (this.$store.state.player.bonus1 == 5 ? 1 : 0)) * 300;
        },
        strIncreaseCost() {
            return (this.futureCharacter.strength - 9 - (this.$store.state.player.bonus1 == 1 ? 4 : 0) - (this.$store.state.player.bonus2 == 1 ? 2 : 0)) * 200;
        },
        dexIncreaseCost() {
            return (this.futureCharacter.dexterity - 9 - (this.$store.state.player.bonus1 == 2 ? 4 : 0) - (this.$store.state.player.bonus2 == 2 ? 2 : 0)) * 200;
        },
        conIncreaseCost() {
            return (this.futureCharacter.constitution - 9 - (this.$store.state.player.bonus1 == 3 ? 4 : 0) - (this.$store.state.player.bonus2 == 3 ? 2 : 0)) * 200;
        },
        magIncreaseCost() {
            return (this.futureCharacter.magic - 9 - (this.$store.state.player.bonus1 == 4 ? 4 : 0) - (this.$store.state.player.bonus2 == 4 ? 2 : 0)) * 200;
        },
        hpRegenIncreaseCost() {
            return ((this.futureCharacter.hpRegen) * (this.futureCharacter.hpRegen) + 1) * 250;
        },
        manaRegenIncreaseCost() {
            return (Number(this.futureCharacter.manaRegen) * Number(this.futureCharacter.manaRegen) + 1) * 250;
        },
    }
}
</script>

<style>

</style>