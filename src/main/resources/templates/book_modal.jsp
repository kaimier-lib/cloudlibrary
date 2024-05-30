<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <title>图书管理</title>
    <link rel="stylesheet" th:href="@{/css/bootstrap.css}">
    <link rel="stylesheet" th:href="@{/css/AdminLTE.css}">
    <link rel="stylesheet" th:href="@{/css/pagination.css}">
    <script th:src="@{/js/jquery.min.js}"></script>
    <script th:src="@{/js/bootstrap.js}"></script>
    <script th:src="@{/js/pagination.js}"></script>
    <script th:src="@{/js/my.js}"></script>
</head>

<body class="hold-transition skin-red sidebar-mini">
<!-- .box-body -->
<div class="box-header with-border">
    <h3 class="box-title">图书借阅</h3>
</div>
<div class="box-body">
    <!-- 新增按钮：如果当前登录用户是管理员，页面展示新增按钮 -->
    <div th:if="${session.user.role == 'ADMIN'}" class="pull-left">
        <div class="form-group form-inline">
            <div class="btn-group">
                <button type="button" class="btn btn-default" title="新建" data-toggle="modal"
                        data-target="#addOrEditModal" onclick="resetFrom()"> 新增
                </button>
            </div>
        </div>
    </div>
    <!-- 新增按钮结束 -->
    <!-- 工具栏 数据搜索 -->
    <div class="box-tools pull-right">
        <div class="has-feedback">
            <form th:action="@{/book/search}" method="post">
                图书名称：<input name="name" th:value="${search.name}">&nbsp;&nbsp;&nbsp;&nbsp;
                图书作者：<input name="author" th:value="${search.author}">&nbsp;&nbsp;&nbsp;&nbsp;
                出版社：<input name="press" th:value="${search.press}">&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="btn btn-default" type="submit" value="查询">
            </form>
        </div>
    </div>
    <!-- 工具栏 数据搜索结束 -->
    <!-- 数据列表 -->
    <div class="table-box">
        <!-- 数据表格 -->
        <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
            <thead>
            <tr>
                <th class="sorting_asc">图书名称</th>
                <th class="sorting">图书作者</th>
                <th class="sorting">出版社</th>
                <th class="sorting">标准ISBN</th>
                <th class="sorting">书籍状态</th>
                <th class="sorting">借阅人</th>
                <th class="sorting">借阅时间</th>
                <th class="sorting">预计归还时间</th>
                <th class="text-center">操作</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="book : ${pageResult}">
                <td th:text="${book.name}">图书名称</td>
                <td th:text="${book.author}">图书作者</td>
                <td th:text="${book.press}">出版社</td>
                <td th:text="${book.isbn}">标准ISBN</td>
                <td>
                    <span th:if="${book.status == 0}">可借阅</span>
                    <span th:if="${book.status == 1}">借阅中</span>
                    <span th:if="${book.status == 2}">归还中</span>
                </td>
                <td th:text="${book.borrower}">借阅人</td>
                <td th:text="${book.borrowTime}">借阅时间</td>
                <td th:text="${book.returnTime}">预计归还时间</td>
                <td class="text-center">
                    <div th:if="${book.status == 0}">
                        <button type="button" class="btn bg-olive btn-xs" data-toggle="modal"
                                data-target="#borrowModal" th:onclick="'findBookById(' + ${book.id} + ', \'borrow\')'"> 借阅
                        </button>
                        <div th:if="${USER_SESSION.role == 'ADMIN'}">
                            <button type="button" class="btn bg-olive btn-xs" data-toggle="modal"
                                    data-target="#addOrEditModal" th:onclick="'findBookById(' + ${book.id} + ', \'edit\')'"> 编辑
                            </button>
                        </div>
                    </div>
                    <div th:if="${book.status == 1 or book.status == 2}">
                        <button type="button" class="btn bg-olive btn-xs" disabled="true">借阅</button>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <!-- 数据表格结束 -->
        <!-- 分页插件 -->
        <div id="pagination" class="pagination"></div>
    </div>
    <!-- 数据列表结束 -->
</div>
<!-- .box-body结束 -->
<!-- 引入存放模态窗口的页面 -->
<div th:replace="admin/book_modal :: modal"></div>

<!-- 添加和编辑图书的模态窗口 -->
<div class="modal fade" id="addOrEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">图书信息</h3>
            </div>
            <div class="modal-body">
                <form id="addOrEditBook">
                    <span><input type="hidden" id="ebid" name="id"></span>
                    <table id="addOrEditTab" class="table table-bordered table-striped" width="800px">
                        <!-- 图书的id,不展示在页面 -->
                        <tr>
                            <td>图书名称</td>
                            <td><input class="form-control" placeholder="图书名称" name="name" id="ebname"></td>
                            <td>标准ISBN</td>
                            <td><input class="form-control" placeholder="标准ISBN" name="isbn" id="ebisbn"></td>
                        </tr>
                        <tr>
                            <td>出版社</td>
                            <td><input class="form-control" placeholder="出版社" name="press" id="ebpress"></td>
                            <td>作者</td>
                            <td><input class="form-control" placeholder="作者" name="author" id="ebauthor"></td>
                        </tr>
                        <tr>
                            <td>书籍页数</td>
                            <td><input class="form-control" placeholder="书籍页数" name="pagination" id="ebpagination"></td>
                            <td>书籍价格<br/></td>
                            <td><input class="form-control" placeholder="书籍价格" name="price" id="ebprice"></td>
                        </tr>
                        <tr>
                            <td>上架状态</td>
                            <td>
                                <select class="form-control" id="ebstatus" name="status">
                                    <option value="0">上架</option>
                                    <option value="3">下架</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" id="aoe" disabled
                        th:onclick="|addOrEdit()|">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<script th:inline="javascript">
    /* 分页插件展示的总页数 */
    var pageargs = {
        total: [[${pageResult.total}]] / pageargs.pagesize,
        cur: [[${pageNum}]],
        gourl: "[[${gourl}]]",
        name: "[[${search.name}]]",
        author: "[[${search.author}]]",
        press: "[[${search.press}]]"
    };
    /* 分页效果 */
    pagination(pageargs);
</script>
</body>
</html>
