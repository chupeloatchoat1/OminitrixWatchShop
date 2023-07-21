function filter(){
    email = document.getElementById('userEmail').value
    phone = document.getElementById('userPhone').value
    if(email && !phone){
        axios.all([
            axios.get('http://localhost:9000/ominitrix/user'),
            axios.get('http://localhost:9000/ominitrix/account')
        ]).then(axios.spread((users,accounts)=>{
            user = users.data.find((u)=>{
                return u.email === email
            })
            if(!user){
                alert("User có email này không tồn tại")
            }
            else{
                account = accounts.data.find(function(a){
                    return a.userName === user.userID
                })
                role = null
                    if(account.role){
                        role = 'Admin'
                    }else{
                        role = 'Customer'
                    }
                document.getElementById('bodyTblCustomer').innerHTML = 
                        '<tr>'+
                                '<td><img style="height:50px;wight:50px;border-radius:50px" src="http://localhost:9000/'+user.avatar+'" style="wight:50px,height:50px"/></td>'+
                                '<td>'+user.firstName+'</td>'+
                                '<td>'+user.lastName+'</td>'+
                                '<td>'+user.email+'</td>'+
                                '<td>'+user.phone+'</td>'+
                                // '<td>'+role+'</td>'+
                                '<td>'+user.address+'</td>'+
                                '<td>'+
                                    '<span style="display: flex;">'+
                                        '<button id="btnEdit" class="tablinks"'+
                                        'onclick="location.href=\'EditCustomer.html?userId='+user.userID+'\'">'+
                                            '<i class="material-icons">edit</i>'+
                                        '</button>'+
                                        '<button id="btnDelete">'+
                                            '<i class="material-icons">delete</i>'+
                                        '</button>'+
                                    '</span>'+
                            '</td>'+
                        '</tr>'
            }
            
        })).catch((err) => {
            console.log(err);
        });
    }else if(!email && phone){
        axios.all([
            axios.get('http://localhost:9000/ominitrix/user'),
            axios.get('http://localhost:9000/ominitrix/account')
        ]).then(axios.spread((users,accounts)=>{
            user = users.data.find((u)=>{
                return u.phone === phone
            })
            if(!user){
                alert("User có số điện thoại cần tìm không tồn tại")
            }
            else{
                account = accounts.data.find(function(a){
                    return a.userName === user.userID
                })
                role = null
                    if(account.role){
                        role = 'Admin'
                    }else{
                        role = 'Customer'
                        }
                    document.getElementById('bodyTblCustomer').innerHTML = 
                            '<tr>'+
                                    '<td><img style="height:50px;wight:50px;border-radius:50px" src="http://localhost:9000/'+user.avatar+'" style="wight:50px,height:50px"/></td>'+
                                    '<td>'+user.firstName+'</td>'+
                                    '<td>'+user.lastName+'</td>'+
                                    '<td>'+user.email+'</td>'+
                                    '<td>'+user.phone+'</td>'+
                                    // '<td>'+role+'</td>'+
                                    '<td>'+user.address+'</td>'+
                                    '<td>'+
                                        '<span style="display: flex;">'+
                                            '<button id="btnEdit" class="tablinks"'+
                                            'onclick="location.href=\'EditCustomer.html?userId='+user.userID+'\'">'+
                                                '<i class="material-icons">edit</i>'+
                                            '</button>'+
                                            '<button id="btnDelete">'+
                                                '<i class="material-icons">delete</i>'+
                                            '</button>'+
                                        '</span>'+
                                '</td>'+
                            '</tr>'
            }
            
        })).catch((err) => {
            console.log(err);
        });
    }
    else if(!email && !phone){
        location.reload()
    }
}

function filterByRole(){
    role = document.getElementById('sortByRole').value
    if(role=='Admin'){
        axios.all([
            axios.get('http://localhost:9000/ominitrix/account'),
            axios.get('http://localhost:9000/ominitrix/user')
        ]).then(axios.spread((accounts,users)=>{
            account = accounts.data.filter((a)=>{
                return a.role === true
            })
            document.getElementById('bodyTblCustomer').innerHTML = account.map((a)=>{
                user = users.data.find((u)=>{
                    return u.userID === a.userName
                })
                return(
                    '<tr>'+
                            '<td><img style="height:50px;wight:50px;border-radius:50px" src="http://localhost:9000/'+user.avatar+'" style="wight:50px,height:50px"/></td>'+
                            '<td>'+user.firstName+'</td>'+
                            '<td>'+user.lastName+'</td>'+
                            '<td>'+user.email+'</td>'+
                            '<td>'+user.phone+'</td>'+
                            // '<td>'+role+'</td>'+
                            '<td>'+user.address+'</td>'+
                            '<td>'+
                                '<span style="display: flex;">'+
                                    '<button id="btnEdit" class="tablinks"'+
                                    'onclick="location.href=\'EditCustomer.html?userId='+user.userID+'\'">'+
                                        '<i class="material-icons">edit</i>'+
                                    '</button>'+
                                    '<button id="btnDelete">'+
                                        '<i class="material-icons">delete</i>'+
                                    '</button>'+
                                '</span>'+
                        '</td>'+
                    '</tr>'
                )
            }).join('')
        })).catch((err) => {
            console.log(err);
        });
    }else if(role==='Customer'){
        axios.all([
            axios.get('http://localhost:9000/ominitrix/account'),
            axios.get('http://localhost:9000/ominitrix/user')
        ]).then(axios.spread((accounts,users)=>{
            account = accounts.data.filter((a)=>{
                return a.role === false
            })
            document.getElementById('bodyTblCustomer').innerHTML = account.map((a)=>{
                user = users.data.find((u)=>{
                    return u.userID === a.userName
                })
                return(
                    '<tr>'+
                            '<td><img style="height:50px;wight:50px;border-radius:50px" src="http://localhost:9000/'+user.avatar+'" style="wight:50px,height:50px"/></td>'+
                            '<td>'+user.firstName+'</td>'+
                            '<td>'+user.lastName+'</td>'+
                            '<td>'+user.email+'</td>'+
                            '<td>'+user.phone+'</td>'+
                            // '<td>'+role+'</td>'+
                            '<td>'+user.address+'</td>'+
                            '<td>'+
                                '<span style="display: flex;">'+
                                    '<button id="btnEdit" class="tablinks"'+
                                    'onclick="location.href=\'EditCustomer.html?userId='+user.userID+'\'">'+
                                        '<i class="material-icons">edit</i>'+
                                    '</button>'+
                                    '<button id="btnDelete">'+
                                        '<i class="material-icons">delete</i>'+
                                    '</button>'+
                                '</span>'+
                        '</td>'+
                    '</tr>'
                )
            })
        })).catch((err) => {
            console.log(err);
        });
    }else{
        location.reload()
    }
}