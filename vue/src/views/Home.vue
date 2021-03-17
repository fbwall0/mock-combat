<template>
  <div id="main">
    <div class="home">
      <img alt="Vue logo" src="../assets/logo.png">
      <p>Character Creation Demo</p>
      <character-creation/>
      <br />
      <br />
      <p>Enemy Creation Demo</p>
      <enemy-creation />
      <br />
      <br />
      <p>Spell Creation Demo</p>
      <spell-creation />
      <br />
      <br />
      <p>Attack Creation Demo</p>
      <attack-creation />
      <br />
      <br />
      <p>Character Selection</p>
      <select-character />
      <br />
      <br />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import CharacterCreation from '../components/CharacterCreation.vue'
import EnemyCreation from '../components/EnemyCreation.vue'
import SpellCreation from '../components/SpellCreation.vue'
import AttackCreation from '../components/AttackCreation.vue'
import SelectCharacter from '../components/SelectCharacter.vue'

export default {
  name: 'Home',
  components: {
    CharacterCreation,
    EnemyCreation,
    SpellCreation,
    AttackCreation,
    SelectCharacter
  }
}
</script>
