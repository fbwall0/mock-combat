<template>
  <div id="main">
      <div class="enemy-creation-section" v-if="!enemyCreated">
          <form v-on:submit.prevent="enemyCreation">
              Enemy Name: <input id="enemy-name" required v-model="enemy.name" />
              <br />
              Enemy Description: <input id="enemy-description" v-model="enemy.description" type="textarea" />
              <br />
              Max Health: <input id="enemy-health" required v-model="enemy.hpMax" type="number" min=1 />
              <br />
              Armor Class: <input id="enemy=armor" required v-model="enemy.baseAC" type="number" min=8 />
              <br />
              Hit Bonus: <input id="enemy-hit-bonus" required v-model="enemy.profBonus" type="number" min=-3/>
              <br />
              Action Points: <input id="enemy-action-points" required v-model="enemy.maxActionPoints" type="number" min=1.5 step=0.5 />
              <br />
              Health Regen: <input id="enemy-health-regen" required v-model="enemy.hpRegen" type="number" min=0 />
              <br />
              
            <button id="enemy-creation-button">Commit Enemy Stats</button>
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
                        <td>{{ spell.damageDie1 }}d{{ spell.dieSize1 }}<span v-if="spell.damageDie2 * spell.dieSize2 > 0"> + {{ spell.damageDie2 }}d{{ spell.dieSize2 }} </span><span v-if="spell.bonusDamage1 + spell.bonusDamage2 != 0"> + {{ spell.bonusDamage1 + spell.bonusDamage2 }}</span></td>
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
                        <th>Magical Bonus</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="attack in allAttacks" v-bind:key="attack.attackId">
                        <td>
                            <input type="checkbox" v-bind:id="attack.attackId" v-bind:value="attack" v-model="enemy.attacks"/>
                        </td>
                        <td>{{ attack.name }}</td>
                        <td>{{ attack.actionCost }}</td>
                        <td>{{ attack.damageDie1 }}d{{ attack.dieSize1 }}<span v-if="attack.damageDie2 * attack.dieSize2 > 0"> + {{ attack.damageDie2 }}d{{ attach.dieSize2 }} </span><span v-if="attack.bonusDamage1 + attack.bonusDamage2 != 0"> + {{ attack.bonusDamage1 + attack.bonusDamage2}}</span></td>
                        <td><span v-if="attack.magic">{{ attack.magicBonus }}</span></td>
                    </tr>
                </tbody>
            </table>
            <button id="enemy-completion-button" v-on:click="addActions">Finish Adding Actions</button>
      </div>
      <div id="enemy-review-section" v-if="actionsAdded && enemyCreated">
          <p>The {{enemy.name }} you have created has an approximate Challenge Rating of {{ calcEnemy.challenge }}  with a experience reward of {{ calcEnemy.experience }}</p>
          <button v-on:click="createEnemy">Confirm Enemy Creation</button>
          <button v-on:click="remakeEnemy">Start Over</button>
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
            damageType: '',
            enemy: {
                enemyId: 0,
                name: 'goblin',
                description: 'A green humanoid',
                experience: Number,
                challenge: Number,
                hpMax: 15,
                baseAC: 12,
                maxActionPoints: 1.5,
                hpRegen: 0,
                profBonus: 1,
                attacks: [],
                spells: []
            },
            calcEnemy: {
                enemyId: 0,
                name: 'goblin',
                description: 'A green humanoid',
                experience: Number,
                challenge: Number,
                hpMax: 15,
                baseAC: 12,
                maxActionPoints: 1.5,
                hpRegen: 0,
                profBonus: 1,
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
            this.calcEnemy = this.enemy;
            this.calcEnemy.experience = this.experienceReward();
            this.calcEnemy.challenge = this.challengeRating();
            this.actionsAdded = true;
        },
        createEnemy() {
            combatService.createEnemy(this.enemy).then((response) => {
                this.enemy = response.data;
                combatService.updateEnemy(this.enemy);
            })
        },
        getDamageType(id) {
            console.log(id);
            this.damagetype = combatService.getDamageType(id);
            console.log(this.damagetype);
            return this.damagetype;
        },
        getSpellType(id) {
            if (id == 1) {
                return 'Combat';
            } else if (id == 2) {
                return 'Healing';
            } else if (id == 3) {
                return 'Utility';
            }
        },
        remakeEnemy() {
            this.enemyCreated = false;
            this.actionsAdded = false;
            this.enemy = {
                enemyId: 0,
                name: 'goblin',
                description: 'A green humanoid',
                experience: Number,
                challenge: Number,
                hpMax: 15,
                baseAC: 12,
                maxActionPoints: 1.5,
                hpRegen: 0,
                profBonus: 1,
                attacks: [],
                spells: []
            };
        },
        challengeRating() {
            return (((this.enemy.hpMax * this.enemy.baseAC * this.enemy.profBonus * this.enemy.maxActionPoints + (this.enemy.hpRegen + this.enemy.attacks.length + this.enemy.spells.length) * 100) / 300 + 1) ^ 0.5) / 2;
        },
        experienceReward() {
            return ((this.enemy.hpMax * this.enemy.baseAC * this.enemy.profBonus * this.enemy.maxActionPoints + (this.enemy.hpRegen + this.enemy.attacks.length + this.enemy.spells.length) * 100) / 300 + 1) * 100;
        }
    },
    created() {
        combatService.getAllEnemyAttacks().then(response => {
            this.allAttacks = response.data;
            combatService.getAllSpells().then(response => {
                this.allSpells = response.data;
            })
        });
    },
    computed: {
        
    }
}
</script>

<style>

</style>