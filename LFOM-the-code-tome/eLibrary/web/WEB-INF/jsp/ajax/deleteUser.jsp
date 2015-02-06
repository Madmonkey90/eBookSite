<!DOCTYPE html>
<html lang="en">
    <head>
        <title>add User</title>  
    </head>
    <form role="form" id="delete">
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
            <h1>Delete Account Form</h1><small>Enter user email or user UUID</small><br><br>
			User UUID: <input id = "key" type="text" name="key"><br><br>                      
			E-mail address: <input type="text" name="email" id="email"><br><br>
                        <button type="button" class="btn btn-primary" onclick="doDeleteUser(document.getElementById('delete'), document.getElementById('key'), document.getElementById('email'))">Delete Account</button>
                        <span id="err" style="color:red"></span>
		</fieldset> 
      </form>
</html>
