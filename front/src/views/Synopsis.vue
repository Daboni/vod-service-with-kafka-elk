<template>
  <div id="Synopsis" class="synopsis">
    <!--콘텐츠 설명 박스-->
    <div class="description">
      <div class="title">
        <div class="trend">{{ this.content.type }}</div>
        <!-- <h1 class="con">{{ this.content.title }}(2022)</h1> -->
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
      <!-- <div class="infos">
        <h2>감독</h2>
        <div class="info text">{{ content.director}}</div>
        <h2>출연</h2>
        <span class="info text" v-for="name in content.actors" v-bind:key="name">{{name}}, </span>
      </div> -->
      &nbsp;
    </div>
    <!-- <div class="poster"><img src="https://image.tmdb.org/t/p/w500/5K0F6lYosnp162drTXTJD9Pisp6.jpg"/></div> -->
    <div class="poster"><img :src="content.poster_path" class="img" /></div>
          <!-- <div class="actions">
        &nbsp;
        <div class="action">
          <i class="fa fa-calendar icon" aria-hidden="true"></i>
          <div class="act text">월정액 가입</div>
          <i class="fa fa-play icon" aria-hidden="true"></i>
          <div class="act text">3분 미리보기</div>
        </div>
        <div class="action">
          <i class="fa fa-star icon" aria-hidden="true"></i>
          <div class="like text">찜하기</div>
          <i class="fa fa-thumbs-up icon" aria-hidden="true"></i>
          <div class="act text">평가하기</div>
        </div>
      </div> -->
      <!-- <div class="menu">
        <div class="top-menu">
          <div class="menu-item">
            <i class="fa fa-search gray icon"></i>월정액 가입
          </div>
          <div class="menu-item">
            <i class="fa fa-search gray icon"></i>3분 미리보기
          </div>
        </div>
        <div class="bottom-menu">
          <div class="menu-item">
            <i class="fa fa-search gray icon"></i>찜하기
          </div>
          <div class="menu-item">
            <i class="fa fa-search gray icon"></i>평가하기
          </div>
        </div>
      </div> -->
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
        // console.log(response.data.result_data)
        this.content = response.data.result_data
        // console.log(this.content.result_data)
        this.content = this.content.result_data
        console.log(this.content)
        this.content.release_year = this.content.release_date.substr(0,4)
        if(this.content.content_id.substr(0,2) == "TV") {
          this.content.type = "트렌드 TV";
        }
        else {
          this.content.type = "트렌드 영화";
        }
        // console.log(this.content.release_year)
      })
      .catch(function(error){
        console.log(error);
      })
    },
    // getRating: function() {
    //   axios.get('https://openapi.naver.com/v1/search/movie.json',{
    //     params:{
    //       query : '기생충',
    //       display : 1,
    //       // yearfrom : this.content.release_year,
    //       // yearto : this.content.release_year,
    //     },
    //     headers: {
    //       'X-Naver-Client-id': "LVt9VKSNulyrJzbnOAL_",
    //       'X-Naver-Client-Secret': 'vwUfz39adG',
          
    //     }
    //   }).then(response => {
    //     console.log(response)
    //   })
    // }
  },
  created() {
    this.getSynop();
    console.log(this.$route.params.id)
    },
  // created() {
  //   this.content = {
  //     id: 92782,
  //     title: "미즈 마블",
  //     release_date: "2022-06-08",
  //     year: "2022",
  //     poster_path:
  //       "https://image.tmdb.org/t/p/w500/5K0F6lYosnp162drTXTJD9Pisp6.jpg",
  //     genres: ["Sci-Fi & Fantasy", "Action & Adventure", "코미디"],
  //     running_time: 50,
  //     overview:
  //       "저지시티에 사는 미국인 무슬림 가정의 10대 소녀 카말라 칸. 게임에 죽고 사는 그녀는 열렬한 팬픽션 작가이자 슈퍼 히어로 덕후로 특히 캡틴 마블에 대한 팬심은 상상을 초월한다. 그러던 어느 날, 그토록 우러러보던 슈퍼 히어로처럼 카말라에게도 슈퍼 파워가 생기는데. 지금껏 학교에서도, 때로는 집에서도 겉돌던 그녀의 인생, 이제 슈퍼 파워가 생겼으니 폼 나게 달라지려나?",
  //     director: "Bisha K. Ali",
  //     actors: [
  //       "Iman Vellani",
  //       "Matt Lintz",
  //       "Yasmeen Fletcher",
  //       "Zenobia Shroff",
  //     ],
  //     certification: "ALL",
  //     recomm: [
  //       {id:92782,title:"토르: 러브 앤 썬더",poster_path:"https://image.tmdb.org/t/p/w185/bZLrpWM065h5bu1msUcPmLFsHBe.jpg",release_date:"2022-07-06"},
  //       {id:92782,title:"마녀(魔女) Part2. The Other One",poster_path:"https://image.tmdb.org/t/p/w185/cah6iIYQBctvCmBjsKGb1NCiygr.jpg",release_date:"2022-06-15"},
  //       {id:92782,title:"마녀",poster_path:"https://image.tmdb.org/t/p/w185/979reU4oeyHuqQn8D8zHrmUUq6P.jpg",release_date:"2018-06-27"},
  //       {id:92782,title:"K-11",poster_path:"https://image.tmdb.org/t/p/w185/ucQn8bEqCRstAHscjH4ryV08QgZ.jpg",release_date:"2013-03-15"},
  //     ],
  //   };
  // },
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
