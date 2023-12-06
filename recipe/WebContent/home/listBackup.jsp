<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	String category = (String) request.getAttribute("category");
	System.out.println("list.jsp의 Category: " + category);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<title>카테고리별 리스트</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon"
	href="<%=cp%>/home/assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="<%=cp%>/home/css/styles.css" rel="stylesheet" />

<script type="text/javascript">
        
        	var category = "<%=category%>";
	        
	        function redirectToLogin() {
	        	document.cookie = "redirectURL=" + encodeURIComponent(window.location.href);
	        	
	            var loginURL = '<%=cp%>/join/login.do';
	            window.location.href = loginURL; // URL로 이동
	        }
	        
	        function logout() {
	            var logoutURL = '<%=cp%>/join/logout.do';

	            // AJAX를 사용하여 로그아웃 요청을 서버에 보냄
	            $.ajax({
	                type: 'POST',
	                url: logoutURL,
	                success: function(data) {
	                    // 로그아웃 처리 성공 시, 클라이언트 측 처리 로직 추가
	                    console.log('로그아웃 성공');
	                },
	                error: function(xhr, status, error) {
	                    // 로그아웃 처리 실패 시, 클라이언트 측 처리 로직 추가
	                    console.error('로그아웃 실패');
	                },
	                complete: function() {
	                    // 로그아웃 처리 완료 시, 클라이언트 측 처리 로직 추가

	                    // 예: 현재 페이지 리로딩 없이 유지
	                    window.location.replace(window.location.href);
	                }
	            });
	        }
        
	        function sendSearchKeywordToServlet() {
	            // 검색어를 가져오고, 원하는 방식으로 처리합니다.
	            var searchInput = document.getElementById("searchInput").value;
	         	// 쿠키에 검색 키워드 저장
	            document.cookie = "searchKeyword=" + searchInput;
	            var encodedSearchKeyword = encodeURIComponent(searchInput);
	            // 서블릿 URL을 생성합니다.
	            var servletURL = '<%=cp%>/rcp/search.do?' + 'keyword='
				+ encodedSearchKeyword + '&category=' + category;
		window.location.href = servletURL;
	}
</script>
<script type="text/javascript">
	window.onload = function() {
		// 쿠키에서 검색 키워드 불러오기
		var searchKeyword = getCookie("searchKeyword");

		// 검색 입력 필드에 검색 키워드 설정
		if (searchKeyword) {
			document.getElementById("searchInput").value = searchKeyword;
		}
	};

	function getCookie(name) {
		var value = "; " + document.cookie;
		var parts = value.split("; " + name + "=");
		if (parts.length == 2)
			return parts.pop().split(";").shift();
	}
</script>
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="<%=cp%>/rcp/main.do">My recipe</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#!">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">best</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">My
							page</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#!">내정보수정</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="#!">나의 레시피</a></li>
							<li><a class="dropdown-item" href="#!">저장한 레시피</a></li>
						</ul></li>
				</ul>
				<form class="d-flex">
					<c:choose>
						<c:when test="${empty sessionScope.customInfo.userId }">
							<button class="btn btn-outline-dark" type="button"
								onclick="redirectToLogin()">
								<!-- 수정 -->
								<i class="bi bi-brightness-alt-high"></i> login
								<!--<span class="badge bg-dark text-white ms-1 rounded-pill">0</span>-->
							</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-outline-dark" type="button"
								onclick="logout()">
								<!-- 수정 -->
								<i class="bi bi-brightness-alt-high"></i> logout
								<!--<span class="badge bg-dark text-white ms-1 rounded-pill">0</span>-->
							</button>
						</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</nav>
	<!-- Header-->
	<header class="bg-dark py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">Recipe in style</h1>
				<p class="lead fw-normal text-white-50 mb-0">Explore a World of
					Delectable Recipes</p>
				<br />
				<p class="lead fw-normal text-white-50 mb-0">
				<div class="input-group">
					<div style="display: flex; align-items: center; margin: auto;">
						<input type="search" id="searchInput" style="width: 70%;"
							class="form-control rounded" placeholder="Search"
							aria-label="Search" aria-describedby="search-addon" size="80" />
						<button type="button" style="width: 30%;"
							class="btn btn-outline-primary"
							onclick="sendSearchKeywordToServlet()">Search</button>
					</div>
				</div>
				</p>
			</div>
		</div>
	</header>
	<!-- Section-->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div>
				<!--브레드크럼-->
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">카테고리</a></li>
						<li class="breadcrumb-item active" aria-current="page">${category}</li>
					</ol>
				</nav>
				<p>총 ${totalCount}개의 데이터가 검색되었습니다.</p>
			</div>
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="item" items="${dataList}">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<div>
								<c:url var="encodedUrl" value="${cp}/rcp/details.do">
									<c:param name="rcpName" value="${item.RCP_NM}" />
								</c:url>
								<a href="${encodedUrl}"> <img
									class="card-img-top" src="${item.ATT_FILE_NO_MAIN}" alt="..." />
								</a> 
							</div>
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<br/>
									<h5 class="fw-bolder">${item.RCP_NM}</h5>
								</div>
							</div>
							
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2023</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>
