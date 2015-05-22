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
package fr.toutatice.ecm.workflows.integration.constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author David Chevrier.
 *
 */
public interface WorkflowsConstants {
    
    /** TasDoc type. */
    String TASKDOC_TYPE = "TaskDoc";

    /** Validation workflows. */
    List<String> VALIDATION_WORKFLOWS = new ArrayList<String>(2) {

        private static final long serialVersionUID = -7603172106297666355L;

        {
            add("wf.parallelDocumentReview.ParallelDocumentReview");
            add("wf.serialDocumentReview.SerialDocumentReview");
        }

    };
    
    /** Choose particpants vtask of alidation workflows. */
    List<String> CHOOSE_PARTICIPANTS_TASKS = new ArrayList<String>(2) {

        private static final long serialVersionUID = -4997608691913605739L;

        {
            add("wf.parallelDocumentReview.chooseParticipants.title");
            add("wf.serialDocumentReview.chooseParticipants");
        }

    };
    
    /** Validation actions redirecting to tasks view. */
    List<String> CONTINUE_VALIDATION_ACTIONS = new LinkedList<String>() {

        private static final long serialVersionUID = -4997608691913605739L;

        {
            add("start_review");
        }

    };
    
    /** Validation actions redirecting to done view. */
    List<String> ENDED_VALIDATION_ACTIONS = new LinkedList<String>() {

        private static final long serialVersionUID = -4997608691913605739L;

        {
            add("cancel");
            add("approve");
            add("reject");
            add("NA");
            add("validate");
        }

    };

    /** Redirect view on canel workflow. */
    String PV_WORKFLOW_ACTION_DONE = "action_done";
    /** View id of workflow choice facelet. */
    public static final String PV_WF_CHOICE = "choose_wf";
    /** Current task portal view id. */
    String PV_CURRENT_TASK = "current_task";

}
