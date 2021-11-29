<template>
  <div id="app">
    <TheHeader></TheHeader>
    <router-view></router-view>
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader";

export default {
  name: 'app',
  components:{
    TheHeader
  }
}
</script>

<style></style>
