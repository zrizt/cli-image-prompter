package models;

public abstract class PromptTemplate { // Parent Class untuk Template Prompt
    protected String subject;
    protected String cameraModel;
    protected String focalLength;
    protected String aperture;
    protected String lightingType;
    protected String colorGrading;

    // Constructor
    public PromptTemplate(String subject, String cameraModel, String focalLength, 
                          String aperture, String lightingType, String colorGrading) {
        this.subject = subject;
        this.cameraModel = cameraModel;
        this.focalLength = focalLength;
        this.aperture = aperture;
        this.lightingType = lightingType;
        this.colorGrading = colorGrading;
    }

    // Abstract Method: Memaksa Child Class untuk memiliki cara sendiri dalam merakit teks
    public abstract String buildPromptString();

    // Getters
    public String getSubject() { return subject; }
    public String getCameraModel() { return cameraModel; }
    public String getFocalLength() { return focalLength; }
    public String getAperture() { return aperture; }
    public String getLightingType() { return lightingType; }
    public String getColorGrading() { return colorGrading; }
}