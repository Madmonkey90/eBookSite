<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>LFOM page</title>
    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/elibraryCommon.css" rel="stylesheet">
    
    <style type="text/css">
        
        div.footer{
            position: fixed;
            bottom: 0;
        }
        
    </style>
    
</head>
<body>
    <jsp:include page="common/toolbar.jsp"/>
    <div class="container contentBackgroundWithAliceBlue row" style="margin-left:auto;margin-right:auto;" >
        <h1>Live Free Or Malloc Team</h1>
        <p>The LFOM team is composed  of some of the most excellent and bodacious developers around these parts. 
            Below is a little about them
        </p>
        <div class="col-lg-6"><img class="" src="assets/teamPhoto.jpg"></div>
        <div class="col-lg-6 well">
            <img src="assets/lfom.png">
            <h2>Stefan Buszwatiuk</h2>
            <p>Developer. Pirate. Evil internet specialist. Problem solver. General social mediaholic. Certified communicator. Lifelong writer.</p>
            <h2>James Cassidy</h2>
            <p>Internet trailblazer. Web advocate. Total organizer. Social media fanatic. Award-winning alcohol nerd.  Pop culture guru. Food fanatic. </p>
            <h2>Brian Costello</h2>
            <p>Energetic and Eager developer. Thinker. Professional bacon enthusiast. Zombie buff. Evil web practitioner. Reader. Proud food advocate. Creator. </p>
            <h2>Andrew</h2>
            <p>Fan of Internet Explorer 5.0 and Windows ME </p>
            <!--all profiles cept Andrew's were mostly randomly generated from some twitter bio generator-->
            <h2>If you'd like to assist the LFOM team,&NewLine; please consider donating</h2>
        </div>            
        <form class="pull-right" action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
            <input type="hidden" name="cmd" value="_donations">
            <input type="hidden" name="business" value="livefreeatlivefree@livefree.com">
            <input type="hidden" name="lc" value="GB">
            <input type="hidden" name="item_name" value="Life Free Team">
            <input type="hidden" name="no_note" value="0">
            <input type="hidden" name="currency_code" value="GBP">
            <input type="hidden" name="bn" value="PP-DonationsBF:btn_donateCC_LG.gif:NonHostedGuest">
            <input type="image" src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" border="0" name="submit" alt="PayPal ? The safer, easier way to pay online.">
            <img alt="" border="0" src="https://www.paypalobjects.com/en_GB/i/scr/pixel.gif" width="1" height="1">
        </form>  
    </div>
</body>
</html>
