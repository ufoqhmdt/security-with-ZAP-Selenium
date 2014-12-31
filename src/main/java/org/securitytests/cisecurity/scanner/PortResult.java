package org.securitytests.cisecurity.scanner;

import org.securitytests.cisecurity.StringUtils;

public class PortResult {
    private PortState state;
    int port;

    public PortResult(int port) {
        this.port = port;
    }

    public PortState getState() {
        return state;
    }

    public void setState(PortState state) {
        this.state = state;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public enum PortState {
        OPEN,
        CLOSED,
        TIMEDOUT;

        public static PortState fromString(String value) {
            if(StringUtils.lowcaseLetter(value).equals("open")) {
            	return PortState.OPEN;
            }
            else if(StringUtils.lowcaseLetter(value).equals("closed"))
            {
            	return PortState.CLOSED;
            }
            else if(StringUtils.lowcaseLetter(value).equals("timedout"))
            {
            	return PortState.TIMEDOUT;
            }
            throw new RuntimeException("Unknown port state: "+value);
        }
    }
}
