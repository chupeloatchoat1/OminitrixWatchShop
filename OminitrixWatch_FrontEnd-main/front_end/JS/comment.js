function postComment(watchID){
    const userID = JSON.parse(sessionStorage.getItem('userid'))
    const commentContent = document.getElementById('commentContent').value
    const date = Date.now()
    axios.post('http://localhost:9000/ominitrix/comment/add', {
        "userID": userID,
        "watchID": watchID,
        "date": date,
        "comment": commentContent
    }).then(function(res){
        if(res.status==200){
            location.reload()
        }
    })
}
function loadComment(watchID){
    axios.all([
        axios.get('http://localhost:9000/ominitrix/comment'),
        axios.get('http://localhost:9000/ominitrix/user')
    ]).then(axios.spread(function(comments,users){
        console.log(comments.data);
        const comment = comments.data.filter(function(c){
            return c.watchID===watchID
        })
        document.getElementById('commentBox').innerHTML = comment.map(function(comment){
            user = users.data.find(function(u){
                return u.userID===comment.userID
            })
            return(
                '<div class="row" id="commentItem" style="border-bottom: 1px solid #ccc;margin-top:10px">'+
                    '<img src="http://localhost:9000/'+user.avatar+'" style="width: 10%; height: 45px;border-radius: 45px;">'+
                    '<div class="col-10">'+
                        '<p style="font-weight: bold;font-size: 12px;">'+user.lastName+'</p>'+
                       ' <p style="font-size: 12px;">'+comment.date+'</p>'+
                        '<p style="text-align: left;">'+comment.comment+'</p>'+
                   ' </div>'+
               ' </div>'
            )
        }).join('')
    }))
}