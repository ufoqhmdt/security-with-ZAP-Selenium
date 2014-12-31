<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"  pageEncoding="utf-8"%>
<html>
<head>
	<title>P2P cloud demo</title>
	<link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/resources/bootstrap/css/bootstrap-p2p.css" />" rel="stylesheet" type="text/css"/>
	<!--
		Used for including CSRF token in JSON requests
		Also see bottom of this file for adding CSRF token to JQuery AJAX requests
	-->
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>


<body>


	<div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a href="../" class="navbar-brand">P2P Cloud</a>
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
              <a href="http://news.bootswatch.com">Blog</a>
            </li>
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="download">Download <span class="caret"></span></a>
              <ul class="dropdown-menu" aria-labelledby="download">
                <li><a href="./bootstrap.css">bootstrap.css</a></li>
                <li class="divider"></li>
                <li><a href="./variables.less">variables.less</a></li>
                <li class="divider"></li>
                <li><a href="./_variables.scss">BBB</a></li>
                <li><a href="./_bootswatch.scss">AAA</a></li>
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
          <div class="col-lg-12">
            <div class="page-header">
              <h1 id="forms">用户注册</h1>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-lg-6">
            <div class="well bs-component">
            <form:form id="userRegisterForm" method="post" modelAttribute="formBean" cssClass="form-horizontal">
                <fieldset>
                  <legend>填写账户信息</legend>
                  <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">电子邮箱</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputEmail" placeholder="电子邮箱">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputUsername" class="col-lg-2 control-label">用户名</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" id="inputUsername" placeholder="用户名">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputPassword" class="col-lg-2 control-label">登录密码</label>
                    <div class="col-lg-10">
                      <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                      <div class="checkbox">
                        <label>
                          <input type="checkbox"> Checkbox
                        </label>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputPasswordRepeat" class="col-lg-2 control-label">重复密码</label>
                    <div class="col-lg-10">
                      <input type="password" class="form-control" id="inputPasswordRepeat" placeholder="Password">
                      <div class="checkbox">
                        <label>
                          <input type="checkbox"> Checkbox
                        </label>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="textArea" class="col-lg-2 control-label">Textarea</label>
                    <div class="col-lg-10">
                      <textarea class="form-control" rows="3" id="textArea"></textarea>
                      <span class="help-block">A longer block of help text that breaks onto a new line and may extend beyond one line.</span>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-2 control-label">Radios</label>
                    <div class="col-lg-10">
                      <div class="radio">
                        <label>
                          <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked="">
                          Option one is this
                        </label>
                      </div>
                      <div class="radio">
                        <label>
                          <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                          Option two can be something else
                        </label>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="select" class="col-lg-2 control-label">Selects</label>
                    <div class="col-lg-10">
                      <select class="form-control" id="select">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                      </select>
                      <br>
                      <select multiple="" class="form-control">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <button class="btn btn-default">Cancel</button>
                      <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                  </div>
                </fieldset>
              </form:form>
            <div id="source-button" class="btn btn-primary btn-xs" style="display: block;">&lt; &gt;</div></div>
          </div>
          <div class="col-lg-4 col-lg-offset-1">

              <form class="bs-component">
                <div class="form-group">
                  <label class="control-label" for="focusedInput">Focused input</label>
                  <input class="form-control" id="focusedInput" type="text" value="This is focused...">
                </div>

                <div class="form-group">
                  <label class="control-label" for="disabledInput">Disabled input</label>
                  <input class="form-control" id="disabledInput" type="text" placeholder="Disabled input here..." disabled="">
                </div>

                <div class="form-group has-warning">
                  <label class="control-label" for="inputWarning">Input warning</label>
                  <input type="text" class="form-control" id="inputWarning">
                </div>

                <div class="form-group has-error">
                  <label class="control-label" for="inputError">Input error</label>
                  <input type="text" class="form-control" id="inputError">
                </div>

                <div class="form-group has-success">
                  <label class="control-label" for="inputSuccess">Input success</label>
                  <input type="text" class="form-control" id="inputSuccess">
                </div>

                <div class="form-group">
                  <label class="control-label" for="inputLarge">Large input</label>
                  <input class="form-control input-lg" type="text" id="inputLarge">
                </div>

                <div class="form-group">
                  <label class="control-label" for="inputDefault">Default input</label>
                  <input type="text" class="form-control" id="inputDefault">
                </div>

                <div class="form-group">
                  <label class="control-label" for="inputSmall">Small input</label>
                  <input class="form-control input-sm" type="text" id="inputSmall">
                </div>

                <div class="form-group">
                  <label class="control-label">Input addons</label>
                  <div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="text" class="form-control">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Button</button>
                    </span>
                  </div>
                </div>
              </form>

          </div>
        </div>
      </div>


</body>

<script  src="<c:url value="/resources/jquery/jquery-2.1.1.js" />"></script>
<script  src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>
<script  src="<c:url value="/resources/json2.js" />"></script>
<script>
jQuery(function(){
		$("#userRegisterForm").submit(function() {  
			$.post($(this).attr("action"), $(this).serialize(), function(html) {
				$("#formsContent").replaceWith(html);
				$('html, body').animate({ scrollTop: $("#message").offset().top }, 500);
			});
			return false;  
		});			
});
</script>
</html>
