<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通テンプレート base.jsp を読み込む --%>
<c:import url="/common/base.jsp">
    <%-- c:param を使用する --%>
    <c:param name="title" value="得点管理システム" />
    
    <%-- コンテンツ部分 --%>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
            
            <%-- 絞り込みフォーム --%>
            <form method="get" action="TestRegist.action">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                <input type="hidden" name="search" value="1">
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
                    
                    <%-- 科目名選択 --%>
                    <div class="col-4">
                        <label class="form-label" for="student-f3-select">科目</label>
                        <select class="form-select" id="student-f3-select" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="sub" items="${name_set}">
                                <option value="${sub.cd}" <c:if test="${sub.cd eq f3}">selected</c:if>>
                                      ${sub.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <%-- 回数選択 --%>
                    <div class="col-2">
                        <label class="form-label" for="student-f4-select">回数</label>
                        <select class="form-select" id="student-f4-select" name="f4">
                            <option value="0">--------</option>
                            <c:forEach var="n" items="${num_set}">
                                <option value="${n}" <c:if test="${n==f4}">selected</c:if>>${n}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <%-- 検索ボタン --%>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary">検索</button>
                    </div>
                    
                    <%--エラーメッセージ --%>
                    <div class="mt-2 text-danger">${errors.get("msg")}</div>
                    
                </div>
            </form>
              
            <%-- 一覧テーブル --%>
            <c:if test="${not empty results}">
            <form method="post" action="TestRegistExecute.action">
                <div class="mx-3">
                <div><label class ="test">科目：${subjectName}  (${f4}回)</label></div>
                
                <!-- ExecuteAction に渡す hidden -->
                <input type="hidden" name="f1" value="${f1}">
                <input type="hidden" name="num" value="${f2}">
                <input type="hidden" name="subject" value="${f3}">
                <input type="hidden" name="count" value="${f4}">
                   <table class="table table-hover">
                      <thead>
                          <tr>
		                      <th>入学年度</th>
		                      <th>クラス</th>
		                      <th>学生番号</th>
		                      <th>氏名</th>
		                      <th>点数</th>
		                      <th></th>
		                  </tr>
		              </thead>
		              <tbody>
		                 <c:forEach var="test" items="${results}">
		                     <tr>
		                         <th>${test.student.entYear}</th>
		                         <td>${test.student.classNum}</td>
		                         <td>${test.student.no}</td>
		                         <td>${test.student.name}</td>
		                         <td>
									<input class="form-control" type="number"
									       name="point_${test.student.no}"
									       value="${not empty pointMap[test.student.no]
                                                    ? pointMap[test.student.no]
                                                    : test.point == 0 ? '' : test.point}"
									       inputmode="numeric">
									 
									      <c:if test="${not empty errorMsg}">
                                                 <div class="mt-2 text-warning">${errorMsg}</div>
                                          </c:if>
									       
		                             
		                         </td>
		                         
		                         <!-- regist（学生番号）を送る -->
		                         <td>
		                              <input type="hidden" name="regist" value="${test.student.no}">
                                 </td>
		                     </tr>
		                 </c:forEach>
		               </tbody>
                   </table>
                   <%-- 登録ボタン --%>
                   <input type="submit" class="btn btn-secondary" value="登録して終了">
                 </div>
              </c:if>
        </section>
    </c:param>
</c:import>