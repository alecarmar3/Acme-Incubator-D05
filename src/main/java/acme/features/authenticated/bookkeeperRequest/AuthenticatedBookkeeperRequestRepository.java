/*
 * AuthenticatedConsumerRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.bookkeeperRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.BookkeeperRequest;
import acme.framework.entities.UserAccount;
import acme.framework.entities.UserRole;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBookkeeperRequestRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select br from BookkeeperRequest br where br.authenticated.id = ?1")
	BookkeeperRequest findTheBookkeeperRequestByUserAccount(int userAccountId);

	@Query("select ur from UserRole ur where ur.userAccount.id = ?1")
	Collection<UserRole> findUserAccountRoles(int id);

}