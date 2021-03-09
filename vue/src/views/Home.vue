<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <character-creation/>
    <enemy-creation />
  </div>
</template>

<script>
// @ is an alias to /src
import CharacterCreation from '../components/CharacterCreation.vue'
import EnemyCreation from '../components/EnemyCreation.vue'

export default {
  name: 'Home',
  components: {
    CharacterCreation,
    EnemyCreation
  }
}
</script>
