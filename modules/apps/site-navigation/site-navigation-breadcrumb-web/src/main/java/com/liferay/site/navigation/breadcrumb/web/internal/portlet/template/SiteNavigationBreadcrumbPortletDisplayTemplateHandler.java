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

package com.liferay.site.navigation.breadcrumb.web.internal.portlet.template;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbUtil;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portlet.display.template.constants.PortletDisplayTemplateConstants;
import com.liferay.site.navigation.breadcrumb.web.configuration.SiteNavigationBreadcrumbWebTemplateConfiguration;
import com.liferay.site.navigation.breadcrumb.web.internal.constants.SiteNavigationBreadcrumbPortletKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author José Manuel Navarro
 */
@Component(
	configurationPid = "com.liferay.site.navigation.breadcrumb.web.configuration.SiteNavigationBreadcrumbWebTemplateConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = "javax.portlet.name=" + SiteNavigationBreadcrumbPortletKeys.SITE_NAVIGATION_BREADCRUMB,
	service = TemplateHandler.class
)
public class SiteNavigationBreadcrumbPortletDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return BreadcrumbEntry.class.getName();
	}

	@Override
	public Map<String, Object> getCustomContextObjects() {
		Map<String, Object> customContextObjects = new HashMap<>(1);

		customContextObjects.put("breadcrumbUtil", BreadcrumbUtil.class);

		return customContextObjects;
	}

	@Override
	public String getDefaultTemplateKey() {
		return _siteNavigationBreadcrumbWebTemplateConfiguration.
			ddmTemplateKeyDefault();
	}

	@Override
	public String getName(Locale locale) {
		String portletTitle = _portal.getPortletTitle(
			SiteNavigationBreadcrumbPortletKeys.SITE_NAVIGATION_BREADCRUMB,
			locale);

		return portletTitle.concat(StringPool.SPACE).concat(
			LanguageUtil.get(locale, "template"));
	}

	@Override
	public String getResourceName() {
		return SiteNavigationBreadcrumbPortletKeys.SITE_NAVIGATION_BREADCRUMB;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception {

		Map<String, TemplateVariableGroup> templateVariableGroups =
			super.getTemplateVariableGroups(classPK, language, locale);

		String[] restrictedVariables = getRestrictedVariables(language);

		TemplateVariableGroup breadcrumbUtilTemplateVariableGroup =
			new TemplateVariableGroup("breadcrumb-util", restrictedVariables);

		breadcrumbUtilTemplateVariableGroup.addVariable(
			"breadcrumb-util", BreadcrumbUtil.class, "breadcrumbUtil");

		templateVariableGroups.put(
			"breadcrumb-util", breadcrumbUtilTemplateVariableGroup);

		TemplateVariableGroup fieldsTemplateVariableGroup =
			templateVariableGroups.get("fields");

		fieldsTemplateVariableGroup.addCollectionVariable(
			"breadcrumb-entries", List.class,
			PortletDisplayTemplateConstants.ENTRIES, "breadcrumb-entry",
			BreadcrumbEntry.class, "curEntry", "getTitle()");
		fieldsTemplateVariableGroup.addVariable(
			"breadcrumb-entry", BreadcrumbEntry.class,
			PortletDisplayTemplateConstants.ENTRY, "getTitle()");

		return templateVariableGroups;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_siteNavigationBreadcrumbWebTemplateConfiguration =
			ConfigurableUtil.createConfigurable(
				SiteNavigationBreadcrumbWebTemplateConfiguration.class,
				properties);
	}

	@Override
	protected String getTemplatesConfigPath() {
		return "com/liferay/site/navigation/breadcrumb/web/portlet/template" +
			"/dependencies/portlet-display-templates.xml";
	}

	@Reference
	private Portal _portal;

	private volatile SiteNavigationBreadcrumbWebTemplateConfiguration
		_siteNavigationBreadcrumbWebTemplateConfiguration;

}