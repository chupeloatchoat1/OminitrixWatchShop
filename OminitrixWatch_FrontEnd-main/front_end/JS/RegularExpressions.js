function checkEmail() {
  var email = document.getElementById("email");
  var filter =
    /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  if (!filter.test(email.value)) {
    document.getElementById("errorEmail").innerHTML = "Email không hợp lệ";
    email.style.borderColor="red"
    email.focus;
  }
  else{
    document.getElementById("errorEmail").innerHTML = ""
  }
}
function checkEmpty(id, error) {
  var input = document.getElementById(id);
  var filter = /^[A-Za-z0-9]+/;
  if (!filter.test(input.value)) {
    document.getElementById(error).innerHTML = "Không được để trống";
    input.style.borderColor="red"
    input.focus;
  }
  else{
    document.getElementById(error).innerHTML = ""
  }
}
function checkPassword(){
    var password = document.getElementById('password');
    var filter = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    if(!filter.test(password.value)){
        document.getElementById('errorPassword').innerHTML="Tối thiểu tám ký tự, ít nhất một chữ cái và một số";
        password.style.borderColor="red"
        password.focus;
    }
    else{
        document.getElementById('errorPassword').innerHTML=""
    }

}
function checkPhoneNumber(id, error){
    var phone = document.getElementById(id);
    var filter = /((09|03|07|08|05)+([0-9]{8})\b)/;
    if(!filter.test(phone.value)){
        document.getElementById(error).innerHTML="Số điện thoại không hợp lệ"
        phone.style.borderColor="red"
        phone.focus;
    }
    else{
        document.getElementById(error).innerHTML="";
    }
}
function checkConfirmPassword(){
    var password = document.getElementById('password');
    var confirmPassword = document.getElementById('confirmPassword');
    if(password.value!==confirmPassword.value){
        document.getElementById('errorConfrim').innerHTML = "Password không khớp";
        confirmPassword.style.borderColor="red"
        confirmPassword.focus;
    }
    else{
        document.getElementById('errorConfrim').innerHTML = ""
    }
}

function scrollFunction() {
  if (document.body.scrollTop > 110 || document.documentElement.scrollTop > 110) {
    document.getElementById("navbar").style.padding = "0px 0px";
    document.getElementById("navbar").style.paddingTop = "15px";
    document.getElementById("navbar").style.paddingBottom = "15px";
    document.getElementById("navbar").style.paddingRight = "15px";
    document.getElementById("navbar").style.top = "0";
    document.getElementById("navbar").style.position = "fixed";
    document.getElementById("navbar").style.zIndex = "1";
    document.getElementById("navbar").style.borderBottomStyle="solid";
    document.getElementById("navbar").style.borderBottomWidth="1px"
    document.getElementById("navbar").style.borderBottomColor="lightgray"
  } else {
    document.getElementById("navbar").style.top = "auto";
    document.getElementById("navbar").style.position = "unset";
    document.getElementById("navbar").style.borderBottomStyle="none";
  }
}

let slideIndex = 1;
showSlides(slideIndex);

function currentSlide(n) {
  showSlides(slideIndex = n);
}
function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("demo");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
}

function likeProduct(watchID){
  var like = document.getElementById('clickHeart');
  if(like.textContent==="favorite_border"){
    like.textContent='favorite';
    like.style.color='red'
    const userID = JSON.parse(sessionStorage.getItem('userid'))

    axios.post('http://localhost:9000/ominitrix/like/add', {
        "userID": userID,
        "watchID": watchID
    }).then(function(res){
        if(res.status==200){
           
        }
    }) 
  }else{
    like.textContent="favorite_border"
    like.style.color='black'
    axios.delete('http://localhost:9000/ominitrix/like/delete?'+"userID="+userID+"&watchID="+watchID)
  }
}

function openContent(evt, cityContent) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityContent).style.display = "block";
  evt.currentTarget.className += " active";
}

function previewIamge(evt){
  const output = document.getElementById('preview')
  const src = URL.createObjectURL(evt.target.files[0]);
                                    console.log(src);
                                    output.src = src
}

function previewImages(evt){
  const output = document.querySelector('#Images')
  while(output.firstChild) {
    output.removeChild(output.firstChild);
  }
  for(const file of evt.target.files){
    const image = document.createElement('img')
    image.style.width='150px'
    image.style.marginBottom='10px'
    image.style.marginTop='10px'
    image.src = URL.createObjectURL(file)
    output.appendChild(image)
  }
}
