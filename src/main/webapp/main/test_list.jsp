<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
            
            <%-- 1. 科目・クラスによる検索フォーム --%>
            <form method="get" action="TestListSubjectExecute.action">
                <div class="row border mx-3 mb-3 py-3 align-items-center rounded bg-light">
                    <div class="col-2">
                    	<p>科目情報</p>
                    </div>
                    <div class="col-2">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" ${year == f1 ? 'selected' : ''}>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" ${num == f2 ? 'selected' : ''}>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-4">
                        <label class="form-label">科目</label>
                        <%-- 【重要】nameを f3 にし、valueを name ではなく cd にする --%>
                        <select class="form-select" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.cd}" ${subject.cd == f3 ? 'selected' : ''}>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    

                    <div class="col-2 text-center mt-4">
                        <button class="btn btn-secondary px-4">検索</button>
                    </div>
                    <!-- ★ 科目情報の枠の中にエラーメッセージを表示する -->
					<c:if test="${not empty message}">
					    <div class="mt-3">
					        <p class="text-danger">${message}</p>
					    </div>
					</c:if>
                    
                </div>
            </form>
            
            <%-- 2. 学生番号による検索フォーム --%>
            <form method="get" action="TestListStudentExecute.action">
                <div class="row border mx-3 mb-3 py-3 align-items-center rounded bg-light">
                    <div class="col-2">
                    	<p>学生情報</p>
                    </div>
                    <div class="col-4">
                        <label class="form-label">学生番号</label>
                        <%-- 【重要】Action側の受け取り名に合わせて student_no に設定 --%>
                        <input class="form-control" type="text" name="f4"  maxlength="10" 
                        placeholder="学生番号を入力してください" value="${f4}" required />
                    </div>

                    <div class="col-2 text-center mt-4">
                        <button class="btn btn-secondary px-4">検索</button>
                    </div>
                </div>
            </form>
            
            <%-- メッセージ表示エリア --%>
            <div class="mx-3">
				<div class="mx-3 mt-3">
			
			    <!-- 学生検索でデータが無かった場合 -->
			    	<c:if test="${empty tests and not empty f4}">
			        	<p class="text-dark">学生情報が存在しませんでした</p>
			    	</c:if>
			
			    <!-- ★ 初期表示メッセージ（常に表示） -->
			    	<p class="text-info mt-2">
			        	科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
			    	</p>
			
				</div>


            </div>
        </section>
    </c:param>
</c:import>