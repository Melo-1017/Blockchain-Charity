<template>
     <div class="register_container">
                <el-row :gutter="12" style="margin-top:20px;">
                  <el-col :span="10">
                    <div style="text-align:right;"><span>用户名：</span></div>
                  </el-col>
                  <el-col :span="5">
                    <el-form-item>
                      <el-input type="text" v-model="user.userName"  ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              <el-row :gutter="12" style="margin-top:1px;">
                <el-col :span="10">
                  <div style="text-align:right;"><span>用户类别：</span></div>
                </el-col>
                <el-col :span="5">
                  <el-select v-model="user.userRoleId">
                    <el-option v-for="role in roles"
                               :key="role.id"
                               :value="role.id"
                               :label="role.name">
                    </el-option>
                  </el-select>
                </el-col>
              </el-row>
              <p></p>
              <el-row :gutter="12" style="margin-top:1px;">
                <el-col :span="10">
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
                  <el-col :span="10">
                    <div style="text-align:right;"><span>年龄：</span></div>
                  </el-col>
                  <el-col :span="5">
                    <el-form-item >
                      <el-input type="text" v-model="user.userAge"  ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              <el-row :gutter="12" style="margin-top:1px;">
                <el-col :span="10">
                  <div style="text-align:right;"><span>电话号码：</span></div>
                </el-col>
                <el-col :span="5">
                  <el-form-item >
                    <el-input type="text" v-model="user.userPhone"  ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="12" style="margin-top:1px;">
                <el-col :span="10">
                  <div style="text-align:right;"><span>登录密码：</span></div>
                </el-col>
                <el-col :span="5">
                  <el-form-item >
                    <el-input type="text" v-model="user.userPassword"  ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="12" style="margin-top:1px;">
                <el-col :span="10">
                  <div style="text-align:right;"><span>邮箱：</span></div>
                </el-col>
                <el-col :span="5">
                  <el-form-item >
                    <el-input type="text" v-model="user.userEmail"  ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
                <el-row :gutter="12" style="margin-top:10px;">
                  <el-col :span="24" style="text-align:center;">
                    <el-button type="primary" plain round size="medium" @click="submit()">注册</el-button>
                  </el-col>
                </el-row>
     </div>
</template>
  
<script>
  //导入组件
  
  //导出数据，default表示默认导出
export default {
    name: 'App',
    data(){
      return{
        roles:[],
        gender:[],
        user: {},
      }
    },
    created(){
        this.roles = [
          {id:0,name:"管理员"},
          {id:1,name:"求助者"},
          {id:2,name:"帮助人"},
       ]
       this.gender=[
          {id:0,name:"女"},
          {id:1,name:"男"},
    ]
    },
    methods:{
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
        this.$http.post('/user', this.user).then(resp => {
          if (resp.data.data == 1) {
            alert("注册成功!");
            this.$router.push("/LoginWeb")
          }
        })
      }
    },
    },
    //注册组件
    components:{
  
    }
  }
</script>
  
<style scoped>

.register_container {
    background-color: rgba(255, 255, 255, 0.25);
    height: 100%;
    width: 800px;
    height: 400px;
    margin-left :200px
  }
</style>