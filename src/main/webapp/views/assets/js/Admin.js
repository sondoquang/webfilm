const togglebtn = document.querySelector('#toggle-btn');
togglebtn.addEventListener('click', () => {
    document.querySelector("#sidebar").classList.toggle("notExpand");
})

const form = document.getElementById("form"),
    password = document.getElementById("pwd"),
    fullname = document.getElementById("fullname"),
    email = document.getElementById("email");
    id = document.getElementById("id");

    const clickReset=()=>{
        form.submit();
    }

    function submitForm(){
        form.addEventListener('submit', e => {
            if(validateInput()){
                form.submit();
            }else{
                e.preventDefault();
            }
        });
    } 
    const clickDelete = (btn)=>{
        const idValue = id.value;
        if(!confirm("Are you sure the removed user has an ID "+idValue+"?")){
            form.addEventListener('submit', e => {
                    e.preventDefault();
            });
        }else{
            if(idValue === ''){
                setError(id,"Id invalid");
                form.addEventListener('submit', e => {
                    e.preventDefault();
                });
            }
        }
    }



const setError =(element,message)=>{
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');
    errorDisplay.innerText = message;
    inputControl.classList.add("error");
    inputControl.classList.remove("success");
}

const setSuccess = element=>{
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');
    errorDisplay.innerText = "";
    inputControl.classList.add("success");
    inputControl.classList.remove("error");
}

const isValidateEmail = email=>{
    const regex = /^[\w\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    return regex.test(String(email).toLowerCase());
}

const isValidateName = fullName =>{
    const regex = /^[a-zA-Z ÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỂưăạảấầẩẫậắằẳẵặẹẻẽềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵýỷỹ]+$/;
    return regex.test(String(fullName));
}

function validateInput(){
    const passwordValue = password.value.trim();
    const fullnameValue = fullname.value.trim();
    const emailValue = email.value.trim();
    error = false;
    if(fullnameValue === ""){
        setError(fullname,"Fullname is required");
        error = true;
    }else{
        messageError = "";
        if(fullnameValue.length >= 50){
           messageError = "Fullname must be at lest 50 character";
        }else if(!isValidateName(fullnameValue)){
           messageError = "Fullname don't correct format";
        }
        if(messageError === ""){
            setSuccess(fullname);
        }else{
            setError(fullname,messageError);
            error = true;
        }
    }

    if(passwordValue === ""){
        setError(password,"Password is required");
        error = true;
    }else{
        if(passwordValue.length > 16){
            setError(password,"Password is must be lest 16 character");
            error = true;
        }
        else{
            setSuccess(password);
        }
    }

    if(emailValue === ""){
        setError(email,"Email is required");
        error = true;
    }else{
        if(!isValidateEmail(emailValue)){
            setError(email,"Provide a valid email address");
            error = true;
        }else{
            setSuccess(email);
        }
    }
    
    if(error){
        return false;
    }else{
        return true;
    }
}


/*======== Js for tab-pane ========*/
const $ = document.querySelector.bind(document);
const $$= document.querySelectorAll.bind(document);

const tabs  = $$('.tab-item');
const panes = $$(".tab-pane")
const tabActive =   $('.tab-item.active_tab');
const line  = $('.container-tabs .line');
line.style.left = tabActive.offsetLeft+10 + "px";
line.style.width = tabActive.offsetWidth - 20 + "px";
tabs.forEach((tab,index) => {

    const pane  = panes[index];
    tab.onclick = function(){
        $('.tab-item.active_tab').classList.remove('active_tab');
        $('.tab-pane.active_pane').classList.remove('active_pane')
        line.style.left = this.offsetLeft+10 + "px";
        line.style.width = this.offsetWidth - 20 + "px";
        pane.classList.add("active_pane");
        this.classList.add('active_tab');
    }
});