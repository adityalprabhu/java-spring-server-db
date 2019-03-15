(function(){
	var $usernameFld, $passwordFld;
	var $removeBtn, $editBtn, $createBtn;
	var $firstNameFld, $lastNameFld;
	var $userRowTemplate, $tbody;
	var userService = new AdminUserServiceClient();
	$(main);

	function main() {
		$usernameFld = $("#usernameFld");
		$passwordFld = $("#passwordFld");
		$firstNameFld = $("#firstNameFld");
		$lastNameFld = $("#lastNameFld");
		$roleFld = $("#roleFld");
		$dobFld = $("#dobFld");
		$userRowTemplate = $(".wbdv-template");
		$tbody = $("tbody");
		updateUserId = 0;
		allUsers = [];
		var userToBeUpdated;
		$("#roleFld").val("");


		userService
		.findAllUsers()
		.then(renderUsers);

		$('#searchBtn').click(searchUser);

		$('#createBtn').click(function(){
			if(!fieldsEmpty()){
				createUser();
			}else{
				alert("All fields required to create user!");
			}
		});

		$('#updateBtn').click(function(){
			if(updateUserId != 0)
				updateUser($(this));
			else
				console.log("User doesn't exist to update!")
		});

		$(".wbdv-remove-btn").click(function() {
			deleteUser($(this));
		});

		$(".wbdv-edit-btn").click(function() {
			findUserById($(this));
		});

		if ( $(window).width() > 450) {      
			$("mobileScreenMsg").hide()
		} 
		else {
			$("mobileScreenMsg").show()
		}

	}

	/* function to create a new user
	 * the values for the user are from the form
	 */
	function createUser() { 
		var user = new User(
				$usernameFld.val(),
				$passwordFld.val(),
				"",
				$firstNameFld.val(),
				$lastNameFld.val(),
				"",
				$roleFld.val(),
				$dobFld.val());

		exists = false;

		for(var u=0; u<allUsers.length; u++) {
			if(allUsers[u].username == $usernameFld.val()){
				console.log("User exists!!!");
				alert("User with the same username already exists!");
				exists = true;
			}

			if(!exists && u+1 == allUsers.length){
				userService
				.createUser(user)
				.then(findAllUsers);
			}
		}
	}


	/*
	 * function to find all the users
	 * present to refresh the list
	 */
	function findAllUsers() { 
		userService
		.findAllUsers()
		.then(renderUsers);
	}


	/*
	 * function to find the user by their id
	 * and to update the form with the values
	 */
	function findUserById(el) {
		updateUserId = el.parent().parent()[0].id
		userService
		.findUserById(updateUserId)
		.then(renderUser);
	}

	/*
	 * function to update selected user
	 * and refresh list
	 */
	function updateUser() {

		var updateUser = new User(
				$usernameFld.val(),
				$passwordFld.val(),
				"",
				$firstNameFld.val(),
				$lastNameFld.val(),
				"",
				$roleFld.val(),
				$dobFld.val());

		userService
		.updateUser(updateUserId, updateUser)
		.then(findAllUsers);
	}


	/*
	 * function to delete selected user
	 * and refresh the list
	 */
	function deleteUser(el) { 

		delUserId = el.parent().parent()[0].id;
		userService
		.deleteUser(delUserId)
		.then(function(response){
			response.text().then(function (text) {
				if(text == "User Deleted"){
					console.log(delUserId + " : " + text);
					findAllUsers();
				}else{
					console.log(text);
				}
			});
		});
	}

	/*
	 * function to reset form fields 
	 * to empty
	 */
	function resetFields(){
		$("#usernameFld").val("");
		$("#passwordFld").val("");
		$("#firstNameFld").val("");
		$("#lastNameFld").val("");
		$("#roleFld").val("");
		$("#dobFld").val("");
	}


	/*
	 * function to accept a user
	 * and update the form values
	 * with the properties of the user
	 */
	function renderUser(user) { 
		$usernameFld.val(user.username);
		$passwordFld.val(user.password);
		$firstNameFld.val(user.firstName);
		$lastNameFld.val(user.lastName);
		$roleFld.val(user.role);
		$dobFld.val(user.dob);
	}



	/*
	 * function to clear existing list
	 * and display refreshed list
	 */
	function renderUsers(users) {

//		clear the list, form and id
		updateUserId = 0;
		resetFields();
		$tbody.empty();
		allUsers = users;
		for(var u=0; u<users.length; u++) {
//			console.log(users[u]);
			var clone = $userRowTemplate.clone(true).prop('id', users[u].id ).show();
			clone.find(".username").html(users[u].username);
			clone.find(".password").html(users[u].password);
			clone.find(".firstName").html(users[u].firstName);
			clone.find(".lastName").html(users[u].lastName);
			clone.find(".role").html(users[u].role);
			clone.find(".dob").html(users[u].dob);
			$tbody.append(clone);
		}

	}

	/*
	 * function to check if form 
	 * fields are empty
	 */
	function fieldsEmpty(){
		return ($usernameFld.val() == "" || $passwordFld.val() == "" || $firstNameFld.val() == "" || $lastNameFld.val() == "" || $roleFld.val() == null || $dobFld.val() == "")
	}

	function searchUser(){

		var usernameValue = $usernameFld.val();
		var passwordValue = $passwordFld.val();
		var firstNameValue = $firstNameFld.val();
		var lastNameValue = $lastNameFld.val();
		var roleValue = $roleFld.val();
		var dobValue = $dobFld.val();


		$("table tr").each(function(index) {
			if (index != 0) {

				$row = $(this);

				if($row[0].id != "userForm"){
					var usernameId = $row.find("td:eq(0)").text();
					var passwordId = $row.find("td:eq(1)").text();
					var firstNameId = $row.find("td:eq(2)").text();
					var lastNameId = $row.find("td:eq(3)").text();
					var roleId = $row.find("td:eq(4)").text();
					var dobId = $row.find("td:eq(5)").text();


					if(usernameValue != ""){
						console.log("username")
						if (usernameId.indexOf(usernameValue) != 0) {
							$(this).hide();
						}
						else {
							$(this).show();
						}
					}

					if(passwordValue != ""){
						console.log("password")

						if (passwordId.indexOf(passwordValue) != 0) {
							$(this).hide();
						}
						else {
							$(this).show();
						}
					}

					if(firstNameValue != ""){
						console.log("firstname")

						if (firstNameId.indexOf(firstNameValue) != 0) {
							$(this).hide();
						}
						else {
							$(this).show();
						}
					}

					if(lastNameValue != ""){
						console.log("lastname")

						if (lastNameId.indexOf(lastNameValue) != 0) {
							$(this).hide();
						}
						else {
							$(this).show();
						}
					}

					if(roleValue != null){
						console.log("role: "+ roleValue)
						if (roleId.indexOf(roleValue) != 0) {
							$(this).hide();
						}
						else {
							$(this).show();
						}
					}

					if(usernameValue == "" && roleValue == null && lastNameValue == "" && firstNameValue == "" && passwordValue == ""){

						if (usernameId == usernameValue) {
							$(this).hide();
						}
						else {
							$(this).show();
						}

					}

					if(dobValue != ""){
						console.log("dob" + dobValue)
						if (dobId.indexOf(dobValue) != 0) {
							$(this).hide();
						}
						else {
							$(this).show();
						}
					}
				}
			}
		});
	}
})();