import axios from 'axios'

Vue.use(axios)
Vue.prototype.$axios = axios

Vue.config.productionTip = false

new Vue({
    el: '#app',
    router,
    components: {App},
    template: '<App/>'
})