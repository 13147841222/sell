<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--sidebar-->
            <#include "../common/nav.ftl" >

<#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/category/save" >
                        <div class="form-group">
                            <label >名称</label>
                            <input name="categoryName" type="text" class="form-control" value="${(productCategory.categoryName)!""}" />
                        </div>
                        <div class="form-group">
                            <label >类型</label>
                            <input name="categoryType" type="number" class="form-control" value="${(productCategory.categoryType)!""}" />
                        </div>

                        <button type="submit" class="btn btn-default">提交</button>
                        <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!""}" >
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>

