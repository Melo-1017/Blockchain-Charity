<template>
  <el-timeline>
    <el-timeline-item
        v-for="(activity, index) in clist"
        :key="index"
        :timestamp="activity.timestamp"
        :type="activity.type || 'primary'"
        :color="activity.color || '#409EFF'"
        :size="activity.size || 'normal'"
    >
      {{ clist[index] }}
    </el-timeline-item>
  </el-timeline>
</template>

<script>
export default {
  name: "LogisticsWeb",
  data() {
    return {
      helplist: {},
      activities: [],
      cont:'',
      clist:[],
      icon:"logo.png"
    }
  },
  methods:{
  },
  created() {
    let listId = this.$route.query.listId
    let url = "helplist/"+listId;
    this.$http.get(url).then(resp=>{
      this.helplist = resp.data.data
      this.cont = this.helplist.logisticsInformation
      this.clist = this.cont.split('/')
      console.log(this.clist)
      // for (let i = 0; i < this.clist.length; i++) {
      //   this.activities[i].content = this.clist[i]
      // }
      // console.log(this.clist)
    })
  }
}
</script>

<style scoped>

</style>