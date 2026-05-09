<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート base.jsp を読み込む --%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <%-- コンテンツ部分 --%>
    <c:param name="content">
        <section class="me-4 text-center">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">登録完了</h2>
            <div class="mt-5">
                <p>得点の登録が完了しました。</p>
                <div class="mt-4">
            
                <a href="TestRegist.action" class="btn btn-primary px-4">戻る</a>
            
                <a href="TestRegistExecute.action" class="btn btn-primary px-4">成績参照</a>
                
                </div>
            </div>
        </section>
    </c:param>
</c:import>

<%--修正するかも--%>