function validateForm(){
    // Variable for Password length
	var y = document.getElementById("exampleInputPassword1").value;

    //Alert for Password length
	if(y.length < 8) {  
	    alert("Password length must be atleast 8 characters in length");
	    return false;  
  	}
}