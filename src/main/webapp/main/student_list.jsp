<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート base.jsp を読み込む --%>
<c:import url="/common/base.jsp">
    <%-- 【修正】jsp:param ではなく c:param を使用する --%>
    <c:param name="title" value="得点管理システム" />
    
    <%-- コンテンツ部分 --%>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>
            
            <div class="my-2 text-end px-4">
                <a href="StudentCreate.action">新規登録</a>
            </div>

            <%-- 絞り込みフォーム --%>
            <form method="get" action="StudentList.action">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <%-- 入学年度選択 --%>
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- クラス選択 --%>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 在学中チェック --%>
                    <div class="col-2 form-check text-center">
                        <label class="form-check-label" for="student-f3-check">在学中
                            <input class="form-check-input" type="checkbox" id="student-f3-check" name="f3" value="t"
                            <c:if test="${!empty f3}">checked</c:if> />
                        </label>
                    </div>

                    <%-- 絞り込みボタン --%>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button">絞込み</button>
                    </div>
                    
                    <%-- エラーメッセージ表示用 --%>
                    <div class="mt-2 text-danger">${errors.get("f1")}</div>
                </div>
            </form>

            <%-- 一覧テーブル --%>
            <div class="mx-3">
                <table class="table table-hover">
                    <thead>
                   
                        <tr>
                            <th>学籍番号</th>
                            <th>氏名</th>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th class="text-center">在学中</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.no}</td>
                                <td>${student.name}</td>
                                <td>${student.entYear}</td>
                                <td>${student.classNum}</td>
                                <td class="text-center">
                                    <c:choose>
                                        <%-- 【修正】Boolean型の getter (isAttend) は .attend で参照する --%>
                                        <c:when test="${student.attend}">○</c:when>
                                        <c:otherwise>×</c:otherwise>
                                    </c:choose>
                                </td>
                                <td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty students}">
                    <p>学生情報が存在しません。</p>
                </c:if>
            </div>
        </section>
    </c:param>
</c:import>