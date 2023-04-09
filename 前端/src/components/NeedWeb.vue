<template>
  <el-form label-width="100px" style="width: 350px">
    <el-form-item label="用户名：">
      {{getUserName()}}
    </el-form-item>
    <el-form-item label="需求概述：">
      <el-input v-model="helplist.donationDemand" placeholder="请简述你的需求" clearable ></el-input>
    </el-form-item>
    <el-form-item label="主办单位:">
      <el-select v-model="helplist.projectOrganizer" placeholder="请选择主办单位">
        <el-option
            v-for="role in roles"
            :key="role.id"
            :value="role.id"
            :label="role.name"
        >
        </el-option>
      </el-select>
    </el-form-item>
<!--    <el-form-item label="需求证明：">-->
<!--      <el-upload-->
<!--          v-model:file-list="fileList"-->
<!--          class="upload-demo"-->
<!--          action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"-->
<!--          multiple-->
<!--          :on-preview="handlePreview"-->
<!--          :on-remove="handleRemove"-->
<!--          :before-remove="beforeRemove"-->
<!--          :limit="3"-->
<!--          :on-exceed="handleExceed"-->
<!--      >-->
<!--        <el-button type="primary">Click to upload</el-button>-->
<!--        <template #tip>-->
<!--          <div class="el-upload__tip">-->
<!--            jpg/png files with a size less than 500KB.-->
<!--          </div>-->
<!--        </template>-->
<!--      </el-upload>-->
<!--    </el-form-item>-->
    <el-form-item >
      <el-button type="primary" @click="add">提交</el-button>
      <el-button type="default" @click="reset">重置</el-button>
    </el-form-item>
  </el-form>
</template>


<script>
import Web3 from "web3";

export default {
  name: "NeedWeb",
  data(){
    return {
      helplist:{},
      roles:[],
      number:0
    }
  },
  methods:{
    getUserName(){
      return sessionStorage.getItem("userName")
    },
    add() {
      this.helplist.recipientId = sessionStorage.getItem("userId")
      this.helplist.donationState = -1
      this.helplist.createDate = this.getDate()
      switch (this.helplist.projectOrganizer)
      {
        case 0:this.helplist.projectOrganizer="四川红十字会";break;
        case 1:this.helplist.projectOrganizer="临港片区志愿者协会";break;
        case 2:this.helplist.projectOrganizer="上海疾控";break;
        case 3:this.helplist.projectOrganizer="云南心连心公益小组";break;
        case 4:this.helplist.projectOrganizer="慈溯自营";break;
      }
      let url = "helplist";
      this.$http.post(url,this.helplist).then(resp=>{
        if(resp.data.data == 1){
          alert("提交成功")
          let url1="helplist/getNewId";
          this.$http.get(url1).then(resp=>{
            this.number=resp.data.data;
            console.log(this.number);

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
            console.log(this.number,this.helplist.createDate,this.helplist.donationDemand+this.helplist.projectOrganizer)
            let txo = {
              from: account1,
              to: "0x91Ca69003Cfbcc0Cc93B91cc357c019Aa7551804",
              value:'0x00',
              data: contract.methods.declaring(this.number,this.helplist.createDate,this.helplist.donationDemand+this.helplist.projectOrganizer).encodeABI()
            }
            web3.eth.sendTransaction(txo, (err, txHash) => {
              console.log(err+txHash)
            })
          })

        }
      });
    },
    reset(){
      this.helplist = {}
    },
    // 获取当前时间
    getDate() {
      const myDate = new Date()
      //获取当前年
      const year = myDate.getFullYear()
      //获取当前月
      const month = myDate.getMonth() + 1
      //获取当前日
      const date = myDate.getDate()
      //获取当前小时数(0-23)
      const h = myDate.getHours()
      //获取当前分钟数(0-59)
      const m = myDate.getMinutes()
      const s = myDate.getSeconds()

      //获取当前时间
      const time =
          year +
          '-' +
          this.convert(month) +
          '-' +
          this.convert(date) +
          ' ' +
          this.convert(h) +
          ':' +
          this.convert(m) +
          ':' +
          this.convert(s)
      return time
    },
    //日期时间处理
    convert(val) {
      return val < 10 ? '0' + val : val
    },
    convertDateFromString(dateString)
    {
    if (dateString)
    {
      let arr1 = dateString.split(" ");
      let sdate = arr1[0].split('-');
      return new Date(sdate[0], sdate[1] - 1, sdate[2]);
    }
    }

  },
  created() {
    this.roles = [
      {id:0,name:"四川红十字会"},
      {id:1,name:"临港片区志愿者协会"},
      {id:2,name:"上海疾控"},
      {id:3,name:"云南心连心公益小组"},
      {id:4,name:"慈溯自营"}
    ]
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
</style>