<template>
  <el-carousel :interval="4000" type="card" height="350px" >
    <el-carousel-item v-for="item in imgList" :key="item.name">
      <img :src="item.src" style="height:100%;width:100%;" alt="图片丢失了" :title="item.title" />
    </el-carousel-item>
  </el-carousel>
  <el-form :inline="true" size="small" align="center">
    <el-form-item label="需求状态：">
      <el-select  placeholder="全部" v-model="donationState">
        <el-option
            v-for="role in roles"
            :key="role.id"
            :value="role.id"
            :label="role.name"
        >
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="需求概述：">
      <el-input v-model="donationDemand"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="danger" @click="clearForm">清空</el-button>
      <el-button @click="showData">查询</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="pageInfo.list" style="width: 100%;margin: auto" stripe highlight-current-row @cell-click="Details">
      <el-table-column prop="projectOrganizer" label="主办单位" width="300" align="center" />
      <el-table-column prop="donationState" label="状态" width="400"  align="center">
        <template #default="scope">
          {{getCondition(scope.row.donationState)}}
        </template>
      </el-table-column>
      <el-table-column prop="donationDemand" label="需求概述" width="500"  align="center"/>
      <el-table-column prop="createDate" label="创建日期" width="200"  align="center"/>
  </el-table>
  <el-pagination
      v-model:currentPage="pageInfo.pageNum"
      :page-size="pageInfo.pageSize"
      :page-sizes="[3, 5, 10]"
      layout="total,sizes,prev, pager, next,jumper"
      :total="pageInfo.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
  />
</template>

<script>
export default {
  name: "DonateWeb",
  data(){
    return {
      act:true,
      roles:[],
      pageInfo:{},
      donationState:'',
      donationDemand:'',
      imgList: [
        {
          name: "1",
          src: require("@/assets/1.png"),
          title: "这是1.png"
        },
        {
          name: "2",
          src: require("@/assets/2.jpeg"),
          title: "这是2.png"
        },
        {
          name: "3",
          src: require("@/assets/3.jpg"),
          title: "这是3.png"
        },
        {
          name: "4",
          src: require("@/assets/4.jpg"),
          title: "这是4.png"
        },
        {
          name: "5",
          src: require("@/assets/5.jpg"),
          title: "这是5.png"
        },
        {
          name: "6",
          src: require("@/assets/6.png"),
          title: "这是6.png"
        },
      ]
    }
  },
  methods:{
    showData(){
      let url="helplist/getPaged";
      this.$http.get(url,{params:{
          pageNum:this.pageInfo.pageNum,
          pageSize:this.pageInfo.pageSize,
          donationState:this.donationState,
          donationDemand:this.donationDemand
        }})
          .then(resp=>{
            //resp:响应对象
            //resp.data:响应对象中的数据（Result）
            //resp.data.data:PageInfo
            this.pageInfo = resp.data.data;
            console.log(this.pageInfo);
            //console.log(this.pageInfo.list[0].listId)
          });
    },
    Details(row){
      this.$router.push({
        path: '/DetailsWeb',
        query:{
          id : row.listId
        }
      })
    },
    handleCurrentChange(pageNum){
      this.pageInfo.pageNum = pageNum;
      this.showData()
    },
    handleSizeChange(pageSize){
      this.pageInfo.pageSize = pageSize;
      this.showData()
    },
    clearForm(){
      this.donationState = "";
      this.donationDemand = "";
      this.showData();
    },
    getCondition(donationCondition){
      let conditon;
      if (donationCondition===0){
        conditon="未被帮助";
      }
      else if (donationCondition===1)
        conditon="已被帮助";
      else
        conditon="待审核";
      return conditon;
    }
  },
  created() {
    this.roles = [
      {id:0,name:"未被帮助"},
      {id:1,name:"已被帮助"},
      {id:-1,name:"待审核"}
    ]
    this.showData();
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.el-pagination{
  text-align:center;
  margin-top:20px;
}
</style>