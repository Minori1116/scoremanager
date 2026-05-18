<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート base.jsp を読み込む --%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <%-- コンテンツ部分 --%>
    <c:param name="content">
        
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
            <div class="alert alert-success text-center py-2" role="alert" style="background-color: #a3cfbb; border: none; color: #0f5132;">
                <p>登録が完了しました</p>
            </div>
                <div class="mt-4">
            
                <a href="TestRegist.action">戻る</a>　　　　　<a href="TestList.action">成績参照</a>
                
                
            </div>
       
    </c:param>
</c:import>

<%--18行目を修正(参照に飛べるようにした)--%>