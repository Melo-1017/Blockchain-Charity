<template>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      background-color=	"#f5f5f5"

      text-color="black"
      active-text-color="red"
      @select="handleSelect"
      router

  >
    <el-menu-item index="/FrontPage" >慈溯</el-menu-item>
    <div class="flex-grow" />
    <el-menu-item index="/DonateWeb" >慈善项目</el-menu-item>
    <el-menu-item index="/NeedWeb" >发布需求</el-menu-item>

    <el-col :span="2" :offset="17" class="user_title">
      <el-dropdown @command="handleCommand" class="el-dropdown-link">
            <span style="line-height: 50px">
              <img :src="person" class="user-avatar" />
            </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="document">个人资料</el-dropdown-item>
            <el-dropdown-item command="examine">信息审核</el-dropdown-item>
            <el-dropdown-item command="exit">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-col>

  </el-menu>
  <el-main>
    <router-view>
    </router-view>
  </el-main>
</template>

<script>
import person from '@/assets/person.jpg'
export default {
  name: "MainPage",
  data() {
    return{
      userName:'',
      userId:'',
      person:person,
    }
  },
  created() {
    this.userName = sessionStorage.getItem("userName");
    this.userId=sessionStorage.getItem("userId");
    console.log(this.userName);
    console.log(this.userId);
    this.$router.push("/Frontpage");
  },
  methods:{
    handleCommand(command) {
      if (command === "exit") {
        this.$router.push("/LoginWeb");
        sessionStorage.clear();
      }
      else if (command==="document"){
        this.$router.push("/DocumentWeb");
      }
      else if (command==="examine"){
        this.$router.push("/ExamineWeb");
      }
    },
  }
}
</script>

<style>
.user-avatar {
  margin-top: 9px;
  margin-left: 5px;
  cursor: pointer;
  width: 40px;
  height: 40px;
  border-radius: 10px;
}
.el-menu {
  margin-top: -10px;
  border: 0;
  align-items: center;
}

.el-menu-item.is-active {
  background-color: gainsboro !important;
}
</style>