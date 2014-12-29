package com.variamos.syntaxsupport.metametamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Juan Carlos Mu�oz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaVertex extends MetaElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3348811616807875183L;
	private boolean topConcept;
	private String backgroundColor;
	private boolean resizable;
	private List<MetaEdge> asOriginEdges;
	private List<MetaEdge> asDestinationEdges;

	public MetaVertex() {
		super();
	}

	public MetaVertex(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable) {
		this(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new HashMap<String, AbstractAttribute>(),
				new ArrayList<MetaEdge>(), new ArrayList<MetaEdge>());

	}

	public MetaVertex(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes) {
		this(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable,
				disPropVisibleAttributes, disPropEditableAttributes,
				disPanelVisibleAttributes, disPanelSpacersAttributes,
				modelingAttributes, new ArrayList<MetaEdge>(),
				new ArrayList<MetaEdge>());

	}

	public MetaVertex(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			List<MetaEdge> asOriginRelations,
			List<MetaEdge> asDestinationRelations) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, disPropVisibleAttributes,
				disPropEditableAttributes, disPanelVisibleAttributes,
				disPanelSpacersAttributes, modelingAttributes);

		this.backgroundColor = backgroundColor;
		this.topConcept = topConcept;
		this.resizable = resizable;
		this.asOriginEdges = asOriginRelations;
		this.asDestinationEdges = asDestinationRelations;
	}

	public void setTopConcept(boolean topConcept) {
		this.topConcept = topConcept;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public void setAsOriginEdge(List<MetaEdge> asOriginEdge) {
		this.asOriginEdges = asOriginEdge;
	}

	public void setAsDestinationEdge(List<MetaEdge> asDestinationEdge) {
		this.asDestinationEdges = asDestinationEdge;
	}

	public List<MetaEdge> getAsOriginRelations() {
		return asOriginEdges;
	}

	public boolean isTopConcept() {
		return topConcept;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public boolean isResizable() {
		return resizable;
	}

	public boolean equals(MetaVertex obj) {
		return getIdentifier().equals(obj.getIdentifier());
	}

	public void addMetaEdgeAsOrigin(MetaConcept metaConcept, MetaEdge metaEdge) {
		metaConcept.addMetaEdgeAsDestination(metaEdge);
		asOriginEdges.add(metaEdge);

	}

	public void addMetaEdgeAsDestination(MetaEdge metaEdge) {
		asDestinationEdges.add(metaEdge);

	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		return this.getModelingAttribute(attributeName);
	}
}
