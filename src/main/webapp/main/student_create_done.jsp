<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <c:param name="content">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
            <div class="mt-5">
            <section class="me-4 text-center">
                <p>登録が完了しました</p></section>
                <div class="mt-4">
                    <a href="StudentCreate.action">戻る</a>　　　　　<a href="StudentList.action">学生一覧</a>
                </div>
            </div>
    </c:param>
</c:import>