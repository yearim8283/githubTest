<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>상세정보</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon"
	href="<%=cp%>/home/assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="<%=cp%>/home/css/styles.css" rel="stylesheet" />
<link href="<%=cp%>/home/css/comment.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<!--카테고리 안 리스트-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<style type="text/css">
.red-heart {
	color: red;
}
</style>

<script type="text/javascript">
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
</script>
<script type="text/javascript">
	    	
	    	function redirectToLogin() {
	            var loginURL = '<%=cp%>/join/login.do';
	            window.location.href = loginURL; // URL로 이동
	        }
	    	
	    	function logout() {
	            var loginURL = '<%=cp%>/join/logout.do';
				window.location.href = loginURL; // URL로 이동
			}
	    	
	    	
	    	
	    	function sendIt() {
	            var content = document.getElementById("comment").value;
	            var recipeId = $("#recipeInfo").data("recipe-id");
	            
	            $.ajax({
	                type: "POST",
	                url: "<%=cp%>/guest/save.do",
	                data: { 
	                    content: content,
	                    no: recipeId,
	                    id: ${sessionScope.customInfo.userId }, 
	                    name: ${sessionScope.customInfo.userName }
	                },
	                success: function(response) {
	                    console.log(response);
	                    // 댓글 등록 후 댓글 목록 다시 불러오기
	                    loadComments();
	                    $("#comment").val('');
	                },
	                error: function(error) {
	                    console.error(error);
	                }
	            });
	        }
	    	// 서버에서 댓글 목록을 불러와서 표시하는 함수
	        function loadComments() {
    $.ajax({
        type: "GET",
        url: "<%=cp%>/guest/list.do",
        data: {
            no: recipeId    
        },
        success: function(response) {
        	var userId = '${sessionScope.customInfo.userId}';
            var commentSection = $("#commentSection");
            var commentsHTML = "";
            console.log(response);
            
            response.forEach(function(comment) {
                let commentNum = comment.num;
                commentsHTML += '<div class="container mt-5">';
                commentsHTML += '<div class="row d-flex justify-content-center">';
                commentsHTML += '<div class="col-md-8">';
                commentsHTML += '<div class="commentCard p-3">';
                commentsHTML += '<div class="d-flex justify-content-between align-items-center">';
                commentsHTML += '<div class="user d-flex flex-row align-items-center">';
                commentsHTML += '<span><small class="font-weight-bold text-primary">' + comment.guestName + '</small>';
                commentsHTML += '&nbsp;';
                commentsHTML += '&nbsp;';
                commentsHTML += '&nbsp;';
                commentsHTML += '&nbsp;';
                commentsHTML += '<small class="font-weight-bold">' + comment.content + '</small></span>';
                commentsHTML += '</div>';
                commentsHTML += '<small>' + comment.created + '</small>';
                commentsHTML += '</div>';
                commentsHTML += '<div class="action d-flex justify-content-between mt-2 align-items-center">';
                commentsHTML += '<div class="reply px-4">';
             // 로그인한 사용자와 댓글 작성자가 같은 경우에만 삭제 링크 표시
                if (userId === comment.guestId) {
                    commentsHTML += '<small><a href="#" class="remove-link" style="text-decoration: none;" data-comment-num="' + commentNum + '">Remove</a></small> ';
                }else{
                	commentsHTML += '<small style="color: #808080;">Remove</small> ';
                }
                
                commentsHTML += '</div>';
                commentsHTML += '</div>';
                commentsHTML += '</div>';
                commentsHTML += '</div>';
                commentsHTML += '</div>';
                commentsHTML += '</div>';
            });

            commentSection.html(commentsHTML);

            $(".remove-link").on("click", function(e) {
                e.preventDefault();
                var commentNum = $(this).data("comment-num");
                removeComment(commentNum);
            });
        },
        
        error: function(error) {
            console.error("댓글 로딩 중 오류 발생: ", error);
        }
    });
}
	    	
	        function removeComment(commentNum) {
	            $.ajax({
	                type: "POST",
	                url: "<%=cp%>/guest/delete.do",
	                data: {
	                    num: commentNum
	                },
	                success: function(response) {
	                    console.log(response);
	                    loadComments(); // Reload comments after deletion
	                    $("#result").text("삭제되었습니다.");
	                },
	                error: function(error) {
	                    $("#result").text("삭제에 실패했습니다.");
	                    console.error(error);
	                }
	            });
	        }
	    	
	    	
</script>


<script type="text/javascript">
$(document).ready(function(){
    var recipeId = $("#recipeInfo").data("recipe-id");
    
    // 추천 버튼 클릭시(추천 추가 또는 추천 제거)
    $("#rec_update").click(function(){
        // 사용자 ID를 JavaScript 변수로 가져오기
        var userId = '${sessionScope.customInfo.userId}';
        
        // 좋아요 상태 확인
        $.ajax({
            url: "<%=cp%>/rec/CheckRecStatus.do",
            type: "POST",
            data: {
                no: recipeId,
                id: userId
            },
            success: function (status) {
                if (status === "liked") {
                    // 이미 좋아요를 누른 경우 - 좋아요 제거                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
                    $.ajax({
                        url: "<%=cp%>/rec/RemoveRec.do",
                        type: "POST",
                        data: {
                            no: recipeId,
                            id: userId
                        },
                        success: function () {
                            // 좋아요 상태 업데이트
                            $("#rec_update").html('<i class="fa fa-heart" style="font-size: 16px; color: white"></i>&nbsp;<span class="rec_count"></span>');
                            recCount();
                        },
                    });
                } else {
                    // 좋아요를 처음 누르는 경우 - 좋아요 추가
                    $.ajax({
                        url: "<%=cp%>/rec/AddRec.do",
                        type: "POST",
                        data: {
                            no: recipeId,
                            id: userId
                        },
                        success: function () {
                            // 좋아요 상태 업데이트
                            $("#rec_update").html('<i class="fa fa-heart" style="font-size: 16px; color: red"></i>&nbsp;<span class="rec_count"></span>');
                            recCount();
                        },
                    });
                }
            },
        });
    });
    
    // 게시글 추천수
    function recCount() {
        $.ajax({
            url: "<%=cp%>/rec/RecCount.do",
            type: "POST",
            data: {
                no: recipeId
            },
            success: function (count) {
                $(".rec_count").html(count);
            },
        });
    };
    recCount();
});


</script>

<script>
document.addEventListener('DOMContentLoaded', function() {
	var userId = '${sessionScope.customInfo.userId}';
	var recipeId = $("#recipeInfo").data("recipe-id");

	var requestData = {
	    id: userId,
	    no: recipeId
	};

	fetch('<%=cp%>/rec/CheckRecStatus2.do', {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/json'
	    },
	    body: JSON.stringify(requestData)
	})
	.then(response => response.json())
	.then(data => {
        updateLikeButton(data.likeStatus); // JSON 객체에서 속성을 읽음
        console.log('likeStatus:', data.likeStatus);
    })
	.catch(error => {
	    console.error('Error fetching like status:', error);
	});

	    
	// 서버로부터 받은 좋아요 상태 (liked 또는 not_liked)를 기반으로 아이콘 클래스를 변경
	function updateLikeButton(likeStatus) {
	    const likeButton = document.querySelector('.like-button');
	    const heartIcon = likeButton.querySelector('i.fa-heart');
	
	    if (likeStatus === 'liked') {
	        heartIcon.classList.add('red-heart'); // 빨간색 하트 클래스 추가
	    } else {
	        heartIcon.classList.remove('red-heart'); // 빨간색 하트 클래스 제거
	    }
	}
});

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
		<c:forEach var="item" items="${RecipeItem}">
			<div class="container px-4 px-lg-5 mt-5">
				<div
					class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
					<img src="${item.ATT_FILE_NO_MAIN}" class="img-fluid" alt="...">
					<h3>${item.RCP_NM}</h3>
					<div>
						<div class="w3-border w3-center w3-padding">
							<c:if test="${ sessionScope.customInfo.userId == null }">
					추천 기능은 <button type="button" id="newLogin">
									<b class="w3-text-blue" onclick="redirectToLogin()">로그인</b>
								</button> 후 사용 가능합니다.<br />
								<i class="fa fa-heart" style="font-size: 16px; color: red"></i>
								<span class="rec_count"></span>
							</c:if>
							<c:if test="${ sessionScope.customInfo.userId != null }">
								<button class="w3-button w3-black w3-round like-button"
									id="rec_update">
									<i class="fa fa-heart" style="font-size: 16px;"></i> &nbsp;<span
										class="rec_count"></span>
								</button>
							</c:if>
						</div>
					</div>
				</div>
				<br /> <br />
				<div class="card border-light mb-3" style="max-width: 100rem;">
					<div class="card-header">레시피 정보</div>
					<div class="card-body">
						<h5 class="card-title">[재료 정보]</h5>
						<p class="card-text">${item.RCP_PARTS_DTLS}</p>
						<br />
						<h5 class="card-title">[영양 정보]</h5>
						<p class="card-text">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">종류</th>
									<th scope="col">함량</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">1</th>
									<td>열량</td>
									<td>${item.INFO_ENG}kcal</td>
								</tr>
								<tr>
									<th scope="row">2</th>
									<td>탄수화물</td>
									<td>${item.INFO_CAR}g</td>
								</tr>
								<tr>
									<th scope="row">3</th>
									<td>단백질</td>
									<td>${item.INFO_PRO}g</td>
								</tr>
								<tr>
									<th scope="row">4</th>
									<td>지방</td>
									<td>${item.INFO_FAT}g</td>
								</tr>
								<tr>
									<th scope="row">5</th>
									<td>나트륨</td>
									<td>${item.INFO_NA}mg</td>
								</tr>
							</tbody>
						</table>
						</p>
					</div>
				</div>
				<div class="card border-light mb-3" style="max-width: 100rem;">
					<div class="card-header">조리 순서</div>
					<div class="card-body">
						<!-- <h5 class="card-title">Light card title</h5> -->
						<p class="card-text">
						<div class="row row-cols-1 row-cols-md-3 g-4">
							<c:if test="${not empty item.MANUAL01}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG01}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL01}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL02}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG02}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL02}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL03}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG03}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL03}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL04}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG04}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL04}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL05}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG05}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL05}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL06}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG06}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL06}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL07}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG07}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL07}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL08}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG08}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL08}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL09}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG09}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL09}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL10}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG10}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL10}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL11}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG11}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL11}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL12}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG12}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL12}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL13}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG13}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL13}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL14}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG14}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL14}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL15}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG15}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL15}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL16}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG16}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL16}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL17}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG17}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL17}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL18}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG18}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL18}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL19}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG19}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL19}</p>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty item.MANUAL20}">
								<div class="col">
									<div class="card h-100">
										<img src="${item.MANUAL_IMG20}" class="card-img-top" alt="...">
										<div class="card-body">
											<p class="card-text">${item.MANUAL20}</p>
										</div>
									</div>
								</div>
							</c:if>



						</div>
						</p>
					</div>
				</div>
				<div class="card border-light mb-3" style="max-width: 100rem;">
					<div class="card-header">저감 조리법 TIP</div>
					<div class="card-body">
						<p class="card-text">${item.RCP_NA_TIP}</p>
					</div>
				</div>
			</div>
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />

			<form class="row g-3 justify-content-center">
				<c:if test="${ sessionScope.customInfo.userId == null }">
					<div class="col-auto">
						<label for="inputPassword2" class="visually-hidden">Password</label>

						<input type="text" class="form-control" id="comment2"
							style="height: 150px; width: 1100px;"
							placeholder="로그인 후 댓글 작성이 가능합니다">
					</div>
					<div class="col-auto mt-auto">
						<button type="submit" class="btn btn-primary mb-3"
							disabled="disabled">등록</button>
					</div>
				</c:if>
				<c:if test="${ sessionScope.customInfo.userId != null }">
					<div class="col-auto">
						<input type="text" class="form-control" id="comment"
							style="height: 150px; width: 1100px;" placeholder="댓글을 작성하세요">
					</div>
					<div class="col-auto mt-auto">
						<button type="button" class="btn btn-primary mb-3"
							onclick="sendIt()">등록</button>
					</div>
				</c:if>
			</form>
			<div id="commentSection"></div>

			<!-- 댓글 -->
			<div class="container mt-5">
				<div class="row  d-flex justify-content-center">

					<div class="col-md-8">
						<c:forEach var="dto" items="${lists}">

							<div
								class="headings d-flex justify-content-between align-items-center mb-3">
								<h5>Comments(6)</h5>
							</div>

							<div class="commentCard p-3">
								<div class="d-flex justify-content-between align-items-center">
									<div class="user d-flex flex-row align-items-center">
										<span><small class="font-weight-bold text-primary"><c:out
													value="${dto.guestName}" /></small> <small
											class="font-weight-bold"><c:out
													value="${dto.content}" /></small></span>
									</div>
									<small>2 days ago</small>
								</div>
								<div
									class="action d-flex justify-content-between mt-2 align-items-center">
									<div class="reply px-4">
										<small>Remove</small> <span class="dots"></span> <small>Reply</small>
										<span class="dots"></span> <small>Translate</small>
									</div>
								</div>
							</div>

						</c:forEach>
					</div>
				</div>
			</div>
			<!-- 댓글 끝 -->
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<div id="recipeInfo" style="display: none;"
				data-recipe-id="${item.RCP_SEQ}"></div>

		</c:forEach>
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
	<script>
	var userId = '<%=session.getAttribute("customInfo.userId")%>';
	 var userName = '<%=session.getAttribute("customInfo.userName")%>';
	 var recipeId = $("#recipeInfo").data("recipe-id");
document.addEventListener('DOMContentLoaded', function() {
    

    // 서버에서 댓글 목록을 불러와서 표시하는 함수
    function loadComments() {
    $.ajax({
        type: "GET",
        url: "<%=cp%>/guest/list.do",
        data: {
            no: recipeId    
        },
        success: function(response) {
        	var userId = '${sessionScope.customInfo.userId}';
            var commentSection = $("#commentSection");
            var commentsHTML = "";
            
            response.forEach(function(comment) {
                let commentNum = comment.num;
                commentsHTML += '<div class="container mt-5">';
                commentsHTML += '<div class="row d-flex justify-content-center">';
                commentsHTML += '<div class="col-md-8">';
                commentsHTML += '<div class="commentCard p-3">';
                commentsHTML += '<div class="d-flex justify-content-between align-items-center">';
                commentsHTML += '<div class="user d-flex flex-row align-items-center">';
                commentsHTML += '<span><small class="font-weight-bold text-primary">' + comment.guestName + '</small>';
                commentsHTML += '&nbsp;';
                commentsHTML += '&nbsp;';
                commentsHTML += '&nbsp;';
                commentsHTML += '&nbsp;';
                commentsHTML += '<small class="font-weight-bold">' + comment.content + '</small></span>';
                commentsHTML += '</div>';
                commentsHTML += '<small>' + comment.created + '</small>';
                commentsHTML += '</div>';
                commentsHTML += '<div class="action d-flex justify-content-between mt-2 align-items-center">';
                commentsHTML += '<div class="reply px-4">';
             // 로그인한 사용자와 댓글 작성자가 같은 경우에만 삭제 링크 표시
                if (userId === comment.guestId) {
                    commentsHTML += '<small><a href="#" class="remove-link" style="text-decoration: none;" data-comment-num="' + commentNum + '">Remove</a></small> ';
                }else{
                	commentsHTML += '<small style="color: #808080;">Remove</small> ';
                }
                
                commentsHTML += '</div>';
                commentsHTML += '</div>';
                commentsHTML += '</div>';
                commentsHTML += '</div>';
                commentsHTML += '</div>';
                commentsHTML += '</div>';
            });

            commentSection.html(commentsHTML);

            $(".remove-link").on("click", function(e) {
                e.preventDefault();
                var commentNum = $(this).data("comment-num");
                removeComment(commentNum);
            });
        },
        
        error: function(error) {
            console.error("댓글 로딩 중 오류 발생: ", error);
        }
    });
}






    
    function removeComment(commentNum) {
        $.ajax({
            type: "POST",
            url: "<%=cp%>/guest/delete.do",
            data: {
                num: commentNum
            },
            success: function(response) {
                console.log(response);
                loadComments(); // Reload comments after deletion
                $("#result").text("삭제되었습니다.");
            },
            error: function(error) {
                $("#result").text("삭제에 실패했습니다.");
                console.error(error);
            }
        });
    }


    // 페이지 로드시 댓글 불러오기
    loadComments();

    // 댓글을 등록한 후에 댓글 목록을 다시 불러올 수 있도록 수정
    
});
</script>
</body>
</html>
