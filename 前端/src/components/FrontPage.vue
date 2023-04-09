<template>
  <el-carousel :interval="4000" style="width: 100%;height: 100%; margin-top:20px;" trigger="click"
               height="400px" >
    <el-carousel-item v-for="(img,index) in imgs" :key="index">
      <el-image
          :class="className"
          style="width: 100%; height: 100%;"
          :src=getImgUrl(img)
          fit="cover"></el-image>

    </el-carousel-item>
  </el-carousel>


  <!-- 组件分割 -->
  <section class="video-section" id="video">
    <div class="video--wrap">
      <div class="video-header">
        <h2>近期热点</h2>
        <hr class="line" />
        <span class="intro">
          <h5 style="text-align: center">news collection</h5>
        </span>
      </div>
      <div class="video-body">
        <div class="video-row">
          <div class="video-panel-left">
            <h2>暴雨导致河南水灾</h2>
            <p>
              在河南中部平原地区，泛滥的水灾成为了农田杀手，生活在该地区的农民受水灾影响，不仅劳作的农田颗粒无收，赖以生存的家园也被摧毁。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <br>
              现在，灾区重建正在艰难地进行中。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </p>
          </div>
          <div class="video-link-left">
            <a
                href="https://www.bilibili.com/video/BV1Zb4y167uf/?spm_id_from=333.337.search-card.all.click&vd_source=f6ef4f60780148400d068c2e27aa8d0b"
                target="_blank"
            ><img src="~@/assets/v01.jpg" alt=""
            /></a>
          </div>
          <div class="video-link-right">
            <a
                href="https://www.bilibili.com/video/BV1qa4y1n7R6/?spm_id_from=333.337.search-card.all.click&vd_source=f6ef4f60780148400d068c2e27aa8d0b"
                target="_blank"
            ><img src="~@/assets/v02.jpg" alt=""
            /></a>
          </div>
          <div class="video-panel-right">
            <h2>云贵高原旱灾</h2>
            <p>
              &nbsp;&nbsp;&nbsp;&nbsp;在辽远的云贵高原，一场史无前例的旱灾席卷而来，如今正事态严重，当地的农田枯萎，居民急需淡水资源。
            </p>
          </div>
        </div>
        <div class="video-row">
          <div class="video-panel-left">
            <h2>东北地区火山频发</h2>
            <p>
              “暗红的岩浆在滚滚的黑烟的裹挟里喷涌而出
              ，轰隆隆的巨大声响
              向四周层层的压去，
              烧的通红的岩石被推到高空
              又疾驰落下，
              在烟幕的空中留下千万条
              火红的划痕”
              这是当地居民的亲眼所见
            </p>
          </div>
          <div class="video-link-left">
            <a
                href="https://www.bilibili.com/video/BV1MT4y127jT/?spm_id_from=333.337.search-card.all.click&vd_source=f6ef4f60780148400d068c2e27aa8d0b"
                target="_blank"
            ><img src="~@/assets/v03.jpg" alt=""
            /></a>
          </div>
          <div class="video-link-right">
            <a
                href="https://www.bilibili.com/video/BV1aQ4y1A7gV/?spm_id_from=333.337.search-card.all.click&vd_source=f6ef4f60780148400d068c2e27aa8d0b"
                target="_blank"
            ><img src="~@/assets/v04.jpg" alt=""
            /></a>
          </div>
          <div class="video-panel-right">
            <h2>云南三岔河希望小学</h2>
            <p>
              &nbsp;&nbsp;&nbsp;&nbsp;在云南省凤庆县的贫困山区里，一所希望小学正在建立，目前正在募集物资。
              <br>
              &nbsp;&nbsp;&nbsp;&nbsp;课桌，椅子，书包，教具...，恳请广大社会人士给予物资援助！
            </p>
          </div>
        </div>
        <div class="video-row">
          <div class="video-panel-left">
            <h2>满载物资向深山进发</h2>
            <p>9月14日，在中秋节来临之际，一辆来自钟石化满载物资的卡车正在开往四川省成都市大邑县内一所
              贫困小学。点击对此行程进行追踪。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </p>
          </div>
          <div class="video-link-left">
            <a
                href="https://www.bilibili.com/video/BV1s5411m7Fd/?spm_id_from=333.337.search-card.all.click&vd_source=f6ef4f60780148400d068c2e27aa8d0b"
                target="_blank"
            ><img src="~@/assets/v05.jpg" alt=""
            /></a>
          </div>
          <div class="video-link-right">
            <a
                href="https://www.bilibili.com/video/BV1PS4y1q7bx/?spm_id_from=333.337.search-card.all.click&vd_source=f6ef4f60780148400d068c2e27aa8d0b"
                target="_blank"
            ><img src="~@/assets/v06.jpg" alt=""
            /></a>
          </div>
          <div class="video-panel-right">
            <h2>天底下有免费的午餐</h2>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;你是否想向处于灾区的人们捐赠一份免费午餐？点击加入“灾区午餐供应计划”。</p>
          </div>
        </div>
      </div>
    </div>
  </section>

</template>

<script>
export default {
  name: "FrontPage",
  data(){
    return{
      name: "MainPage",
      userName:'',
      loginflag:'',
      className:'',
      imgs:[
        "img01.jpg",
        "img02.jpg",
        "img03.jpg"
      ],
      titles:[
        "01",
        "02",
        "03"
      ]
    }
  },
  methods:{
    getImgUrl(src){
      return require("@/assets/"+src)
    },
  }
}
</script>

<style scoped>
/*videos*/
.video-body {
  width: 1270px;
  padding: 20px 15px 10px;
  margin: 0px auto;
  position: relative;
}
.video-row {
  display: flex;
  height: 180px;
  justify-content: space-between;
  margin-bottom: 80px;
}
.video-row h2 {
  font-size: 28px;
  color: #000;
}
.video-row p {
  line-height: 1.5;
  color: #3f5b50;
}
.video-panel-left {
  width: 360px;
  text-align: right;
}
.video-link-left img,
.video-link-right img {
  border: 7px double #eee;
  border-radius: 20px;
  width: 220px;
  height: 150px;
}
.video-panel-right {
  width: 360px;
  text-align: left;
}

h2 {
  font-weight: 550;
  color: #333;
  letter-spacing: 1px;
  font-family: "Ek Mukta";
  font-size: 36px;
  margin: 0;
  text-align: center;
  margin-top: 40px;
}

.line {
  border:none;
  border-top:10px solid orangered;
  max-width:500px;
  margin:15px auto 15px
}


/* .img-list li:nth-child(1) {
  z-index: 1;
} */


.intro{
  color:#6f6f6f;
  font-size:18px
}
</style>