<#import "components/common.ftlh" as c>

<@c.page>
    <h1>Загрузить новый pdf</h1>
    <form method="POST" action="/pdf" enctype="multipart/form-data">
        Pdf: <input type="file" name="pdf" accept="application/pdf"/> <br> <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input class="btn btn-primary mb-1" type="submit" value="Загрузить"/>
    </form>
    <h1>Список Pdf:</h1>
    <#list pdfs?reverse as pdf>
        <span>${pdf.title}</span>
        <a href="/pdf/${pdf.id}" download="${pdf.title}">Скачать</a>
        <br>
        <form action="/pdf/del/${pdf.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-danger" type="submit">Удалить</button>
        </form>
        <br> <br>
    </#list>
</@c.page>