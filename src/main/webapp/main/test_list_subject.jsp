<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>
            
            <%-- 検索フォーム (検索条件を保持した状態で表示) --%>
            <form method="get" action="TestListSubjectExecute.action">
                <div class="row border mx-3 mb-3 py-3 align-items-center rounded bg-light">
                    <div class="col-1">科目情報</div>
                    <div class="col-2">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="f1">
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" ${year == f1 ? 'selected' : ''}>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="f2">
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" ${num == f2 ? 'selected' : ''}>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="f3">
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.cd}" ${subject.cd == f3 ? 'selected' : ''}>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2 text-center mt-4">
                        <button class="btn btn-secondary px-4">検索</button>
                    </div>
                </div>
            </form>

            <div class="mx-3">
                <%-- エラーメッセージ表示 --%>
                <c:if test="${not empty message}">
                    <p class="text-danger">${message}</p>
                </c:if>

                <%-- 検索結果がある場合のみテーブルを表示 --%>
                <c:if test="${not empty tests}">
                    <p class="fw-bold mt-4">科目：${subject_name}</p>
                    <table class="table table-hover mt-2">
                        <thead>
                            <tr class="border-bottom border-dark">
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th class="text-center">1回</th>
                                <th class="text-center">2回</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="tls" items="${tests}">
                                <tr>
                                    <td>${tls.entYear}</td>
                                    <td>${tls.classNum}</td>
                                    <td>${tls.studentNo}</td>
                                    <td>${tls.studentName}</td>
                                    <td class="text-center">${tls.points.get(1) != null ? tls.points.get(1) : '-'}</td>
                                    <td class="text-center">${tls.points.get(2) != null ? tls.points.get(2) : '-'}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </section>
    </c:param>
</c:import>