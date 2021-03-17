<template>
  <div id="main">
      <div class="spell-creation-section">
          <form v-on:submit.prevent="spellCreation">
              Spell Name: <input id="spell-name" required v-model="spell.name" />
              <br />
              Action Cost: <input id="spell-action-cost" required v-model="spell.actionCost" type="number" min=0.5 step=0.5 />
              <br />
              Spell Type: <select id="spell-type" required v-model="spell.spellType" >
                  <option value=1>Damage</option>
                  <option value=2>Healing</option>
                  <!-- <option value=3>Utility</option> -->
              </select>
              <br />
              Number of Damage Dice 1: <input id="spell-damage-dice-1" v-model="spell.damageDie1" type="number" min=0 />
              <br />
              Size of Damage Dice 1: <select id="spell-die-size-1" v-model="spell.dieSize1" type="number">
                  <option value=4>4</option>
                  <option value=6>6</option>
                  <option value=8>8</option>
                  <option value=10>10</option>
                  <option value=12>12</option>
                  </select>
              <br />
              Bonus Damage 1: <input id="spell-bonus-damage-1" v-model="spell.bonusDamage1" type="number" />
              <br />
              Damage Type 1: <select id="spell-damage-type-1" v-model="spell.damageType1">
                  <option value=1>Bludgeoning</option>
                  <option value=2>Piercing</option>
                  <option value=3>Slashing</option>
                  <option value=4>Radiant</option>
                  <option value=5>Necrotic</option>
                  <option value=6>Fire</option>
                  <option value=7>Cold</option>
                  <option value=8>Lightning</option>
                  <option value=9>Thunder</option>
                  <option value=10>Force</option>
                  <option value=11>Psychic</option>
                  <option value=12>Poison</option>
                  <option value=13>Acid</option>
                  </select>
              <br />
              <input type="checkbox" v-model="spell.hasSecondDamage">Has Second Damage
              <br />
              <div v-if="spell.hasSecondDamage">
                  Number of Damage Dice 2: <input id="spell-damage-dice-2" v-model="spell.damageDie2" type="number" min=0 />
                  <br />
                  Size of Damage Dice 2: <select id="spell-die-size-2" v-model="spell.dieSize2" type="number">
                      <option value=4>4</option>
                      <option value=6>6</option>
                      <option value=8>8</option>
                      <option value=10>10</option>
                      <option value=12>12</option>
                      </select>
                  <br />
                  Bonus Damage 2: <input id="spell-bonus-damage-2" v-model="spell.bonusDamage2" type="number" />
                  <br />
                  Damage Type 2: <select id="spell-damage-type-2" v-model="spell.damageType2">
                  <option value=1>Bludgeoning</option>
                  <option value=2>Piercing</option>
                  <option value=3>Slashing</option>
                  <option value=4>Radiant</option>
                  <option value=5>Necrotic</option>
                  <option value=6>Fire</option>
                  <option value=7>Cold</option>
                  <option value=8>Lightning</option>
                  <option value=9>Thunder</option>
                  <option value=10>Force</option>
                  <option value=11>Psychic</option>
                  <option value=12>Poison</option>
                  <option value=13>Acid</option>
                  </select>
              <br />
              </div>
              Estimated Mana Cost: {{ manaCostCalc }}
              <br />
              Estimated Experience Cost: {{ experienceCostCalc }}
              <br />
            <button id="spell-creation-button" v-if="spell.name.length > 0">Create Spell</button>
          </form>
      </div>
  </div>
</template>

<script>
import combatService from "@/services/CombatService";
export default {
    data() {
        return {

            spell: {
                name: '',
                actionCost: .5,
                damageType1: 1,
                damageType2: 1,
                damageDie1: 1,
                dieSize1: 4,
                bonusDamage1: 0,
                hasSecondDamage: false,
                damageDie2: 1,
                dieSize2: 4,
                bonusDamage2: 0,
                spellType: 1,
                manaCost: Number,
                targetSelf: false,
                xpCost: Number,
            }
        }
    },
    methods: {
        spellCreation() {
            this.spell.manaCost = this.manaCostCalc;
            this.spell.xpCost = this.experienceCostCalc;
            combatService.createSpell(this.spell);
            this.spell = {
                name: '',
                actionCost: .5,
                damageType1: 1,
                damageType2: 1,
                damageDie1: 1,
                dieSize1: 4,
                bonusDamage1: 0,
                hasSecondDamage: false,
                damageDie2: 1,
                dieSize2: 4,
                bonusDamage2: 0,
                spellType: 1,
                manaCost: Number,
                targetSelf: false,
                xpCost: Number,
            }
        }
    },
    computed: {
        manaCostCalc() {
            if (this.spell.hasSecondDamage) {
                return Math.round((Number(this.spell.damageDie1 * this.spell.dieSize1 + Number(this.spell.bonusDamage1) * Math.abs(this.spell.bonusDamage1) / 2) + Number(this.spell.damageDie2 * this.spell.dieSize2 + Number(this.spell.bonusDamage2) * Math.abs(this.spell.bonusDamage2) / 2)) / (this.spell.actionCost * 2));
            } else {
                return Math.round((this.spell.damageDie1 * this.spell.dieSize1 + Number(this.spell.bonusDamage1) * Math.abs(this.spell.bonusDamage1) / 2) / (this.spell.actionCost * 2));
            }
        },
        experienceCostCalc() {
            if (this.spell.hasSecondDamage) {
                let damage1 = Number(this.spell.damageDie1 * this.spell.dieSize1 + Number(this.spell.bonusDamage1));
                let damage2 = Number(this.spell.damageDie2 * this.spell.dieSize2 + Number(this.spell.bonusDamage2));
                let totalDamage = Number(damage1 + damage2);
                return Math.round(Number(totalDamage * 50) / Number(this.spell.actionCost));
            } else {
                return Math.round((this.spell.damageDie1 * this.spell.dieSize1 + Number(this.spell.bonusDamage1)) * 50 / (this.spell.actionCost));
            }
        }
    }
}
</script>

<style>

</style>