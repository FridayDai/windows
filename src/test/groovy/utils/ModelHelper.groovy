package utils

import model.ClientModel
import model.OutcomeTaskModel
import model.OutcomeToolModel
import model.StaffModel
import model.TreatmentModel

class ModelHelper {
    private static long IDENTIFY = System.currentTimeMillis()

    private static StaffModel adminAccount
    private static ClientModel client
    private static StaffModel agent

    private static TreatmentModel treatmentFirst
    private static TreatmentModel treatmentSecond

    private static OutcomeToolModel NDITool
    private static OutcomeToolModel NRSNECKTool
    private static OutcomeToolModel NRSBACKTool
    private static OutcomeToolModel DASHTool
    private static OutcomeToolModel QUICKDASHTool
    private static OutcomeToolModel ODITool
    private static OutcomeToolModel KOOSTool
    private static OutcomeToolModel HOOSTool
    private static OutcomeToolModel FNSTool
    private static OutcomeToolModel HARRISHIPSCORETool

    private static OutcomeTaskModel NDITask
    private static OutcomeTaskModel NRSNECKTask
    private static OutcomeTaskModel NRSBACKTask
    private static OutcomeTaskModel DASHTask
    private static OutcomeTaskModel QUICKDASHTask
    private static OutcomeTaskModel ODITask
    private static OutcomeTaskModel KOOSTask
    private static OutcomeTaskModel HOOSTask
    private static OutcomeTaskModel FNSTask
    private static OutcomeTaskModel HARRISHIPSCORETask

    static long getIdentity() {
        IDENTIFY
    }

    static StaffModel getAdminAccount() {
        if (!adminAccount) {
            adminAccount = new StaffModel(
                email: "joseph@ratchethealth.com",
                password: "password"
            )
        }

        adminAccount
    }

    static ClientModel getClient() {
        if (!client) {
            client =  new ClientModel(
                clientName: "AST${IDENTIFY}CN",
                subDomain: "ast${IDENTIFY}sd",
                patientPortalName: "AST${IDENTIFY}PPN"
            )
        }

        client
    }

    static StaffModel getAgent() {
        if (!agent) {
            agent = new StaffModel(
                email: "ratchet.testing+ast${IDENTIFY}@gmail.com",
                firstName: "FN+ast${IDENTIFY}",
                lastName: "AST"
            )
        }

        agent
    }

    static TreatmentModel getTreatment1() {
        if (!treatmentFirst) {
            treatmentFirst = new TreatmentModel(
                treatmentTitle: "treatment${IDENTIFY}FIRST"
            )
        }

        treatmentFirst
    }

    static TreatmentModel getTreatment2() {
        if (!treatmentSecond) {
            treatmentSecond = new TreatmentModel(
                treatmentTitle: "treatment${IDENTIFY}SECOND"
            )
        }

        treatmentSecond
    }

    static OutcomeToolModel getNDITool() {
        if (!NDITool) {
            NDITool = new OutcomeToolModel(
                name: "NDI",
                description: "Neck Disability Index"
            )
        }

        NDITool
    }

    static OutcomeTaskModel getNDITask() {
        if (!NDITask) {
            NDITask = new OutcomeTaskModel(
                tool: getNDITool()
            )
        }

        NDITask
    }

    static OutcomeToolModel getNRSNECKTool() {
        if (!NRSNECKTool) {
            NRSNECKTool = new OutcomeToolModel(
                name: "NRS-NECK",
                description: "Numeric Rating Scale (NRS) for Neck Pain Intensity"
            )
        }

        NRSNECKTool
    }

    static OutcomeTaskModel getNRSNECKTask() {
        if (!NRSNECKTask) {
            NRSNECKTask = new OutcomeTaskModel(
                tool: getNRSNECKTool()
            )
        }

        NRSNECKTask
    }

    static OutcomeToolModel getNRSBACKTool() {
        if (!NRSBACKTool) {
            NRSBACKTool = new OutcomeToolModel(
                name: "NRS-BACK",
                description: "Numeric Rating Scale (NRS) for Back Pain Intensity"
            )
        }

        NRSBACKTool
    }

    static OutcomeTaskModel getNRSBACKTask() {
        if (!NRSBACKTask) {
            NRSBACKTask = new OutcomeTaskModel(
                tool: getNRSBACKTool()
            )
        }

        NRSBACKTask
    }

    static OutcomeToolModel getDASHTool() {
        if (!DASHTool) {
            DASHTool = new OutcomeToolModel(
                name: "DASH",
                description: "Disabilities of the Arm, Shoulder and Hand"
            )
        }

        DASHTool
    }

    static OutcomeTaskModel getDASHTask() {
        if (!DASHTask) {
            DASHTask = new OutcomeTaskModel(
                tool: getDASHTool()
            )
        }

        DASHTask
    }

    static OutcomeToolModel getQUICKDASHTool() {
        if (!QUICKDASHTool) {
            QUICKDASHTool = new OutcomeToolModel(
                name: "QuickDASH",
                description: "Quick measurement tool for disabilities of the arm, shoulder and hand"
            )
        }

        QUICKDASHTool
    }

    static OutcomeTaskModel getQUICKDASHTask() {
        if (!QUICKDASHTask) {
            QUICKDASHTask = new OutcomeTaskModel(
                tool: getQUICKDASHTool()
            )
        }

        QUICKDASHTask
    }

    static OutcomeToolModel getODITool() {
        if (!ODITool) {
            ODITool = new OutcomeToolModel(
                name: "ODI",
                description: "Oswestry Disability Index"
            )
        }

        ODITool
    }

    static OutcomeTaskModel getODITask() {
        if (!ODITask) {
            ODITask = new OutcomeTaskModel(
                tool: getODITool()
            )
        }

        ODITask
    }

    static OutcomeToolModel getKOOSTool() {
        if (!KOOSTool) {
            KOOSTool = new OutcomeToolModel(
                name: "KOOS",
                description: "Knee injury and Osteoarthritis Outcome Score"
            )
        }

        KOOSTool
    }

    static OutcomeTaskModel getKOOSTask() {
        if (!KOOSTask) {
            KOOSTask = new OutcomeTaskModel(
                tool: getKOOSTool()
            )
        }

        KOOSTask
    }

    static OutcomeToolModel getHOOSTool() {
        if (!HOOSTool) {
            HOOSTool = new OutcomeToolModel(
                name: "HOOS",
                description: "Hip dysfunction and Osteoarthritis Outcome Score"
            )
        }

        HOOSTool
    }

    static OutcomeTaskModel getHOOSTask() {
        if (!HOOSTask) {
            HOOSTask = new OutcomeTaskModel(
                tool: getHOOSTool()
            )
        }

        HOOSTask
    }

    static OutcomeToolModel getFNSTool() {
        if (!FNSTool) {
            FNSTool = new OutcomeToolModel(
                name: "Fairley Nasal Symptom",
                description: "Fairley Nasal Symptom"
            )
        }

        FNSTool
    }

    static OutcomeTaskModel getFNSTask() {
        if (!FNSTask) {
            FNSTask = new OutcomeTaskModel(
                tool: getFNSTool()
            )
        }

        FNSTask
    }

    static OutcomeToolModel getHARRISHIPSCORETool() {
        if (!HARRISHIPSCORETool) {
            HARRISHIPSCORETool = new OutcomeToolModel(
                name: "Harris Hip Score",
                description: "Harris Hip Score"
            )
        }

        HARRISHIPSCORETool
    }

    static OutcomeTaskModel getHARRISHIPSCORETask() {
        if (!HARRISHIPSCORETask) {
            HARRISHIPSCORETask = new OutcomeTaskModel(
                tool: getHARRISHIPSCORETool()
            )
        }

        HARRISHIPSCORETask
    }
}
