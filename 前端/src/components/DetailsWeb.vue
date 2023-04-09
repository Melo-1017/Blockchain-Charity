<template>
  <el-divider></el-divider>
  <el-row :gutter="100">
    <el-col :span="6"><div class="grid-content bg-purple">详细信息</div></el-col>
    <el-col :span="10"><div class="grid-content bg-purple">{{pageInfo.donationDemand}}</div></el-col>
  </el-row>
  <el-row :gutter="100">
    <el-col :span="6"><div class="grid-content bg-purple">创建时间</div></el-col>
    <el-col :span="10"><div class="grid-content bg-purple">{{pageInfo.createDate}}</div></el-col>
  </el-row>
  <el-row :gutter="100">
    <el-col :span="6"><div class="grid-content bg-purple">捐赠哈希</div></el-col>
    <el-col :span="10"><div class="grid-content bg-purple">{{this.infohash}}</div></el-col>
  </el-row>
  <el-divider></el-divider>
  <span >当前进度</span>
  <el-timeline>
    <el-timeline-item timestamp="2018/4/12" placement="top">
      <el-card>
        <h4>完成</h4>
        <p>2022/10/12 20:46</p>
      </el-card>
    </el-timeline-item>
    <el-timeline-item timestamp="2018/4/3" placement="top">
      <el-card>
        <h4>补充信息</h4>
        <p>2022/10/11 20:46</p>
      </el-card>
    </el-timeline-item>
    <el-timeline-item timestamp="2018/4/2" placement="top">
      <el-card>
        <h4>发布信息</h4>
        <p>2022/10/10 20:46</p>
      </el-card>
    </el-timeline-item>
  </el-timeline>
  <el-divider></el-divider>
  <el-row :gutter="60">
    <el-col :span="6"><div class="grid-content bg-purple">用户评价</div></el-col>
    <el-col :span="6"><div class="grid-content bg-purple">{{pageInfo.recipientResponse}}</div></el-col>
  </el-row>

</template>

<script>
import Web3 from "web3";


export default {
  name: 'App',
  data(){
    return{
      pageInfo:{},
      DetailsId:'',
      value: null,
      iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'],
      infohash:''
    }
  },
  methods:{
    showData(){
      let url='helplist/'+this.DetailsId;
      this.$http.get(url)
          .then(resp=>{
            this.pageInfo = resp.data.data;
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
            contract.methods.getinfo(this.DetailsId).call((err,log)=>{
              console.log(log)
              this.infohash=log.hash
            });
          });
    }
  },
  created() {
    this.DetailsId=this.$route.query.id;
    this.showData();

  },
  //注册组件
  components:{

  }
}
</script>

<style scoped>
.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #ced5dc;
  color: #333;
  text-align: center;
  line-height: 160px;
}
.el-card{
  height: 200px;
  width: auto;
}
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.el-timeline{
  margin-top:15px;
}
</style>