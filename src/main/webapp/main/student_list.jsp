<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>学生一覧</h2>

<form action="StudentList.action" method="get">

    <!-- 入学年度 -->
    <label>入学年度</label>
    <select name="f1" class="form-select">
        <option value="0">選択してください</option>
        <c:forEach var="year" items="${ent_year_set}">
            <option value="${year}" ${year == f1 ? "selected" : ""}>${year}</option>
        </c:forEach>
    </select>

    <!-- クラス -->
    <label>クラス</label>
    <select name="f2" class="form-select">
        <option value="0">選択してください</option>
        <c:forEach var="num" items="${class_num_set}">
            <option value="${num}" ${num == f2 ? "selected" : ""}>${num}</option>
        </c:forEach>
    </select>

    <!-- 在学中 -->
    <label>
        <input type="checkbox" name="f3" value="1" ${f3 != null ? "checked" : ""}>
        在学中のみ
    </label>

    <button type="submit" class="btn btn-primary">検索</button>
</form>

<hr>

<!-- 検索結果 -->
<table class="table">
    <tr>
        <th>学籍番号</th>
        <th>氏名</th>
        <th>入学年度</th>
        <th>クラス</th>
        <th>在学</th>
    </tr>

    <c:forEach var="stu" items="${students}">
        <tr>
            <td>${stu.no}</td>
            <td>${stu.name}</td>
            <td>${stu.entYear}</td>
            <td>${stu.classNum}</td>
            <td>${stu.attend ? "○" : "×"}</td>
        </tr>
    </c:forEach>
</table>