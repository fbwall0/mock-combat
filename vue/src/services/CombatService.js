import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080/api'
});

export default {
    damageTypes: ['Bludgeoning', 'Piercing', 'Slashing', 'Radiant', 'Necrotic', 'Fire', 'Cold', 'Lightning', 'Thunder', 'Force', 'Psychic', 'Poison', 'Acid'],

    getAllPlayers() {
        return http.get('/players');
    },

    getAllEnemies() {
        return http.get('/enemies');
    },

    getAllSpells() {
        return http.get('/spells');
    },

    getAllAttacks() {
        return http.get('/attacks');
    },

    getAllEnemyAttacks() {
        return http.get('/attacksE');
    },

    getPlayer(playerId) {
        return http.get(`/player/${playerId}`);
    },

    getEnemy(enemyId) {
        return http.get(`/enemy/${enemyId}`);
    },

    getSpell(spellId) {
        return http.get(`/spell/${spellId}`);
    },

    getAttack(attackId) {
        return http.get(`/attack/${attackId}`);
    },

    getEnemyAttack(attackId) {
        return http.get(`/attackE/${attackId}`);
    },

    getUnknownActions(playerId) {
        return http.get(`/player/${playerId}/learn`);
    },

    getUnknownSpells(playerId) {
        return http.get(`/player/${playerId}/learn/spells`);
    },

    getUnknownAttacks(playerId) {
        return http.get(`/player/${playerId}/learn/attacks`);
    },

    getUnknownEnemyActions(enemyId) {
        return http.get(`/enemy/${enemyId}/learn`);
    },

    getUnknownEnemySpells(enemyId) {
        return http.get(`/enemy/${enemyId}/learn/spells`);
    },

    getUnknownEnemyAttacks(enemyId) {
        return http.get(`/enemy/${enemyId}/learn/attacks`);
    },

    createPlayer() {
        return http.post('/player');
    },

    createEnemy(enemy) {
        return http.post('/enemy', enemy);
    },

    createAttack(attack) {
        return http.post('/attack', attack);
    },

    createEnemyAttack(attack) {
        return http.post('/attackE', attack);
    },

    createSpell(spell) {
        return http.post('/spell', spell);
    },

    deletePlayer(playerId) {
        http.delete(`/${playerId}`);
    },

    deleteEnemy(enemyId) {
        http.delete(`/enemy/${enemyId}`);
    },

    learnSpell(playerId, spellId) {
        return http.get(`/player/${playerId}/learn/spell/${spellId}`);
    },

    learnAttack(playerId, attackId) {
        return http.get(`/player/${playerId}/learn/attack/${attackId}`);
    },

    enemyLearnSpell(enemyId, spellId) {
        return http.get(`/enemy/${enemyId}/learn/spell/${spellId}`);
    },

    enemyLearnAttack(enemyId, attackId) {
        return http.get(`/enemy/${enemyId}/learn/attack/${attackId}`);
    },

    updatePlayer(player) {
        return http.put('/player', player);
    },

    updateEnemy(enemy) {
        return http.put('/enemy', enemy);
    },

    getPlayerAttacks(playerId) {
        return http.get(`/player/${playerId}/attacks`);
    },

    getPlayerSpells(playerId) {
        return http.get(`/player/${playerId}/spells`);
    },

    getEnemyAttacks(enemyId) {
        return http.get(`/enemy/${enemyId}/attacks`);
    },

    getEnemySpells(enemyId) {
        return http.get(`/enemy/${enemyId}/spells`);
    },

    getDamageType(id) {
        return this.damageTypes[id - 1];
        //return http.get(`/damageType/${id}`);
    }
}