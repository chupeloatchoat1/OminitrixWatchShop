function findBillByEmail(email){
    axios.all([
        axios.get('http://localhost:9000/ominitrix/bill'),
        axios.get('http://localhost:9000/ominitrix/user'),
        axios.get('http://localhost:9000/ominitrix/payment')
    ]).then(axios.spread((bills, users, payments)=>{
        userByEmail = users.data.filter((u)=>{
            return u.email === email
        })
        bill = bills.data.filter((b)=>{
            return b.userID === user.userID
        })
        document.getElementById('bodyTblOrder').innerHTML = bill.map(function(bill){
            var user = userByEmail.find(function(u){
                return u.userID === bill.userID
            })
            console.log(user);
            var payment = payments.data.find(function(p){
                return p.billID === bill.billID
            })
            return(
                '<tr>'+
                    '<td><a class="tablinks" onclick="location.href=\'EditOrder.html?billId='+bill.billID+'\'">'+bill.billID+'</a></td>'+
                    '<td id="email">'+user.email+'</td>'+
                    '<td>'+bill.date+'</td>'+
                    '<td>'+payment.status+'</td>'+
                    '<td>'+bill.total.toLocaleString('en-US', {style : 'currency', currency : 'VND'})+'</td>'+
                    '<td>'+
                        '<span style="display: flex;background:none">'+
                            '<button id="btnEdit"'+
                                'onclick="location.href=\'EditOrder.html?billId='+bill.billID+'\'">'+
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
        
    });
}

function changeQuantityInBill(watchID,billID,price){
    quantity = document.querySelector('#quantity'+watchID+'').value
    console.log(quantity);
    console.log(billID);
    console.log(price);
    
}
async function totalBy() {
    var zzz =[]
    try {
      const result = await axios.get('http://localhost:9000/ominitrix/bill-detail/getBillByMonth')
      result.data.forEach(element => {
        zzz.push(element)
      });
    
      console.log(zzz + "z");
      
      return zzz;
    } catch (err) {
      console.log(err);
    }
  }
function addBill(){
    userID = JSON.parse(sessionStorage.getItem('userid'))

    total = parseFloat(sessionStorage.getItem('total'))
    date = new Date()

    var index = 0
    axios.get('http://localhost:9000/ominitrix/bill')
        .then(function(res){
            index += Object.entries(res.data).length
            axios.post('http://localhost:9000/ominitrix/bill/add',{
                    "date": date,
                    // "total": total,
                    "userID": userID,
                    "billID":"B_"+(index+1)
                }).then(function(res){
                    if(res.status==200){
                        console.log(res.data.billID);
                        var billID = res.data.billID
                        cart = JSON.parse(sessionStorage.getItem('cart'))
                        cart.forEach(item=>{
                            axios.post('http://localhost:9000/ominitrix/bill-detail/add',{
                                "quantity": item.quantity,
                                "price": item.price,
                                "watchID": item.watchID,
                                "billID":billID
                            }).then(function(res){
                                var data=new FormData();
                                data.append("buyQuantity",item.quantity)
                                axios({
                                    url:'http://localhost:9000/ominitrix/watch/updateQuantity/'+item.watchID,
                                    method:'PUT',
                                    data:data
                                }).then(function(res){
                                    sessionStorage.removeItem('cart')
                                    sessionStorage.removeItem('total')
                                    location.href='../HTML/Home.html'
                                }).catch(function(err){
                                    console.log(err);
                                })
                            })
                            
                        })
                        // axios.post('http://localhost:9000/ominitrix/payment/add',{
                        //         "status": "paid",
                        //         "billID":billID
                        //     }).then(function(res){
                        //         console.log(res.data);
                        //         sessionStorage.removeItem('cart')
                        //         sessionStorage.removeItem('total')
                        //         location.reload()
                        //     })
                        
                    }
                })
        })
}

function filterBill(){
    email =document.getElementById('orderEamil').value
    date = document.getElementById('orderDate').value

    if(email && !date){
        axios.all([
            axios.get('http://localhost:9000/ominitrix/bill'),
            axios.get('http://localhost:9000/ominitrix/user'),
            // axios.get('http://localhost:9000/ominitrix/payment'),
            axios.get('http://localhost:9000/ominitrix/bill-detail')
        ]).then(axios.spread(function(bills,users,bill_details){
            var total = 0
            userByEmail = users.data.find(function(u){
                return u.email===email
            })
            if(userByEmail){
                billByUser = bills.data.filter(function(b){
                    return b.userID === userByEmail.userID
                })
                document.getElementById('bodyTblOrder').innerHTML = billByUser.map(function(bill){
                    var detail = bill_details.data.filter(function(b){
                        return b.billID===bill.billID
                    })
                    detail.forEach(element => {
                        total = total+(element.price*element.quantity)
                    });
                    return(
                        '<tr>'+
                            '<td><a class="tablinks" onclick="location.href=\'EditOrder.html?billId='+bill.billID+'\'">'+bill.billID+'</a></td>'+
                            '<td id="email">'+userByEmail.email+'</td>'+
                            '<td>'+bill.date+'</td>'+
                            // '<td>'+payment.status+'</td>'+
                            '<td>'+total.toLocaleString('en-US', {style : 'currency', currency : 'VND'})+'</td>'+
                            // '<td>'+
                            //     '<span style="display: flex;background:none">'+
                            //         '<button id="btnEdit"'+
                            //             'onclick="location.href=\'EditOrder.html?billId='+bill.billID+'\'">'+
                            //             '<i class="material-icons">edit</i>'+
                            //         '</button>'+
                            //         '<button id="btnDelete">'+
                            //             '<i class="material-icons">delete</i>'+
                            //         '</button>'+
                            //     '</span>'+
                            // '</td>'+
                        '</tr>'
                    )
                }).join('')
            }else{
                alert('Không tìm thấy bill')
            }
            
        }))
    }else if(!email && !date){
        location.reload()
    }
}
function filterByDate(){
    date = document.getElementById('orderDate').value
    if(date){
        axios.all([
            axios.get('http://localhost:9000/ominitrix/bill'),
            axios.get('http://localhost:9000/ominitrix/user'),
            // axios.get('http://localhost:9000/ominitrix/payment'),
            axios.get('http://localhost:9000/ominitrix/bill-detail')
        ]).then(axios.spread(function(bills,users,bill_details){
            billByDate = bills.data.filter(function(b){
                return formatDate(new Date(Date.parse(b.date))) === date
            })
            document.getElementById('bodyTblOrder').innerHTML = billByDate.map(function(bill){
                var total=0
                var user = users.data.find(function(u){
                    return u.userID === bill.userID
                })
                // var payment = payments.data.find(function(p){
                //     return p.billID === bill.billID
                // })
                var detail = bill_details.data.filter(function(b){
                    return b.billID===bill.billID
                })
                detail.forEach(element => {
                    total = total+(element.price*element.quantity)
                });
                return(
                    '<tr>'+
                        '<td><a class="tablinks" onclick="location.href=\'EditOrder.html?billId='+bill.billID+'\'">'+bill.billID+'</a></td>'+
                        '<td id="email">'+user.email+'</td>'+
                        '<td>'+bill.date+'</td>'+
                        // '<td>'+payment.status+'</td>'+
                        '<td>'+total.toLocaleString('en-US', {style : 'currency', currency : 'VND'})+'</td>'+
                        // '<td>'+
                        //     '<span style="display: flex;background:none">'+
                        //         '<button id="btnEdit"'+
                        //             'onclick="location.href=\'EditOrder.html?billId='+bill.billID+'\'">'+
                        //             '<i class="material-icons">edit</i>'+
                        //         '</button>'+
                        //         '<button id="btnDelete">'+
                        //             '<i class="material-icons">delete</i>'+
                        //         '</button>'+
                        //     '</span>'+
                        // '</td>'+
                    '</tr>'
                )
            }).join('')
        }))
    }else{
        location.reload()
    }
}
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}