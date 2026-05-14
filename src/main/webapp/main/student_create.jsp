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
                        <label class="form-label" for="student-ent-year-select">入学年度</label>
                        <select class="form-select" id="student-ent-year-select" name="ent_year">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" ${year == ent_year ? 'selected' : ''}>${year}</option>
                            </c:forEach>
                        </select>
                        <%-- ① 入学年度未選択エラーの表示用 --%>
                        <c:if test="${not empty errors.get('ent_year')}">
                            <div class="text-warning small mt-1">${errors.get("ent_year")}</div>
                        </c:if>
                    </div>

                    <%-- 学生番号（未入力時はバルーン、重複時は文字表示） --%>
                    <div class="mb-3">
                        <label class="form-label" for="student-no-input">学生番号</label>
                        <input type="text" name="no" id="student-no-input" class="form-control" 
                               value="${no}" placeholder="学生番号を入力してください" maxlength="10" required>
                        <%-- ② 学生番号重複エラーの表示用 --%>
                        <c:if test="${not empty errors.get('no')}">
                            <div class="text-warning small mt-1">${errors.get("no")}</div>
                        </c:if>
                    </div>

                    <%-- 氏名（未入力時はバルーン） --%>
                    <div class="mb-3">
                        <label class="form-label" for="student-name-input">氏名</label>
                        <input type="text" name="name" id="student-name-input" class="form-control" 
                               value="${name}" placeholder="氏名を入力してください" maxlength="30" required>
                    </div>

                    <%-- クラス --%>
                    <div class="mb-3">
                        <label class="form-label" for="student-class-select">クラス</label>
                        <select class="form-select" id="student-class-select" name="class_num" required>
                            <option value="">選択してください</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" ${num == class_num ? 'selected' : ''}>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-secondary px-4" name="end">登録して終了</button>
                    <div class="mt-3">
                        <a href="StudentList.action">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>