function registration(){
                var first_name = document.getElementById('firstName').value
                var last_name = document.getElementById('lastName').value
                var phone = document.getElementById('phone').value
                var email = document.getElementById('email').value
                var password = document.getElementById('confirmPassword').value

                var user={
                    "userID":email,
                    "address": "",
                    "email": email,
                    "firstName": first_name,
                    "lastName": last_name,
                    "phone": phone,
                    "otp":null,
                    "otpexpire":null
                }
                var account ={
                    "userName":user.userID,
                    "password": password,
                    "role": false,
                    "user_id":user.userID
                }
                axios.post('http://localhost:9000/ominitrix/user/add', user)
                    .then(function(response){
                        console.log(response.status);
                    if (response.status == 201||response.status == 200) {
                        axios.post('http://localhost:9000/ominitrix/account/add',account)
                            .then(function(response){
                                console.log(response.status);
                                if(response.status==201 || response.status==200){
                                    
                                    sessionStorage.setItem('userid',JSON.stringify(user.userID))
                                    sessionStorage.setItem('account',JSON.stringify(account))
                                    location.href = '../HTML/Home.html'
                                }
                            })    
                    }    
                    }).catch(function(err){
                        console.log(err);
                    })

}

function isCustomer(){
        if('userid' in sessionStorage && 'account' in sessionStorage){
            userID = JSON.parse(sessionStorage.getItem('userid'))
            account = JSON.parse(sessionStorage.getItem('account'))
            axios.get('http://localhost:9000/ominitrix/user/findByID/'+userID)
                .then(function(user){
                    document.getElementById('guest').style.display ="none"
                    document.getElementById('customer').style.display ="inline"
                    document.getElementById('userName').innerHTML = '<img style="height:30px;wight:30px;border-radius:30px" src="http://localhost:9000/'+user.data.avatar+'"/>'+user.data.lastName+''
                    if(account.role===false){
                        document.getElementById('roleAdmin').style.display="none"
                        console.log('customer');
                    }else if(account.role===true){
                        document.getElementById('roleAdmin').style.display="inline"
                        console.log('admin');
                    }
                    console.log("exist");
                })
        }else{
            document.getElementById('guest').style.display ="inline"
            document.getElementById('customer').style.display ="none"
            console.log('userid dont exist');
            return false
        }
    
}

function logOut(){
    sessionStorage.removeItem('userid')
    sessionStorage.removeItem('account')
    document.getElementById('guest').style.display ="inline"
    document.getElementById('customer').style.display ="none"
    console.log('userid dont exit');
    if('cart' in sessionStorage){
        sessionStorage.removeItem('cart')
    }
    history.go()
}

function login(){
    var email = document.getElementById('email').value
    var password = document.getElementById('password').value
    axios.get('http://localhost:9000/ominitrix/user')
            .then(function(response){
                let user = response.data.find(function(u){
                    return u.email===email
                })
                console.log(user);
                if(typeof(user)!=='undefined'){
                    axios.get('http://localhost:9000/ominitrix/account/'+user.userID)
                    .then(function(response){
                        if(typeof(response.data)!=='undefined'){
                            if(response.data.password===password){
                                // location.href = '../HTML/Home.html' 
                                sessionStorage.setItem('userid',JSON.stringify(user.userID))
                                sessionStorage.setItem('account',JSON.stringify(response.data))
                                location.href='../HTML/Home.html'
                            }else{
                                alert('Password sai. Nhập lại password')
                                document.getElementById('password').focus()
                            }
                        }else{
                            alert('Tài khoản này không tồn tại')
                        }
                    })
                }else{
                    alert('Email không đúng')
                    document.getElementById('email').focus()
                }
                
            })
        
        
}