<!DOCTYPE html>
<html lang="en">
    <head>
        <title>add User</title>  
    </head>
    <form role="form" method="POST" action="admin/editUser">
        <fieldset style = " 
                background-color: #F0F0F0;
                width: 33%;
                min-width: 198px;
                padding: 10px;
                border: 1.5px solid black;
                border-radius: 5px;
                box-shadow: 0px 0px 10px 4px rgba(119, 119, 119, 0.75);
                -moz-box-shadow: 0px 0px 10px 4px rgba(119, 119, 119, 0.75);
                -webkit-box-shadow: 0px 0px 10px 4px rgba(119, 119, 119, 0.75);
                ">
            <h1>Edit Account Form</h1>
            E-mail address: <input type="text" name="email" id="email" value="${userToEdit.email}"><span><button type="button" onclick="fillForm()" class="btn btn-success">Find User</button></span><br><br>
                        First Name: <input type="text" name="fname" value="${userToEdit.firstName}"><br><br>
                        Last Name: <input type="text" name="lname" value="${userToEdit.lastName}"><br><br>
			New Password: <input type="text" name="password"><br><br>
                        Set Role: <select name="role" id="roleList">
                            <option value="ROLE_USER">ROLE_USER</option>
                            <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                        </select><br><br>
                        <input type="hidden" name= "key" value="${userToEdit.key}">
                        <button type="submit" class="btn btn-primary">Edit Account</button>
		</fieldset> 
      </form>
</html>
