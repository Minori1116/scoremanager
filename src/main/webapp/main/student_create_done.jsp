<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <c:param name="content">
        <section class="me-4 text-center">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">登録完了</h2>
            <div class="mt-5">
                <p>学生情報の登録が完了しました。</p>
                <div class="mt-4">
                    <a href="StudentList.action" class="btn btn-primary">学生一覧へ戻る</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>