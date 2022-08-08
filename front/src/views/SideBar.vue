<template>
  <div id="icon" class="icon-bar">
    <router-link to="/search" @click="update(0)"
      ><i
        class="fa fa-search gray"
        aria-hidden="true"
        v-if="activeIndex !== 0"
      ></i>
      <i
        class="fa fa-search white"
        aria-hidden="true"
        v-else-if="activeIndex === 0"
      ></i>
    </router-link>
    <router-link to="/" @click="update(1)"
      ><i class="fa fa-home gray" v-if="activeIndex !== 1"></i>
      <i class="fa fa-home white" v-if="activeIndex === 1"></i>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "#icon",
  data() {
    return {
      activeIndex: 1,
    };
  },
  methods: {
    update(n) {
      this.activeIndex = n;
      // console.log(this.activeIndex);
    },
  },
  mounted() {
    this.activeIndex = 1;
  },
};
//
</script>

<style>
.icon-bar {
  height: 100%;
  width: 90px;
  background-color: #282d49;
  position: fixed;
  top: 0;
  left: 0;
  overflow-x: hidden;
  padding-top: 0;
}
.icon-bar a {
  display: block;
  text-align: center;
  padding: 16px;
  transition: all 0.3s ease;
  color: white;
  font-size: 36px;
}
.icon-bar a:hover {
  background-color: #858585;
}
.active {
  background-color: #5eaf13;
}
.gray {
  color: gray;
}
.white {
  color: white;
}
/* .fa-search {
  color: gray;
  border: 1px solid transparent;
  border-radius: 5px;
}
.fa-home {
  color: gray;
  border: 1px solid transparent;
  border-radius: 5px;
} */
</style>
