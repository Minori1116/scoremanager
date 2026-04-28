<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/common/base.jsp">
    <jsp:param name="title" value="得点管理システム メニュー" />
    <jsp:param name="content" value="
        <div class='p-2 mb-3 bg-light border fw-bold'>メニュー</div>
        
        <div class='row g-4'>
            <div class='col-md-4'>
                <a href='StudentList.action' class='card h-100 text-center text-decoration-none shadow-sm bg-danger bg-opacity-10 py-5'>
                    <div class='card-body d-flex align-items-center justify-content-center'>
                        <h4 class='text-primary'>学生管理</h4>
                    </div>
                </a>
            </div>

            <div class='col-md-4'>
                
                <div class='card h-100 shadow-sm bg-success bg-opacity-10 p-3'>
                    <div class='card-body text-center'>
                        <h4 class='mb-3'>成績管理</h4>
                        <div class='d-grid gap-2'>

                            <a href='TestRegist.action' class='text-decoration-none'>成績登録</a>
                            <a href='TestList.action' class='text-decoration-none'>成績参照</a>

                        </div>
                    </div>
                </div>
            </div>

            <div class='col-md-4'>
                <a href='SubjectList.action' class='card h-100 text-center text-decoration-none shadow-sm bg-primary bg-opacity-10 py-5'>
                    <div class='card-body d-flex align-items-center justify-content-center'>
                        <h4 class='text-primary'>科目管理</h4>
                    </div>
                </a>
            </div>
        </div>
    " />
</jsp:include>
