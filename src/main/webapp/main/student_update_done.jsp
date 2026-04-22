<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート base.jsp を読み込む --%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <c:param name="content">
        <section class="me-4 text-center">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 text-start">学生情報変更</h2>
            
            <div class="mt-5">
                <p class="fs-4">変更に成功しました</p>
            </div>
            
            <div class="mt-4">
                <a href="StudentList.action" class="btn btn-secondary px-4">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>