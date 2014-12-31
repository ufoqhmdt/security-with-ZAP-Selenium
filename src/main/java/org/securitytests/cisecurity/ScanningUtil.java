package org.securitytests.cisecurity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;


import org.zaproxy.clientapi.core.Alert;

public class ScanningUtil {
	//List<Alert> alerts = new ArrayList<Alert>();
    public static void checkVulnerabilities( List<Alert> alerts,String risk) {
        List<Alert> filteredAlerts = null;
        Alert.Risk riskLevel = Alert.Risk.High;

        if ("HIGH".equalsIgnoreCase(risk)) {
            riskLevel = Alert.Risk.High;
        } else if ("MEDIUM".equalsIgnoreCase(risk)) {
            riskLevel = Alert.Risk.Medium;
        } else if ("LOW".equalsIgnoreCase(risk)) {
            riskLevel = Alert.Risk.Low;
        }
        filteredAlerts = getAllAlertsByRiskRating(alerts, riskLevel);
        String details = getAlertDetails(filteredAlerts);

        assertThat(filteredAlerts.size() + " " + risk + " vulnerabilities found.\nDetails:\n" + details, filteredAlerts.size(),
                equalTo(0));
    }
    public static List<Alert> getAllAlertsByRiskRating(List<Alert> alerts, Alert.Risk rating) {
        List<Alert> results = new ArrayList<Alert>();
        for (Alert alert : alerts) {
            if (alert.getRisk().ordinal() >= rating.ordinal()) results.add(alert);
        }
        return results;
    }

    public static String getAlertDetails(List<Alert> alerts) {
        String detail = "";
        if (alerts.size() != 0) {
            for (Alert alert : alerts) {
                detail = detail + alert.getAlert() + "\n"
                        + "URL: " + alert.getUrl() + "\n"
                        + "Parameter: " + alert.getParam() + "\n"
                        + "CWE: " + alert.getCweId() + "\n"
                        +"Description" + alert.getDescription()+ "\n"
                        +"Solution" +alert.getSolution()+ "\n";
           
                
            }
        }
        return detail;
    }

    public boolean alertsMatchByValue(Alert first, Alert second) {
        //The built in Alert.matches(Alert) method includes risk, reliability and alert, but not cweid.
        if (first.getCweId() != second.getCweId()) return false;
        if (!first.getParam().equals(second.getParam())) return false;
        if (!first.getUrl().equals(second.getUrl())) return false;
        if (!first.matches(second)) return false;
        return true;
    }


    public boolean containsAlertByValue(List<Alert> alerts, Alert alert) {
        boolean found = false;
        for (Alert existing : alerts) {
            if (alertsMatchByValue(alert, existing)) {
                found = true;
                break;
            }
        }
        return found;
    }

}
