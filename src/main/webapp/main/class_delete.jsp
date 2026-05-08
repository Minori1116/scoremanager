<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス情報削除</h2>
            
			<form action="ClassDeleteExecute.action" method="post">
    			<input type="hidden" name="class_num" value="${classnum.class_num}">

			    <div class="mx-3">
			        <div class="mb-3">
			            <p>クラス番号「${classnum.class_num}」を削除してもよろしいですか</p>
			        </div>
			
			
			        <button type="submit" class="btn btn-primary px-4">削除</button>
			        <div class="mt-3">
			            <a href="ClassList.action">戻る</a>
			        </div>
			    </div>
			</form>
        </section>
    </c:param>
</c:import>