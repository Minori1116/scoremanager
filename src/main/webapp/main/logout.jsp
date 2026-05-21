<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 text-start">ログアウト</h2>
            
            <div class="container">
                <div class="mt-4 py-2 bg-success bg-opacity-25 text-dark text-center rounded">
                    ログアウトしました
                </div>
                
                <div class="mt-4 text-start">
                    <a href="Login.action" class="text-primary text-decoration-underline">ログイン</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>