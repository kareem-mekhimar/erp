/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.utils;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.DataTableRenderer;
import org.primefaces.util.ComponentUtils;

/**
 *
 * @author kareem
 */
public class AlTairTableRenderer extends DataTableRenderer {

    @Override
    protected void encodeMarkup(FacesContext context, DataTable table) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String clientId = table.getClientId(context);
        boolean scrollable = table.isScrollable();
        boolean hasPaginator = table.isPaginator();
        String style = table.getStyle();
        String paginatorPosition = table.getPaginatorPosition();
        int frozenColumns = table.getFrozenColumns();
        boolean hasFrozenColumns = (frozenColumns != 0);

        //style class
        String containerClass = scrollable ? " " + DataTable.SCROLLABLE_CONTAINER_CLASS : DataTable.CONTAINER_CLASS;
        containerClass = table.getStyleClass() != null ? containerClass + " " + table.getStyleClass() : containerClass;
        
      //  System.out.println(containerClass);
        containerClass = null ;
        
        if (table.isResizableColumns()) {
            containerClass = containerClass + " " + DataTable.RESIZABLE_CONTAINER_CLASS;
        }
        if (table.isStickyHeader()) {
            containerClass = containerClass + " " + DataTable.STICKY_HEADER_CLASS;
        }
        if (ComponentUtils.isRTL(context, table)) {
            containerClass = containerClass + " " + DataTable.RTL_CLASS;
        }
        if (table.isReflow()) {
            containerClass = containerClass + " " + DataTable.REFLOW_CLASS;
        }
        if (hasFrozenColumns) {
            containerClass = containerClass + " ui-datatable-frozencolumn";
        }

        writer.startElement("div", table);
        writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("class", containerClass, "styleClass");
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }

        encodeFacet(context, table, table.getHeader(), DataTable.HEADER_CLASS);

        if (hasPaginator && !paginatorPosition.equalsIgnoreCase("bottom")) {
            encodePaginatorMarkup(context, table, "top");
        }

        if (scrollable) {
            encodeScrollableTable(context, table);
        } else {
            encodeRegularTable(context, table);
        }

        if (hasPaginator && !paginatorPosition.equalsIgnoreCase("top")) {
            encodePaginatorMarkup(context, table, "bottom");
        }

        encodeFacet(context, table, table.getFooter(), DataTable.FOOTER_CLASS);

        if (table.isSelectionEnabled()) {
            encodeStateHolder(context, table, table.getClientId(context) + "_selection", table.getSelectedRowKeysAsString());
        }

        if (table.isDraggableColumns()) {
            encodeStateHolder(context, table, table.getClientId(context) + "_columnOrder", null);
        }

        if (scrollable) {
            encodeStateHolder(context, table, table.getClientId(context) + "_scrollState", table.getScrollState());
        }

        writer.endElement("div");
    }

}
