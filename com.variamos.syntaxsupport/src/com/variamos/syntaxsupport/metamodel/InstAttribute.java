package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.EditableElementAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticEdge;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;

/**
 * A class to represented modeling instances of attributes from meta model and
 * semantic model AbstractAttributes on VariaMos. Part of PhD work at University
 * of Paris 1
 * 
 * @author Juan C. Mu�oz Fern�ndez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.syntaxsupport.metametamodel.AbtractAttribute
 */
public class InstAttribute implements Serializable,EditableElementAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6097914483168975659L;
	/**
	 * Object associated to an existing metaElement, syntaxElement or
	 * InstElement - from JList or JComboBox
	 */
	private Object valueObject;
	/**
	 * MetaModel attribute object supporting the instance
	 */
	private AbstractAttribute attributeObject;
	
	public static final String
	/**
	 * Name of Instance identifier
	 */
	VAR_IDENTIFIER = "Identifier",
	/**
	 * Name of MetaModel attribute identifier, to recover object after load
	 */
	VAR_ATTRIBUTEIDEN = "attributeIden",
	/**
	 * Name of the Value for the InstAttribute - indexes in case of JList 
	 */
	VAR_VALUE = "Value",
	/**
	 * Name of display value for JList with indexes for VAR_VALUE (MClass and MEnumeration)
	 */
	VAR_DISPLAYVALUE = "DispValue",
	/**
	 * Initially for List IntSemanticGroupDependency
	 */
	VAR_VALIDATIONGROUPDEPLIST = "ValidGDList",
	/**
	 * Initially for List IntSemanticDirectRelation
	 */
	VAR_VALIDATIONDIRECTRELLIST = "ValidDRList",
	/**
	 * Initially for List to support MetaEnumerations
	 */
	VAR_VALIDATIONMETAEDGELIST = "ValidMEList";
	/**
	 * Dynamic storage of attributes
	 */
	protected Map<String, Object> vars = new HashMap<>();

	public InstAttribute() {

	}

	public InstAttribute(String identifier) {
		vars.put(VAR_IDENTIFIER, identifier);
	}

	public InstAttribute(String identifier,
			AbstractAttribute modelingAttribute, Object value) {
		super();
		this.attributeObject = modelingAttribute;
		vars.put(VAR_IDENTIFIER, identifier);
		vars.put(VAR_ATTRIBUTEIDEN, modelingAttribute.getName());
		vars.put(VAR_VALUE, value);
		vars.put(VAR_DISPLAYVALUE, null);
	}

	public InstAttribute(String identifier,
			AbstractAttribute modelingAttribute, Object value,
			Object valueObject) {
		super();
		this.attributeObject = modelingAttribute;
		vars.put(VAR_IDENTIFIER, identifier);
		vars.put(VAR_ATTRIBUTEIDEN, modelingAttribute.getName());
		vars.put(VAR_VALUE, value);
		vars.put(VAR_DISPLAYVALUE, null);
		this.valueObject = valueObject;

		// this.value = value;
	}

	public Object getVariable(String name) {
		return vars.get(name);
	}

	public void setVariable(String name, Object value) {
		vars.put(name, value);
	}

	public void setIdentifier(String identifier) {
		// this.identifier = identifier;
		setVariable(VAR_IDENTIFIER, identifier);
	}

	public void setAffectProperties(boolean affectProperties) {
		attributeObject.setAffectProperties(affectProperties);
	}

	public boolean isAffectProperties() {
		return attributeObject.isAffectProperties();
	}

	public void setDisplayName(String displayName) {
		attributeObject.setDisplayName(displayName);
	}

	public String getDisplayName() {
		return attributeObject.getDisplayName();
	}

	public void setValidationMEList(List<MetaEdge> metaEdge) {
		// this.identifier = identifier;
		setVariable(VAR_VALIDATIONMETAEDGELIST, metaEdge);
	}

	@SuppressWarnings("unchecked")
	public List<MetaEdge> getValidationMEList() {
		return (List<MetaEdge>) getVariable(VAR_VALIDATIONMETAEDGELIST);
		// return identifier;
	}

	public void setValidationGDList(List<IntSemanticGroupDependency> semGD) {
		// this.identifier = identifier;
		setVariable(VAR_VALIDATIONGROUPDEPLIST, semGD);
	}

	@SuppressWarnings("unchecked")
	public List<IntSemanticGroupDependency> getValidationGDList() {
		return (List<IntSemanticGroupDependency>) getVariable(VAR_VALIDATIONGROUPDEPLIST);
		// return identifier;
	}

	public void setValidationDRList(List<IntDirectSemanticEdge> semGD) {
		// this.identifier = identifier;
		setVariable(VAR_VALIDATIONDIRECTRELLIST, semGD);
	}

	@SuppressWarnings("unchecked")
	public List<IntDirectSemanticEdge> getValidationDRList() {
		return (List<IntDirectSemanticEdge>) getVariable(VAR_VALIDATIONDIRECTRELLIST);
		// return identifier;
	}

	public void setAttribute(AbstractAttribute modelingAttribute) {
		this.attributeObject = modelingAttribute;
		if (modelingAttribute != null)
			setVariable(VAR_ATTRIBUTEIDEN, modelingAttribute.getName());
	}

	public String getIdentifier() {
		return (String) getVariable(VAR_IDENTIFIER);
		// return identifier;
	}

	public AbstractAttribute getAttribute() {
		return attributeObject;
	}

	public String getAttributeName() {
		return (String) getVariable(VAR_ATTRIBUTEIDEN);
	}

	public Object getValue() {
		return getVariable(VAR_VALUE);
		// return value;
	}

	public Object getDisplayValue() {
		if (getVariable(VAR_DISPLAYVALUE) == null)
			return getVariable(VAR_VALUE);
		else
			return getVariable(VAR_DISPLAYVALUE);
		// return value;
	}

	public void setValue(Object value) {
		setVariable(VAR_VALUE, value);
		// this.value = value;
	}

	public String getAttributeType() {
		return attributeObject.getType();
	}

	public Object getEnumType() {
		// TODO Auto-generated method stub
		return attributeObject.getClassCanonicalName();
	}

	public void setType(String selectedItem) {
		// TODO Auto-generated method stub

	}

	// Method from com.cfm.productline.Variable class
	public Float getAsFloat() {
		Object val = getValue();
		if (val == null)
			return null;

		if (val instanceof Float)
			return (Float) val;

		if (val instanceof String)
			return Float.parseFloat((String) val);

		return 0f;
	}

	// Method from com.cfm.productline.Variable class
	public Integer getAsInteger() {
		Object val = getValue();
		if (val == null)
			return null;

		if (val instanceof Integer)
			return (Integer) val;

		if (val instanceof String)
			return Integer.parseInt((String) val);

		return 0;
	}

	// Method from com.cfm.productline.Variable class
	public Boolean getAsBoolean() {
		Object val = getValue();
		if (val == null)
			return null;

		if (val instanceof Boolean)
			return (Boolean) val;

		if (val instanceof String) {
			// Check if it's 0
			if ("0".equals(val))
				return false;

			// Check if it's "false"
			if ("false".equalsIgnoreCase((String) val))
				return false;
		}
		return true;
	}

	public String toString() {
		Object val = getDisplayValue();
		if (val == null)
			return "";
		return val.toString();
	}

	public Map<String, Object> getVars() {
		return vars;
	}

	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}

	public void clearModelingAttribute() {
		attributeObject = null;
		valueObject = null;
		setVariable(VAR_VALIDATIONGROUPDEPLIST, null);
		setVariable(VAR_VALIDATIONDIRECTRELLIST, null);
		setVariable(VAR_VALIDATIONMETAEDGELIST, null);
	}

	public void displayValue(String out) {
		setVariable(VAR_DISPLAYVALUE, out);

	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	public Object getValueObject() {
		return valueObject;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof InstAttribute)
			return getIdentifier().equals(((InstAttribute) o).getIdentifier());
		else
			return false;
	}

	@Override
	public int hashCode() {
		return getIdentifier().hashCode();
	}

	@Override
	public String getName() {
		return (String) getVariable(VAR_ATTRIBUTEIDEN);
	}
}
