<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/logo-2.jpg" alt="">
      </div>
      <!-- 登录表单区域 -->
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="0px" class="login_form">
        <!--        用户名-->
        <el-form-item prop="userName">
          <el-input v-model="loginForm.userName" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!--        密码-->
        <el-form-item prop="userPassword">
          <el-input v-model="loginForm.userPassword" prefix-icon="el-icon-key" type="password"></el-input>
        </el-form-item>
        <!--        按钮区域-->
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
          <el-button type="primary" @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

  export default {
    data() {
      return {
        // 数据绑定对象
        loginForm: {
          userName: '',
          userPassword: ''
        },
        // 验证规则对象
        loginFormRules: {
          //验证用户
          userName: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
          ],
          userPassword: [
            {required: true, message: '请输入登录密码', trigger: 'blur'},
            {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      login(){
        let url = "user/login";
        //向服务器发送异步请求，地址是：url 如果请求的响应回来，就调用then()中的方法
        this.$http.get(url,{params:this.loginForm}).then(resp=>{
          if(resp.data.data != null){
            sessionStorage.setItem("userName",JSON.parse(JSON.stringify(resp.data.data.userName)));
            sessionStorage.setItem("userId",JSON.stringify(resp.data.data.userId));
            sessionStorage.setItem("userRoleId",JSON.stringify(resp.data.data.userRoleId));
            this.$router.push("/MainPage")
          }else{
            alert("用户名或密码错误！")
          }
        })
      },
      register(){
        this.$router.push("/Register")
      },
      //重置登录表单
      resetLoginForm() {
        this.loginForm.userName="";
        this.loginForm.userPassword="";
      }

    }
  }
</script>
<!--加上scoped是将样式应用于此组件，不加是全局-->
<style lang="less" scoped>
  .login_container {
    background-color: rgba(21, 35, 242, 0.25);
    height: 100%;
  }

  .login_box {
    width: 450px;
    height: 300px;
    background-color: white;
    border-radius: 3px;
    /*容器内居中*/
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);

    .avatar_box {
      height: 130px;
      width: 130px;
      border: 1px solid #eee;
      border-radius: 50%;
      padding: 10px;
      /*边框阴影*/
      box-shadow: 0 0 10px #ddd;
      position: absolute;
      left: 50%;
      transform: translate(-50%, -50%);
      background-color: #fff;

      img {
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: #eee;
      }
    }

    .login_form {
      position: absolute;
      bottom: 0;
      width: 100%;
      padding: 0 20px;
      box-sizing: border-box;
    }

    .btns {
      display: flex;
      justify-content: flex-end;
    }
  }
</style>
