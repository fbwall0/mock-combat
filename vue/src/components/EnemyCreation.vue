<template>
  <div id="main">
      <div class="enemy-creation-section" v-if="!enemyCreated" v-on:submit="enemyCreation()">
          <form v-on:submit.prevent="enemyCreation">
              Enemy Name: <input id="enemy-name" v-model="enemy.name" />
              <br />
              Enemy Description: <input id="enemy-description" v-model="enemy.description" />
              Max Health: <input id="enemy-health" v-model="enemy.hpMax" />
              Armor Class: <input id="enemy=armor" v-model="enemy.baseAC" />
              Hit Bonus: <input id="enemy-hit-bonus" v-model="enemy.profBonus" />
              Action Points: <input id="enemy-action-points" v-model="enemy.maxActionPoints" />
              Health Regen: <input id="enemy-health-regen" v-model="enemy.hpRegen" />
              
            <submit id="enemy-creation-button">Commit Enemy Stats</submit>
          </form>
      </div>
      <div class="enemy-actions-section" v-if="enemyCreated && !actionsAdded" v-on:submit="createEnemy()">
          <p>Select the Spells that {{ enemy.name }} knows</p>
            <table id="spells-table">
                <thead>
                    <tr>
                        <th>&nbsp;</th>
                        <th>Name</th>
                        <th>Action Cost</th>
                        <th>Damage</th>
                        <th>Spell Type</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="spell in allSpells" v-bind:key="spell.spellId">
                        <td>
                            <input type="checkbox" v-bind:id="spell.spellId" v-bind:value="spell" v-model="enemy.spells"/>
                        </td>
                        <td>{{ spell.name }}</td>
                        <td>{{ spell.actionCost }}</td>
                        <td>{{ spell.damageDie1 }}d{{ spell.dieSize1 }}<span v-if="spell.bonusDamage1 > 0"> + {{ spell.bonusDamage1 }}</span> {{getDamageType(spell.damageType1Id)}}</td>
                        <td>{{ getSpellType(spell.spellType) }}</td>
                    </tr>
                </tbody>
            </table>
          <br />
          <p>Select the Attacks that {{ enemy.name }} knows</p>
            <table id="attacks-table">
                <thead>
                    <tr>
                        <th>&nbsp;</th>
                        <th>Name</th>
                        <th>Action Cost</th>
                        <th>Damage</th>
                        <th>Magic</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="attack in allAttacks" v-bind:key="attack.attackId">
                        <td>
                            <input type="checkbox" v-bind:id="attack.attackId" v-bind:value="attack" v-model="enemy.attacks"/>
                        </td>
                        <td>{{ attack.name }}</td>
                        <td>{{ attack.actionCost }}</td>
                        <td>{{ attack.damageDie1 }}d{{ attack.dieSize1 }}<span v-if="attack.bonusDamage1 > 0"> + {{ attack.bonusDamage1 }}</span> {{getDamageType(attack.damageType1Id)}}</td>
                        <td><span v-if="attack.magic">{{ attack.magicBonus }}</span></td>
                    </tr>
                </tbody>
            </table>
            <button id="enemy-completion-button" v-on:click="addActions">Finish Aldding Actions</button>
      </div>
      <div id="enemy-review-section" v-if="actionsAdded && enemyCreated">

      </div>
  </div>
</template>

<script>
import combatService from "@/services/CombatService";
export default {
    data() {
        return {
            enemyCreated: false,
            actionsAdded: false,
            allAttacks: [],
            allSpells: [],

            enemy: {
                enemyId: 0,
                name: '',
                description: '',
                experience: Number,
                challenge: Number,
                hpMax: Number,
                baseAC: Number,
                maxActionPoints: Number,
                hpRegen: Number,
                profBonus: Number,
                attacks: [],
                spells: []
            },
            calcEnemy: {
                enemyId: 0,
                name: '',
                description: '',
                experience: Number,
                challenge: Number,
                hpMax: Number,
                baseAC: Number,
                maxActionPoints: Number,
                hpRegen: Number,
                profBonus: Number,
                attacks: [],
                spells: []
            }
        }
    },
    methods: {
        enemyCreation() {
            console.log(this.enemy);
            this.enemyCreated = true;
        },
        addActions() {
            this.actionsAdded = true;
        },
        createEnemy() {
            combatService.createEnemy(this.enemy).then((response) => {
                this.enemy = response.data;
                combatService.updateEnemy(this.enemy);
            })
        },
        remakeEnemy() {
            this.enemyCreated = false;
            this.actionsAdded = false;
            this.enemy = {
                enemyId: 0,
                name: '',
                description: '',
                experience: Number,
                challenge: Number,
                hpMax: Number,
                baseAC: Number,
                maxActionPoints: Number,
                hpRegen: Number,
                profBonus: Number,
                attacks: [],
                spells: []
            }
        }
    },
    created() {
        combatService.getAllEnemyAttacks().then(response => {
            this.allAttacks = response.data;
            combatService.getAllSpells().then(response => {
                this.allSpells = response.data;
            })
        });
    }
}
</script>

<style>

</style>