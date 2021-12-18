<#import "components/common.ftlh" as c>
<@c.page>
    <form action="" method="post">
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Тема: </label>
            <div class="col-sm-6">
                <input class="form-control"
                       type="text" name="theme"
                       placeholder="light"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 col-form-label"> Язык: </label>
            <div class="col-sm-6">
                <input class="form-control"
                       type="text" name="language"
                       placeholder="english"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div>
            <button type="submit" class="btn btn-primary mb-3">
                Отправить
            </button>
        </div>
    </form>
    <h5>Тема: <#if theme??>${theme}</#if></h5>
    <h5>Язык: <#if language??>${language}</#if></h5>
    <h5>Никнейм: <#if username??>${username}</#if></h5>
</@c.page>