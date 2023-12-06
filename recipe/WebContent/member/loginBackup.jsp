<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com-->
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>로그인/회원가입</title>

        <!-- CSS -->
        <link rel="stylesheet" href="<%=cp %>/home/css/loginStyle.css">
                
        <!-- Boxicons CSS -->
        <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
        
        <!--Navbar-->
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="<%=cp %>/home/assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="<%=cp %>/home/css/styles.css" rel="stylesheet" />
        <!--Navbar-->
        
        <script type="text/javascript">

	function sendIt(){
		
		var f = document.myForm;
		
		str = f.userId.value;
		str = str.trim();
		if(!str){
			alert("아이디를 입력하세요.");
			f.userId.focus();
			return;
		}
		f.userId.value = str;
		
		
		
		str = f.userName.value;
		str = str.trim();
		if(!str){
			alert("이름을 입력하세요.");
			f.userName.focus();
			return;
		}
		
		
		str = f.userTel.value;
		str = str.trim();
		if(!str){
			alert("전화번호를 입력하세요.");
			f.userTel.focus();
			return;
		}
		f.userTel.value = str;
		
		
		
		
		
		str = f.userPwd.value;
		str = str.trim();
		if(!str){
			alert("패스워드를 입력하세요.");
			f.userPwd.focus();
			return;
		}
		
		str = f.userPwd2.value;
		str = str.trim();
		if(!str){
			alert("패스워드 확인을 입력하세요.");
			f.userPwd2.focus();
			return;
		}
		
		var password = f.userPwd.value;
        var password2 = f.userPwd2.value;
		
		if (password !== password2) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            f.userPwd.focus();
            return;
        }
		
		f.userPwd.value = str;
		
		
		
		
		
		
		
		
		f.action = "<%=cp%>/join/created_ok.do";
		f.submit();
		
		
		
	}
	
	function login() {
		
		
		
		f = document.myLoginForm;
		
		
		if(!f.userId.value){
			alert("아이디를 입력하세요.");
			f.userId.focus();
			return;
		}
		
		if(!f.userPwd.value){
			alert("패스워드를 입력하세요.");
			f.userPwd.focus();
			return;
		}
		
		f.action = "<%=cp%>/join/login_ok.do";
		f.submit();
	}
	</script>
    </head>
    
    <body>
        <!-- Navbar -->
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container px-4 px-lg-5">
            <a class="navbar-brand" href="<%=cp%>/rcp/main.do">My recipe</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!">best</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">My page</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#!">내정보수정</a></li>
                            <li><hr class="dropdown-divider" /></li>
                            <li><a class="dropdown-item" href="#!">나의 레시피</a></li>
                            <li><a class="dropdown-item" href="#!">저장한 레시피</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex">
                    <button class="btn btn-outline-dark" type="submit">
                        <i class="bi bi-brightness-alt-high"></i>
                        login
                        <!--<span class="badge bg-dark text-white ms-1 rounded-pill">0</span>-->
                    </button>
                </form>
            </div>
        </div>
    </nav>      
        <section class="container2 forms">
            <div class="form login">
                <div class="form-content">
                    <header>Login</header>
                    <form action="#" method="post" name="myLoginForm">
                        <div class="field input-field">
                            <input type="id" placeholder="Id" name="userId" class="input">
                        </div>

                        <div class="field input-field">
                            <input type="password" placeholder="Password" name="userPwd" class="password">
                            <i class='bx bx-hide eye-icon'></i>
                        </div>

                        <div class="form-link">
                            <a href="#" class="forgot-pass">Forgot password?</a>
                        </div>
                        
                        

                        <div class="field button-field">
                            <button onclick="login();">Login</button>
                        </div>
                        
                        <div class="form-link">
                            <h7><b style="color: red;">${message}</b></h7>

                        </div>
                    </form>

                    <div class="form-link">
                        <span>Don't have an account? <a href="#" class="link signup-link">Signup</a></span>
                    </div>
                </div>

                <!-- <div class="line"></div>

                <div class="media-options">
                    <a href="#" class="field facebook">
                        <i class='bx bxl-facebook facebook-icon'></i>
                        <span>Login with Facebook</span>
                    </a>
                </div>

                <div class="media-options">
                    <a href="#" class="field google">
                        <img src="images/google.png" alt="" class="google-img">
                        <span>Login with Google</span>
                    </a>
                </div> -->

            </div>

            <!-- Signup Form -->

            <div class="form signup">
                <div class="form-content">
                    <header>Signup</header>
                    <form action="#" method="post" name="myForm">
                        <div class="field input-field">
                            <input type="id" placeholder="Id" name="userId" class="input">
                        </div>

                        <div class="field input-field">
                            <input type="name" placeholder="Name" name="userName" class="input">
                        </div>

                        <div class="field input-field">
                            <input type="phone" placeholder="Phone number" name="userTel" class="input">
                        </div>

                        <div class="field input-field">
                            <input type="password" placeholder="Create password" class="password" name="userPwd">
                        </div>

                        <div class="field input-field">
                            <input type="password" placeholder="Confirm password" class="password" name="userPwd2">
                            <i class='bx bx-hide eye-icon'></i>
                        </div>

                        <div class="field button-field">
                            <button onclick="sendIt();">Signup</button>
                        </div>
                    </form>

                    <div class="form-link">
                        <span>Already have an account? <a href="#" class="link login-link">Login</a></span>
                    </div>
                </div>

                <!-- <div class="line"></div> -->

            </div>
        </section>

        <!-- JavaScript -->
        <script src="<%=cp %>/member/js/script.js"></script>
    </body>
</html>