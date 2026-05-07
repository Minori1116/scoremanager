<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
            
            <form action="ClassCreateExecute.action" method="post">
                <div class="mx-3">
                    
                    <%-- 氏名 --%>
                    <div class="mb-3">
                        <label class="form-label" for="class-name-input">クラス</label>
                        <input class="form-control" type="text" id="class-name-input" name="class_num" 
                               placeholder="クラスを入力してください" required />
                    </div>

                    
                    <div class="mt-4">
                        <button class="btn btn-primary" type="submit" id="register-button">登録</button>
                        <a href="ClassList.action" class="btn btn-outline-secondary ms-2">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>