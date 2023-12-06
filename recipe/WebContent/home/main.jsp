<%@page import="com.join.CustomInfo"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
%>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>메인화면</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="<%=cp %>/home/assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="<%=cp %>/home/css/styles.css" rel="stylesheet" />
        
        <script type="text/javascript">
        
        function sendKeywordToServlet(keyword) {
        	
        	var encodedKeyword = encodeURIComponent(keyword);
            // 서블릿 URL을 생성합니다.
            var servletURL = '<%=cp%>/rcp/list.do?' + 'keyword=' + encodedKeyword;

            // 생성된 URL로 이동합니다.
            window.location.href = servletURL;
        }


	        
	        function sendSearchKeywordToServlet() {
	            // 검색어를 가져오고, 원하는 방식으로 처리합니다.
	            var searchInput = document.getElementById("searchInput").value;
	         	// 쿠키에 검색 키워드 저장
	            document.cookie = "searchKeyword=" + searchInput;
	            var encodedSearchKeyword = encodeURIComponent(searchInput);
	            // 서블릿 URL을 생성합니다.
	            var servletURL = '<%=cp%>/rcp/search.do?' + 'keyword=' + encodedSearchKeyword;
	            window.location.href = servletURL;
	        }
	        
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

      
        </script>
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">My recipe</a>
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
	                    <c:choose>
							<c:when test="${empty sessionScope.customInfo.userId }">
		                        <button class="btn btn-outline-dark" type="button" onclick="redirectToLogin()"> <!-- 수정 -->
		                            <i class="bi bi-brightness-alt-high"></i>
		                            login
		                            <!--<span class="badge bg-dark text-white ms-1 rounded-pill">0</span>-->
		                        </button>
	                        </c:when>
	                        <c:otherwise>
		                        <button class="btn btn-outline-dark" type="button" onclick="logout()"> <!-- 수정 -->
		                            <i class="bi bi-brightness-alt-high"></i>
		                            logout
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
                    <p class="lead fw-normal text-white-50 mb-0">Explore a World of Delectable Recipes</p>
                    <br/>
                    <div class="input-group">
                        <div style="display: flex; align-items: center; margin: auto;">
                            <input type="search" id="searchInput" style="width: 70%;" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" size="80" />
							<button type="button" style="width: 30%;" class="btn btn-outline-primary" onclick="sendSearchKeywordToServlet()">Search</button>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            
                            <img class="card-img-top" src="<%=cp %>/home/img/disert.jpg" alt="..." onclick="sendKeywordToServlet('후식')" />
                            
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <br/>
                                    <br/>
                                    <h5 class="fw-bolder">후식</h5>
                                    <!-- Product price-->
                                    
                                </div>
                            </div>
                            <!-- Product actions
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#" onclick="sendKeywordToServlet('후식')">목록 보기</a></div>
                            </div>-->
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <!--<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">best</div>-->
                            <!-- Product image-->
                            <img class="card-img-top" src="<%=cp %>/home/img/bob.jpg" alt="..." onclick="sendKeywordToServlet('밥')"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <br/>
                                    <br/>
                                    <h5 class="fw-bolder">밥</h5>
                                    <!-- Product reviews
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    -->
                                    <!-- Product price-->
    
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <!--<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">best</div>-->
                            <!-- Product image-->
                            <img class="card-img-top" src="<%=cp %>/home/img/gook.jpg" alt="..." onclick="sendKeywordToServlet('국')"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <br/>
                                    <br/>
                                    <h5 class="fw-bolder">국&찌개</h5>
                                    <!-- Product price-->
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="<%=cp %>/home/img/banchan.jpg" alt="..." onclick="sendKeywordToServlet('반찬')"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <br/>
                                    <br/>
                                    <h5 class="fw-bolder">반찬</h5>
                                    
                                    <!-- Product reviews
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    -->
                                    <!-- Product price-->
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                </div>
            </div>
        </section>
        <br/>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
