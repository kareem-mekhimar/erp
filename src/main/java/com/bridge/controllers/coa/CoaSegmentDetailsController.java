/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.coa;

import com.bridge.entities.coa.CoaSegment;
import com.bridge.entities.coa.CoaSegmentLine;
import com.bridge.services.coa.CoaStructureService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */
@Named
@ViewScoped
public class CoaSegmentDetailsController implements Serializable {

    @EJB
    private CoaStructureService structureService;

    private Integer currentSegmentId;
    private Integer type;
    private CoaSegment currentSegment;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void updateType(CoaSegment segment) {
        if (segment.getNaturalAccountSegment()) {
            type = 2;
        } else if (segment.getBalancingSegment()
                || segment.getCostCenterSegment()
                || segment.getManagementSegment()
                || segment.getNaturalAccountSegment()
                || segment.getSecondaryTrackingSegment()) {
            type = 1;
        } else {
            type = 0;
        }
    }

    public void manageParent(CoaSegmentLine segment) {
        segment.setAllowPostFlag(false);
        segment.setReconcile(false);
    }

    public void manageEnable(CoaSegmentLine segment) {
        long i = currentSegment.getCoaSegmentLineList().stream().filter(a -> segment.equals(a.getParentAccountId())).count();
        if (i > 0) {
            segment.setEnabledFlag(true);
        }
    }

    public void loadSegment() {
        
        if (currentSegmentId != null) {
            currentSegment = structureService.findSegmentWithLines(currentSegmentId);

            if (currentSegment == null) {
                JSFUtils.redirectTo404("Coa Segment");
            }

        }

        updateType(currentSegment);

    }

    public void addLine() {
        List<CoaSegmentLine> lines = currentSegment.getCoaSegmentLineList();

        if (lines == null) {
            lines = new ArrayList<>();

            currentSegment.setCoaSegmentLineList(lines);

        }

        CoaSegmentLine line = new CoaSegmentLine();

        line.setSegment(currentSegment);
        line.setEnabledFlag(true);
        lines.add(line);
    }

    public void validateSegmentCode(FacesContext context, UIComponent component, Object value) {
        String val = (String) value;

        int size = currentSegment.getMaximumSize();

        if (val.length() > size) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can't Exceed Max Size (" + size + ")",
                     "Can't Exceed Max Size (" + size + ")"));
        }

        if (currentSegment.getUppercaseOnlyFlagId()) {
            if (!val.equals(val.toUpperCase())) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Must Be UpperCase",
                         "Must Be UpperCase"));
            }
        } else if (currentSegment.getNumbersOnlyFlagId()) {
            int valueAsNo = 0;

            try {
                valueAsNo = Integer.parseInt(val);

            } catch (NumberFormatException e) {

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "is not a Number",
                         "is not a Number"));
            }

            Integer minValue = currentSegment.getMinimumValue();

            if (minValue != null && valueAsNo < minValue) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Can't Be Below (" + currentSegment.getMinimumValue() + ")",
                         "Can't Be Below (" + currentSegment.getMinimumValue() + ")"));
            }

            Integer maxValue = currentSegment.getMaximumValue();

            if (maxValue != null && valueAsNo > maxValue) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Can't Exceed (" + currentSegment.getMaximumValue() + ")",
                         "Can't Exceed (" + currentSegment.getMaximumValue() + ")"));
            }
        }

        CoaSegmentLine coaSegmentLine = JSFUtils.evaluateValueExpression("#{line}", CoaSegmentLine.class);

        if (currentSegment.getCoaSegmentLineList().stream().filter(line -> line.getSegmentDetailCode()
                != null && !line.getSegmentDetailCode().isEmpty() && line != coaSegmentLine)
                .anyMatch(line -> line.getSegmentDetailCode().equals(val))) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "This value already exists",
                     "This value already exists"));
        }

    }

    public void validateSegmentDetailName(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;
        CoaSegmentLine coaSegmentLine = JSFUtils.evaluateValueExpression("#{line}", CoaSegmentLine.class);
        if (coaSegmentLine.getSegmentDetailId() != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }

        if (structureService.isSegmentDetailNameExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void onZeroPaddingOnChange(CoaSegmentLine line) {
        if (currentSegment.getRightJustifyZeroFillNum() && line.getSegmentDetailCode().length() < currentSegment.getMaximumSize()) {
            char[] v = new char[currentSegment.getMaximumSize()];

            Arrays.fill(v, '0');

            String filled = new String(v);

            String padded = filled.substring(line.getSegmentDetailCode().length()) + line.getSegmentDetailCode();

            line.setSegmentDetailCode(padded);
        }

    }

    public String save() {
        List<CoaSegmentLine> lines = currentSegment.getCoaSegmentLineList();

        if (lines != null && !lines.isEmpty()) {
            currentSegment.setHasChildren(true);

//          if(currentSegment.getRightJustifyZeroFillNum())
//          {
//               char [] v = new char[currentSegment.getMaximumSize()] ;
//               
//               Arrays.fill(v, '0');
//               
//               String filled = new String(v);
//               
//               lines.stream().filter(l -> l.getSegmentDetailCode().length() < currentSegment.getMaximumSize())
//                            .forEach(l -> {
//
//                                String padded = filled.substring(l.getSegmentDetailCode().length()) + l.getSegmentDetailCode() ;
// 
//                                l.setSegmentDetailCode(padded);
//                            });
//          }
        } else {
            currentSegment.setHasChildren(false);
        }

        structureService.updateSegment(currentSegment);

        return "coaStructureDetails?id=" + currentSegment.getCoaStructure().getCoaId() + "&faces-redirect=true";
    }

    public Integer getCurrentSegmentId() {
        return currentSegmentId;
    }

    public void setCurrentSegmentId(Integer currentSegmentId) {
        this.currentSegmentId = currentSegmentId;
    }

    public CoaSegment getCurrentSegment() {

        if (currentSegment == null) {
            currentSegment = new CoaSegment();
        }

        return currentSegment;
    }

    public void setCurrentSegment(CoaSegment currentSegment) {
        this.currentSegment = currentSegment;
    }

    public List<CoaSegmentLine> completeDependsOnSegments(String text) {
        return structureService.findSegmentLinesBySegmentSeqNo(currentSegment.getDependentOnSegmentSequence(), currentSegment.getCoaStructure(), text);
    }

    public List<CoaSegmentLine> completeParentAccounts(String text) {

        CoaSegmentLine currentLine = JSFUtils.evaluateValueExpression("#{line}", CoaSegmentLine.class);

        List list = new ArrayList();
        list = currentSegment.getCoaSegmentLineList().stream()
                .filter(l -> l.getSegmentDetailCode().toUpperCase().startsWith(text.toUpperCase())
                && l.getSegmentDetailId() != null
                && l != currentLine
                && l.getSegmentDetailCode().compareTo(currentLine.getSegmentDetailCode()) < 0
                && l.getParentAccountFlag() == true).collect(Collectors.toList());

        return list;
    }

}
