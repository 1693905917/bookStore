<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2022/1/28
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <%--<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">
            ${requestScope.page.pageNo-1}
    </a>
【${requestScope.page.pageNo}】
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">
        ${requestScope.page.pageNo+1}
    </a>--%>

    <c:choose>
        <%--如果总页码小于等于 5 的情况，页码的范围是：1-总页码--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
            <%--<c:forEach begin="1" end="" var="i">
                <c:if test="${i==requestScope.page.pageNo}">
                    【<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo}">${requestScope.page.pageNo}</a>】
                </c:if>
                <c:if test="${i!=requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>--%>
        </c:when>
        <%--情况 2：总页码大于 5 的情况。--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <%--小情况 1：当前页码为前面 3 个：1，2，3 的情况，页码范围是：1-5.--%>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                    <%--	<c:forEach begin="1" end="5" var="i">
                            <c:if test="${i==requestScope.page.pageNo}">
                                【<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo}">${requestScope.page.pageNo}</a>】
                            </c:if>
                            <c:if test="${i!=requestScope.page.pageNo}">
                                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                            </c:if>
                        </c:forEach>--%>
                </c:when>
                <%--小情况 2：当前页码为最后 3 个，8，9，10，页码范围是：总页码减 4 - 总页--%>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                    <%--	<c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                            <c:if test="${i==requestScope.page.pageNo}">
                                【<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo}">${requestScope.page.pageNo}</a>】
                            </c:if>
                            <c:if test="${i!=requestScope.page.pageNo}">
                                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                            </c:if>
                        </c:forEach>--%>
                </c:when>
                <%--小情况 3：4，5，6，7，页码范围是：当前页码减 2 - 当前页码加 2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
                    <%--<c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo}">${requestScope.page.pageNo}</a>】
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>--%>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo}">${requestScope.page.pageNo}</a>】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第
    <input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                var pageNo=$("#pn_input").val();
                var pageTotal=${requestScope.page.pageTotal};
                if(pageNo<=0){
                    alert("非法输入");
                    return false;
                }else if(pageNo>pageTotal){
                    alert("超越页码范围");
                    return false;
                }
                location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
            });
        })
    </script>
</div>
