<template>
  <div id="main">
      <div class="attack-creation-section">
          <form v-on:submit.prevent="attackCreation">
              Attack Name: <input id="attack-name" required v-model="attack.name" />
              <br />
              Action Cost: <input id="attack-action-cost" required v-model="attack.actionCost" type="number" min=0.5 step=0.5 />
              <br />
              <input type="checkbox" v-model="enemyAttack">Is an Enemy Attack
              <br />
              Number of Damage Dice 1: <input id="attack-damage-dice-1" v-model="attack.damageDie1" type="number" min=0 />
              <br />
              Size of Damage Dice 1: <select id="attack-die-size-1" v-model="attack.dieSize1" type="number">
                  <option value=4>4</option>
                  <option value=6>6</option>
                  <option value=8>8</option>
                  <option value=10>10</option>
                  <option value=12>12</option>
                  </select>
              <br />
              Bonus Damage 1: <input id="attack-bonus-damage-1" v-model="attack.bonusDamage1" type="number" />
              <br />
              Damage Type 1: <select id="attack-damage-type-1" v-model="attack.damageType1">
                  <option value=1>Bludgeoning</option>
                  <option value=2>Piercing</option>
                  <option value=3>Slashing</option>
                  </select>
              <br />
              <input type="checkbox" v-model="attack.hasSecondDamage">Has Second Damage
              <br />
              <div v-if="attack.hasSecondDamage">
                  Number of Damage Dice 2: <input id="attack-damage-dice-2" v-model="attack.damageDie2" type="number" min=0 />
                  <br />
                  Size of Damage Dice 2: <select id="attack-die-size-2" v-model="attack.dieSize2" type="number">
                      <option value=4>4</option>
                      <option value=6>6</option>
                      <option value=8>8</option>
                      <option value=10>10</option>
                      <option value=12>12</option>
                      </select>
                  <br />
                  Bonus Damage 2: <input id="attack-bonus-damage-2" v-model="attack.bonusDamage2" type="number" />
                  <br />
                  Damage Type 2: <select id="attack-damage-type-2" v-model="attack.damageType2">
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
              </div>
              <br />
              <input type="checkbox" v-model="attack.magic">Has Magical Bonus
              <br />
              <div v-if="attack.magic">
                  Magical Bonus: <input id="attack-magic-bonus" v-model="attack.magicBonus" type="number" />
              </div>
              Estimated Experience Cost: {{ experienceCostCalc }}
              <br />
            <button id="attack-creation-button" v-if="attack.name.length > 0">Create Attack</button>
          </form>
      </div>
  </div>
</template>

<script>
import combatService from "@/services/CombatService";
export default {
    data() {
        return {
            enemyAttack: false,

            attack: {
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
                magic: false,
                magicBonus: 0,
                xpCost: Number,
            }
        }
    },
    methods: {
        attackCreation() {
            this.attack.xpCost = this.experienceCostCalc;
            if (this.isEnemyAttack) {
                combatService.createEnemyAttack(this.attack);
            } else {
                combatService.createAttack(this.attack);
            }
            this.attack = {
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
                magic: false,
                magicBonus: 0,
                xpCost: Number,
            }
        }
    },
    computed: {
        experienceCostCalc() {
            if (this.attack.hasSecondDamage) {
                let damage1 = Number(this.attack.damageDie1 * this.attack.dieSize1 + Number(this.attack.bonusDamage1) * Math.abs(this.attack.bonusDamage1) / 2);
                let damage2 = Number(this.attack.damageDie2 * this.attack.dieSize2 + Number(this.attack.bonusDamage2) * Math.abs(this.attack.bonusDamage2) / 2);
                let magicBonus = Number(this.attack.magic ? this.attack.magicBonus : 0) * this.attack.magicBonus * this.attack.magicBonus;
                let totalDamage = Number(Number(damage1) + Number(damage2) + Number(magicBonus));
                return Math.round(Number(totalDamage * 50) / Number(this.attack.actionCost));
            } else {
                return (this.attack.damageDie1 * this.attack.dieSize1 + Number(this.attack.bonusDamage1) * Math.abs(this.attack.bonusDamage1) / 2 + (Number(this.attack.magic ? this.attack.magicBonus : 0) * this.attack.magicBonus * this.attack.magicBonus)) * 50 / (this.attack.actionCost);
            }
        }
    }
}
</script>

<style>

</style>