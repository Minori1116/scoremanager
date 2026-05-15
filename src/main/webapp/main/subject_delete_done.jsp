<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート base.jsp を読み込む --%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <c:param name="content">
        <section class="me-4">
            <%-- 見出し部分は背景あり・左寄せ --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
            
            <div class="mx-3 mt-4">
                <%-- 写真のような緑色のアラート帯 --%>
                <div class="alert alert-success text-center py-2" role="alert" style="background-color: #a3cfbb; border: none; color: #0f5132;">
                    削除が完了しました
                </div>
                
                <%-- 下部のナビゲーションリンク --%>
                <div class="mt-5">
                    <a href="SubjectList.action" class="text-decoration-underline text-primary">科目一覧</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>