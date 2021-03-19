import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const currentPlayer = localStorage.getItem('player');
const currentEnemy = localStorage.getItem('enemy');

export default new Vuex.Store({

    state: {
    
        
        currentPlayer: {
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
          boost1: 1, //Determines highest starting stat, affects upgrade cost
          boost2: 2, //Determines second highest starting stat, affects upgrade cost
          attacks: [],
          spells: []
        },

        currentEnemy: {
          enemyId: 0,
          name: '',
          experience: 0,
          hpMax: 0,
          baseAC: 0,
          challenge: 0,
          profBonus: 0,
          maxActionPoints: 0,
          hpRegen: 0,
          description: '',
          attacks: [],
          spells: []
        },

        enemies: [],
        allSpells: [],
        allAttacks: [],
        allEnemyAttacks: [],
        
    
    
        player: currentPlayer || {},
        enemy: currentEnemy || {},
      },
      mutations: {
    
        SET_CURRENT_PLAYER(state, player) {
          state.player = player;
          localStorage.setItem('player', player);
        },
        SET_CURRENT_ENEMY(state, enemy) {
          state.enemy = enemy;
          localStorage.setItem('enemy', enemy);
        },
        SET_ENEMIES(state, enemies) {
          state.enemies = enemies;
        },
        SET_SPELLS(state, spells) {
          state.allSpells = spells;
        },
        SET_ATTACKS(state, attacks) {
          state.allAttacks = attacks;
        },
        SET_ENEMY_ATTACKS(state, attacks) {
          state.allEnemyAttacks = attacks;
        },
        ADD_ENEMY(state, enemy) {
          state.enemies.unshift(enemy);
        },
        ADD_SPELL(state, spell) {
          state.allSpells.unshift(spell);
        },
        ADD_ATTACK(state, attack) {
          state.allAttacks.unshift(attack);
        },
        ADD_ENEMY_ATTACK(state, attack) {
          state.allEnemyAttacks.unshift(attack);
        },

      }
})