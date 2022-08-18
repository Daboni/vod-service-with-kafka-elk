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
      ],
    };
  },
  methods: {
    getList: function() {
      axios.get("http://34.64.116.159:9090/v1/euxp/api/get/movie").then(response => {
        this.contents = response.data.result_data
        this.contents = this.contents.result_data
      })
      .catch(function(error){
        console.log(error);
      })
    },
  },
  created() {
    this.getList();
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
