<!DOCTYPE html>
<#macro login path isRegsterForm>
<form action="${path}" method="post">
    <div class="form-group row">

        <label class="col-cm-2 col-form-label"> User Name :</label>
        <div class="col-sm-3">
        <input type="text" name="username" class="form-control" placeholder="User Name"/>
        </div>
    </div>
    <div class="form-group row" >
        <label class="col-cm-2 col-form-label" > Password: </label>
        <div class="col-sm-3">
            <input type="password" name="password" class="form-control" placeholder="password"/>
        </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </div>
    <#if !isRegsterForm><a href="/registration">Add new user</a></#if>
    <button class="btn btn-primary" type="submit" ><#if isRegsterForm>Create<#else >Sign In</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary">Sign out</button>
</form>
</#macro>