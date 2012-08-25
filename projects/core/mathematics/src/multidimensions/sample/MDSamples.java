/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.sample;

/**
 *
 * @author stellarspot
 */
public enum MDSamples {

    SHAPE("MD Shapes", MDShapeSample.values());
    String category;
    IMDSample[] samples;

    private MDSamples(String category, IMDSample[] samples) {
        this.category = category;
        this.samples = samples;
    }

    public String getCategory() {
        return category;
    }

    public IMDSample[] getSamples() {
        return samples;
    }
}
