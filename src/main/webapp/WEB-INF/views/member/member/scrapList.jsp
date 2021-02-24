<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<h1 style="text-align: center; margin-top: 30px;">내가 스크랩한 글</h1>
<div style="margin: 70px;">
	<table style="width: 1000px;" class="table table-hover">
		<thead>
			<tr>
				<th scope="col">스크랩 번호</th>
				<th scope="col">게시글 번호</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="vo" items="${list }" varStatus="status">
			<tr>
				<th scope="row"><c:out value="${(pu.pageNum-1)*10 + status.count }"/></th>
				<td><a href="${pageContext.request.contextPath }/community/detailMy?pNum=${vo.PNum }">${vo.PNum }</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<c:if test="${pu.startPageNum>5 }">
		<a href="/lol/member/member/scrapList?pageNum=${pu.pageNum-1}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${pu.startPageNum}" end="${pu.endPageNum }">
			<c:choose>
				<c:when test="${i==pu.pageNum }">
					<a href="/lol/member/member/scrapList?pageNum=${i}"><span style='color:blue'>[${i }]</span></a>
				</c:when>
				<c:otherwise>
					<a href="/lol/member/member/scrapList?pageNum=${i}"><span style='color:gray'>[${i }]</span></a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pu.endPageNum<pu.totalPageCount }">
			<a href="/lol/member/member/scrapList?pageNum=${pu.pageNum+1}">[다음]</a>
		</c:if>
	</div>
</div>