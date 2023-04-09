<template>
  <el-form label-width="100px" style="width: 350px">
    <el-form-item>当前正在变更物流信息！</el-form-item>
    <el-form-item label="管理员名：">
      {{getUserName()}}
    </el-form-item>
    <el-form-item label="物流情况：">
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
    </el-form-item>
    <el-form-item label="新增物流：">
      <el-input v-model="inp" placeholder="Please input" > </el-input>
    </el-form-item>



    <el-form-item >
      <el-button type="primary" @click="add">提交</el-button>
      <el-button type="default" @click="reset">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import Web3 from "web3";
export default {
  name: "LogisticsEdit",
  data(){
    return {
      DetailsId:"",
      helplist: {},
      con:'',
      clist:[],
      inp:'',
    }
  },
  methods:{
    showData(){
      this.DetailsId=this.$route.query.cont;
      console.log(this.DetailsId)
      let listId = this.$route.query.cont
      let url = "helplist/"+listId;
      this.$http.get(url).then(resp=>{
        this.helplist = resp.data.data
        this.con = this.helplist.logisticsInformation
        console.log(this.con)
        if(this.con!==''&&this.con!=='null')
        {
          this.clist = (this.con ||"").split('/')
        }
      })
    },
    add(){
      if(this.helplist.logisticsInformation!==''&&this.helplist.logisticsInformation!==null) {
        this.helplist.logisticsInformation = this.helplist.logisticsInformation + '/' + this.inp
      }
      else {
        this.helplist.logisticsInformation = this.inp
        console.log("无内容")
      }
      let web3;
      const data = [
        {
          "inputs": [
            {
              "internalType": "int256",
              "name": "id",
              "type": "int256"
            },
            {
              "internalType": "string",
              "name": "time",
              "type": "string"
            },
            {
              "internalType": "string",
              "name": "information",
              "type": "string"
            }
          ],
          "name": "declaring",
          "outputs": [],
          "stateMutability": "nonpayable",
          "type": "function"
        },
        {
          "inputs": [
            {
              "internalType": "int256",
              "name": "id",
              "type": "int256"
            }
          ],
          "name": "getinfo",
          "outputs": [
            {
              "components": [
                {
                  "internalType": "string",
                  "name": "time",
                  "type": "string"
                },
                {
                  "internalType": "bytes32",
                  "name": "hash",
                  "type": "bytes32"
                }
              ],
              "internalType": "struct Mappings.Employee",
              "name": "",
              "type": "tuple"
            }
          ],
          "stateMutability": "view",
          "type": "function"
        }
      ];
      web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:12345"));
      const contract = new web3.eth.Contract(data, '0x91Ca69003Cfbcc0Cc93B91cc357c019Aa7551804');
      const account1 = '0xaaB9CE2544758C4f1336bC888E98B16ee2d05E0C'
      console.log(this.helplist.listId,this.helplist.createDate,this.helplist.donationDemand+this.helplist.projectOrganizer+this.helplist.logisticsInformation)
      let txo = {
        from: account1,
        to: "0x91Ca69003Cfbcc0Cc93B91cc357c019Aa7551804",
        value:'0x00',
        data: contract.methods.declaring(this.helplist.listId,this.helplist.createDate,this.helplist.donationDemand+this.helplist.projectOrganizer+this.helplist.logisticsInformation).encodeABI()
      }
      web3.eth.sendTransaction(txo, (err, txHash) => {
        console.log(err+txHash)
      })

      let url = "helplist";
      this.$http.put(url,this.helplist).then(resp=>{
        if (resp.data.data == 1) {
          this.$message({message:"变更完成！",type:'success'})
          this.reset()
          this.showData();
        }
      })

    },
    reset(){
      this.inp=''
    },
    getUserName(){
      return sessionStorage.getItem("userName")
    },
  },
  created() {
    this.showData();
  }
}
</script>

<style scoped>

</style>