<template>
  <div v-infinite-scroll="loadMore" infinite-scroll-throttle-delay="500" infinite-scroll-disabled="busy" infinite-scroll-distance="10">
    <div v-for="item in data" :key="item.index">{{item.name}}</div>
  </div>
</template>

<script>
export default {
  name: "infiniteScroll",
  data () {
    return {
      count: 0,
      data: [],
      busy: false
    }
  },
  methods: {
    loadMore: function() {
      this.busy = true
      setTimeout(() => {
        for (var i = 0, j = 10; i < j; i++) {
          this.data.push({name: this.count++ })
        }
        console.log(this.data)
        this.busy = false
      }, 1000)
    }
  }
}
</script>

<style scoped>

</style>