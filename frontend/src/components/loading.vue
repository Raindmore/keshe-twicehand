<template>
<div class="loading"></div>
</template>

<script>
export default {
  name: 'Loading' 
}
</script>

<style scoped>
.loading {
  position: fixed;
  left: 0;
  top: 0;
  background: url('../assets/loading (2).gif') center center no-repeat rgba(232,240,254,0.85);
  width: 100vw;
  height: 100vh;
  z-index: 1000;
}
</style>
