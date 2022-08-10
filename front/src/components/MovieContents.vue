<template>
  <div id="MovieContents">
    <table class="content-tb">
      <th class="content-th" v-for="content in contents" v-bind:key="content">
        <router-link v-bind:to="`/synopsis/${content.content_id}`">
          <div>
            <div class="image"><img :src="content.poster_path" /></div>
            <div class="name">{{ content.title }}</div>
            <div class="date">{{ content.release_date }}</div>
          </div>
        </router-link>
      </th>
    </table>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  name: "MovieContents",

  data() {
    return {
      contents: [
        // {
        //   id: 616037,
        //   name: "토르: 러브 앤 썬더",
        //   release_date: "2022-07-06",
        //   backdrop_path:
        //     "https://image.tmdb.org/t/p/w342/bZLrpWM065h5bu1msUcPmLFsHBe.jpg",
        // },
        // {
        //   id: 504827,
        //   name: "밥스 버거스 무비",
        //   release_date: "2019-07-25",
        //   backdrop_path:
        //     "https://image.tmdb.org/t/p/w342/31vliI2mopLlh5kUoWpJZ19cF8y.jpg",
        // },
        // {
        //   id: 560057,
        //   name: "씨 비스트",
        //   release_date: "2022-06-24",
        //   backdrop_path:
        //     "https://image.tmdb.org/t/p/w342/axtQ6KM40hpwhcEJy0UNZ9rXOLV.jpg",
        // },
        // {
        //   id: 453395,
        //   name: "닥터 스트레인지: 대혼돈의 멀티버스",
        //   release_date: "2022-05-04",
        //   backdrop_path:
        //     "https://image.tmdb.org/t/p/w342/vL5ktZauR0fZMDOHKjakb1idhuU.jpg",
        // },
        // {
        //   id: 613093,
        //   name: "죽은 자들의 골짜기",
        //   release_date: "2022-03-11",
        //   backdrop_path:
        //     "https://image.tmdb.org/t/p/w342/avokvOwhjVR9GgISsSSgb2A343G.jpg",
        // },
        // {
        //   id: 507086,
        //   name: "쥬라기 월드: 도미니언",
        //   release_date: "2022-06-01",
        //   backdrop_path:
        //     "https://image.tmdb.org/t/p/w342/odxdUZWZ7fBfy3ZRj063wuJnZvo.jpg",
        // },
      ],
      //   activeIndex: 1,
    };
  },
  methods: {
    getList: function() {
      axios.get("http://34.64.116.159:9090/v1/euxp/api/get/movie").then(response => {
        // console.log(response.data.result_data)
        this.contents = response.data.result_data
        this.contents = this.contents.result_data
        // console.log(response.data.result_data)
      })
      .catch(function(error){
        console.log(error);
      })
    },
  },
  created() {
    this.getList();
    // console.log(this.contents)
    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* #MovieContents {
  margin-left: 83px;
} */
.content-th {
  width: 600px;
  /* margin: auto; */
}
.image {
  margin-top: 13px;
}
.name {
  /* background-color: white; */
  color: white;
  font-size: 28px;
  margin-top: 13px;
}
.date {
  /* background-color: white; */
  color: gray;
  font-size: 22px;
  margin-top: 13px;
}
</style>
