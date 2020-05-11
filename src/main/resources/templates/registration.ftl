<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div class="mb-3"> Add new user</div>
${message!}
<@l.login "/registration" true />
</@c.page>