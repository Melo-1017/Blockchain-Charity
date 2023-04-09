<template>
  <div style="background-color:#FCFCFC;font-family:'宋体';height:100%;">
    <div style="margin-top:25px;margin-left:80px;">
      <el-row :gutter="10">
        <el-col :span="2">
          <div style="margin-top:5px;margin-left:6px;"><span>{{user.userName}}</span></div>
          <div style="margin-top:50px;margin-left:1px;"><el-button  class="el-buttons"  style="font-size:15px;color:#4D4D4D;"  @click="informationClick()">个人信息<span style="color:#B0E0E6;" v-show="informationShow" class="el-icon-s-promotion"></span></el-button></div>
          <div style="margin-top:5px;margin-left:1px;"><el-button class="el-buttons"  style="font-size:15px; color:#4D4D4D;" @click="informationReviseClick()">修改信息<span style="color:#B0E0E6;" v-show="informationReviseShow" class="el-icon-s-promotion"></span></el-button></div>
          <div style="margin-top:5px;margin-left:1px;"><el-button  class="el-buttons" style="font-size:15px;color:#4D4D4D;" @click="passwordClick()">修改密码<span style="color:#B0E0E6;" v-show="passwordShow" class="el-icon-s-promotion"></span></el-button></div>

        </el-col>

        <el-col :span="21">
          <!-- 个人信息 -->
          <el-row v-show="informationShow">
            <el-card style="margin-top:20px; width: 900px">
              <el-row>
                <el-col :span="10">
                  <el-row :gutter="12" style="margin-top:20px;">
                    <el-col :span="10" ><div style="text-align:right;"><span>用户名</span></div></el-col>&nbsp;{{user.userName}}
                  </el-row>

                  <el-row :gutter="12" style="margin-top:30px;">
                    <el-col :span="10"><div style="text-align:right;"><span>性别</span></div></el-col>&nbsp;{{getGenderName(user.userGender)}}
                  </el-row>

                  <el-row :gutter="12" style="margin-top:30px;">
                    <el-col :span="10"><div style="text-align:right;"><span>年龄</span></div></el-col>&nbsp;{{user.userAge}}
                  </el-row>

                  <el-row :gutter="12" style="margin-top:30px;">
                    <el-col :span="10"><div style="text-align:right;"><span>角色</span></div></el-col>&nbsp;{{getRoleName(user.userRoleId)}}
                  </el-row>

                  <el-row :gutter="12" style="margin-top:30px;">
                    <el-col :span="10"><div style="text-align:right;"><span>电话号码</span></div></el-col>&nbsp;{{user.userPhone}}
                  </el-row>

                  <el-row :gutter="12" style="margin-top:30px;">
                    <el-col :span="10"><div style="text-align:right;"><span>邮箱</span></div></el-col>&nbsp;{{user.userEmail}}
                  </el-row>

                </el-col>
              </el-row>
            </el-card>
            <div style="background-color:#FCFCFC;font-family:'宋体';height:100%;">
              <el-card style="margin-top:20px; width:950px">
              <div style="margin-top:25px;margin-left:0;">
                <el-table :data="pageInfo.list" style="width: 100%;margin: auto" stripe>
                  <el-table-column prop="projectOrganizer" label="主办单位" width="200" align="center" />
                  <el-table-column prop="donationState" label="状态" width="100"  align="center">
                    <template #default="scope">
                      {{getCondition(scope.row.donationState)}}
                    </template>
                  </el-table-column>
                  <el-table-column prop="donationDemand" label="需求概述" width="500"  align="center"/>
                  <el-table-column prop="createDate" label="创建日期" width="100"  align="center"/>
                  <el-table-column label="操作">
                    <template #default="scope">
                      <el-button size="small" type="primary" @click="showL(scope.row.listId)">查看</el-button>
                    </template>
                  </el-table-column>
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
              </div>
              </el-card>
            </div>

          </el-row>

          <el-row v-show="informationReviseShow">
            <el-card style="margin-top:30px;width: 900px">
                <el-row :gutter="12" style="margin-top:20px;">
                  <el-col :span="6">
                    <div style="text-align:right;"><span>用户名：</span></div>
                  </el-col>
                  <el-col :span="5">
                    <el-form-item>
                      <el-input type="text" v-model="user.userName"  ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              <el-row :gutter="12" style="margin-top:1px;">
                <el-col :span="6">
                  <div style="text-align:right;"><span>用户类别：</span></div>
                </el-col>
                <el-col :span="5">
                  {{getRoleName(user.userRoleId)}}
                </el-col>
              </el-row>
              <p></p>
              <el-row :gutter="12" style="margin-top:1px;">
                <el-col :span="6">
                  <div style="text-align:right;"><span>性别：</span></div>
                </el-col>
                <el-col :span="5">
                  <el-select v-model="user.userGender">
                    <el-option v-for="role in gender"
                               :key="role.id"
                               :value="role.id"
                               :label="role.name">
                    </el-option>
                  </el-select>
                </el-col>
              </el-row>
              <p></p>
                <el-row :gutter="12" style="margin-top:1px;">
                  <el-col :span="6">
                    <div style="text-align:right;"><span>年龄：</span></div>
                  </el-col>
                  <el-col :span="5">
                    <el-form-item >
                      <el-input type="text" v-model="user.userAge"  ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              <el-row :gutter="12" style="margin-top:1px;">
                <el-col :span="6">
                  <div style="text-align:right;"><span>电话号码：</span></div>
                </el-col>
                <el-col :span="5">
                  <el-form-item >
                    <el-input type="text" v-model="user.userPhone"  ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="12" style="margin-top:1px;">
                <el-col :span="6">
                  <div style="text-align:right;"><span>邮箱：</span></div>
                </el-col>
                <el-col :span="5">
                  <el-form-item >
                    <el-input type="text" v-model="user.userEmail"  ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
                <el-row :gutter="12" style="margin-top:10px;">
                  <el-col :span="17" style="text-align:center;">
                    <el-button type="primary" plain round size="medium" @click="submit()">修改</el-button>
                  </el-col>
                </el-row>

            </el-card>
          </el-row>

          <el-row v-show="passwordShow">
            <el-card style="margin-top:30px;width: 900px">
              <el-form :model="personalForm" :rules="personalFormRules">
                <el-row :gutter="12" style="margin-top:1px;">
                  <el-col :span="6">
                    <div style="text-align:right;"><span>旧密码：</span></div>
                  </el-col>
                  <el-col :span="5">
                    <el-form-item prop="password1">
                      <el-input type="password" show-password v-model="personalForm.password1"  ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="12" style="margin-top:1px;">
                  <el-col :span="6">
                    <div style="text-align:right;"><span>密码：</span></div>
                  </el-col>
                  <el-col :span="5">
                    <el-form-item prop="password2">
                      <el-input type="password" show-password v-model="personalForm.password2" ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="12" style="margin-top:1px;">
                  <el-col :span="6">
                    <div style="text-align:right;"><span>确认密码：</span></div>
                  </el-col>
                  <el-col :span="5">
                    <el-form-item prop="password3">
                      <el-input type="password" show-password v-model="personalForm.password3"  ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="12" style="margin-top:10px;">
                  <el-col :span="17" style="text-align:center;">
                    <el-button type="primary" plain round size="medium" @click="passwordSubmit()">修改</el-button>
                  </el-col>
                </el-row>
              </el-form>
            </el-card>
          </el-row>
        </el-col>
      </el-row>
    </div>
  </div>

</template>
<script>
export default {
  name: "DocumentWeb",
  data() {
    return {
      pageInfo:{},
      roles:[],
      gender:[],
      userId: '',
      user: {},
      src: '',
      levelName: '',
      isCollapse: false,
      informationShow: true,
      informationReviseShow:false,
      passwordShow: false,
      isDisabled: false,
      time: 60,
      personalForm: {
        password1: "",
        password2: "",
        password3:""
      },
      personalFormRules:{
        password1: [
          {required: true, message: '请输入旧密码', trigger: 'blur'},
        ],
        password2:[
          {required: true, message: '请输入新密码', trigger: 'blur'},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ],
        password3:[
          {required: true, message: '请再次输入新密码', trigger: 'blur'},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    this.roles = [
      {id:0,name:"管理员"},
      {id:1,name:"求助者"},
      {id:2,name:"帮助人"},
    ]
    this.gender=[
      {id:0,name:"女"},
      {id:1,name:"男"},
    ]
    this.showData();
  },
  methods: {
    showData(){
      this.userId = sessionStorage.getItem("userId");
      let url = "user/" + this.userId;
      this.$http.get(url).then(resp => {
        this.user = resp.data.data;
        if(this.user.userRoleId===2){
        let url2="helplist/getPagedByGiverId";
        this.$http.get(url2,{params:{
            pageNum:this.pageInfo.pageNum,
            pageSize:this.pageInfo.pageSize,
            giverId:this.userId
          }})
            .then(resp=>{
              //resp:响应对象
              //resp.data:响应对象中的数据（Result）
              //resp.data.data:PageInfo
              this.pageInfo = resp.data.data;
              console.log(this.pageInfo);
            });}
      else if (this.user.userRoleId===1){
        let url2="helplist/getPagedByRecipientId";
        this.$http.get(url2,{params:{
            pageNum:this.pageInfo.pageNum,
            pageSize:this.pageInfo.pageSize,
            recipientId:this.userId
          }})
            .then(resp=>{
              //resp:响应对象
              //resp.data:响应对象中的数据（Result）
              //resp.data.data:PageInfo
              this.pageInfo = resp.data.data;
              console.log(this.pageInfo);
            });
      }
      })
    },
    showL(listId){
      this.$router.push({"path":"/LogisticsWeb",query:{"listId":listId}})
    },
    informationClick() {    //个人信息事件
      this.informationShow = true
      this.passwordShow = false
      this.informationReviseShow = false
    },
    informationReviseClick() {
      this.informationReviseShow = true
      this.informationShow = false
      this.passwordShow = false
    },
    passwordClick() {     //密码事件
      this.informationReviseShow = false
      this.informationShow = false
      this.passwordShow = true
    },
    handleCurrentChange(pageNum){
      this.pageInfo.pageNum = pageNum;
      this.showData()
    },
    handleSizeChange(pageSize){
      this.pageInfo.pageSize = pageSize;
      this.showData()
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
    },
    getRoleName(roleId) {
      let name;
      if (roleId == 0) {
        name = "管理员";
      } else if (roleId == 1) {
        name = "求助者";
      } else if (roleId == 2) {
        name = "帮助者";
      }
      return name;
    },
    getGenderName(GenderId) {
      let name;
      if (GenderId == 0) {
        name = "女";
      } else if (GenderId == 1) {
        name = "男";
      }
      return name;
    }
    ,
    submit() {  //提交
      if (this.user.userPhone === "") {
        this.$message.warning("手机号不能为空")
      } else if (this.user.userName === "") {
        this.$message.warning("用户名不能为空")
      } else if (this.user.userPassword === "") {
        this.$message.warning("密码不能为空")
      } else if (this.user.userEmail === "") {
        this.$message.warning("邮箱不能为空")
      } else if (this.user.userAge===""){
        this.$message.warning("年龄不能为空")
      }
      else {
        this.$http.put('/user', this.user).then(resp => {
          if (resp.data.data == 1) {
            alert("修改成功!");
            this.informationClick();
            this.showData();
          }
        })
      }
    },
    passwordSubmit(){
      if (this.personalForm.password1===""||this.personalForm.password2===""||this.personalForm.password3==="") {
        this.$message.success("密码不能为空！")
      } else if(this.personalForm.password1 !==this.user.userPassword) {
        this.$message.warning("旧密码错误！")
      } else if (this.personalForm.password2 !==this.personalForm.password3) {
        this.$message.warning("两次输入的密码不一致,请重新输入")
      }else {
        this.user.userPassword=this.personalForm.password2;
        this.$http.put('/user', this.user).then(resp => {
          if (resp.data.data == 1) {
            alert("修改成功!");
            this.personalForm.password1="";
            this.personalForm.password2="";
            this.personalForm.password3="";
            this.informationClick();
            this.showData();

          }
        })
      }
  }
}
}

</script>

<style scoped>
.name{ text-align: right; }
.value{ text-align: left; }

.el-buttons {
  background: #d3dce6;
  color: #d3dce6;
  border: 1px solid #d3dce6;
  opacity: 1
}

</style>


<style>

.el-buttons:active {
  background: #126c9e !important;
  font-weight: bold;
}
/*按钮悬浮*/
.el-buttons:hover {
  background: orange !important;
  color: white !important;
  font-weight: bold;
  border-color: orange !important;
}
/*按钮点击*/
.el-buttons:focus {
  background: orangered !important;
  color: white !important;
  font-weight: bold;

  border-color: orangered !important;
}

</style>
