<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート base.jsp を読み込む --%>
<c:import url="/common/base.jsp">
    <%-- 【修正】jsp:param ではなく c:param を使用する --%>
    <c:param name="title" value="得点管理システム" />
    
    <%-- コンテンツ部分 --%>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
            
            <div class="my-2 text-end px-4">
                <a href="ClassCreate.action">新規登録</a>
            </div>

            <%-- 絞り込みフォーム --%>
            <form method="get" action="StudentList.action">
                
                    <%-- エラーメッセージ表示用 --%>
                    <div class="mt-2 text-danger">${errors.get("f1")}</div>
            </form>

            <%-- 一覧テーブル --%>
            <div class="mx-3">
                <table class="table table-hover">
                    <thead>
                   
                        <tr>
                            <th>クラス</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="classnum" items="${classes}">
                            <tr>
                            	
                                <td>${classnum}</td>
                                <td class="text-center">

                                <td>
                                    <a href="ClassDelete.action?class_num=${classnum}">削除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty classes}">
                    <p>クラス情報が存在しません。</p>
                </c:if>
            </div>
        </section>
    </c:param>
</c:import>