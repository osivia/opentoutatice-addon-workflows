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

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.runtime.api.Framework;

import fr.toutatice.ecm.platform.portalviews.bean.ToutaticeSkinBean;
import fr.toutatice.ecm.platform.service.portalviews.adapter.WidgetsAdapterService;
import fr.toutatice.ecm.workflows.integration.constants.ExtendedSeamPrecedence;
import fr.toutatice.ecm.workflows.integration.constants.WorkflowsConstants;


/**
 * @author David Chevrier.
 *
 */
@Name("skinBean")
@Scope(ScopeType.CONVERSATION)
@Install(precedence = ExtendedSeamPrecedence.ADD_ON)
public class IntegrationSkinBean extends ToutaticeSkinBean {

    private static final long serialVersionUID = 801586722630681649L;

    /**
     * To not apply plain skin on portal views of
     * nuxeo workflows (because they have galaxy theme
     * - for the moment).
     */
    @Override
    public String getSkin() {
        String conversationSkean = super.getSkin();

        WidgetsAdapterService widgetsAdapterService = Framework.getLocalService(WidgetsAdapterService.class);
        String currentPortalView = widgetsAdapterService.getCurrentPortalView();

        if (currentPortalView.contains(WorkflowsConstants.PV_WF_CHOICE) || currentPortalView.contains(WorkflowsConstants.PV_CURRENT_TASK)) {
            conversationSkean = DEFAULT_SKIN;
        }
        return conversationSkean;
    }

}
