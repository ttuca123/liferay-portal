/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.password.policies.admin.uad.anonymizer;

import com.liferay.password.policies.admin.uad.constants.PasswordPoliciesAdminUADConstants;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PasswordPolicyLocalService;

import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

import java.util.Arrays;
import java.util.List;

/**
 * Provides the base implementation for the password policy UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link PasswordPolicyUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BasePasswordPolicyUADAnonymizer
	extends DynamicQueryUADAnonymizer<PasswordPolicy> {
	@Override
	public void autoAnonymize(PasswordPolicy passwordPolicy, long userId,
		User anonymousUser) throws PortalException {
		if (passwordPolicy.getUserId() == userId) {
			passwordPolicy.setUserId(anonymousUser.getUserId());
			passwordPolicy.setUserName(anonymousUser.getFullName());
		}

		passwordPolicyLocalService.updatePasswordPolicy(passwordPolicy);
	}

	@Override
	public void delete(PasswordPolicy passwordPolicy) throws PortalException {
		passwordPolicyLocalService.deletePasswordPolicy(passwordPolicy);
	}

	@Override
	public List<String> getNonanonymizableFieldNames() {
		return Arrays.asList();
	}

	@Override
	public Class<PasswordPolicy> getTypeClass() {
		return PasswordPolicy.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return passwordPolicyLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return PasswordPoliciesAdminUADConstants.USER_ID_FIELD_NAMES_PASSWORD_POLICY;
	}

	@Reference
	protected PasswordPolicyLocalService passwordPolicyLocalService;
}