<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
            
            <form action="StudentUpdateExecute.action" method="post">
                <div class="mx-3">
                    <div class="mb-3">
                        <label class="form-label">学籍番号</label>
                        <%-- 学籍番号は主キーなので編集不可（readonly） --%>
                        <input type="text" name="no" class="form-control-plaintext fw-bold" value="${student.no}" readonly>
                    </div>

                    <div class="mb-3">
                        <label class="form-label" for="student-name-input">氏名</label>
                        <input type="text" name="name" id="student-name-input" class="form-control" value="${student.name}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label" for="student-class-select">クラス</label>
                        <select class="form-select" id="student-class-select" name="class_num">
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-4 form-check">
                        <input type="checkbox" name="is_attend" id="student-attend-check" class="form-check-input" value="true"
                            <c:if test="${student.attend}">checked</c:if>>
                        <label class="form-check-label" for="student-attend-check">在学中</label>
                    </div>

                    <button type="submit" class="btn btn-primary px-4">変更</button>
                    <div class="mt-3">
                        <a href="StudentList.action">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>