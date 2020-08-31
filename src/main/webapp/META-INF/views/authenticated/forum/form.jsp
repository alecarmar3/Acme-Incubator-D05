<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.forum.label.title" path="title"/>
	<acme:form-moment code="authenticated.forum.label.creationDate" path="creationDate"/>
	<acme:form-textbox code="authenticated.forum.label.investmentRound" path="investmentRound.title"/>

	<acme:form-submit code="authenticated.forum.button.getMessages" method="get" action="/authenticated/message/list?id=${id}"/>
  	<acme:form-return code="authenticated.forum.button.return"/>
</acme:form>
