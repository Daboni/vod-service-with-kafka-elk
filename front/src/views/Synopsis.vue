<template>
  <div id="Synopsis" class="synopsis">
    <!--콘텐츠 설명 박스-->
    <div class="description">
      <div class="title">
        <div class="trend">{{ this.content.type }}</div>
        <h1 class="con">{{ this.content.title }}({{this.content.release_year}})</h1>
        <div class="certification">{{ this.content.certification }}</div>
        <span
          class="genrs text"
          v-for="genr in content.genres"
          v-bind:key="genr"
        >
          &nbsp; {{ genr }},
        </span>
        <span class="text">&nbsp;{{ content.running_time }}분 </span>
        <div class="rating">
          <span>&nbsp; <img class="logo" src="../assets/gVZIvphd_400x400.jpg"/>★{{content.voteAverage}}
            </span>
            <span>&nbsp; <img class="logo" src="../assets/unnamed.png"/>★{{content.userRating}}</span>
          </div>

      </div>

      <div class="overview">
        <h2>개요</h2>
        <div class="text">{{ content.overview }}</div>
      </div>
      <table class="infos">
      <th>
        <h2>감독</h2>
        <div class="info text">{{ content.director}}</div>
      </th>
      <th class="actors">
          <h2>출연</h2>
        <span class="info text" v-for="name in content.actors" v-bind:key="name">{{name}}, </span>
      
      </th>
    </table>
        <div class="recomm-hd text">관련 추천 콘텐츠</div>
  
    <table class="recomm">
      <th v-for="recom in content.recomm" v-bind:key="recom">
        <div class="recom">
            <img :src="recom.poster_path" class="img" />
            <div>{{recom.title}}</div>
            <div>{{recom.release_date}}</div>
          </div>
      </th>
    </table>
      &nbsp;
    </div>
    <div class="poster"><img :src="content.poster_path" class="img" /></div>
    </div>
    
</template>

<script>
import axios from 'axios';
export default {
  name: "Synopsis",
  data() {
    return {
      content: {},
    };
  },
  methods: {
      getSynop: function() {
        axios.get("http://34.64.116.159:9090/v1/euxp/api/get/"+this.$route.params.id).then(response => {
        this.content = response.data.result_data
        this.content = this.content.result_data
        this.content.release_year = this.content.release_date.substr(0,4)
        if(this.content.userRating == 0) {
          this.content.userRating = Math.round((this.content.voteAverage - 0.1)*10)/10;
        }
        if(this.$route.params.id.substr(0,2) == "TV") {
          this.content.type = "트렌드 TV";
        }
        else {
          this.content.type = "트렌드 영화";
        }
      })
      .catch(function(error){
        console.log(error);
      })
    },
  },
  created() {
    this.getSynop();
    },
};
</script>

<style>
.sidemargin {
  height: 100%;
  width: 300px;
  background-color: white;
}
.synopsis {
  color: white;
  margin: 20px 0 0 83px;
}
.description {
  text-align: left;
  float: left; /* 왼쪽 정렬*/
  width: 50%; /*상위 div의 너비 50%*/
}
.poster {
  float: right;
  width: 50%;
  height: 100%;
  margin-bottom: 10px;
}

.title {
  margin: 0px 0px 100px 0px; /* 아래 공간 */
}
.trend {
  width: 120px;
  height: 23px;
  border-radius: 400px;
  background-color: gray;
  text-align: center;
}
.certification {
  width: 30px;
  height: 30px;
  border-radius: 20px;
  border: 1px solid white;
  text-align: center;
  line-height: 30px;
  font-size: 12px;
  display: inline-block;
}
.genrs {
  display: inline-block;
}
.overview {
  height: 75%;
  width: 75%;
  margin: -40px 0px 80px 0px;
}
.actions {
  flex-direction: column;
  display: flex;
}
.action {
  margin: 0px 0px 25px 0px;
}
.info {
  margin: 0px 0px 50px 0px;
}
.fa {
  float: left;
  margin: 4px 0 0 0;
}
.icon {
  font-size: 1.5em;
}
.act {
  float: left;
  margin: 0 100px 0 20px;
}
.like {
  float: left;
  margin: 0 147px 0 20px;
}
.text {
  font-size: 1.3em;
}
.rating {
  margin: 10px 20px 0 -22px;
  font-size: 2.3em;
  color:gold
}
.recomm {
  /* float: left; */
  margin: 20px 0px 0 0;
}
.recom {
  margin: 0 15px 0 0;
}
.logo {
  width: 5%
}
.actors {
  padding-left: 20px
}
/* .top-menu {
  display: flex;
}
.bottom-menu {
  display: flex;
}

.menu-item {
  margin: 12px;
  margin-left: 0px;
} */
</style>
