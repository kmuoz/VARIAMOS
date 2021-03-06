package com.variamos.gui.refas.editor;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import com.mxgraph.examples.swing.GraphEditor;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.maineditor.AbstractGraphEditorFunctions;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.EditorPalette;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.pl.editor.PLEditorPopupMenu;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.pl.editor.logic.ConstraintMode;
import com.variamos.refas.core.refas.Refas;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEnumeration;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaEnumeration;
import com.variamos.syntaxsupport.metamodelsupport.MetaOverTwoRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaView;

public class RefasGraphEditorFunctions extends AbstractGraphEditorFunctions {

	private ArrayList<PaletteElement> paletteElements = new ArrayList<PaletteElement>();

	public RefasGraphEditorFunctions(VariamosGraphEditor editor) {
		super(editor);
		Collection<MetaElement> metaElements = new HashSet<MetaElement>();
		if (((Refas) editor.getEditedModel()).getSyntaxRefas() == null) {
			for (MetaView metaView : editor.getMetaViews()) {
				metaElements.addAll(metaView.getElements());
			}
		} else {
			for (InstVertex instVertex : ((Refas) editor.getEditedModel())
					.getSyntaxRefas().getVertices()) {
				metaElements.add(instVertex.getEditableMetaElement());
			}

		}

		for (MetaElement metaElement : metaElements) {

			paletteElements.add(new PaletteElement(metaElement.getIdentifier(),
					metaElement.getName(), metaElement.getImage(), metaElement
							.getStyle(), metaElement.getWidth(), metaElement
							.getHeight(), null, metaElement));
		}
	}

	public void updateEditor(List<String> validElements,
			mxGraphComponent graphComponent, int modelViewIndex) {
		editor.setPerspective(2);
		editor.editModelReset();
		// System.out.println("requirements perspective");
		updateView(validElements, graphComponent, modelViewIndex);
	}

	public void updateView(List<String> validElements,
			mxGraphComponent graphComponent, int modelViewIndex) {
		editor.clearPalettes();
		EditorPalette palette = editor.insertPalette(mxResources
				.get("modelViewPalette" + modelViewIndex));
		AbstractGraph refasGraph = (AbstractGraph) graphComponent.getGraph();
		loadPalette(palette, validElements, refasGraph);
		editor.refreshPalette();
	}

	/**
	 * @param palette
	 * @param validElements
	 * @param plgraph
	 */
	public void loadPalette(EditorPalette palette, List<String> validElements,
			AbstractGraph plgraph) {
		// Load regular palette
		if (validElements != null) {
			for (int i = 0; i < paletteElements.size(); i++)
				try {
					PaletteElement paletteElement = paletteElements.get(i);
					if (validElements.contains(paletteElement.getId())) {
						Object obj = null;
						if (paletteElement.getMetaElement() != null) {
							MetaElement metaVertex = paletteElement
									.getMetaElement();
							
							if (metaVertex instanceof MetaConcept) {
								MetaElement metaElement = new MetaConcept();
								Object o = new InstConcept();
								Constructor<?> c = o.getClass().getConstructor(String.class,
										MetaConcept.class,MetaElement.class);
								obj = c.newInstance("",(MetaConcept) metaVertex, metaElement);
							} else if (metaVertex instanceof MetaOverTwoRelation) {
								MetaElement metaElement = new MetaOverTwoRelation();
								Object o = new InstOverTwoRelation();
								Constructor<?> c = o.getClass().getConstructor(String.class,
										MetaOverTwoRelation.class,MetaElement.class);
								obj = c.newInstance("",(MetaOverTwoRelation) metaVertex, metaElement);
							} else if (metaVertex instanceof MetaEnumeration) {

								MetaElement metaElement = new MetaEnumeration();
								Object o = new InstEnumeration();
								Constructor<?> c = o.getClass().getConstructor(String.class,MetaVertex.class,
										MetaElement.class);
								obj = c.newInstance("",(MetaVertex) metaVertex, metaElement);
							}

						} else {
							String classSingleName = paletteElement
									.getClassName().substring(
											paletteElement.getClassName()
													.lastIndexOf(".") + 1);
							Class<?> ref = Class.forName(paletteElement
									.getClassName());

							if (paletteElement.getId().equals(classSingleName)) {
								obj = ref.newInstance();
							} else {
								Constructor<?> c = ref
										.getConstructor(String.class);
								obj = c.newInstance(paletteElement.getId());
							}
						}
						palette.addTemplate(
								// mxResources.get(
								paletteElement.getElementTitle(),
								new ImageIcon(GraphEditor.class
										.getResource(paletteElement.getIcon())),
								paletteElement.getStyle(), paletteElement
										.getWidth(),
								paletteElement.getHeight(), obj);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		final AbstractGraph graph = plgraph;

		palette.addListener(mxEvent.SELECT, new mxIEventListener() {
			public void invoke(Object sender, mxEventObject evt) {
				Object tmp = evt.getProperty("transferable");
				graph.setConsMode(ConstraintMode.None);

				if (tmp instanceof mxGraphTransferable) {
					mxGraphTransferable t = (mxGraphTransferable) tmp;
					Object obj = t.getCells()[0];

					if (graph.getModel().isEdge(obj)) {
						mxCell cell = (mxCell) obj;
						((ProductLineGraph) graph)
								.setConsMode((ConstraintMode) cell.getValue());
					}
				}
			}
		});

	}

	public void showGraphPopupMenu(MouseEvent e,
			mxGraphComponent graphComponent, BasicGraphEditor editor) {
		Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),
				graphComponent);
		PLEditorPopupMenu menu = new PLEditorPopupMenu(editor);
		// RefasEditorPopupMenu menu = new RefasEditorPopupMenu(editor);
		menu.show(graphComponent, pt.x, pt.y);

		e.consume();
	}

}
