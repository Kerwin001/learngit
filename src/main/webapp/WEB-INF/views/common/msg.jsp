<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        <s:if test="actionMessages.size>0">
        $.dialog({
            title: "温馨提示",
            content: "<s:property value="actionMessages[0]"/>",
            ok: true
        });
        </s:if>
        <s:if test="errorMessages.size>0">
        $.dialog({
            title: "温馨提示",
            content:"<s:property value="errorMessages[0]"/>",
            ok: true
        });
        </s:if>
    });
</script>
