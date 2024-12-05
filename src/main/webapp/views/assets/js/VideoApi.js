// API like "Like" //
function likeApi(button,id) {
    // Gửi yêu cầu POST đến RESTful API sử dụng Fetch
    fetch("http://localhost:6789/TMovies/api/videos/"+id, {
        method: 'POST',  // Hoặc PUT nếu bạn cập nhật dữ liệu hiện có //
    })
        .then(response => response.json())
        .then(data => {
            button.style.display= 'none'
            button.nextElementSibling.style.display = 'block'
        })
        .catch(error => {
            console.log("error: ",error)
        });
}

/*== Api dislike cho video ==*/
function disLikeApi(button,id) {
    // Gửi yêu cầu POST đến RESTful API sử dụng Fetch //
    fetch("http://localhost:6789/TMovies/api/videos/"+id, {
        method: 'DELETE',  // Hoặc PUT nếu bạn cập nhật dữ liệu hiện có
    })
        .then(response => response.json())
        .then(data => {
            button.style.display= 'none'
            button.previousElementSibling.style.display = 'block'
        })
        .catch(error => {
            console.log("error: ",error)
        });
}