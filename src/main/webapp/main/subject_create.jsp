<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
            
            <form action="SubjectCreateExecute.action" method="post">
                <div class="mx-3">
                    
                    <%-- 科目コード --%>
                    <div class="mb-3">
                        <label class="form-label" for="subject-cd-input">科目コード</label>
                        <input class="form-control" type="text" id="subject-cd-input" name="cd" 
                               value="${cd}" <%-- 入力した値を保持させる --%>
                               placeholder="科目コードを入力してください" maxlength="3" required />
                        
                        <%-- ① エラーメッセージ表示エリア --%>
                        <c:if test="${errors.get('cd') != null}">
                            <div class="text-danger mt-1 small">
                                ${errors.get('cd')}
                            </div>
                        </c:if>
                    </div>

                    <%-- 科目名 --%>
                    <div class="mb-3">
                        <label class="form-label" for="subject-name-input">科目名</label>
                        <input class="form-control" type="text" id="subject-name-input" name="name" 
                               value="${name}" <%-- 入力した値を保持させる --%>
                               placeholder="科目名を入力してください" required />
                    </div>

                    <div class="mt-4">
                        <button class="btn btn-primary" type="submit" id="register-button">登録</button>
                        <a href="SubjectList.action" class="btn btn-outline-secondary ms-2">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>