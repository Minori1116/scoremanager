<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
            
            <form action="StudentCreateExecute.action" method="post">
                <div class="mx-3">
                    <%-- 入学年度 --%>
                    <div class="mb-3">
                        <label class="form-label" for="student-ent_year-input">入学年度</label>
                        <select class="form-select" id="student-ent_year-input" name="ent_year" required>
                            <option value="">選択してください</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 学籍番号 --%>
                    <div class="mb-3">
                        <label class="form-label" for="student-no-input">学籍番号</label>
                        <input class="form-control" type="text" id="student-no-input" name="no" 
                               placeholder="学籍番号を入力してください" maxlength="10" required />
                    </div>

                    <%-- 氏名 --%>
                    <div class="mb-3">
                        <label class="form-label" for="student-name-input">氏名</label>
                        <input class="form-control" type="text" id="student-name-input" name="name" 
                               placeholder="氏名を入力してください" required />
                    </div>

                    <%-- クラス --%>
                    <div class="mb-3">
                        <label class="form-label" for="student-class_num-select">クラス</label>
                        <select class="form-select" id="student-class_num-select" name="class_num" required>
                            <option value="">選択してください</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}">${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mt-4">
                        <button class="btn btn-primary" type="submit" id="register-button">登録</button>
                        <a href="StudentList.action" class="btn btn-outline-secondary ms-2">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>