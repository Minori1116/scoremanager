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
            

            <%-- 絞り込みフォーム --%>
            <form method="get" action="TestListSubjectExecute.action">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter1">
                    <%-- 入学年度選択 --%>
                    <div class="col-2">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- クラス選択 --%>
                    <div class="col-2">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 在学中チェック --%>
                    <div class="col-2">
                        <label class="form-label" for="subject-f4-select">科目</label>
                        <select class="form-select" id="subject-f4-select" name="f4">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.name}">${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>


                    <%-- 絞り込みボタン --%>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>
                    
                    <%-- エラーメッセージ表示用 --%>
                    <div class="mt-2 text-danger">${errors.get("f1")}</div>
                </div>
            </form>
            
            
            <form method="get" action="TestListStudentExecute.action">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter2">
                    <%-- 入学年度選択 --%>
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">学生番号</label>
                        <input class="form-control" type="text" id="student-id-input" name="student_no" 
                               placeholder="学生番号を入力してください" required />
                   
                    </div>

                    <%-- 絞り込みボタン --%>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>
                    
                    <%-- エラーメッセージ表示用 --%>
                    <div class="mt-2 text-danger">${errors.get("f1")}</div>
                </div>
            </form>
            
            <div class="col-4">
               <label class="form-label" for="student-f1-select">
               	<p class="text-info">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
               </label>

        </section>
    </c:param>
</c:import>