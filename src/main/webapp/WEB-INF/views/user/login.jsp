<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"  pageEncoding="utf-8"%>
<html>
<head>
    <title>SAP Security Demo</title>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap-p2p.css" />" rel="stylesheet" type="text/css"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>


<body>

    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a href="../" class="navbar-brand">SAP Cloud</a>
          <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">AAA <span class="caret"></span></a>
              <ul class="dropdown-menu" aria-labelledby="themes">
                <li><a href="../default/">Default</a></li>
                <li class="divider"></li>
                <li><a href="../cerulean/">Cerulean</a></li>                
              </ul>
            </li>
            <li>
              <a href="../help/">Help</a>
            </li>
            <li>
              <a href="http://www.baidu.com">Blog</a>
            </li>
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="download">Download <span class="caret"></span></a>
              <ul class="dropdown-menu" aria-labelledby="download">
                <li class="divider"></li>
                <li><a href="./_variables.scss">menu1</a></li>
                <li><a href="./_bootswatch.scss">menu2</a></li>
              </ul>
            </li>
          </ul>

          <ul class="nav navbar-nav navbar-right">
            <li><a href="http://www.ufoqiao.com/" target="_blank">P2PB</a></li>
            <li><a href="https://www.baidu.com" target="_blank">P2PA</a></li>
          </ul>

        </div>
      </div>
    </div>

    <div class="bs-docs-section">
        <div class="row">
          <div class="col-lg-6">
            <div class="well bs-component">
              <form class="form-horizontal">
                <fieldset>
                  <legend>登录</legend>
                  <div class="form-group">
                    <label for="inputUserName" class="col-lg-2 control-label">用户名：</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputUserName" name="user.userName" placeholder="请输入用户名">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputPassword" class="col-lg-2 control-label">密码</label>
                    <div class="col-lg-10">
                      <input type="password" class="form-control" id="inputPassword" name="user.password" placeholder="Password">
                      <div class="checkbox">
                        <label>
                          <input type="checkbox"> 保留登录信息24小时
                        </label>
                      </div>
                    </div>
                  </div>
                </fieldset>
                <input type="submit" class="btn btn-primary btn-xs" value="登录" id="inputLogin"/>
              </form>
          </div>
        </div>
      </div>
	</div>

</body>

<script  src="<c:url value="/resources/jquery/jquery-2.1.1.js" />"></script>
<script  src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>
<script  src="<c:url value="/resources/json2.js" />"></script>
<script  src="<c:url value="/resources/supersite.js" />"></script>
<script>
jQuery(function(){
    jQuery("#inputLogin").on("click", function(event,args) {
        event.preventDefault();
        Utils.log($(this).text());
        login();
    });
    function login() {
        var inputUserName=jQuery("#inputUserName").val();
        var inputPassWord=jQuery("#inputPassword").val();
        if(!inputUserName){
            Utils.log('请输入用户名！');
            jQuery("#inputUsername").focus();
            return;
        }
        if(!inputPassWord){
            Utils.log('请输入密码！');
            jQuery("#inputPassword").focus();
            return;
        }

        $.ajax({
            url : "<c:url value="/user/login" />",
            data :{
                   "userName":inputUserName,
                   "password":inputPassWord
                  },
            type : 'GET',
            dataType : 'json',
            contentType : "application/json"
        }).done(function(res){
            var result = res;
            if (result === "success") {
                Utils.log("success");
            }
            if (result === "fail") {
                Utils.log('用户名或密码错误！');
                jQuery("#inputUsername").focus();
                jQuery("#password").val('');
            }
        }).fail(function(res) {
            Utils.log("登录失败，请检查用户名和密码！");
        })
        .always(function(res) {
            Utils.log( "complete" );
        });
    };

});

</script>
</html>
