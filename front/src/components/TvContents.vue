<template>
  <div id="TvContents">
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
  name: "TvContents",

  data() {
    return {
      contents:[]
      // [
      //   {
      //     id: 92782,
      //     name: "미즈 마블",
      //     air_date: "2022-06-08",
      //     backdrop_path:
      //       "https://image.tmdb.org/t/p/w342/5K0F6lYosnp162drTXTJD9Pisp6.jpg",
      //   },
      //   {
      //     id: 76479,
      //     name: "더 보이즈",
      //     air_date: "2019-07-25",
      //     backdrop_path:
      //       "https://image.tmdb.org/t/p/w342/stTEycfG9928HYGEISBFaG1ngjM.jpg",
      //   },
      //   {
      //     id: 60059,
      //     name: "베터 콜 사울",
      //     air_date: "2015-02-08",
      //     backdrop_path:
      //       "https://image.tmdb.org/t/p/w342/fC2HDm5t0kHl7mTm7jxMR31b7by.jpg",
      //   },
      //   {
      //     id: 78074,
      //     name: "신토니아",
      //     air_date: "2019-08-09",
      //     backdrop_path:
      //       "https://image.tmdb.org/t/p/w342/olF5Bchok7ot8B8tR4O8zYjanlD.jpg",
      //   },
      //   {
      //     id: 97645,
      //     name: "솔라 오포짓",
      //     air_date: "2020-05-08",
      //     backdrop_path:
      //       "https://image.tmdb.org/t/p/w342/8jwvGxPs8fnv95g4XtkPPuxFlcp.jpg",
      //   },
      //   {
      //     id: 63247,
      //     name: "웨스트월드",
      //     air_date: "2016-10-02",
      //     backdrop_path:
      //       "https://image.tmdb.org/t/p/w342/8OQBd8ohXJGloonJgYAQFItGnUc.jpg",
      //   },
      // ],
    };
  },
  methods: {
    getList: function() {
      axios.get("http://34.64.116.159:9090/v1/euxp/api/get/tv").then(response => {
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
.content-th {
  width: 600px;
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
