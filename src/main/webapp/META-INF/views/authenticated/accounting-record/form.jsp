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
	<acme:form-textbox code="authenticated.accountingRecord.label.title" path="title"/>
	<acme:form-textbox code="authenticated.accountingRecord.label.status" path="status"/>
	<acme:form-moment code="authenticated.accountingRecord.label.creationDate" path="creationDate"/>
	<acme:form-textarea code="authenticated.accountingRecord.label.body" path="body"/>
	<acme:form-textbox code="authenticated.accountingRecord.label.bookkeeper" path="bookkeeper.userAccount.username"/>
	<acme:form-textbox code="authenticated.accountingRecord.label.investmentRound" path="investmentRound.title"/>
	
  	<acme:form-return code="authenticated.accountingRecord.button.return"/>
</acme:form>
