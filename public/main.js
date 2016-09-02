var ericApp ={

  urls:{

    createUser:       '/create-user/',
  }
};


function addUser(user){
  $.ajax({
    url: ericApp.urls.createUser,
    method: "POST",
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    data: JSON.stringify(user),
    success: function(data){
      console.log('user added!', data);

    },
    error: function(error){
      console.log("Add User", error);
    }
  });
}


$('#userForm').submit(function(event){
  event.preventDefault();
  var user= {};
  user.userName = $('input[name="newName"]').val();
  user.passwordHash = $('input[name="newPassword"]').val();
  addUser(user);
  $('.hello').html('');
});
function grabSessionValue(name) {
  return window.sessionStorage.getItem(name);
}
