<template>
  <div id="main">
      <div id="character-stat" v-if="characterLoaded">
        <div id="current-character">
            <p>Current Character Stats</p>
            <p>Name: {{ this.$store.state.player.name }}</p>
            <p>Max HP: {{ this.$store.state.player.hpMax + (this.$store.state.player.constitution - 10) * 5 + (this.$store.state.player.profBonus * 5) }}</p>
            <p>Max Mana: {{ this.$store.state.player.manaMax + (this.$store.state.player.magic - 10) * 5 + (this.$store.state.player.profBonus * 5) }}</p>
            <p>Armor: {{ this.$store.state.player.baseAC + (this.$store.state.player.dexterity - 10) / 2 + (this.$store.state.player.profBonus) }}</p>
            <p>Proficiency Bonus: {{ this.$store.state.player.profBonus }}</p>
            <p>Action Points: {{ 1.5 + Number(Math.round((this.$store.state.player.profBonus - 1) / 2)) / 2 }}</p>
            <p>Strength: {{ this.$store.state.player.strength }}</p>
            <p>Dexterity: {{ this.$store.state.player.dexterity }}</p>
            <p>Constitution: {{ this.$store.state.player.constitution }}</p>
            <p>Magical Ability: {{ this.$store.state.player.magic }}</p>
            <p>Health Regen: {{ this.$store.state.player.hpRegen }}</p>
            <p>Mana Regen: {{ this.$store.state.player.manaRegen }}</p>
            <p>Primary Specialization: {{ specialty1 }}</p>
            <p>Secondary Specialization: {{ specialty2 }}</p>
        </div>
        <br />
        <div id="stat-boosts" v-if="!midLevel">
            <p>Available Experience: {{ futureCharacter.xp }}</p>
            <br />
            <div v-if="profIncreaseCost <= futureCharacter.xp">
                <button v-on:click="profIncrease(profIncreaseCost)">Increase Proficiency</button> Cost: {{ profIncreaseCost }}
            </div>
            <br />
            <div v-if="strIncreaseCost <= futureCharacter.xp">
                <button v-on:click="strIncrease(strIncreaseCost)">Increase Strength</button> Cost: {{ strIncreaseCost }}
            </div>
            <br />
            <div v-if="dexIncreaseCost <= futureCharacter.xp">
                <button v-on:click="dexIncrease(dexIncreaseCost)">Increase Dexterity</button> Cost: {{ dexIncreaseCost }}
            </div>
            <br />
            <div v-if="conIncreaseCost <= futureCharacter.xp">
                <button v-on:click="conIncrease(conIncreaseCost)">Increase Constitution</button> Cost: {{ conIncreaseCost }}
            </div>
            <br />
            <div v-if="magIncreaseCost <= futureCharacter.xp">
                <button v-on:click="magIncrease(magIncreaseCost)">Increase Magic</button> Cost: {{ magIncreaseCost }}
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
            <p>Armor: {{ futureCharacter.baseAC + Number(Math.round((futureCharacter.dexterity - 11) / 2)) + (futureCharacter.profBonus) }}</p>
            <p>Proficiency Bonus: {{ futureCharacter.profBonus }}</p>
            <p>Action Points: {{ 1.5 + Number(Math.round((futureCharacter.profBonus - 1) / 2)) / 2 }}</p>
            <p>Strength: {{ futureCharacter.strength }}</p>
            <p>Dexterity: {{ futureCharacter.dexterity }}</p>
            <p>Constitution: {{ futureCharacter.constitution }}</p>
            <p>Magical Ability: {{ futureCharacter.magic }}</p>
            <p>Health Regen: {{ futureCharacter.hpRegen }}</p>
            <p>Mana Regen: {{ futureCharacter.manaRegen }}</p>
        </div>
        
      </div>

      <div id="spell-learning">
          <p>Spells to Learn</p>
          <table>
              <thead>
                  <tr>
                    <th></th>
                    <th>Name</th>
                    <th>|</th>
                    <th>Experience Cost</th>
                    <th>|</th>
                    <th>Mana Cost</th>
                    <th>|</th>
                    <th>Damage 1</th>
                    <th>|</th>
                    <th>Damage 2</th>
                    <th>|</th>
                    <th>Type</th>
                  </tr>
              </thead>
              <tbody>
                <tr v-for="spell in unknownSpells" v-bind:key="spell.spellId">
                    
                    <td>
                        <input type="checkbox" v-bind:id="spell.spellId" v-bind:value="spell" v-model="selectedSpells" />
                    </td>
                    <td>{{ spell.name }}</td>
                    <td>|</td>
                    <td>{{ spell.xpCost }}</td>
                    <td>|</td>
                    <td>{{ spell.manaCost }}</td>
                    <td>|</td>
                    <td>{{spell.damageDie1}}d{{spell.dieSize1}} {{spell.bonusDamage1 != 0 ? (spell.bonusDamage1 > 0 ? '+ ' : '- ') + Math.abs(spell.bonusDamage1) : ''}} {{damageType(spell.damageType1)}}</td>
                    <td>|</td>
                    <td v-if="spell.hasSecondDamage">{{spell.damageDie2}}d{{spell.dieSize2}} {{spell.bonusDamage2 != 0 ? (spell.bonusDamage2 > 0 ? '+ ' : '- ') + Math.abs(spell.bonusDamage2) : ''}} {{damageType(spell.damageType2)}}</td>
                    <td v-if="!spell.hasSecondDamage"></td>
                    <td>|</td>
                    <td>{{ spellType(spell.spellType) }}</td>
                </tr>
              </tbody>
          </table>
      </div>
      <div id="attack-learning">
          <p>Attacks to Learn</p>
          <table>
              <thead>
                  <tr>
                    <th></th>
                    <th>Name</th>
                    <th>|</th>
                    <th>Experience Cost</th>
                    <th>|</th>
                    <th>Damage 1</th>
                    <th>|</th>
                    <th>Damage 2</th>
                    <th>|</th>
                    <th>Magic Bonus</th>
                  </tr>
              </thead>
              <tbody>
                <tr v-for="attack in unknownAttacks" v-bind:key="attack.attackId">
                    <td>
                        <input type="checkbox" v-bind:id="attack.attackId" v-bind:value="attack" v-model="selectedAttacks" />
                    </td>
                    <td>{{ attack.name }}</td>
                    <td>|</td>
                    <td>{{ attack.xpCost }}</td>
                    <td>|</td>
                    <td>{{attack.damageDie1}}d{{attack.dieSize1}} {{attack.bonusDamage1 != 0 ? (attack.bonusDamage1 > 0 ? '+ ' : '- ') + Math.abs(attack.bonusDamage1) : ''}} {{damageType(attack.damageType1)}}</td>
                    <td>|</td>
                    <td v-if="attack.hasSecondDamage">{{attack.damageDie2}}d{{attack.dieSize2}} {{attack.bonusDamage2 != 0 ? (attack.bonusDamage2 > 0 ? '+ ' : '- ') + Math.abs(attack.bonusDamage2) : ''}} {{damageType(attack.damageType2)}}</td>
                    <td v-if="!attack.hasSecondDamage"></td>
                    <td>|</td>
                    <td v-if="attack.magic">{{ attack.magicBonus }}</td>
                    <td v-if="!attack.magic"></td>
                </tr>
              </tbody>
          </table>
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
            specialty1: '',
            specialty2: '',
            baseCharacter: {},
            futureCharacter: {
                playerId: 0
            },
            selectedSpells: [],
            selectedAttacks: [],


        }
    },
    methods: {
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
        setBase() {
            this.baseLoaded = false;
            combatService.getPlayer(this.$store.state.player.playerId).then((response) => {
                this.baseCharacter = response.data;
                this.baseLoaded = true;
            });
        },
        resetFutureCharacter() {
            this.characterLoaded = false;
            this.midLevel = true,
            combatService.getPlayer(this.$store.state.player.playerId).then((response) => {
                this.baseCharacter = response.data;
                this.specialty1 = this.specialty(this.baseCharacter.boost1);
                this.specialty2 = this.specialty(this.baseCharacter.boost2);
                this.futureCharacter = this.baseCharacter;
                combatService.getUnknownSpells(this.$store.state.player.playerId).then((response) => {
                    this.unknownSpells = response.data;
                    this.spellsLoaded = true;
                    combatService.getUnknownAttacks(this.$store.state.player.playerId).then((response) => {
                        this.unknownAttacks = response.data;
                        this.attacksLoaded = true;
                        //for testing
                        this.futureCharacter.xp = 10000;
                        this.characterLoaded = true;
                        this.midLevel = false;
                    });
                });
            })

        },
        profIncrease(profIncreaseCost) {
            this.midLevel = true;
            this.spendXp(profIncreaseCost);
            this.futureCharacter.profBonus = this.futureCharacter.profBonus + 1;
            this.midLevel = false;
        },
        strIncrease(strIncreaseCost) {
            this.midLevel = true;
            this.spendXp(strIncreaseCost);
            this.futureCharacter.strength = this.futureCharacter.strength + 1;
            this.midLevel = false;
        },
        dexIncrease(dexIncreaseCost) {
            this.midLevel = true;
            this.spendXp(dexIncreaseCost);
            this.futureCharacter.dexterity = this.futureCharacter.dexterity + 1;
            this.midLevel = false;
        },
        conIncrease(conIncreaseCost) {
            this.midLevel = true;
            this.spendXp(conIncreaseCost);
            this.futureCharacter.constitution = this.futureCharacter.constitution + 1;
            this.midLevel = false;
        },
        magIncrease(magIncreaseCost) {
            this.midLevel = true;
            this.spendXp(magIncreaseCost);
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
        },
        spellType(id) {
            if (id == 3) {
                return 'Utility';
            }
            else if (id == 2) {
                return 'Healing';
            }
            else {
                return 'Damage';
            }
        },
        damageType(id) {
            switch(id) {
                case 1: return 'Bludgeoning';
                case 2: return 'Piercing';
                case 3: return 'Slashing';
                case 4: return 'Radiant';
                case 5: return 'Necrotic';
                case 6: return 'Fire';
                case 7: return 'Cold';
                case 8: return 'Lightning';
                case 9: return 'Thunder';
                case 10: return 'Force';
                case 11: return 'Psychic';
                case 12: return 'Poison';
                case 13: return 'Acid';
                default: return 'Divine';
            }
        }
    },
    created() {
        this.resetFutureCharacter;
        
        
    },
    computed: {
        profIncreaseCost() {
            return ((this.futureCharacter.profBonus - (this.baseCharacter.boost1 == 5 ? 1 : 0)) * (this.futureCharacter.profBonus - (this.baseCharacter.boost1 == 5 ? 1 : 0) - 1) + 1) * 300;
        },
        strIncreaseCost() {
            return (this.futureCharacter.strength - 9 - (this.baseCharacter.boost1 == 1 ? 4 : 0) - (this.baseCharacter.boost2 == 1 ? 2 : 0)) * 200;
        },
        dexIncreaseCost() {
            return (this.futureCharacter.dexterity - 9 - (this.baseCharacter.boost1 == 2 ? 4 : 0) - (this.baseCharacter.boost2 == 2 ? 2 : 0)) * 200;
        },
        conIncreaseCost() {
            return (this.futureCharacter.constitution - 9 - (this.baseCharacter.boost1 == 3 ? 4 : 0) - (this.baseCharacter.boost2 == 3 ? 2 : 0)) * 200;
        },
        magIncreaseCost() {
            return (this.futureCharacter.magic - 9 - (this.baseCharacter.boost1 == 4 ? 4 : 0) - (this.baseCharacter.boost2 == 4 ? 2 : 0)) * 200;
        },
        hpRegenIncreaseCost() {
            return ((this.futureCharacter.hpRegen) * (this.futureCharacter.hpRegen) + 1) * 250;
        },
        manaRegenIncreaseCost() {
            return (Number(this.futureCharacter.manaRegen) * Number(this.futureCharacter.manaRegen) + 1) * 250;
        },
        spellLearnCost() {
            let xpCost = 0;
            this.selectedSpells.forEach(spell => {
                xpCost = xpCost + spell.xpCost;
            });
            return xpCost;
        },
        attackLearnCost() {
            let xpCost = 0;
            this.selectedAttacks.forEach(attack => {
                xpCost = xpCost + attack.xpCost;
            });
            return xpCost;
        }
    }
}
</script>

<style>

</style>