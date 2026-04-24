<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート base.jsp を読み込む --%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <c:param name="content">
        <section class="me-4 text-center">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 text-start">科目情報削除</h2>
            
            <div class="mt-5">
                <p class="fs-4">削除が完了しました</p>
            </div>
            
            <div class="mt-4">
                <a href="SubjectList.action" class="btn btn-secondary px-4">科目一覧</a>
            </div>
        </section>
    </c:param>
</c:import>