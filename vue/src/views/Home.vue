<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <character-creation/>
  </div>
</template>

<script>
// @ is an alias to /src
import CharacterCreation from '../components/CharacterCreation.vue'

export default {
  name: 'Home',
  components: {
    CharacterCreation
  }
}
</script>
