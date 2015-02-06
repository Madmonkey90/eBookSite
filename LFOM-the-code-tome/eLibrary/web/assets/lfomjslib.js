
function searchRedirect(form){
    var search = form;
    search.method = 'post';
    search.action = './search';
    search.submit();
}

function enterSearch(event, form){
    if (event.which === 13 || event.keyCode === 13) {
        var search = form;
        search.method = 'post';
        search.action = './search';
        search.submit();
        return false;
        }
    return true;
}

function signUp(password, cpassword, email, cemail, form){
    var signup = form;
    if(email.value === ""){
        document.getElementById("errEmail").innerHTML = "Email cannot be blank";
        return;
    }
    if(password.value === ""){
        document.getElementById("errPass").innerHTML = "Password cannot be blank";
		return;
    }
    if(password.value !== cpassword.value){
        document.getElementById("errPass").innerHTML = "Passwords do not match"; 
        return;
    }
    if(email.value !== cemail.value){
        document.getElementById("errEmail").innerHTML = "Emails do not match"; 
        return;
    }
    signup.method = 'post';
    signup.action = './signup';
    signup.submit();
}

function expand(listid){
    document.getElementById("expand"+listid).style.visibility = "visible";
    document.getElementById("exell"+listid).innerHTML = "";
}

function getAddUser(){
                $.ajax({
                   type:"GET",
                   url: "admin/addUser",
                   success: function(response){
                       $("#page-content-wrapper").html(response);
                   }
                });
}

function getEditUser(){
    $.ajax({
        type:"GET",
        url: "admin/editUser",
        success: function(response){
            $("#page-content-wrapper").html(response);
        }
    });
}

function getDeleteUser(){
    $.ajax({
        type:"GET",
        url: "admin/deleteUser",
        success: function(response){
            $("#page-content-wrapper").html(response);
        }
    });
}

function fillForm(){
    var userEmail = $("#email").val();
    $.ajax({
        type:"POST",
        url: "admin/fillEditForm",
        data: {email: userEmail}, 
        success: function(response){
            $("#page-content-wrapper").html(response);
        }
    });
}

function doDeleteUser(form, UUID, email){
    var doDelete = form;
    if((UUID.value !== "") && (email.value !== "")){
        document.getElementById("err").innerHTML = "Only fill one field!";
        return;
    }
    if((UUID.value === "") && (email.value === "")){
        document.getElementById("err").innerHTML = "Fill a field!";
        return; 
    }
    doDelete.method = 'post';
    doDelete.action = './admin/deleteUser';
    doDelete.submit();
}

function getAddItem(){
                $.ajax({
                   type:"GET",
                   url: "admin/addItem",
                   success: function(response){
                       $("#page-content-wrapper").html(response);
                   }
                });
}

function getSearchGraph(){
    $.ajax({
        type: "GET",
        url: "admin/searchData",
        success: function(data){
           drawSearchGraph(data); 
        },
        error: function(xhr,status, error){
            alert("Error: Status " + status+" Message: "+error);
        }
    });
    
}
    
function drawSearchGraph(searchData){
    if(!(typeof window.myGraph === 'undefined')){ window.myGraph.destroy(); }
            var graphData = {
    labels: [],
    datasets: [
        {
            label: "dataset",
            fillColor: "rgba(45,195,225,0.5)",
            strokeColor: "rgba(45,195,225,1)",
            pointColor: "rgba(45,195,225,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(45,195,225,1)",
            data: []
        }
    ]
};
    for(i = 0; i < searchData.length; i++){
        var label = searchData[i][0];
        var dataPoint = searchData[i][1];
        graphData.labels[graphData.labels.length] = label;
        graphData.datasets[0].data[graphData.datasets[0].data.length] = dataPoint;
    }
    
    var ctx = document.getElementById("topCanvas").getContext("2d");
    ctx.canvas.width = 400;
    ctx.canvas.height = 200;
    window.myGraph = new Chart(ctx).Line(graphData, {responsive: true});
}


function getPageGraph(){
    $.ajax({
        type: "GET",
        url: "admin/pageData",
        success: function(data){
           drawPageGraph(data); 
        },
        error: function(xhr,status, error){
            alert("Error: Status " + status+" Message: "+error);
        }
    });
}
    
  function drawPageGraph(pageData){
      if(!(typeof window.myGraph === 'undefined')){ window.myGraph.destroy(); }
            var graphData = {
    labels: [],
    datasets: [
        {
            label: "dataset",
            fillColor: "rgba(45,195,225,0.5)",
            strokeColor: "rgba(45,195,225,1)",
            pointColor: "rgba(45,195,225,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(45,195,225,1)",
            data: []
        }
    ]
};
    for(i = 0; i < pageData.length; i++){
        var label = pageData[i][0];
        var dataPoint = pageData[i][1];
        graphData.labels[graphData.labels.length] = label;
        graphData.datasets[0].data[graphData.datasets[0].data.length] = dataPoint;
    }
    
    var ctx = document.getElementById("topCanvas").getContext("2d");
    ctx.canvas.width = 400;
    ctx.canvas.height = 200;
    window.myGraph = new Chart(ctx).Radar(graphData, {responsive: true});
}  

function getCheckoutGraph(){
    $.ajax({
        type: "GET",
        url: "admin/checkoutData",
        success: function(data){
           drawCheckoutGraph(data); 
        },
        error: function(xhr,status, error){
            alert("Error: Status " + status+" Message: "+error);
        }
    });
}
    
  function drawCheckoutGraph(checkoutData){
      if(!(typeof window.myGraph === 'undefined')){ window.myGraph.destroy(); }
            var graphData = {
    labels: [],
    datasets: [
        {
            label: "dataset",
            fillColor: "rgba(45,195,225,0.5)",
            strokeColor: "rgba(45,195,225,1)",
            pointColor: "rgba(45,195,225,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(45,195,225,1)",
            data: []
        }
    ]
};
    for(i = 0; i < checkoutData.length; i++){
        var label = checkoutData[i][0];
        var dataPoint = checkoutData[i][1];
        graphData.labels[graphData.labels.length] = label;
        graphData.datasets[0].data[graphData.datasets[0].data.length] = dataPoint;
    }
    
    var ctx = document.getElementById("topCanvas").getContext("2d");
    ctx.canvas.width = 400;
    ctx.canvas.height = 200;
    window.myGraph = new Chart(ctx).Line(graphData, {responsive: true});
}  

function getTicketList(){
    $.ajax({
        type: "GET",
        url: "admin/tickets",
        success: function(response){
           $("#page-content-wrapper").html(response);
        },
        error: function(xhr,status, error){
            alert("Error: Status " + status+" Message: "+error);
        }
    });
}

function resolveTicket(key){
    $.ajax({
        type: "POST",
        url: "admin/tickets",
        data: { key : key},
        success: function(response){
           getTicketList();
        },
        error: function(xhr,status, error){
            alert("Error: Status " + status+" Message: "+error);
        }
    });
}

function getWishlist(){
    $.ajax({
        type: "GET",
        url: "accountPage/wishlist",
        success: function(response){
           $("#page-content-wrapper").html(response);
        },
        error: function(xhr,status, error){
            alert("Error: Status " + status+" Message: "+error);
        }
    });
}


function getChangePass(){
                $.ajax({
                   type:"GET",
                   url: "accountPage/changePass",
                   success: function(response){
                       $("#page-content-wrapper").html(response);
                   }
                });
}

function removeFromWishlist(bookid){
    $.ajax({
        type: "GET",
        url: "accountPage/removeFromWishlist",
        data: { key : bookid },
        success: function(){
           var path = location.pathname;
           if(path === "/eLibrary/accountPage") 
           {
              getWishlist(); 
           }
           else{ location.reload();}
        },
        error: function(xhr,status, error){
            alert("Error: Status " + status+" Message: "+error);
        }
    });

}


function deactivateAccount(){
    var c = confirm("Are you sure you want to deactivate your account?");
    if(c == true){
        window.location = "http://69.121.220.130:8085/eLibrary/accountPage/deactivateAccount";
    }
}
function sortBy(sortCritera, input){
    var url = "search/sortBy";
    var car = sortCritera.valueOf();
    url = url + car;
   $.ajax({
        type: "POST",
        url: url,
        data : {input : input},
        success: function(response){
           $("#page-content-wrapper").html(response);
        },
        error: function(xhr,status, error){
            alert("Error: Status " + status+" Message: "+error);
        }
    }); 

}

function getCheckoutList(){
    $.ajax({
        type: "GET",
        url: "accountPage/checkedoutItems",
        success: function(response){
           $("#page-content-wrapper").html(response);
        },
        error: function(xhr,status, error){
            alert("Error: Status " + status+" Message: "+error);
        }
    });
}

function rebuildSearchIndex(){
    $.ajax({
        type: "GET",
        url: "admin/rebuildIndex",
        success: function(response){
           alert("Index Rebuilt!");
        },
        error: function(xhr,status, error){
            alert("Error: Status " + status+" Message: "+error);
        }
    });
}










