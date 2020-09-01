
 
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.participates-in.label.username" path="participant.userAccount.username" width="50%"/>
	<acme:list-column code="authenticated.participates-in.label.fullname" path="participant.userAccount.identity.fullName" width="50%"/>
</acme:list>