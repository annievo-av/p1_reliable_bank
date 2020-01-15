function validatePassword() {
	
	var password1Signup = document.getElementById('password1Signup');
	var password2Signup = document.getElementById('password2Signup');

	if (password1Signup.value == password2Signup.value) {
		password2Signup.style.borderColor = '#80bdff';
		password2Signup.style.boxShadow = '0 0 0 0.2rem rgba(0,123,255,.25)';
	} else {
		password2Signup.style.borderColor = 'red';
		password2Signup.style.boxShadow = '0px 0px 10px red';
	}
}