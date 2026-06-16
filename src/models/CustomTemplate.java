package models;

public class CustomTemplate extends PromptTemplate {
    private int id;
    private String templateName;

    public CustomTemplate(int id, String templateName, String subject, String cameraModel, 
                          String focalLength, String aperture, String lightingType, String colorGrading) {
        super(subject, cameraModel, focalLength, aperture, lightingType, colorGrading);
        this.id = id;
        this.templateName = templateName;
    }

    @Override
    public String buildPromptString() {
        return "[Custom: " + templateName + "] Subject: " + subject + 
               ", Settings: " + cameraModel + ", " + focalLength + ", " + aperture;
    }
    
    public int getId() { return id; }
    public String getTemplateName() { return templateName; }
}