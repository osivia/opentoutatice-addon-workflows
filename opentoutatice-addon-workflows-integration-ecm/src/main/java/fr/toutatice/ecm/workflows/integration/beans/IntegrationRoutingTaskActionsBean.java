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
 * mberhaut1
 * dchevrier
 * lbillon
 */
package fr.toutatice.ecm.workflows.integration.beans;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.platform.routing.api.DocumentRoute;
import org.nuxeo.ecm.platform.task.Task;
import org.nuxeo.ecm.platform.ui.web.util.SeamComponentCallHelper;
import org.nuxeo.ecm.webapp.action.MainTabsActions;
import org.nuxeo.runtime.api.Framework;

import fr.toutatice.ecm.platform.service.portalviews.adapter.WidgetsAdapterService;
import fr.toutatice.ecm.platform.service.workflows.ToutaticeWorkflowServiceImpl;
import fr.toutatice.ecm.platform.web.workflows.ToutaticeRoutingTaskActionsBean;
import fr.toutatice.ecm.workflows.integration.constants.ExtendedSeamPrecedence;
import fr.toutatice.ecm.workflows.integration.constants.WorkflowsConstants;


/**
 * @author David Chevrier.
 *
 */
@Scope(CONVERSATION)
@Name("routingTaskActions")
@Install(precedence = ExtendedSeamPrecedence.ADD_ON)
public class IntegrationRoutingTaskActionsBean extends ToutaticeRoutingTaskActionsBean {

    private static final long serialVersionUID = -4786832650539519266L;

    @Override
    public String endTask(Task task) throws ClientException {

        String view = MainTabsActions.DEFAULT_VIEW;

        String currentWfName = getCurrentWorkflowName(task);

        if (WorkflowsConstants.VALIDATION_WORKFLOWS.contains(currentWfName)) {

            String clickedButton = getClickedButton();


            WidgetsAdapterService aSrv = (WidgetsAdapterService) Framework.getService(WidgetsAdapterService.class);

            if (aSrv.isInPortalViewContext()) {


                if (WorkflowsConstants.CONTINUE_VALIDATION_ACTIONS.contains(clickedButton)) {
                    view = WorkflowsConstants.PV_CURRENT_TASK;
                } else if (WorkflowsConstants.ENDED_VALIDATION_ACTIONS.contains(clickedButton)) {
                    view = WorkflowsConstants.PV_WORKFLOW_ACTION_DONE;
                }
            }

            super.endTask(task);

        } else {
            // no-op. only forward processing to mother class
            view = super.endTask(task);
        }

        return view;
    }

    /**
     * @param task
     * @return workflow name of given task.
     */
    public String getCurrentWorkflowName(Task task) {
        DocumentModel documentRouteModel = getCurrentWfModel(task);
        return documentRouteModel.getTitle();
    }

    /**
     * @param task
     * @return workflow document model of task.
     */
    public DocumentModel getCurrentWfModel(Task task) {
        String routeInstanceDocId = task.getVariable("routeInstanceDocId");
        return documentManager.getDocument(new IdRef(routeInstanceDocId));
    }

}
