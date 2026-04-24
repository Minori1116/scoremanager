<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
            
            <form action="SubjectUpdateExecute.action" method="post">
                <div class="mx-3">
                    <div class="mb-3">
                        <label class="form-label">科目番号</label>
                        <%-- 学籍番号は主キーなので編集不可（readonly） --%>
                        <input type="text" name="subject_no" class="form-control-plaintext fw-bold" value="${subject.cd}" readonly>
                    </div>

                    <div class="mb-3">
                        <label class="form-label" for="subject-name-input">氏名</label>
                        <input type="text" name="subject_name" id="subject-name-input" class="form-control" value="${subject.name}" required>
                    </div>

                    <div class="mb-4 form-check">
                        <input type="checkbox" name="is_attend" id="subject-attend-check" class="form-check-input" value="true"
                            <c:if test="${subject.attend}">checked</c:if>>
                        <label class="form-check-label" for="subject-attend-check">在学中</label>
                    </div>

                    <button type="submit" class="btn btn-primary px-4">変更</button>
                    <div class="mt-3">
                        <a href="SubjectList.action">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>