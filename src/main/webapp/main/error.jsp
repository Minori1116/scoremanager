<%-- エラーページJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<c:import url="/common/base.jsp">
    <c:param name="title">
        エラー - 得点管理システム
    </c:param>
 
    <c:param name="content">
        <section class="me-4">
            <%-- 画像の①の部分：シンプルにテキストを表示 --%>
            <div class="mt-4">
                <p>エラーが発生しました</p>
            </div>
        </section>
    </c:param>
</c:import>
 