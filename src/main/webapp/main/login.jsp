t<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <div class="d-flex justify-content-center mt-5">
            <div class="card bg-light" style="width: 500px;">
                <div class="card-header text-center py-3">
                    <h2 class="h4 mb-0 fw-normal">ログイン</h2>
                </div>
                <div class="card-body px-5 py-4">
                    <form action="LoginExecute.action" method="post">
                        <div class="mb-3">
                            <label class="form-label text-secondary small" for="id">ＩＤ</label>
                            <input class="form-control bg-light-blue" type="texte" id="id" name="id"
                                   placeholder="admin" required>
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label text-secondary small" for="password">パスワード</label>
                            <input class="form-control bg-light-blue" type="password" id="password" name="password"
                                   placeholder="･･････" required>
                        </div>
 
                        <div class="form-check d-flex justify-content-center mb-4">
                            <input class="form-check-input me-2" type="checkbox" id="show-password">
                            <label class="form-check-label small text-secondary" for="show-password">
                                パスワードを表示
                            </label>
                        </div>
 
                        <c:if test="${!empty errors}">
                            <div class="text-danger text-center small mb-3">${errors}</div>
                        </c:if>
 
                        <div class="text-center">
                            <button class="btn btn-primary px-5 py-2" id="login-button">ログイン</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

 
        <%-- パスワード表示切り替えの簡易スクリプト --%>
        <script>
            document.getElementById('show-password').addEventListener('change', function() {
                const passInput = document.getElementById('password');
                passInput.type = this.checked ? 'text' : 'password';
            });
        </script>
 
        <style>
            /* 入力欄の色を画像っぽく少し青みがかった色にする場合 */
            .bg-light-blue {
                background-color: #eef4ff !important;
            }
            .card {
                border: 1px solid #dee2e6;
                box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
            }
            .card-header {
                background-color: #f8f9fa;
                border-bottom: 1px solid #dee2e6;
            }
        </style>
    </c:param>
</c:import>