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

package com.liferay.user.associated.data.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.user.associated.data.web.internal.export.background.task.UADExportBackgroundTaskManagerUtil;

import java.io.Serializable;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pei-Jung Lan
 */
public class UADExportProcessDisplayContext {

	public UADExportProcessDisplayContext(
		HttpServletRequest request, RenderResponse renderResponse) {

		_request = request;
		_renderResponse = renderResponse;
	}

	public int getBackgroundTaskStatus(String status) {
		if (status.equals("failed")) {
			return BackgroundTaskConstants.STATUS_FAILED;
		}
		else if (status.equals("in-progress")) {
			return BackgroundTaskConstants.STATUS_IN_PROGRESS;
		}
		else if (status.equals("successful")) {
			return BackgroundTaskConstants.STATUS_SUCCESSFUL;
		}

		return 0;
	}

	public Comparator<BackgroundTask> getComparator(
		String orderByCol, String orderByType) {

		Comparator<BackgroundTask> comparator = Comparator.comparing(
			BackgroundTask::getCreateDate);

		if (orderByCol.equals("name")) {
			comparator =
				(BackgroundTask backgroundTask1, BackgroundTask backgroundTask2)
					-> {
					Map<String, Serializable> taskContextMap1 =
						backgroundTask1.getTaskContextMap();
					Map<String, Serializable> taskContextMap2 =
						backgroundTask2.getTaskContextMap();

					String applicationKey1 = (String)taskContextMap1.get(
						"applicationKey");
					String applicationKey2 = (String)taskContextMap2.get(
						"applicationKey");

					return applicationKey1.compareTo(applicationKey2);
				};
		}

		if (orderByType.equals("desc")) {
			comparator = comparator.reversed();
		}

		return comparator;
	}

	public CreationMenu getCreationMenu() throws PortalException {
		CreationMenu creationMenu = new CreationMenu();

		User selectedUser = PortalUtil.getSelectedUser(_request);

		creationMenu.addPrimaryDropdownItem(
			dropdownItem -> {
				dropdownItem.setHref(
					_renderResponse.createRenderURL(), "mvcRenderCommandName",
					"/add_uad_export_processes", "backURL",
					PortalUtil.getCurrentURL(_request), "p_u_i_d",
					String.valueOf(selectedUser.getUserId()));
				dropdownItem.setLabel("add-export-processes");
			});

		return creationMenu;
	}

	public DropdownItemList getDropdownItems() throws PortalException {
		DropdownItemList dropdownItems = new DropdownItemList();

		PortletURL navigationPortletURL = getPortletURL();

		dropdownItems.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					getNavigationDropdownItems(navigationPortletURL));
				dropdownGroupItem.setLabel(
					LanguageUtil.get(_request, "filter-by-navigation"));
			});

		PortletURL orderByPortletURL = getPortletURL();

		dropdownItems.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					getOrderByDropdownItems(orderByPortletURL));
				dropdownGroupItem.setLabel(
					LanguageUtil.get(_request, "order-by"));
			});

		return dropdownItems;
	}

	public String getNavigation() {
		if (_navigation != null) {
			return _navigation;
		}

		_navigation = ParamUtil.getString(_request, "navigation", "all");

		return _navigation;
	}

	public DropdownItemList getNavigationDropdownItems(PortletURL portletURL) {
		DropdownItemList navigationDropdownItems = new DropdownItemList();

		for (String navigation :
				new String[] {"all", "in-progress", "successful", "failed"}) {

			navigationDropdownItems.add(
				dropdownItem -> {
					dropdownItem.setActive(navigation.equals(getNavigation()));
					dropdownItem.setLabel(
						LanguageUtil.get(_request, navigation));
					dropdownItem.setHref(portletURL, "navigation", navigation);
				});
		}

		return navigationDropdownItems;
	}

	public String getOrderByCol() {
		if (_orderByCol != null) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_request, "orderByCol", "create-date");

		return _orderByCol;
	}

	public DropdownItemList getOrderByDropdownItems(PortletURL portletURL) {
		DropdownItemList orderByDropdownItems = new DropdownItemList();

		for (String orderByCol : new String[] {"create-date", "name"}) {
			orderByDropdownItems.add(
				dropdownItem -> {
					dropdownItem.setActive(orderByCol.equals(getOrderByCol()));
					dropdownItem.setLabel(
						LanguageUtil.get(_request, orderByCol));
					dropdownItem.setHref(
						portletURL, "orderByCol", orderByCol, "orderByType",
						getOrderByType());
				});
		}

		return orderByDropdownItems;
	}

	public String getOrderByType() {
		if (_orderByType != null) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(_request, "orderByType", "desc");

		return _orderByType;
	}

	public PortletURL getPortletURL() throws PortalException {
		PortletRequest portletRequest = (PortletRequest)_request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
		PortletResponse portletResponse =
			(PortletResponse)_request.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		PortletURL portletURL = PortletURLUtil.getCurrent(
			PortalUtil.getLiferayPortletRequest(portletRequest),
			PortalUtil.getLiferayPortletResponse(portletResponse));

		portletURL.setParameter(
			"mvcRenderCommandName", "/view_uad_export_processes");

		User selectedUser = PortalUtil.getSelectedUser(_request);

		portletURL.setParameter(
			"p_u_i_d", String.valueOf(selectedUser.getUserId()));

		portletURL.setParameter("navigation", getNavigation());
		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public SearchContainer getSearchContainer() throws PortalException {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		PortletRequest portletRequest = (PortletRequest)_request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);

		SearchContainer searchContainer = new SearchContainer(
			portletRequest, getPortletURL(), null,
			"no-personal-data-export-processes-were-found");

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByType(getOrderByType());

		String navigation = getNavigation();

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		User selectedUser = PortalUtil.getSelectedUser(_request);

		List<BackgroundTask> results = null;

		if (navigation.equals("failed") || navigation.equals("in-progress") ||
			navigation.equals("successful")) {

			int status = getBackgroundTaskStatus(navigation);

			searchContainer.setTotal(
				UADExportBackgroundTaskManagerUtil.getBackgroundTasksCount(
					themeDisplay.getScopeGroupId(), selectedUser.getUserId(),
					status));

			results = UADExportBackgroundTaskManagerUtil.getBackgroundTasks(
				themeDisplay.getScopeGroupId(), selectedUser.getUserId(),
				status);
		}
		else {
			searchContainer.setTotal(
				UADExportBackgroundTaskManagerUtil.getBackgroundTasksCount(
					themeDisplay.getScopeGroupId(), selectedUser.getUserId()));

			results = UADExportBackgroundTaskManagerUtil.getBackgroundTasks(
				themeDisplay.getScopeGroupId(), selectedUser.getUserId());
		}

		Stream<BackgroundTask> backgroundTaskStream = results.stream();

		results = backgroundTaskStream.sorted(
			getComparator(
				searchContainer.getOrderByCol(),
				searchContainer.getOrderByType())
		).skip(
			searchContainer.getStart()
		).limit(
			searchContainer.getDelta()
		).collect(
			Collectors.toList()
		);

		searchContainer.setResults(results);

		_searchContainer = searchContainer;

		return _searchContainer;
	}

	public String getSortingURL() throws PortalException {
		PortletURL sortingURL = getPortletURL();

		String orderByType;

		if (Objects.equals(getOrderByType(), "asc")) {
			orderByType = "desc";
		}
		else {
			orderByType = "asc";
		}

		sortingURL.setParameter("orderByType", orderByType);

		return sortingURL.toString();
	}

	private String _navigation;
	private String _orderByCol;
	private String _orderByType;
	private final RenderResponse _renderResponse;
	private final HttpServletRequest _request;
	private SearchContainer _searchContainer;

}