(function() {
    function onModifyClick() {
        const baseUrl = document.location.origin;
        const contents = document.getElementById('post-modify-content');
        const url = baseUrl + "/posts/" + contents.dataset.postid;
        const api = new AjaxApi();
        api.put(url, { "contents": contents.value }, (res) => document.location.href = baseUrl);
    }

    document.getElementById("post-edit-btn").addEventListener("click", onModifyClick);
})();