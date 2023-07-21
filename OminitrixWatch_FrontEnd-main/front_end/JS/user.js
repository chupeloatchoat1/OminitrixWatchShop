function editUser(){
    console.log("edit user");
    const email = document.getElementById('email').value
    const fName = document.getElementById('newFName').value 
    const lName = document.getElementById('newLName').value 
    const phone = document.getElementById('newPhone').value 
    const address = document.getElementById('newAddress').value 
    const userID= JSON.parse(sessionStorage.getItem('userid'))
    const avatar = document.getElementById('image_uploads')

    const data = new FormData()
    data.append("email",email)
    data.append("firstName",fName)
    data.append("lastName",lName)
    data.append("phone",phone)
    data.append("address",address)
    data.append("userID",userID)
    data.append("fileName",avatar.files[0])
    
    axios({
        method: 'put',
        url:'http://localhost:9000/ominitrix/user/update',
        headers: { "Content-Type": "multipart/form-data" },
        data:data,
    }).then((result) => {
        console.log(result.status);
        location.reload()
    }).catch((err) => {
        console.log(err);
    });
    // axios.put('http://localhost:9000/ominitrix/user/update',{
    //     userID:userID,
    //     address: address,
    //     email: email,
    //     firstName: fName,
    //     lastName: lName,
    //     phone: phone,
    //     avatar:avatar
    // })
    //     .then((result) => {
    //         if(result.status==200){
    //             alert("Sửa thông tin thành công")
    //         }   
    //     }).catch((err) => {
    //         console.log(err);
    //     });
}