package tn.esprit.pibakcend.entities;

import java.util.Arrays;

public class JFreeChartResponse {
    private byte[] chartImageBytes;

    public JFreeChartResponse() {
    }

    public JFreeChartResponse(byte[] chartImageBytes) {
        this.chartImageBytes = chartImageBytes;
    }

    public byte[] getChartImageBytes() {
        return chartImageBytes;
    }

    public void setChartImageBytes(byte[] chartImageBytes) {
        this.chartImageBytes = chartImageBytes;
    }

    @Override
    public String toString() {
        return "JFreeChartResponse{" +
                "chartImageBytes=" + Arrays.toString(chartImageBytes) +
                '}';
    }
}
