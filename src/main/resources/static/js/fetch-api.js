const AjaxApi = function() {
    this.bodyData = function(method, data) {
        this.method = method;
        this.headers = {
            'Content-Type': 'application/json'
        };
        this.body = JSON.stringify(data);
    };

    this.post = function(url, data, callback) {
        fetch(url, new this.bodyData("POST", data))
            .then(res => res.json())
            .then(callback);
    };

    this.put = function(url, data, callback) {
        fetch(url, new this.bodyData("PUT", data))
            .then(res => res.json())
            .then(callback);
    };

    this.delete = function(url, callback) {
        fetch(url, new this.bodyData("DELETE"))
            .then(res => res.json())
            .then(callback);
    };

    this.get = function(url, callback) {
        fetch(url, new this.bodyData("GET"))
            .then(res => res.json())
            .then(callback);
    };
}