/*
 * (C) Copyright 2014 Académie de Rennes (http://www.ac-rennes.fr/), OSIVIA (http://www.osivia.com) and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * 
 * Contributors:
 * dchevrier
 */
package fr.toutatice.ecm.workflows.integration.beans;

import static org.jboss.seam.ScopeType.CONVERSATION;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.NuxeoException;

import fr.toutatice.ecm.platform.service.portalviews.adapter.WidgetsAdapterService;
import fr.toutatice.ecm.platform.web.workflows.ToutaticeDocumentRoutingActionsBean;
import fr.toutatice.ecm.workflows.integration.constants.ExtendedSeamPrecedence;
import fr.toutatice.ecm.workflows.integration.constants.WorkflowsConstants;

/**
 * @author David Chevrier
 *
 */
@Scope(CONVERSATION)
@Name("routingActions")
@Install(precedence = ExtendedSeamPrecedence.ADD_ON)
public class IntegrationDocumentRoutingActionsBean extends ToutaticeDocumentRoutingActionsBean {

    private static final long serialVersionUID = 1074863841271697758L;


    /** Widgets adaptor service. */
    @In(create = true)
    WidgetsAdapterService wgtSrv;

    /**
     * Initialize portal views.
     */
    @Create
    public void initialize() throws Exception {
        wgtSrv.addPortalViewsIds(WorkflowsConstants.PV_WF_CHOICE, WorkflowsConstants.PV_CURRENT_TASK);
    }

    /**
     * Override to be able to return a viewId,
     * according to PortalView context.
     */
    @Override
    public String startRouteRelatedToCurrentDocument() throws NuxeoException {

        String viewId = super.startRouteRelatedToCurrentDocument();

        if (wgtSrv.isInPortalViewContext()) {
            viewId = WorkflowsConstants.PV_CURRENT_TASK;
        }

        return viewId;
    }

    /**
     * Override for portal view redirection.
     */
    @Override
    public String cancelRoute() throws NuxeoException {

        String viewId = super.cancelRoute();

        if (wgtSrv.isInPortalViewContext()) {
            viewId = WorkflowsConstants.PV_WORKFLOW_ACTION_DONE;
        }

        return viewId;

    }

}
