var app = new Vue({
    el: "#app",
    data: {
        message: [],
        id: ""
    },
    methods: {
        getMessage: function () {
            var temp = this;
            axios.get("http://localhost:8080/design/" + this.id)
                .then(function (res) {
                    temp.message = res.data;
                }).catch(function (err) {
                console.log(err);
            })
        },
        getAll: function () {
            var temp = this;
            axios.get("http://localhost:8080/design/recent")
                .then(function (res) {
                    temp.message = res.data;
                }).catch(function (err) {
                console.log(err);
            })
        }
    }
})