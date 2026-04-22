<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="container-fluid d-flex justify-content-between align-items-center">
    <%-- 左側：タイトル --%>
    <a href="Menu.action" class="text-decoration-none text-dark">
        <h1 class="h3 mb-0">得点管理システム</h1>
    </a>

    <%-- 右側：ユーザー情報 --%>
    <div class="d-flex align-items-center">
        <%-- セッションのuserオブジェクトから名前を取得 --%>
        <c:choose>
            <c:when test="${not empty user}">
                <span class="me-3">${user.name}様</span>
            </c:when>
            <c:otherwise>
                <%-- 万が一セッションが切れていた場合の表示 --%>
                <span class="me-3">ゲスト様</span>
            </c:otherwise>
        </c:choose>
        
        <a href="Logout.action" class="btn btn-outline-primary btn-sm">ログアウト</a>
    </div>
</div>