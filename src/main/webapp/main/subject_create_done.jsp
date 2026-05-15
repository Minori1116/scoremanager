<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
            
            <div class="mx-3 mt-4">
                <div class="alert alert-success text-center py-2" role="alert" style="background-color: #a3cfbb; border: none; color: #0f5132;">
                    登録が完了しました
                </div>
                <div class="mt-5 d-flex gap-4">
                    <a href="SubjectCreate.action" class="text-decoration-underline text-primary">戻る</a>
                    <a href="SubjectList.action" class="text-decoration-underline text-primary">科目一覧</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>