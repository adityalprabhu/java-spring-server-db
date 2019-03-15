function AdminUserServiceClient() {
	this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	this.findUserById = findUserById;
	this.deleteUser = deleteUser;
	this.updateUser = updateUser;
//	this.domain = 'http://localhost:8080';
	this.domain = 'https://cs5610-sp19-adityalprabhu.herokuapp.com/';
	this.url = '/api/user'
	var self = this;


	function createUser(user) {
		return fetch(this.domain + this.url, {
			method: 'post',
			body: JSON.stringify(user),
			headers: {
				'content-type': 'application/json'
			}
		}).then(function(response) {
//			console.log(response);
			return response.json();
		});

		console.log(user.username);
	}


	function findAllUsers() {
		return fetch(this.domain + this.url)
		.then(function(response) {
			return response.json();
		});
	}


	function findUserById(userId) {
		return fetch(this.domain + this.url + '/' + userId)
		.then(function(response) {
			return response.json();
		});
	}

	function updateUser(userId, user) { 

		return fetch(this.domain + this.url + '/' + userId, {
			method: 'put',
			body: JSON.stringify(user),
			headers: {
				'content-type': 'application/json'
			}
		}).then(function(response) {
//			console.log(response);
			return response.json();
		});

	}


	function deleteUser(userId) {
		return fetch(this.domain + this.url + '/' + userId, {
			method: 'delete',
			body: "",
			headers: {
				'content-type': 'application/json'
			}
		}).then(function(response) {
//			console.log(response.text());
			return response;
		});
	}
}
