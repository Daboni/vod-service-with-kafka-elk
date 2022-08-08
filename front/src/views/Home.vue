<template>
  <div class="home">
    <div class="home-title">홈</div>
    <div class="favorite-box">
      <h3>가장 인기있는 TV</h3>
      <TvContents />
    </div>
    <div class="favorite-box">
      <h3>가장 인기있는 영화</h3>
      <MovieContents />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import TvContents from "@/components/TvContents.vue";
import MovieContents from "@/components/MovieContents.vue";

export default {
  name: "Home",
  components: {
    TvContents,
    MovieContents,
  },
};
</script>
<style>
.home-title {
  color: white;
  font-size: 23px;
  margin: 13px 0 0 83px;
  width: 100%;
  text-align: left;
}
.favorite-box {
  /* background-color: white; */
  color: white;
  font-size: 28px;
  text-align: left;
  width: 100%;
  height: 50%;
  margin: 13px 0 0 83px;
  float: left;
}
</style>
