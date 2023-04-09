<template>
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
  <el-table :data="pageInfo.list" stripe>
    <el-table-column prop="projectOrganizer" label="主办单位" width="300px" align="center" />
    <el-table-column prop="donationState" label="状态" width="100px"  align="center">
      <template #default="scope">
        {{getCondition(scope.row.donationState)}}
      </template>
    </el-table-column>
    <el-table-column prop="donationDemand" label="需求概述" width="550px"  align="center"/>
    <el-table-column prop="createDate" label="创建日期" width="250px"  align="center"/>
    <el-table-column label="操作" width="400px">
      <template #default="scope">
        <el-button v-if="scope.row.donationState === -1" size="small" type="success" @click="pass(scope.row.listId)">通过</el-button>
        <el-button v-if="scope.row.donationState !== -1" size="small" type="warning" @click="logistics(scope.row.listId)">物流</el-button>
        <el-button size="small" type="danger" @click="del(scope.row.listId)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      v-model:currentPage="pageInfo.pageNum"
      :page-size="pageInfo.pageSize"
      :background="true"
      :page-sizes="[3, 5, 10]"
      layout="total,sizes,prev, pager, next,jumper"
      :total="pageInfo.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
  />



</template>

<script>
export default {
  data(){
    return {
      roles:[],
      pageInfo:{},
      donationState:'',
      donationDemand:'',
      helplist:{},
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
          });
    },
    logistics(id){
      this.$router.push({
        path: '/LogisticsEdit',
        query:{
          cont : id
        }
      })
    },
    pass(id){
      let url = "helplist/"+id
      this.$http.get(url).then(resp=>{
        this.helplist = resp.data.data
        this.helplist.donationState = 0
        let urlNew = "helplist";
        this.$http.put(urlNew,this.helplist).then(resp=>{
          if(resp.data.data === 1){
            this.$message({message:"已通过审批！",type:'success'})
            console.log("已通过审批！")
            this.showData();
          }
        });
      })
    },
    del(id){
      this.$confirm("是否删除？","提示").then(()=>{
        let url = "helplist/"+id;
        this.$http.delete(url).then(resp=>{
          if(resp.data.data == 1){
            this.$message({message:"删除成功！",type:'success'})
            //重新加载数据
            this.showData();
          }else{
            // alert("删除失败！")
            this.$message.error("删除失败！")
          }
        });
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

<style>






</style>