
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>add User</title>  
    </head>
    <form role="form" method="POST" action="admin/addUser">
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
            <h1>New Account Form</h1>
			First Name: <input type="text" name="fname"><br><br>
                        Last Name: <input type="text" name="lname"><br><br>
			Password: <input type="text" name="password" id="password"><br><br>                       
			E-mail address: <input type="text" name="email" id="email"><br><br>
                        <button type="submit" class="btn btn-primary">Add New Account</button>
		</fieldset> 
      </form>
</html>