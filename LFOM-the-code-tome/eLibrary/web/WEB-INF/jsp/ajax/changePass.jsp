<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function changePass(password, cpassword, form){
                if(password !== cpassword){
                    document.getElementById('err').innerHTML = "Passwords do not match.";
                }
                else{
                    var passform = form;
                    passform.method = 'post';
                    passform.action = './accountPage/changePass';
                    form.submit();
                }
            }
        </script>  
   </head>
    <form role="form" method="POST" action="accountPage/changePass" onsubmit="changePass(document.getElementById('password'),document.getElementById('cpassword'))">
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
            <h1>Change Password</h1>
            New Password: <input type="password" name="password" id="title"><br><br>
                        Confirm Password: <input type="password" name="cpassword"><br><br>
                        <span id="err"></span><br><br>
                        <button type="submit" class="btn btn-primary">Change Password</button>
		</fieldset> 
      </form>
</html>
