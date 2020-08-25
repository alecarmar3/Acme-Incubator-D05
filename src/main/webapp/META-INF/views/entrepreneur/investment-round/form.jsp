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

	<jstl:if test="${command !='create'}">
	<acme:form-textbox code="entrepreneur.investmentRound.label.ticker" path="ticker" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command =='create'}">
	<acme:form-textbox code="entrepreneur.investmentRound.label.ticker" path="ticker"/>
	</jstl:if>
	
	<jstl:if test="${command !='create'}">
	<jstl:choose>
		<jstl:when test="${finalMode}">
			<acme:form-select readonly="true" code="entrepreneur.investmentRound.label.finalMode" path="finalMode">
				<acme:form-option code="entrepreneur.investmentRound.label.finalMode.yes" selected="true" value="true"/>
				<acme:form-option code="entrepreneur.investmentRound.label.finalMode.no" value="false"/>
			</acme:form-select>
			
		</jstl:when>
		<jstl:otherwise>
			<acme:form-select readonly="true" code="entrepreneur.investmentRound.label.finalMode" path="finalMode">
				<acme:form-option code="entrepreneur.investmentRound.label.finalMode.yes" value="true"/>
				<acme:form-option code="entrepreneur.investmentRound.label.finalMode.no"  selected="true" value="false"/>
			</acme:form-select>
		</jstl:otherwise>
	</jstl:choose>
	</jstl:if>
	
	<jstl:if test="${command !='create'}">
	<acme:form-moment code="entrepreneur.investmentRound.label.creationDate" path="creationDate" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command !='create'}">
	<acme:form-moment code="entrepreneur.investmentRound.label.updateDate" path="updateDate" readonly="true"/>
	</jstl:if>
	
	<acme:form-textbox code="entrepreneur.investmentRound.label.kindOfRound" path="kindOfRound"/>
	<acme:form-textbox code="entrepreneur.investmentRound.label.title" path="title"/>
	<acme:form-textarea code="entrepreneur.investmentRound.label.description" path="description"/>
	<acme:form-money code="entrepreneur.investmentRound.label.amountOfMoney" path="amountOfMoney"/>
	<acme:form-url code="entrepreneur.investmentRound.label.additionalInfo" path="additionalInfo"/>
	
	<jstl:if test="${command ==('show')}">
	<acme:form-textbox code="entrepreneur.investmentRound.label.entrepreneur" path="entrepreneur.userAccount.username" readonly="true"/>
	<acme:form-submit code="authenticated.investmentRound.accountingRecords" method="get" action="/authenticated/accounting-record/list-its?id=${id}"/>
  	<acme:form-submit code="authenticated.investmentRound.workProgramme" method="get" action="/authenticated/activity/list?id=${id}"/>
  	</jstl:if>
  	
  	<acme:form-submit test="${command == 'show'}" code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment-round/update"/>
	<acme:form-submit test="${command == 'show'}" code="entrepreneur.investmentRound.form.button.delete" action="/entrepreneur/investment-round/delete"/>
	<acme:form-submit test="${command == 'create'}" code="entrepreneur.investmentRound.form.button.create" action="/entrepreneur/investment-round/create"/>
	<acme:form-submit test="${command == 'update'}" code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment-round/update"/>
	<acme:form-submit test="${command == 'delete'}" code="entrepreneur.investmentRound.form.button.delete" action="/entrepreneur/investment-round/delete"/>
	
	<acme:form-return code="entrepreneur.investmentRound.button.return"/>
</acme:form>
