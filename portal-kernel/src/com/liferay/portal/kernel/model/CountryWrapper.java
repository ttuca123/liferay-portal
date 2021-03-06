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

package com.liferay.portal.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Country}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Country
 * @generated
 */
@ProviderType
public class CountryWrapper implements Country, ModelWrapper<Country> {
	public CountryWrapper(Country country) {
		_country = country;
	}

	@Override
	public Class<?> getModelClass() {
		return Country.class;
	}

	@Override
	public String getModelClassName() {
		return Country.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("countryId", getCountryId());
		attributes.put("name", getName());
		attributes.put("a2", getA2());
		attributes.put("a3", getA3());
		attributes.put("number", getNumber());
		attributes.put("idd", getIdd());
		attributes.put("zipRequired", isZipRequired());
		attributes.put("active", isActive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String a2 = (String)attributes.get("a2");

		if (a2 != null) {
			setA2(a2);
		}

		String a3 = (String)attributes.get("a3");

		if (a3 != null) {
			setA3(a3);
		}

		String number = (String)attributes.get("number");

		if (number != null) {
			setNumber(number);
		}

		String idd = (String)attributes.get("idd");

		if (idd != null) {
			setIdd(idd);
		}

		Boolean zipRequired = (Boolean)attributes.get("zipRequired");

		if (zipRequired != null) {
			setZipRequired(zipRequired);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@Override
	public Object clone() {
		return new CountryWrapper((Country)_country.clone());
	}

	@Override
	public int compareTo(Country country) {
		return _country.compareTo(country);
	}

	/**
	* Returns the a2 of this country.
	*
	* @return the a2 of this country
	*/
	@Override
	public String getA2() {
		return _country.getA2();
	}

	/**
	* Returns the a3 of this country.
	*
	* @return the a3 of this country
	*/
	@Override
	public String getA3() {
		return _country.getA3();
	}

	/**
	* Returns the active of this country.
	*
	* @return the active of this country
	*/
	@Override
	public boolean getActive() {
		return _country.getActive();
	}

	/**
	* Returns the country ID of this country.
	*
	* @return the country ID of this country
	*/
	@Override
	public long getCountryId() {
		return _country.getCountryId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _country.getExpandoBridge();
	}

	/**
	* Returns the idd of this country.
	*
	* @return the idd of this country
	*/
	@Override
	public String getIdd() {
		return _country.getIdd();
	}

	/**
	* Returns the mvcc version of this country.
	*
	* @return the mvcc version of this country
	*/
	@Override
	public long getMvccVersion() {
		return _country.getMvccVersion();
	}

	/**
	* Returns the name of this country.
	*
	* @return the name of this country
	*/
	@Override
	public String getName() {
		return _country.getName();
	}

	@Override
	public String getName(java.util.Locale locale) {
		return _country.getName(locale);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _country.getNameCurrentLanguageId();
	}

	@Override
	public String getNameCurrentValue() {
		return _country.getNameCurrentValue();
	}

	/**
	* Returns the number of this country.
	*
	* @return the number of this country
	*/
	@Override
	public String getNumber() {
		return _country.getNumber();
	}

	/**
	* Returns the primary key of this country.
	*
	* @return the primary key of this country
	*/
	@Override
	public long getPrimaryKey() {
		return _country.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _country.getPrimaryKeyObj();
	}

	/**
	* Returns the zip required of this country.
	*
	* @return the zip required of this country
	*/
	@Override
	public boolean getZipRequired() {
		return _country.getZipRequired();
	}

	@Override
	public int hashCode() {
		return _country.hashCode();
	}

	/**
	* Returns <code>true</code> if this country is active.
	*
	* @return <code>true</code> if this country is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _country.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _country.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _country.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _country.isNew();
	}

	/**
	* Returns <code>true</code> if this country is zip required.
	*
	* @return <code>true</code> if this country is zip required; <code>false</code> otherwise
	*/
	@Override
	public boolean isZipRequired() {
		return _country.isZipRequired();
	}

	/**
	* Sets the a2 of this country.
	*
	* @param a2 the a2 of this country
	*/
	@Override
	public void setA2(String a2) {
		_country.setA2(a2);
	}

	/**
	* Sets the a3 of this country.
	*
	* @param a3 the a3 of this country
	*/
	@Override
	public void setA3(String a3) {
		_country.setA3(a3);
	}

	/**
	* Sets whether this country is active.
	*
	* @param active the active of this country
	*/
	@Override
	public void setActive(boolean active) {
		_country.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_country.setCachedModel(cachedModel);
	}

	/**
	* Sets the country ID of this country.
	*
	* @param countryId the country ID of this country
	*/
	@Override
	public void setCountryId(long countryId) {
		_country.setCountryId(countryId);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_country.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_country.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_country.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the idd of this country.
	*
	* @param idd the idd of this country
	*/
	@Override
	public void setIdd(String idd) {
		_country.setIdd(idd);
	}

	/**
	* Sets the mvcc version of this country.
	*
	* @param mvccVersion the mvcc version of this country
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_country.setMvccVersion(mvccVersion);
	}

	/**
	* Sets the name of this country.
	*
	* @param name the name of this country
	*/
	@Override
	public void setName(String name) {
		_country.setName(name);
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_country.setNameCurrentLanguageId(languageId);
	}

	@Override
	public void setNew(boolean n) {
		_country.setNew(n);
	}

	/**
	* Sets the number of this country.
	*
	* @param number the number of this country
	*/
	@Override
	public void setNumber(String number) {
		_country.setNumber(number);
	}

	/**
	* Sets the primary key of this country.
	*
	* @param primaryKey the primary key of this country
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_country.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_country.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this country is zip required.
	*
	* @param zipRequired the zip required of this country
	*/
	@Override
	public void setZipRequired(boolean zipRequired) {
		_country.setZipRequired(zipRequired);
	}

	@Override
	public CacheModel<Country> toCacheModel() {
		return _country.toCacheModel();
	}

	@Override
	public Country toEscapedModel() {
		return new CountryWrapper(_country.toEscapedModel());
	}

	@Override
	public String toString() {
		return _country.toString();
	}

	@Override
	public Country toUnescapedModel() {
		return new CountryWrapper(_country.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _country.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CountryWrapper)) {
			return false;
		}

		CountryWrapper countryWrapper = (CountryWrapper)obj;

		if (Objects.equals(_country, countryWrapper._country)) {
			return true;
		}

		return false;
	}

	@Override
	public Country getWrappedModel() {
		return _country;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _country.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _country.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_country.resetOriginalValues();
	}

	private final Country _country;
}